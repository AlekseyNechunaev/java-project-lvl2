package hexlet.code;

import hexlet.code.serivces.Status;
import hexlet.code.serivces.ValueInfo;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Objects;

public class DiffGenerator {

    public static Map<String, ValueInfo> genDiff(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Map<String, ValueInfo> resultDiff = new TreeMap<>();
        Set<String> allKeys = new HashSet<>(firstMap.keySet());
        allKeys.addAll(secondMap.keySet());
        allKeys.forEach(key -> {
            if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (!Objects.equals(firstMap.get(key), secondMap.get(key))) {
                    resultDiff.put(key, new ValueInfo(firstMap.get(key), secondMap.get(key), Status.CHANGED));
                } else {
                    resultDiff.put(key, new ValueInfo(firstMap.get(key), secondMap.get(key), Status.UNCHANGED));
                }
            } else {
                if (firstMap.containsKey(key)) {
                    resultDiff.put(key, new ValueInfo(firstMap.get(key), null, Status.DELETED));
                } else {
                    resultDiff.put(key, new ValueInfo(null, secondMap.get(key), Status.ADDED));
                }
            }
        });
        return resultDiff;
    }
}
