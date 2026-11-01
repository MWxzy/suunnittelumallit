package visitor.filesystem;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory src = new Directory("src");
        Directory model = new Directory("model");
        Directory docs = new Directory("docs");
        Directory build = new Directory("build");

        src.add(new File("Main.java", 0.02));
        src.add(new File("Util.java", 0.01));
        model.add(new File("User.java", 0.03));
        model.add(new File("Document.java", 0.04));
        docs.add(new File("readme.md", 0.01));
        docs.add(new File("manual.pdf", 2.5));
        build.add(new File("app.jar", 15.0));
        build.add(new File("build.log", 1.2));

        src.add(model);
        root.add(src);
        root.add(docs);
        root.add(build);
        root.add(new File("photo.png", 3.4));

        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        System.out.printf(Locale.US, "Total size: %.2f MB%n", sizeCalculator.getTotalSize());

        SearchVisitor javaFiles = new SearchVisitor(
                file -> file.getName().endsWith(".java"));
        root.accept(javaFiles);
        System.out.println("Java files:");
        for (File file : javaFiles.getMatches()) {
            System.out.println("- " + file.getName() + " (" + file.getSize() + " MB)");
        }

        SearchVisitor largeFiles = new SearchVisitor(
                file -> file.getSize() > 1.0);
        root.accept(largeFiles);
        System.out.println("Files larger than 1 MB:");
        for (File file : largeFiles.getMatches()) {
            System.out.println("- " + file.getName() + " (" + file.getSize() + " MB)");
        }
    }
}
