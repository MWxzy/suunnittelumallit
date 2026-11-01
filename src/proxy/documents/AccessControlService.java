package proxy.documents;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccessControlService {

    private static final AccessControlService INSTANCE = new AccessControlService();

    private final Map<String, Set<String>> allowedDocuments = new HashMap<>();

    private AccessControlService() {
    }

    public static AccessControlService getInstance() {
        return INSTANCE;
    }

    public void allow(String username, String documentId) {
        allowedDocuments
                .computeIfAbsent(username, key -> new HashSet<>())
                .add(documentId);
    }

    public boolean isAllowed(String documentId, String username) {
        Set<String> documents = allowedDocuments.get(username);
        return documents != null && documents.contains(documentId);
    }
}
