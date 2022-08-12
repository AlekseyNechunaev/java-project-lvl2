package hexlet.code.formats;

import hexlet.code.ValueInfo;

import java.util.List;
import java.util.Map;

import static hexlet.code.serivces.Status.UNCHANGED;

public class PlainFormatter {

    public static String diffToPlain(Map<String, ValueInfo<Object>> diff) {
        StringBuilder resultStylish = new StringBuilder();
        for (Map.Entry<String, ValueInfo<Object>> entry : diff.entrySet()) {
            ValueInfo<Object> valueInfo = entry.getValue();
            if (!valueInfo.getStatus().equals(UNCHANGED)) {
                String oldValue = normalizeValueToPlainStyle(valueInfo.getOldValue());
                String newValue = normalizeValueToPlainStyle(valueInfo.getNewValue());
                resultStylish.append("\n").append("Property '");
                switch (valueInfo.getStatus()) {
                    case DELETED -> resultStylish.append(entry.getKey()).append("' was removed");
                    case ADDED -> resultStylish.append(entry.getKey()).append("' was added with value: ")
                            .append(newValue);
                    default -> resultStylish.append(entry.getKey()).append("' was updated. From ")
                            .append(oldValue).append(" to ").append(newValue);
                }
            }
        }
        return resultStylish.toString().trim();
    }

    private static String normalizeValueToPlainStyle(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
