package hexlet.code;

import hexlet.code.formats.Formatter;
import hexlet.code.formats.FormatterFactory;
import hexlet.code.serivces.Utils;
import hexlet.code.serivces.ValueInfo;

import java.util.Map;

public class Differ {

    public static String generate(String pathToFirstFile, String pathToSecondFile, String format) {
        String contentInFirstFile = Utils.readFile(pathToFirstFile);
        String contentInSecondFile = Utils.readFile(pathToSecondFile);
        String firstFileExtension = Utils.getFileExtension(pathToFirstFile);
        String secondFileExtension = Utils.getFileExtension(pathToSecondFile);
        Map<String, Object> mapFirstFileContent = Parser.parse(contentInFirstFile, firstFileExtension);
        Map<String, Object> mapSecondFileContent = Parser.parse(contentInSecondFile, secondFileExtension);
        Map<String, ValueInfo> diffMap = DiffGenerator.genDiff(mapFirstFileContent, mapSecondFileContent);
        final Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.toFormat(diffMap);
    }

    public static String generate(String pathToFirstFile, String pathToSecondFile) {
        return generate(pathToFirstFile, pathToSecondFile, "stylish");
    }
}
