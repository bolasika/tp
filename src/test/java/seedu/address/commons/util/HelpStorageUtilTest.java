package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HelpStorageUtilTest {
    private static final Path HELP_DIR = Paths.get("data/help");

    @BeforeEach
    @AfterEach
    public void cleanup() throws IOException {
        HelpStorageUtil.clearDirectory();
    }

    @Test
    public void copyOverOfflineHelp_createsDirectory_success() {
        assertFalse(Files.exists(HELP_DIR));
        assertDoesNotThrow(() -> HelpStorageUtil.copyOverOfflineHelp());

        // Verify the directory was created
        assertTrue(Files.exists(HELP_DIR));
        assertTrue(Files.isDirectory(HELP_DIR));
    }

    @Test
    public void clearDirectory_existingDirectoryWithFiles_deleteSuccess() throws IOException {
        Files.createDirectories(HELP_DIR);
        Path dummyFile = HELP_DIR.resolve("dummy_test_file.txt");
        Files.createFile(dummyFile);
        assertTrue(Files.exists(dummyFile));
        assertTrue(Files.exists(HELP_DIR));

        HelpStorageUtil.clearDirectory();

        assertFalse(Files.exists(dummyFile), "The dummy file should be deleted");
        assertFalse(Files.exists(HELP_DIR), "The help directory should be deleted");
    }

    @Test
    public void clearDirectory_nonExistentDirectory_doesNothing() {
        assertFalse(Files.exists(HELP_DIR));
        assertDoesNotThrow(() -> HelpStorageUtil.clearDirectory());
    }
}
