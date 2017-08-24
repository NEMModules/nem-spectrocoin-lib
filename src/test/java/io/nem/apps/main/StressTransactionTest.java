package io.nem.apps.main;

import org.junit.Test;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.test.Utils;

import io.nem.apps.builders.GenericTransactionBuilder;
import io.nem.apps.builders.TransactionBuilder;
import io.nem.apps.factories.AttachmentFactory;


/**
 * The Class BuildTransactionTest.
 */
public class StressTransactionTest extends TransactionUnitTest {

	/** The sample  msg. */
	final String sampleMsg = "{1:F21FOOLHKH0AXXX0304009999}{4:{177:1608140809}{451:0}}{1:F01FOOLHKH0AXXX0304009999}{2:O9401609160814FOOLHKH0AXXX03040027341608141609N}{4:\n"
			+ ":20:USD940NO1\n" + ":21:123456/DEV\n" + ":25:USD234567\n" + ":28C:1/1\n" + ":60F:C160418USD672,\n"
			+ ":61:160827C642,S1032\n" + ":86:ANDY\n" + ":61:160827D42,S1032\n" + ":86:BANK CHARGES\n"
			+ ":62F:C160418USD1872,\n" + ":64:C160418USD1872,\n" + "-}{5:{CHK:0FEC1E4AEC53}{TNG:}}{S:{COP:S}}";

	/**
	 * Test cb build transaction.
	 */
	@Test
	public void testCbBuildTransaction() {

		// Build a transaction.
		TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount).amount(Amount.ZERO)
				.buildTransaction();

	}

	/**
	 * Test cb build and send  transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendTransactionWithOutMosaic() {

		try {
			for (int i = 0; i < 500; i++) {
				// test from a string.
				final Account senderAccount = this.recipientPublicAccount;
				final Account recipientAccount = this.recipientPublicAccount;

				final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
						this.sampleMsg.getBytes());

				TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);

				TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
						.amount(Amount.ZERO).attachment(AttachmentFactory.createTransferTransactionAttachment(message))
						.buildAndSendTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test cb build and send  transaction with mosaic.
	 */
	@Test
	public void testCbBuildAndSendTransactionWithMosaic() {

		try {
			for (int i = 0; i < 500; i++) {
				// test from a string.
				final Account senderAccount = this.senderPrivateAccount;
				final Account recipientAccount = this.recipientPublicAccount;

				final SecureMessage message = SecureMessage.fromDecodedPayload(senderAccount, recipientAccount,
						this.sampleMsg.getBytes());

				TransferTransactionAttachment attachment = new TransferTransactionAttachment(message);
				attachment.addMosaic(Utils.createMosaic(1).getMosaicId(), new Quantity(12));

				TransactionBuilder.initiateTransactionBuild().sender(this.senderPrivateAccount).recipient(this.recipientPublicAccount)
						.amount(Amount.ZERO).attachment(AttachmentFactory.createTransferTransactionAttachment(message))
						.buildAndSendTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}