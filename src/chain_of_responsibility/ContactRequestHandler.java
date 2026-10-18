package chain_of_responsibility;

public class ContactRequestHandler extends FeedbackHandler {

    private static final String DEFAULT_DEPARTMENT = "Customer Service";

    @Override
    protected MessageType getHandledType() {
        return MessageType.CONTACT_REQUEST;
    }

    @Override
    protected void process(Message message) {
        String department = resolveDepartment(message);

        log("Request analyzed and routed to the " + department + " department.");
        log("A contact ticket was created and the customer at " + message.getSenderEmail()
                + " was informed that the " + department + " department will reply within 2 working days.");
    }

    private String resolveDepartment(Message message) {
        if (message.contentContains("delivery") || message.contentContains("shipment")
                || message.contentContains("parcel") || message.contentContains("order")) {
            return "Logistics";
        }
        if (message.contentContains("invoice") || message.contentContains("billing")
                || message.contentContains("charge")) {
            return "Billing";
        }
        if (message.contentContains("refund") || message.contentContains("payment")
                || message.contentContains("card")) {
            return "Payments";
        }
        if (message.contentContains("login") || message.contentContains("password")
                || message.contentContains("account") || message.contentContains("error")) {
            return "Technical Support";
        }
        return DEFAULT_DEPARTMENT;
    }
}
