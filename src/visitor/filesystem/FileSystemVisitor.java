package visitor.filesystem;

public interface FileSystemVisitor {

    void visit(File file);

    void visit(Directory directory);
}
