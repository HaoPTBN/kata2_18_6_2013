package org.qsoft.tdd.kata2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: haopt
 * Date: 6/12/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBankAccount {

    BankAccountDao mockAccountDao = mock(BankAccountDao.class);
    BankAccountService bankAccountService = new BankAccountService();

    @Before
    public void setUp() {
        reset(mockAccountDao);
        bankAccountService.setBankAccountDao(mockAccountDao);
    }

    @Test
    public void openNewAccount() {
        bankAccountService.openBankAccount("1234567890");

        ArgumentCaptor<BankAccount> savedAccountRecords = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountDao).save(savedAccountRecords.capture());

        assertEquals(savedAccountRecords.getValue().getBalance(), 0.0, 0.01);
        assertEquals(savedAccountRecords.getValue().getAccountNumber(),"1234567890");
    }

    @Test
    public void testGetAccountByAccountNumber() {
        String strParam = "123";
        //setup for DAO
        when(mockAccountDao.getBankAccountByAccountNumber(strParam)).thenReturn(new BankAccount(strParam, 0, new Date()));

        //get BankAccount from service
        BankAccount bankAccount = bankAccountService.getBankAccountByAccountNumber(strParam);
        // check Not Null
        assertNotNull(bankAccount);

        // check Account true, false
        assertEquals(bankAccount.getAccountNumber(),strParam);
    }

    @Test
    public void testAccountDeposit() {
        String strParam = "123";

        when(mockAccountDao.getBankAccountByAccountNumber(strParam)).thenReturn(new BankAccount(strParam, 0, new Date()));
        bankAccountService.deposit(strParam,100,"Deposit money");

        ArgumentCaptor<BankAccount> savedAccountRecords = ArgumentCaptor.forClass(BankAccount.class);
        verify(mockAccountDao).getBankAccountByAccountNumber(strParam);
        verify(mockAccountDao).save(savedAccountRecords.capture());

        assertEquals(savedAccountRecords.getValue().getBalance(), 100, 0.01);
        assertEquals(savedAccountRecords.getValue().getAccountNumber(),strParam);

    }
}
