package hexlet.code;

import hexlet.code.serivces.ValueInfo;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String pathToFirstFile, String pathToSecondFile, String format) {
        String contentInFirstFile = readFile(pathToFirstFile);
        String contentInSecondFile = readFile(pathToSecondFile);
        String firstFileExtension = getFileExtension(pathToFirstFile);
        String secondFileExtension = getFileExtension(pathToSecondFile);
        Map<String, Object> mapFirstFileContent = Parser.parse(contentInFirstFile, firstFileExtension);
        Map<String, Object> mapSecondFileContent = Parser.parse(contentInSecondFile, secondFileExtension);
        Map<String, ValueInfo> diffMap = DiffGenerator.genDiff(mapFirstFileContent, mapSecondFileContent);
        return Formatter.toFormat(diffMap, format);
    }

    public static String generate(String pathToFirstFile, String pathToSecondFile) {
        return generate(pathToFirstFile, pathToSecondFile, "stylish");
    }

    private static String readFile(String pathToFile) {
        Path fullPath = Paths.get(pathToFile);
        String contentFile;
        try {
            contentFile = Files.readString(fullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contentFile;
    }

    private static String getFileExtension(String pathToFile) {
        return FilenameUtils.getExtension(pathToFile);
    }
}
