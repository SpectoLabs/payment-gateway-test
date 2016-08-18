package io.specto.demos.paymentgateway.account;

public class Account {
    String name;
    String email;

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
