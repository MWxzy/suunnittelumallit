package proxy.documents;

import java.time.LocalDate;

public class Document implements IDocument {

    private final String id;
    private final LocalDate creationDate;
    private final String content;

    Document(String id, LocalDate creationDate, String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String getContent(User user) {
        return content;
    }
}
