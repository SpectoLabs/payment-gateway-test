package io.specto.demos.paymentgateway.account;

import java.util.UUID;

public class Transaction {
    UUID id;
    Account payer;
    Account payee;
    double amount;

    public Transaction(UUID id, Account payer, Account payee, double amount) {
        this.id = id;
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public Account getPayer() {
        return payer;
    }

    public Account getPayee() {
        return payee;
    }

    public double getAmount() {
        return amount;
    }
}
