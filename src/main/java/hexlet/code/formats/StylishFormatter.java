package hexlet.code.formats;

import hexlet.code.serivces.ValueInfo;

import java.util.Map;

public class StylishFormatter implements Formatter {

    @Override
    public String toFormat(Map<String, ValueInfo> diff) {
        StringBuilder resultStylish = new StringBuilder();
        resultStylish.append("{").append(LINE_SEPARATOR);
        for (Map.Entry<String, ValueInfo> entry : diff.entrySet()) {
            ValueInfo valueInfo = entry.getValue();
            switch (valueInfo.getStatus()) {
                case DELETED -> resultStylish.append(" ".repeat(2)).append("- ").append(entry.getKey())
                        .append(": ")
                        .append(valueInfo.getOldValue());
                case ADDED -> resultStylish.append(" ".repeat(2)).append("+ ").append(entry.getKey()).append(": ")
                        .append(valueInfo.getNewValue());
                case CHANGED -> {
                    resultStylish.append(" ".repeat(2)).append("- ").append(entry.getKey()).append(": ")
                            .append(valueInfo.getOldValue())
                                    .append(LINE_SEPARATOR);
                    resultStylish.append(" ".repeat(2)).append("+ ").append(entry.getKey()).append(": ")
                            .append(valueInfo.getNewValue());
                }
                default -> resultStylish.append(" ".repeat(2)).append("  ").append(entry.getKey()).append(": ")
                        .append(valueInfo.getOldValue());
            }
            resultStylish.append(LINE_SEPARATOR);
        }
        resultStylish.append("}");
        return resultStylish.toString();
    }
}
