package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DifferTest {

    @Test
    void differTestBothFileJsonFormat() {
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
    void differTestBothJsonFilesAreTheSame() {
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
    void differTestFileFormatIsNotEqualToJsonOrYaml() {
        var pathToFirstFileJson = "src/test/resources/first.jsons";
        var pathToSecondFileJson = "src/test/resources/first.json";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFileJson, pathToSecondFileJson))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("incorrect file format");
        var pathToFirstFileYaml = "src/test/resources/first.yaml";
        var pathToSecondFileYaml = "src/test/resources/first.yml";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFileYaml, pathToSecondFileYaml))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("incorrect file format");
    }

    @Test
    void differTestFirstFileIsEmpty() {
        var pathToFirstFile = "src/test/resources/empty.yaml";
        var pathToSecondFile = "src/test/resources/first.json";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFile, pathToSecondFile))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("There is no content in the file");
    }

    @Test
    void differTestWhereFirstJsonAndSecondYaml() {
        var pathToFirstFile = "src/test/resources/first.yaml";
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
    void differTestWhereBothFilesYamlFormat() {
        var pathToFirstFile = "src/test/resources/first.yaml";
        var pathToSecondFile = "src/test/resources/second.yaml";
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
}
