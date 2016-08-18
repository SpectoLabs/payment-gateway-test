package io.specto.demos.paymentgateway.account;

public class RefundExceedsExecuteValueException extends Exception {

    public RefundExceedsExecuteValueException() {
        super();
    }

    public RefundExceedsExecuteValueException(String message) {
        super(message);
    }
}
