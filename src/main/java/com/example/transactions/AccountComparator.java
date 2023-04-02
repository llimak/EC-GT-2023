package com.example.transactions;

import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {
    @Override
    public int compare(Account account1, Account account2) {
        String account1Id = account1.getId();
        String account2Id = account2.getId();
        int lengthCompareResult = Integer.compare(account1Id.length(), account2Id.length());
        if (lengthCompareResult != 0)
            return lengthCompareResult;
        else {
            for (int i = 0; i < account1Id.length(); i++) {
                char ch1 = account1Id.charAt(i);
                int num1 = Character.getNumericValue(ch1);
                char ch2 = account2Id.charAt(i);
                int num2 = Character.getNumericValue(ch2);
                int numCompareResult = Integer.compare(num1, num2);
                if (numCompareResult != 0)
                    return numCompareResult;
            }
        }
        return 0;
    }
}
