package proxy.documents;

import java.time.LocalDate;

public class DocumentProxy implements IDocument {

    private final Document document;
    private final AccessControlService accessControlService;

    DocumentProxy(Document document) {
        this.document = document;
        this.accessControlService = AccessControlService.getInstance();
    }

    @Override
    public String getId() {
        return document.getId();
    }

    @Override
    public LocalDate getCreationDate() {
        return document.getCreationDate();
    }

    @Override
    public String getContent(User user) throws AccessDeniedException {
        if (accessControlService.isAllowed(document.getId(), user.getUsername())) {
            return document.getContent(user);
        }

        throw new AccessDeniedException(
                "Access denied for user '" + user.getUsername()
                        + "' to document '" + document.getId() + "'");
    }
}
