package com.example.transactions;

import org.json.simple.JSONObject;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    private String id;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance;

    Account(String id) {
        this.id = id;
        this.debitCount = 0;
        this.creditCount = 0;
        this.balance = BigDecimal.ZERO;
        balance.setScale(2, RoundingMode.HALF_UP);
    }

    Account(String id, BigDecimal balance) {
        this.id = id;
        this.debitCount = 0;
        this.creditCount = 0;
        this.balance = balance;
        balance.setScale(2, RoundingMode.HALF_UP);
    }

    public String getId() {
        return this.id;
    }

    public int getDebitCount() {
        return this.debitCount;
    }

    public int getCreditCount() {
        return this.creditCount;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        this.creditCount++;
    }

    public void withdraw(BigDecimal amount) {
        this.balance = balance.subtract(amount);
        this.debitCount++;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", this.id);
        jsonObject.put("debitCount", this.debitCount);
        jsonObject.put("creditCount", this.creditCount);
        jsonObject.put("balance", this.balance.floatValue());
        return jsonObject;
    }
}
