package proxy.documents;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Library {

    private final Map<String, IDocument> documents = new HashMap<>();

    public void addDocument(String id, String content) {
        documents.put(id, new Document(id, LocalDate.now(), content));
    }

    public void addProtectedDocument(String id, String content, String... allowedUsernames) {
        Document document = new Document(id, LocalDate.now(), content);
        documents.put(id, new DocumentProxy(document));

        for (String username : allowedUsernames) {
            AccessControlService.getInstance().allow(username, id);
        }
    }

    public IDocument getDocument(String id) {
        IDocument document = documents.get(id);
        if (document == null) {
            throw new IllegalArgumentException("Unknown document: " + id);
        }
        return document;
    }

    public Map<String, IDocument> getDocuments() {
        return Collections.unmodifiableMap(documents);
    }
}
