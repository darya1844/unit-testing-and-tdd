package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is null");
        }
        if (client == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount is null");
        }
        this.id = id;
        this.client = client;
        this.amount = amount;
        client.getAccounts().add(this);
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }
}
