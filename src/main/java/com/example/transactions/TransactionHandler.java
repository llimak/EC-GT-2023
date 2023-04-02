package com.example.transactions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TransactionHandler {
    Map<String, Account> accountsMap;
    JSONParser parser = new JSONParser();
    AccountComparator accountComparator = new AccountComparator();

    public TransactionHandler() {
        this.accountsMap = new HashMap<>();
    }

    private Account getAccountFromMapById(String id) {
        if (!accountsMap.containsKey(id)) {
            accountsMap.put(id, new Account(id));
        }
        return accountsMap.get(id);
    }

    private void performTransaction(String debitAccountId, String creditAccountId, BigDecimal amount) {
        Account debitAccount = getAccountFromMapById(debitAccountId);
        Account creditAccount = getAccountFromMapById(creditAccountId);
        debitAccount.withdraw(amount);
        creditAccount.deposit(amount);
    }

    public void processTransactions(String jsonArrayString) throws ParseException {
        JSONArray jsonArray = (JSONArray) parser.parse(jsonArrayString);
        for (Object transaction : jsonArray) {
            JSONObject transactionObj = (JSONObject) transaction;
            String debitAccount = transactionObj.get("debitAccount").toString();
            String creditAccount = transactionObj.get("creditAccount").toString();
            BigDecimal amount = new BigDecimal(transactionObj.get("amount").toString());
            performTransaction(debitAccount, creditAccount, amount);
        }
    }

    public List<Account> getSortedAccounts() {
        List<Account> listOfAccounts = new ArrayList<>(this.accountsMap.values());
        Collections.sort(listOfAccounts, this.accountComparator);
        return listOfAccounts;
    }

    public JSONArray getSortedAccountsInJSONArray() {
        JSONArray jsonArray = new JSONArray();
        List<Account> listOfAccounts = this.getSortedAccounts();
        for (Account account : listOfAccounts) {
            jsonArray.add(account.toJSONObject());
        }
        return jsonArray;
    }
}
