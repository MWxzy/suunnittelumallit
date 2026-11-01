package proxy.documents;

import java.time.LocalDate;

public interface IDocument {

    String getId();

    LocalDate getCreationDate();

    String getContent(User user) throws AccessDeniedException;
}
