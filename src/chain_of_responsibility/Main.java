package chain_of_responsibility;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FeedbackHandler[] chain = createHandlerChain();

        printHeader(chain);

        List<Message> messages = generateMessages();
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);

            System.out.printf("Message %d/%d%n", i + 1, messages.size());
            System.out.println("  Type    : " + message.getType().getDisplayName());
            System.out.println("  From    : " + message.getSenderEmail());
            System.out.println("  Content : " + message.getContent());

            chain[0].handle(message);

            System.out.println();
        }

        printSummary(chain);
    }

    private static FeedbackHandler[] createHandlerChain() {
        FeedbackHandler[] chain = new FeedbackHandler[] {
                new CompensationClaimHandler(),
                new ContactRequestHandler(),
                new DevelopmentSuggestionHandler(),
                new GeneralFeedbackHandler()
        };

        for (int i = 0; i < chain.length - 1; i++) {
            chain[i].setNext(chain[i + 1]);
        }
        return chain;
    }

    private static List<Message> generateMessages() {
        return List.of(
                new Message(MessageType.COMPENSATION_CLAIM,
                        "The coffee machine I ordered arrived broken. "
                                + "I would like a compensation of 120 euros.",
                        "matti.meikalainen@example.com"),

                new Message(MessageType.COMPENSATION_CLAIM,
                        "My holiday trip was cancelled by your service and I demand a "
                                + "compensation of 1500,50 euros for the lost days.",
                        "liisa.virtanen@example.com"),

                new Message(MessageType.CONTACT_REQUEST,
                        "Could you tell me when my order will be delivered? "
                                + "The shipment has been stuck for a week.",
                        "antti.korhonen@example.com"),

                new Message(MessageType.CONTACT_REQUEST,
                        "I cannot log in to my account and the password reset ends with an error.",
                        "sofia.lindqvist@example.com"),

                new Message(MessageType.DEVELOPMENT_SUGGESTION,
                        "It would be great if you could add a dark mode feature to the mobile app.",
                        "juhani.nieminen@example.com"),

                new Message(MessageType.DEVELOPMENT_SUGGESTION,
                        "The app crashes every time I open the reports page. Please fix this bug.",
                        "emmi.salonen@example.com"),

                new Message(MessageType.GENERAL_FEEDBACK,
                        "Thank you for the excellent service, I really love the new website!",
                        "pekka.aho@example.com"),

                new Message(MessageType.GENERAL_FEEDBACK,
                        "The waiting times were terrible and the staff was not helpful at all.",
                        "aino.makinen@example.com")
        );
    }

    private static void printHeader(FeedbackHandler[] chain) {
        System.out.println("===============================================================");
        System.out.println(" Customer Feedback Handler - Chain of Responsibility");
        System.out.println("===============================================================");
        System.out.println("Handler chain:");
        for (int i = 0; i < chain.length; i++) {
            System.out.printf("  %d. %s%n", i + 1, chain[i].getName());
        }
        System.out.println();
        System.out.println("Every message is given to the first handler and is routed");
        System.out.println("forward until a handler responsible for its type processes it.");
        System.out.println("---------------------------------------------------------------");
        System.out.println();
    }

    private static void printSummary(FeedbackHandler[] chain) {
        System.out.println("===============================================================");
        System.out.println(" Handling summary");
        System.out.println("===============================================================");

        int total = 0;
        for (FeedbackHandler handler : chain) {
            System.out.printf("  %-30s %d message(s)%n",
                    handler.getName(), handler.getHandledCount());
            total += handler.getHandledCount();
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("  %-30s %d message(s)%n", "Total handled", total);
        System.out.println("===============================================================");
    }
}
