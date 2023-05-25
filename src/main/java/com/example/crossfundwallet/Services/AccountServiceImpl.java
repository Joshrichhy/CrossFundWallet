package com.example.crossfundwallet.Services;


import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Models.CurrencyType;
import com.example.crossfundwallet.Data.Repositories.AccountRepository;
import com.example.crossfundwallet.Exceptions.AccountDoesNotExist;
import com.example.crossfundwallet.Exceptions.CurrencyNotFound;
import com.example.crossfundwallet.Exceptions.InsufficientFundException;
import com.example.crossfundwallet.Exceptions.InvalidCredentialException;
import com.example.crossfundwallet.dtos.request.DepositRequest;
import com.example.crossfundwallet.dtos.request.RegisterUserRequest;
import com.example.crossfundwallet.dtos.response.TransactionReceipt;
import com.example.crossfundwallet.dtos.request.WithdrawalRequest;
import com.example.crossfundwallet.utils.CurrencyApi;
import com.example.crossfundwallet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(RegisterUserRequest userRequest) {
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setAccountCurrencyType(generateAccountCurrentType(userRequest.getAccountCurrencyType()));
       Mapper.mapAccount(userRequest, account);
        return accountRepository.save(account);

    }

    @Override
    public TransactionReceipt deposit(DepositRequest depositRequest) throws AccountDoesNotExist, CurrencyNotFound {
        if(accountRepository.findByAccountNumber(depositRequest.getAccountNumber())== null){
            throw new AccountDoesNotExist("Account does not exist");}
        else {
            BigDecimal depositAmount;

            Account account = accountRepository.findByAccountNumber(depositRequest.getAccountNumber());
            depositAmount = convertDepositAmountToCurrencyType(depositRequest, account);
            account.setAccountBalance(account.getAccountBalance().add(depositAmount));
            accountRepository.save(account);

            TransactionReceipt transactionReceipt = getTransactionReceipt(depositRequest.getAccountNumber(), depositAmount);
            transactionReceipt.setTransactionType(depositRequest.getTransactionType());

            return transactionReceipt;
        }
    }

    private static TransactionReceipt getTransactionReceipt(String accountNumber, BigDecimal amount) {
        TransactionReceipt transactionReceipt = transactionReceiptPrimaryDetail(accountNumber);
        transactionReceipt.setTransactionAmount(amount);
        return transactionReceipt;
    }

    private static TransactionReceipt transactionReceiptPrimaryDetail(String accountNumber) {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setStatus("Successful");
        transactionReceipt.setTransactionDate(LocalDate.now());
        transactionReceipt.setAccountNumber(accountNumber);
        return transactionReceipt;
    }

    private static BigDecimal convertDepositAmountToCurrencyType(DepositRequest depositRequest, Account account) throws CurrencyNotFound {
        BigDecimal depositAmount;
        CurrencyApi currencyApi = new CurrencyApi(depositRequest.getCurrency());
        depositAmount = currencyConversion(depositRequest.getAmount(), account, currencyApi);
        return depositAmount;
    }

    private static BigDecimal currencyConversion(BigDecimal amount, Account account, CurrencyApi currencyApi) throws CurrencyNotFound {
        BigDecimal convertedAmount;
        if(Objects.equals(CurrencyType.USD, account.getAccountCurrencyType())){
            convertedAmount = currencyApi.dollarRate().multiply(amount);
        }
        else  if(Objects.equals(CurrencyType.GBP, account.getAccountCurrencyType())){
            convertedAmount = currencyApi.poundsRate().multiply(amount);
        }
        else  if(CurrencyType.CAD.equals(account.getAccountCurrencyType())){
            convertedAmount = currencyApi.canadianDollarRate().multiply(amount);
        }
        else  if(CurrencyType.EUR.equals(account.getAccountCurrencyType())){
            convertedAmount = currencyApi.eurosRate().multiply(amount);
        }
        else  if(CurrencyType.NGN.equals(account.getAccountCurrencyType())){
            convertedAmount = currencyApi.nairaRate().multiply(amount);
        }
        else throw new CurrencyNotFound("Currency not found");
        return convertedAmount;
    }

    @Override
    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public TransactionReceipt withdraw(WithdrawalRequest withdrawalRequest) throws InvalidCredentialException, InsufficientFundException, CurrencyNotFound {
        Account foundAccount = findByAccountNumber(withdrawalRequest.getAccountNumber());
        if (!foundAccount.getAccountPin().equals(withdrawalRequest.getAccountPin()))
            throw new InvalidCredentialException("Invalid Credentials");
        else if (foundAccount.getAccountBalance().compareTo(withdrawalRequest.getAmount()) < 0)
            throw new InsufficientFundException("Your account is too low, kindly deposit before withdrawing");
        else {
            BigDecimal withdrawalAmount = convertWithdrawalAmountToCurrencyType(withdrawalRequest, foundAccount);
            foundAccount.setAccountBalance(foundAccount.getAccountBalance().subtract(withdrawalRequest.getAmount()));
            accountRepository.save(foundAccount);

            TransactionReceipt transactionReceipt = new TransactionReceipt();
            transactionReceipt.setStatus("Successful");
            transactionReceipt.setTransactionDate(LocalDate.now());
            transactionReceipt.setAccountNumber(withdrawalRequest.getAccountNumber());
            transactionReceipt.setTransactionType(withdrawalRequest.getTransactionType());
            transactionReceipt.setTransactionAmount(withdrawalAmount);

            return transactionReceipt;
        }

    }

    private static BigDecimal convertWithdrawalAmountToCurrencyType(WithdrawalRequest withdrawalRequest, Account foundAccount) throws CurrencyNotFound {

        CurrencyApi currencyApi = new CurrencyApi(foundAccount.getAccountCurrencyType());
        return currencyConversion(withdrawalRequest.getAmount(), foundAccount, currencyApi);
    }


    private CurrencyType generateAccountCurrentType(String currencyType) {
        if(currencyType.equalsIgnoreCase("Naira")){
            return CurrencyType.NGN;}
        else if (currencyType.equalsIgnoreCase("Pounds")) {
            return CurrencyType.GBP;}
        else if(currencyType.equalsIgnoreCase("Canadian Dollars")){
            return CurrencyType.CAD;}
        else if(currencyType.equalsIgnoreCase("Dollars")){
            return CurrencyType.USD;}
        else if(currencyType.equalsIgnoreCase("Euros")){
            return CurrencyType.EUR;}
        return null;
    }

    private String generateAccountNumber() {
        String accountNumber = "0";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 9; i++) {
            int number =  random.nextInt(0, 9);
            accountNumber = accountNumber + ""+number+"";
        }

        return accountNumber;
    }

}
