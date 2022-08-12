package hexlet.code;

import hexlet.code.formats.JsonFormatter;
import hexlet.code.formats.PlainFormatter;
import hexlet.code.formats.StylishFormatter;
import hexlet.code.serivces.ValueInfo;

import java.util.Map;

public class Formatter {

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static String toFormat(Map<String, ValueInfo> diff, String format) {
        switch (format.toLowerCase()) {
            case STYLISH -> {
                return StylishFormatter.diffToStylish(diff);
            }
            case PLAIN -> {
                return PlainFormatter.diffToPlain(diff);
            }
            case JSON -> {
                return JsonFormatter.diffToJson(diff);
            }
            default -> throw new RuntimeException("unknown format");
        }
    }
}
