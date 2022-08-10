package hexlet.code;

import hexlet.code.formats.StylishFormatter;
import hexlet.code.serivces.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {

    public static String generate(String pathToFirstFile, String pathToSecondFile) {
        String contentInFirstFile = readFile(pathToFirstFile);
        String contentInSecondFile = readFile(pathToSecondFile);
        Map<String, Object> mapFirstFileContent = Parser.getData(contentInFirstFile);
        Map<String, Object> mapSecondFileContent = Parser.getData(contentInSecondFile);
        Map<String, ValueInfo<Object>> diffMap = genDiff(mapFirstFileContent, mapSecondFileContent);
        return StylishFormatter.doffToStylish(diffMap);
    }

    private static String readFile(String pathToFile) {
        if (!pathToFile.endsWith(".json") && !pathToFile.endsWith(".yaml")) {
            throw new RuntimeException("incorrect file format");
        }
        Path fullPath = Paths.get(pathToFile);
        String contentFile;
        try {
            contentFile = Files.readString(fullPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contentFile;
    }

    private static Map<String, ValueInfo<Object>> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, ValueInfo<Object>> resultDiff = new TreeMap<>();
        Set<String> allKeys = new HashSet<>(firstMap.keySet());
        allKeys.addAll(secondMap.keySet());
        allKeys.forEach(key -> {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (!firstMap.get(key).equals(secondMap.get(key))) {
                    resultDiff.put(key, new ValueInfo<>(firstMap.get(key), secondMap.get(key), Status.CHANGED));
                } else {
                    resultDiff.put(key, new ValueInfo<>(firstMap.get(key), secondMap.get(key), Status.UNCHANGED));
                }
            } else {
                if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                    resultDiff.put(key, new ValueInfo<>(firstMap.get(key), null, Status.DELETED));
                } else {
                    resultDiff.put(key, new ValueInfo<>(null, secondMap.get(key), Status.ADDED));
                }
            }
        });
        return resultDiff;
    }
}
