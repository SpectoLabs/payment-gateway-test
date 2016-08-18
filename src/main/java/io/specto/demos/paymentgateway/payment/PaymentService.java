package io.specto.demos.paymentgateway.payment;

import io.specto.demos.paymentgateway.account.Account;
import io.specto.demos.paymentgateway.account.RefundExceedsExecuteValueException;
import io.specto.demos.paymentgateway.account.Transaction;
import io.specto.demos.paymentgateway.account.TxnNotFoundException;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    UUID execute(Account payer, Account payee, double amount);
    Transaction view(UUID transactionId) throws TxnNotFoundException;
    UUID refund(UUID transactionId, double amount) throws TxnNotFoundException, RefundExceedsExecuteValueException;
    double getCumulativeTxnValue();
    List<String> getCumulativePayerNames();
}
