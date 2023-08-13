package hexlet.code.formats;

import java.util.Locale;

public final class FormatterFactory {
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static Formatter getFormatter(String formatType) {
        switch (formatType.toLowerCase(Locale.ROOT)) {
            case STYLISH -> {
                return new StylishFormatter();
            }
            case PLAIN -> {
                return new PlainFormatter();
            }
            case JSON -> {
                return new JsonFormatter();
            }
            default -> throw new RuntimeException("unknown format");
        }
    }

    private FormatterFactory() {

    }
}
