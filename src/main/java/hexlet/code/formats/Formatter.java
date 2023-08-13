package hexlet.code.formats;

import hexlet.code.serivces.ValueInfo;

import java.util.Map;

public interface Formatter {

    String LINE_SEPARATOR = System.lineSeparator();

    String toFormat(Map<String, ValueInfo> diff);
}
