package chain_of_responsibility;

/**
 * A single piece of customer feedback travelling through the handler chain.
 *
 * <p>This is the "request" object of the Chain of Responsibility pattern. It is
 * intentionally a plain, immutable data holder: it only carries the feedback
 * and knows nothing about how it will be processed.</p>
 *
 * <ul>
 *   <li>{@code type} &ndash; which form of feedback this is</li>
 *   <li>{@code content} &ndash; the actual message written by the customer</li>
 *   <li>{@code senderEmail} &ndash; where the answer should be sent</li>
 * </ul>
 */
public class Message {

    private final MessageType type;
    private final String content;
    private final String senderEmail;

    /**
     * Creates a new feedback message.
     *
     * @param type        the form of feedback, must not be {@code null}
     * @param content     the customer's message text, must not be {@code null}
     * @param senderEmail the customer's email address, must not be {@code null}
     * @throws IllegalArgumentException if any argument is {@code null}
     */
    public Message(MessageType type, String content, String senderEmail) {
        if (type == null) {
            throw new IllegalArgumentException("Message type cannot be null.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Message content cannot be empty.");
        }
        if (senderEmail == null || senderEmail.isBlank()) {
            throw new IllegalArgumentException("Sender email cannot be empty.");
        }

        this.type = type;
        this.content = content;
        this.senderEmail = senderEmail;
    }

    /**
     * @return the form of this feedback
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @return the customer's message text
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the customer's email address
     */
    public String getSenderEmail() {
        return senderEmail;
    }

    /**
     * @return {@code true} if the content contains the given word, ignoring case
     */
    public boolean contentContains(String word) {
        return content.toLowerCase().contains(word.toLowerCase());
    }

    @Override
    public String toString() {
        return "Message{" + type + ", from='" + senderEmail + "', content='" + content + "'}";
    }
}
