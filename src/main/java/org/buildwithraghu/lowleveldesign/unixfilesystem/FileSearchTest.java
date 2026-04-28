package org.buildwithraghu.lowleveldesign.unixfilesystem;

import org.buildwithraghu.lowleveldesign.unixfilesystem.operators.EqualsOperator;
import org.buildwithraghu.lowleveldesign.unixfilesystem.operators.GreaterThanOperator;
import org.buildwithraghu.lowleveldesign.unixfilesystem.operators.LessThanOperator;
import org.buildwithraghu.lowleveldesign.unixfilesystem.operators.RegexMatchOperator;
import static java.util.List.of;

import org.buildwithraghu.lowleveldesign.unixfilesystem.predicates.AndPredicate;
import org.buildwithraghu.lowleveldesign.unixfilesystem.predicates.NotPredicate;
import org.buildwithraghu.lowleveldesign.unixfilesystem.predicates.OrPredicate;
import org.buildwithraghu.lowleveldesign.unixfilesystem.predicates.SimplePredicate;

public class FileSearchTest {

    public static void main(String[] args) {

        // Build a sample directory tree:
        // root/
        // ├── docs/
        // |   ├── readme.txt (owner: alice, size: 100)
        // |   └── notes.txt  (owner: bob,   size: 200)
        // ├── images/
        // |   ├── photo.jpg  (owner: alice, size: 5000)
        // |   └── logo.png   (owner: alice, size: 3000)
        // ├── script.sh      (owner: bob,   size: 500)
        // └── data.csv       (owner: charlie, size: 2000)

        File docs = new File(true, 0, "system", "docs");
        File images = new File(true, 0, "system", "images");
        File readme = new File(false, 100, "alice", "readme.txt");
        File notes = new File(false, 200, "bob", "notes.txt");
        File photo = new File(false, 5000, "alice", "photo.jpg");
        File logo = new File(false, 3000, "alice", "logo.png");
        File script = new File(false, 500, "bob", "script.sh");
        File data = new File(false, 2000, "charlie", "data.csv");

        File root = new File(true, 0, "alice", "root");

        docs.addEntry(readme);
        docs.addEntry(notes);
        images.addEntry(photo);
        images.addEntry(logo);
        root.addEntry(docs);
        root.addEntry(images);
        root.addEntry(script);
        root.addEntry(data);

        FileSearch searcher = new FileSearch();

        // Test 1: Search by filename pattern
        System.out.println("=== Test 1: Files ending with .txt ===");
        var txtCriteria = new FileSearchCriteria(
                new SimplePredicate<>(FileAttribute.FILENAME, new RegexMatchOperator(), ".*\\.txt$"));
        var txtResults = searcher.search(root, txtCriteria);
        printResults(txtResults);
        assert txtResults.size() == 2 : "Expected 2 .txt files, got " + txtResults.size();

        // Test 2: Search by owner
        System.out.println("\n=== Test 2: Files owned by alice ===");
        var aliceCriteria = new FileSearchCriteria(
                new SimplePredicate<>(FileAttribute.OWNER, new EqualsOperator<>(), "alice"));
        var aliceResults = searcher.search(root, aliceCriteria);
        printResults(aliceResults);
        assert aliceResults.size() == 4 : "Expected 4 files owned by alice, got " + aliceResults.size();

        // Test 3: Search directories only
        System.out.println("\n=== Test 3: Directories only ===");
        var dirCriteria = new FileSearchCriteria(
                new SimplePredicate<>(FileAttribute.IS_DIRECTORY, new EqualsOperator<>(), true));
        var dirResults = searcher.search(root, dirCriteria);
        printResults(dirResults);
        assert dirResults.size() == 3 : "Expected 3 directories, got " + dirResults.size();

        // Test 4: Search by file size range
        System.out.println("\n=== Test 4: Files larger than 1000 bytes ===");
        var largeCriteria = new FileSearchCriteria(
                new SimplePredicate<>(FileAttribute.SIZE, new GreaterThanOperator<>(), 1000));
        var largeResults = searcher.search(root, largeCriteria);
        printResults(largeResults);
        assert largeResults.size() == 3 : "Expected 3 files > 1000 bytes, got " + largeResults.size();

        // Test 5: Search with AND predicate (owner alice AND extension .jpg)
        System.out.println("\n=== Test 5: Owned by alice AND is .jpg ===");
        var andCriteria = new FileSearchCriteria(
                new AndPredicate(of(
                        new SimplePredicate<>(FileAttribute.OWNER, new EqualsOperator<>(), "alice"),
                        new SimplePredicate<>(FileAttribute.FILENAME, new RegexMatchOperator(), ".*\\.jpg$"))));
        var andResults = searcher.search(root, andCriteria);
        printResults(andResults);
        assert andResults.size() == 1 : "Expected 1 file owned by alice with .jpg, got " + andResults.size();
        assert andResults.get(0).getFilename().equals("photo.jpg");

        // Test 6: Search with OR predicate (owner bob OR owner charlie)
        System.out.println("\n=== Test 6: Owned by bob OR charlie ===");
        var orCriteria = new FileSearchCriteria(
                new OrPredicate(of(
                        new SimplePredicate<>(FileAttribute.OWNER, new EqualsOperator<>(), "bob"),
                        new SimplePredicate<>(FileAttribute.OWNER, new EqualsOperator<>(), "charlie"))));
        var orResults = searcher.search(root, orCriteria);
        printResults(orResults);
        assert orResults.size() == 2 : "Expected 2 files owned by bob or charlie, got " + orResults.size();

        // Test 7: Search by filename containing "logo"
        System.out.println("\n=== Test 7: Files containing 'logo' ===");
        var logoCriteria = new FileSearchCriteria(
                new SimplePredicate<>(FileAttribute.FILENAME, new RegexMatchOperator(), ".*logo.*"));
        var logoResults = searcher.search(root, logoCriteria);
        printResults(logoResults);
        assert logoResults.size() == 1 : "Expected 1 file with 'logo' in name, got " + logoResults.size();

        // Test 8: Search with NOT predicate (not a directory)
        System.out.println("\n=== Test 8: Not a directory (regular files) ===");
        var notCriteria = new FileSearchCriteria(
                new NotPredicate(
                        new SimplePredicate<>(FileAttribute.IS_DIRECTORY, new EqualsOperator<>(), true)));
        var notResults = searcher.search(root, notCriteria);
        printResults(notResults);
        assert notResults.size() == 6 : "Expected 6 regular files, got " + notResults.size();

        System.out.println("\n=== All tests passed! ===");
    }

    private static void printResults(java.util.List<File> files) {
        for (File f : files) {
            System.out.printf("  [%s] %s (owner=%s, size=%d)%n",
                    f.isDirectory() ? "DIR" : "FILE", f.getFilename(), f.getOwner(), f.getSize());
        }
    }
}
