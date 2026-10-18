package chain_of_responsibility;

public class DevelopmentSuggestionHandler extends FeedbackHandler {

    private static final int FIRST_SUGGESTION_ID = 1001;

    private int nextSuggestionId = FIRST_SUGGESTION_ID;

    private enum Priority {
        HIGH, MEDIUM, LOW
    }

    @Override
    protected MessageType getHandledType() {
        return MessageType.DEVELOPMENT_SUGGESTION;
    }

    @Override
    protected void process(Message message) {
        Priority priority = resolvePriority(message);
        String suggestionId = "SUG-" + nextSuggestionId++;

        log("Suggestion logged as " + suggestionId + " with priority " + priority + ".");
        log("Added to the product backlog for triage; the customer at " + message.getSenderEmail()
                + " will be notified if the idea is taken into development.");
    }

    private Priority resolvePriority(Message message) {
        if (message.contentContains("crash") || message.contentContains("bug")
                || message.contentContains("error") || message.contentContains("security")
                || message.contentContains("data loss")) {
            return Priority.HIGH;
        }
        if (message.contentContains("feature") || message.contentContains("add")
                || message.contentContains("improve") || message.contentContains("enhancement")
                || message.contentContains("support")) {
            return Priority.MEDIUM;
        }
        return Priority.LOW;
    }
}
