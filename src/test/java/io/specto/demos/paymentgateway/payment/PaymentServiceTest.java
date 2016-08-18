package io.specto.demos.paymentgateway.payment;

import io.specto.demos.paymentgateway.account.Account;
import io.specto.demos.paymentgateway.account.RefundExceedsExecuteValueException;
import io.specto.demos.paymentgateway.account.Transaction;
import io.specto.demos.paymentgateway.account.TxnNotFoundException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaymentServiceTest {

    private PaymentService paymentService = new InMemoryPaymentService();

    private Account payer = new Account("benji", "benji@specto.io");
    private Account payee = new Account("daniel", "daniel@specto.io");

    @Test
    public void executeReturnsTxnId() {
        UUID txnId = paymentService.execute(payer, payee, 4.5d);

        assertThat(txnId, is(notNullValue()));
    }

    @Test
    public void viewTxnReturnsAssociatedExecuteData() throws TxnNotFoundException {
        UUID txnId = paymentService.execute(payer, payee, 4.5d);

        Transaction transaction = paymentService.view(txnId);
        assertThat(transaction.getAmount(), is(4.5d));
    }

    @Test
    public void validRefundReturnsNewTxn() throws TxnNotFoundException, RefundExceedsExecuteValueException {
        UUID txnId = paymentService.execute(payer, payee, 4.5d);

        UUID refundTxnId = paymentService.refund(txnId, -2.5d);
        assertThat(refundTxnId, is(notNullValue()));
    }

    @Test(expected = RefundExceedsExecuteValueException.class)
    public void invalidRefundThrowsREEVE() throws TxnNotFoundException, RefundExceedsExecuteValueException {
        UUID txnId = paymentService.execute(payer, payee, 4.5d);

        Transaction transaction = paymentService.view(txnId);
        paymentService.refund(transaction.getId(), -6.5d);
    }

    @Test
    public void cumulativeTxnValueIncRefundsIsCorrect() throws TxnNotFoundException, RefundExceedsExecuteValueException {
        UUID txnId = paymentService.execute(payer, payee, 4.5d);
        UUID refundTxnId = paymentService.refund(txnId, -2.5d);
        UUID txnId2 = paymentService.execute(payer, payee, 6.0d);

        assertThat(paymentService.getCumulativeTxnValue(), is(8.0d));
    }

    @Test
    public void cumulativePayersIsCorrect() throws TxnNotFoundException, RefundExceedsExecuteValueException {
        UUID txnId = paymentService.execute(payer, payee, 4.5d);
        UUID refundTxnId = paymentService.refund(txnId, -2.5d);
        UUID txnId2 = paymentService.execute(payer, payee, 6.0d);

        List<String> names = Arrays.asList("benji","benji");

        assertThat(paymentService.getCumulativePayerNames(), is(names));
    }
}
