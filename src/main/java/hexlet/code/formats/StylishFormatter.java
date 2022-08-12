package hexlet.code.formats;

import hexlet.code.ValueInfo;

import java.util.Map;

public class StylishFormatter {

    public static String diffToStylish(Map<String, ValueInfo<Object>> diff) {
        StringBuilder resultStylish = new StringBuilder();
        resultStylish.append("{\n");
        for (Map.Entry<String, ValueInfo<Object>> entry : diff.entrySet()) {
            ValueInfo<Object> valueInfo = entry.getValue();
            switch (valueInfo.getStatus()) {
                case DELETED -> resultStylish.append(" ".repeat(2)).append("- ").append(entry.getKey())
                        .append(": ")
                        .append(valueInfo.getOldValue())
                        .append("\n");
                case ADDED -> resultStylish.append(" ".repeat(2)).append("+ ").append(entry.getKey()).append(": ")
                        .append(valueInfo.getNewValue())
                        .append("\n");
                case CHANGED -> {
                    resultStylish.append(" ".repeat(2)).append("- ").append(entry.getKey()).append(": ")
                            .append(valueInfo.getOldValue())
                            .append("\n");
                    resultStylish.append(" ".repeat(2)).append("+ ").append(entry.getKey()).append(": ")
                            .append(valueInfo.getNewValue())
                            .append("\n");
                }
                default -> resultStylish.append(" ".repeat(2)).append("  ").append(entry.getKey()).append(": ")
                        .append(valueInfo.getOldValue())
                        .append("\n");
            }
        }
        resultStylish.append("}");
        return resultStylish.toString();
    }
}
