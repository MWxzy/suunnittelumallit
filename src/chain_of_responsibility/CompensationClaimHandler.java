package chain_of_responsibility;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompensationClaimHandler extends FeedbackHandler {

    private static final double AUTO_APPROVAL_LIMIT = 200.0;

    private static final Pattern AMOUNT_PATTERN =
            Pattern.compile("(\\d+(?:[.,]\\d+)?)\\s*(?:euros?|eur|€)", Pattern.CASE_INSENSITIVE);

    @Override
    protected MessageType getHandledType() {
        return MessageType.COMPENSATION_CLAIM;
    }

    @Override
    protected void process(Message message) {
        Double amount = extractAmount(message.getContent());

        if (amount == null) {
            log("Claim reviewed, but no amount was found in the message.");
            log("Reply sent to " + message.getSenderEmail()
                    + " asking the customer to specify the claimed sum.");
            return;
        }

        if (amount <= AUTO_APPROVAL_LIMIT) {
            log(String.format(Locale.ROOT,
                    "Claim of %.2f EUR is within the %.2f EUR limit -> APPROVED.",
                    amount, AUTO_APPROVAL_LIMIT));
            log("Refund will be paid to the original payment method within 5 working days.");
        } else {
            log(String.format(Locale.ROOT,
                    "Claim of %.2f EUR exceeds the %.2f EUR limit -> REJECTED for automatic handling.",
                    amount, AUTO_APPROVAL_LIMIT));
            log("Forwarded to the finance manager for manual review. "
                    + "The customer is informed of the delay.");
        }
    }

    private Double extractAmount(String content) {
        Matcher matcher = AMOUNT_PATTERN.matcher(content);
        if (matcher.find()) {
            // Accept both 89,50 and 89.50 as the separator.
            return Double.parseDouble(matcher.group(1).replace(',', '.'));
        }
        return null;
    }
}
