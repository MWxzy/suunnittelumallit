package chain_of_responsibility;

public class GeneralFeedbackHandler extends FeedbackHandler {

    private enum Sentiment {
        POSITIVE, NEGATIVE, NEUTRAL
    }

    @Override
    protected MessageType getHandledType() {
        return MessageType.GENERAL_FEEDBACK;
    }

    @Override
    protected void process(Message message) {
        Sentiment sentiment = analyzeSentiment(message);

        log("Feedback analyzed: sentiment is " + sentiment + ".");
        switch (sentiment) {
            case POSITIVE:
                log("A thank-you reply has been sent to " + message.getSenderEmail()
                        + " and the message was forwarded to marketing as a testimonial.");
                break;
            case NEGATIVE:
                log("An apology draft was created for " + message.getSenderEmail()
                        + " and the responsible team was alerted to the complaint.");
                break;
            case NEUTRAL:
            default:
                log("A standard acknowledgement was sent to " + message.getSenderEmail()
                        + " and the feedback was stored for reporting.");
                break;
        }
    }

    private Sentiment analyzeSentiment(Message message) {
        if (message.contentContains("great") || message.contentContains("excellent")
                || message.contentContains("love") || message.contentContains("thank")
                || message.contentContains("happy") || message.contentContains("awesome")
                || message.contentContains("good")) {
            return Sentiment.POSITIVE;
        }
        if (message.contentContains("bad") || message.contentContains("terrible")
                || message.contentContains("awful") || message.contentContains("hate")
                || message.contentContains("disappointed") || message.contentContains("slow")
                || message.contentContains("worst")) {
            return Sentiment.NEGATIVE;
        }
        return Sentiment.NEUTRAL;
    }
}
