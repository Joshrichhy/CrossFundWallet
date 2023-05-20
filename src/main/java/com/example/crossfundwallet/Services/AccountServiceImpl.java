package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Models.CurrencyType;
import com.example.crossfundwallet.Data.Models.TransactionType;
import com.example.crossfundwallet.Data.Repositories.AccountRepository;
import com.example.crossfundwallet.Exceptions.AccountDoesNotExist;
import com.example.crossfundwallet.Exceptions.CurrencyNotFound;
import com.example.crossfundwallet.Exceptions.InsufficientFundException;
import com.example.crossfundwallet.Exceptions.InvalidCredentialException;
import com.example.crossfundwallet.dtos.DepositRequest;
import com.example.crossfundwallet.dtos.RegisterUserRequest;
import com.example.crossfundwallet.dtos.TransactionReceipt;
import com.example.crossfundwallet.dtos.WithdrawalRequest;
import com.example.crossfundwallet.utils.CurrencyApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(RegisterUserRequest userRequest) {
        Account account = new Account();
        account.setAccountFirstName(userRequest.getFirstName());
        account.setAccountLastName(userRequest.getLastName());
       account.setAccountNumber(generateAccountNumber());
       account.setAccountPin(userRequest.getAccountPin());
       account.setAccountCurrencyType(generateAccountCurrentType(userRequest.getAccountCurrencyType()));
       account.setAccountStatus("ACTIVE");
        return accountRepository.save(account);

    }

    @Override
    public TransactionReceipt deposit(DepositRequest depositRequest) throws AccountDoesNotExist, CurrencyNotFound {
        if(accountRepository.findByAccountNumber(depositRequest.getAccountNumber())== null){
            throw new AccountDoesNotExist("Account does not exist");}
        else {
            BigDecimal depositAmount = BigDecimal.ZERO;

            Account account = accountRepository.findByAccountNumber(depositRequest.getAccountNumber());
            CurrencyApi currencyApi = new CurrencyApi(depositRequest.getCurrency());
            if(Objects.equals(CurrencyType.USD, account.getAccountCurrencyType())){
                depositAmount = currencyApi.dollarRate().multiply(depositRequest.getAmount());
            }
            else  if(Objects.equals(CurrencyType.GBP, account.getAccountCurrencyType())){
                depositAmount = currencyApi.poundsRate().multiply(depositRequest.getAmount());
            }
            else  if(CurrencyType.CAD.equals(account.getAccountCurrencyType())){
                System.out.println("Curency Rate " + currencyApi.canadianDollarRate());
                depositAmount = currencyApi.canadianDollarRate().multiply(depositRequest.getAmount());
            }
            else  if(CurrencyType.EUR.equals(account.getAccountCurrencyType())){
                depositAmount = currencyApi.eurosRate().multiply(depositRequest.getAmount());
            }
            else  if(CurrencyType.NGN.equals(account.getAccountCurrencyType())){
                depositAmount = currencyApi.nairaRate().multiply(depositRequest.getAmount());
            }
            else throw new CurrencyNotFound("Currency not found");
            account.setAccountBalance(account.getAccountBalance().add(depositAmount));
            accountRepository.save(account);


            TransactionReceipt transactionReceipt = new TransactionReceipt();
            transactionReceipt.setStatus("Successful");
            transactionReceipt.setTransactionDate(LocalDate.now());
            transactionReceipt.setTransactionType(TransactionType.DEPOSIT);
            transactionReceipt.setTransactionAmount(depositAmount);

            return transactionReceipt;
        }
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
            BigDecimal withdrawalAmount = BigDecimal.ZERO;
            CurrencyApi currencyApi = new CurrencyApi(foundAccount.getAccountCurrencyType());
            if(Objects.equals(CurrencyType.USD, withdrawalRequest.getCurrencyType())){
                withdrawalAmount = currencyApi.dollarRate().multiply(withdrawalRequest.getAmount());
            }
            else  if(Objects.equals(CurrencyType.GBP, withdrawalRequest.getCurrencyType())){
                withdrawalAmount = currencyApi.poundsRate().multiply(withdrawalRequest.getAmount());
            }
            else  if(CurrencyType.CAD.equals(withdrawalRequest.getCurrencyType())){
                withdrawalAmount = currencyApi.canadianDollarRate().multiply(withdrawalRequest.getAmount());
            }
            else  if(CurrencyType.EUR.equals(withdrawalRequest.getCurrencyType())){
                withdrawalAmount = currencyApi.eurosRate().multiply(withdrawalRequest.getAmount());
            }
            else  if(CurrencyType.NGN.equals(withdrawalRequest.getCurrencyType())){
                withdrawalAmount = currencyApi.nairaRate().multiply(withdrawalRequest.getAmount());
                System.out.println(withdrawalAmount);
            }
            else throw new CurrencyNotFound("Currency not found");
            foundAccount.setAccountBalance(foundAccount.getAccountBalance().subtract(withdrawalRequest.getAmount()));
            accountRepository.save(foundAccount);

            TransactionReceipt transactionReceipt = new TransactionReceipt();
            transactionReceipt.setStatus("Successful");
            transactionReceipt.setTransactionDate(LocalDate.now());
            transactionReceipt.setTransactionType(TransactionType.WITHDRAWAL);
            transactionReceipt.setTransactionAmount(withdrawalAmount);

            return transactionReceipt;
        }

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
            accountNumber = accountNumber+ ""+number+"";
        }

        return accountNumber;
    }


}
