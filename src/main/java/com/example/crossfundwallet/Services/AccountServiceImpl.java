package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Models.CurrencyType;
import com.example.crossfundwallet.Data.Models.TransactionType;
import com.example.crossfundwallet.Data.Repositories.AccountRepository;
import com.example.crossfundwallet.Exceptions.AccountDoesNotExist;
import com.example.crossfundwallet.Exceptions.CurrencyNotFound;
import com.example.crossfundwallet.dtos.DepositRequest;
import com.example.crossfundwallet.dtos.RegisterUserRequest;
import com.example.crossfundwallet.dtos.TransactionReceipt;
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
            if(Objects.equals(CurrencyType.USD, depositRequest.getCurrency())){
                depositAmount = CurrencyApi.dollarRate().multiply(depositRequest.getAmount());
            }
            else  if(Objects.equals(CurrencyType.GBP, depositRequest.getCurrency())){
                depositAmount = CurrencyApi.poundsRate().multiply(depositRequest.getAmount());
            }
            else  if(CurrencyType.CAD.equals(depositRequest.getCurrency())){
                depositAmount = CurrencyApi.canadianDollarRate().multiply(depositRequest.getAmount());
            }
            else  if(CurrencyType.EUR.equals(depositRequest.getCurrency())){
                depositAmount = CurrencyApi.eurosRate().multiply(depositRequest.getAmount());
            }
            else  if(CurrencyType.NGN.equals(depositRequest.getCurrency())){
                depositAmount = CurrencyApi.nairaRate().multiply(depositRequest.getAmount());
            }
            else throw new CurrencyNotFound("Currency not found");

            account.setAccountBalance(depositAmount);

            TransactionReceipt transactionReceipt = new TransactionReceipt();
            transactionReceipt.setStatus("Successful");
            transactionReceipt.setTransactionDate(LocalDate.now());
            transactionReceipt.setTransactionType(TransactionType.DEPOSIT);
            transactionReceipt.setTransactionAmount(depositRequest.getAmount());

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
