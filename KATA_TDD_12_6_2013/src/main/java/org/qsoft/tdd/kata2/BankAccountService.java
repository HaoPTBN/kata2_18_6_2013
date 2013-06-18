package org.qsoft.tdd.kata2;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 6/12/13
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountService {

    private BankAccountDao bankAccountDao;

    public BankAccountDao getBankAccountDao() {
        return bankAccountDao;
    }

    public void setBankAccountDao(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

//    ------------------------ Service

    public BankAccount openBankAccount(String accountNumber) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setBalance(0);
        bankAccount.setOpenTimestamp(new Date());

        return bankAccountDao.save(bankAccount);
    }

    public BankAccount getBankAccountByAccountNumber(String accountNumber) {

        BankAccount bankAccountReturn = bankAccountDao.getBankAccountByAccountNumber(accountNumber);

        return bankAccountReturn;
    }

    public BankAccount deposit(String accountNumber, long amount, String description) {
        BankAccount bankAccount = bankAccountDao.getBankAccountByAccountNumber(accountNumber);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccount.addDescription(description);
        return bankAccountDao.save(bankAccount);
    }
}
