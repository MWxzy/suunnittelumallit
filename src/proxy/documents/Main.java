package proxy.documents;

import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

        library.addDocument("public-guide", "Welcome to the public user guide.");
        library.addDocument("release-notes", "Version 1.0 has been released.");
        library.addProtectedDocument("salary-report", "Salary report contents.", "alice");
        library.addProtectedDocument("hr-plan", "Human resources plan.", "alice", "bob");
        library.addProtectedDocument("admin-keys", "Administrator access keys.", "admin");

        User alice = new User("alice");
        User bob = new User("bob");
        User charlie = new User("charlie");

        System.out.println("Documents in the library:");
        for (Map.Entry<String, IDocument> entry : new TreeMap<>(library.getDocuments()).entrySet()) {
            IDocument document = entry.getValue();
            System.out.println("- " + document.getId()
                    + " (created " + document.getCreationDate() + ")");
        }

        System.out.println();
        access(library, "public-guide", alice);
        access(library, "public-guide", charlie);
        access(library, "salary-report", alice);
        access(library, "salary-report", bob);
        access(library, "hr-plan", bob);
        access(library, "hr-plan", charlie);
        access(library, "admin-keys", alice);

        System.out.println();
        IDocument protectedDocument = library.getDocument("salary-report");
        System.out.println("Protected document uses a proxy: "
                + (protectedDocument instanceof DocumentProxy));
        System.out.println("Creation date is public: " + protectedDocument.getCreationDate());
        System.out.println("Access control service is a singleton: "
                + (AccessControlService.getInstance() == AccessControlService.getInstance()));
    }

    private static void access(Library library, String documentId, User user) {
        IDocument document = library.getDocument(documentId);
        try {
            String content = document.getContent(user);
            System.out.println(user.getUsername() + " read '" + documentId + "': " + content);
        } catch (AccessDeniedException e) {
            System.out.println(user.getUsername() + " was denied access to '"
                    + documentId + "': " + e.getMessage());
        }
    }
}
