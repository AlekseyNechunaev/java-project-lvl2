package hexlet.code.serivces;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Utils {

    private Utils() {

    }

    public static String readFile(String pathToFile) {
        Path fullPath = Paths.get(pathToFile);
        String contentFile;
        try {
            contentFile = Files.readString(fullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contentFile;
    }

    public static String getFileExtension(String pathToFile) {
        return FilenameUtils.getExtension(pathToFile);
    }
}
