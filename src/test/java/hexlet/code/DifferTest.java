package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DifferTest {

    @Test
    void testWhereBothFileJsonFormat() {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/second.json";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        String exceptedResult = """
                {
                - follow: false
                  host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                }""";
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void testWhereBothJsonFilesAreTheSame() {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/first.json";
        String exceptedResult = """
                {
                  follow: false
                  host: hexlet.io
                  proxy: 123.234.53.22
                  timeout: 50
                }""";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void testWhereFileFormatIsNotEqualToJson() {
        var pathToFirstFile = "src/test/resources/first.jsons";
        var pathToSecondFile = "src/test/resources/first.json";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFile, pathToSecondFile))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("incorrect file format");
    }

    @Test
    void testWhereBothJsonFilesIsEmpty() {
        var pathToFirstFile = "src/test/resources/empty.json";
        var pathToSecondFile = "src/test/resources/empty.json";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        assertThat(result).isNull();
    }

    @Test
    void testWhereFirstFileIsEmpty() {
        var pathToFirstFile = "src/test/resources/empty.json";
        var pathToSecondFile = "src/test/resources/first.json";
        String exceptedResult = """
                {
                + follow: false
                + host: hexlet.io
                + proxy: 123.234.53.22
                + timeout: 50
                }""";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void testWhereSecondFileIsEmpty() {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/empty.json";
        String exceptedResult = """
                {
                - follow: false
                - host: hexlet.io
                - proxy: 123.234.53.22
                - timeout: 50
                }""";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        assertThat(result).isEqualTo(exceptedResult);
    }
}
