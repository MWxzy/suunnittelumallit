package chain_of_responsibility;

public enum MessageType {

    COMPENSATION_CLAIM("Compensation claim"),

    CONTACT_REQUEST("Contact request"),

    DEVELOPMENT_SUGGESTION("Development suggestion"),

    GENERAL_FEEDBACK("General feedback");

    private final String displayName;

    MessageType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
