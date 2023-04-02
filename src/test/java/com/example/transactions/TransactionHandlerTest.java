package com.example.transactions;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class TransactionHandlerTest {
    @Test
    public void firstTestCase() {
        TransactionHandler transactionHandler = new TransactionHandler();
        String inputJSONString = "[{\"debitAccount\":\"32309111922661937852684864\",\"creditAccount\":\"06105023389842834748547303\",\"amount\":10.90},{\"debitAccount\":\"31074318698137062235845814\",\"creditAccount\":\"66105036543749403346524547\",\"amount\":200.90},{\"debitAccount\":\"66105036543749403346524547\",\"creditAccount\":\"32309111922661937852684864\",\"amount\":50.10}]";
        try {
            transactionHandler.processTransactions(inputJSONString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Account> accounts = transactionHandler.getSortedAccounts();
        Assert.assertEquals("06105023389842834748547303", accounts.get(0).getId());
        Assert.assertEquals(0, accounts.get(0).getDebitCount());
        Assert.assertEquals(1, accounts.get(0).getCreditCount());
        Assert.assertEquals(new BigDecimal("10.9"), accounts.get(0).getBalance());

        Assert.assertEquals("31074318698137062235845814", accounts.get(1).getId());
        Assert.assertEquals(1, accounts.get(1).getDebitCount());
        Assert.assertEquals(0, accounts.get(1).getCreditCount());
        Assert.assertEquals(new BigDecimal("-200.9"), accounts.get(1).getBalance());

        Assert.assertEquals("32309111922661937852684864", accounts.get(2).getId());
        Assert.assertEquals(1, accounts.get(2).getDebitCount());
        Assert.assertEquals(1, accounts.get(2).getCreditCount());
        Assert.assertEquals(new BigDecimal("39.2"), accounts.get(2).getBalance());

        Assert.assertEquals("66105036543749403346524547", accounts.get(3).getId());
        Assert.assertEquals(1, accounts.get(3).getDebitCount());
        Assert.assertEquals(1, accounts.get(3).getCreditCount());
        Assert.assertEquals(new BigDecimal("150.8"), accounts.get(3).getBalance());
    }
}
