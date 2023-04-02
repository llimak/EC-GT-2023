package com.example.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AccountComparatorTest {
    @Test
    public void theSameLengthOfId() {
        List<Account> accounts = new ArrayList<>();
        AccountComparator accountComparator = new AccountComparator();
        accounts.add(new Account("66105036543749403346524547"));
        accounts.add(new Account("32309111922661937852684864"));
        accounts.add(new Account("06105023389842834748547303"));
        Collections.sort(accounts, accountComparator);

        Assert.assertEquals("06105023389842834748547303", accounts.get(0).getId());
        Assert.assertEquals("32309111922661937852684864", accounts.get(1).getId());
        Assert.assertEquals("66105036543749403346524547", accounts.get(2).getId());
    }

    @Test
    public void notTheSameLengthOfId() {
        List<Account> accounts = new ArrayList<>();
        AccountComparator accountComparator = new AccountComparator();
        accounts.add(new Account("06105023389842834748547303"));
        accounts.add(new Account("66105036543749403346524547"));
        accounts.add(new Account("09111922661937852684864"));
        Collections.sort(accounts, accountComparator);

        Assert.assertEquals("09111922661937852684864", accounts.get(0).getId());
        Assert.assertEquals("06105023389842834748547303", accounts.get(1).getId());
        Assert.assertEquals("66105036543749403346524547", accounts.get(2).getId());
    }

    @Test
    public void someSameIds() {
        List<Account> accounts = new ArrayList<>();
        AccountComparator accountComparator = new AccountComparator();
        accounts.add(new Account("06105023389842834748547303"));
        accounts.add(new Account("09111922661937852684864"));
        accounts.add(new Account("66105036543749403346524547"));
        accounts.add(new Account("09111922661937852684864"));
        Collections.sort(accounts, accountComparator);

        Assert.assertEquals("09111922661937852684864", accounts.get(0).getId());
        Assert.assertEquals("09111922661937852684864", accounts.get(1).getId());
        Assert.assertEquals("06105023389842834748547303", accounts.get(2).getId());
        Assert.assertEquals("66105036543749403346524547", accounts.get(3).getId());
    }
}
