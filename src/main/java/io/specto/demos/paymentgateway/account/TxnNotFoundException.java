package io.specto.demos.paymentgateway.account;

public class TxnNotFoundException extends Exception {

    public TxnNotFoundException() {
        super();
    }

    public TxnNotFoundException(String message) {
        super(message);
    }
}
