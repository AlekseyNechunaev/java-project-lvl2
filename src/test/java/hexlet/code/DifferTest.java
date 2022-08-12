package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DifferTest {

    private static final Path RESULT_FILES_STYLISH = Paths.get("src/test/resources/result_two_files_stylish.txt");
    private static final Path RESULT_FILES_PLAIN = Paths.get("src/test/resources/result_two_files_plain.txt");
    private static final Path RESULT_FILES_JSON = Paths.get("src/test/resources/result_two_files_json.txt");

    @Test
    void differTestBothFileJsonStylish() throws IOException {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/second.json";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile, "stylish");
        String exceptedResult = Files.readString(RESULT_FILES_STYLISH);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void differTestFileFormatIsNotEqualToJsonOrYaml() {
        var pathToFirstFileJson = "src/test/resources/first.jsons";
        var pathToSecondFileJson = "src/test/resources/first.json";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFileJson, pathToSecondFileJson, "stylish"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("incorrect file format");
        var pathToFirstFileYaml = "src/test/resources/first.yaml";
        var pathToSecondFileYaml = "src/test/resources/first.yml";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFileYaml, pathToSecondFileYaml, "stylish"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("incorrect file format");
    }

    @Test
    void differTestFirstFileIsEmpty() {
        var pathToFirstFile = "src/test/resources/empty.yaml";
        var pathToSecondFile = "src/test/resources/first.json";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFile, pathToSecondFile, "stylish"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("There is no content in the file");
    }

    @Test
    void differTestWhereFirstJsonAndSecondYamlStylish() throws IOException {
        var pathToFirstFile = "src/test/resources/first.yaml";
        var pathToSecondFile = "src/test/resources/second.json";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        String exceptedResult = Files.readString(RESULT_FILES_STYLISH);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void differTestWhereBothFilesYamlFormatStylish() throws IOException {
        var pathToFirstFile = "src/test/resources/first.yaml";
        var pathToSecondFile = "src/test/resources/second.yaml";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile);
        String exceptedResult = Files.readString(RESULT_FILES_STYLISH);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void differTestFormatPlain() throws IOException {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/second.yaml";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile, "plain");
        String exceptedResult = Files.readString(RESULT_FILES_PLAIN);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void differTestFormatJson() throws IOException {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/second.yaml";
        String result = Differ.generate(pathToFirstFile, pathToSecondFile, "json");
        String exceptedResult = Files.readString(RESULT_FILES_JSON);
        assertThat(result).isEqualTo(exceptedResult);
    }

    @Test
    void differTestUnknownFormat() {
        var pathToFirstFile = "src/test/resources/first.json";
        var pathToSecondFile = "src/test/resources/second.yaml";
        assertThatThrownBy(() -> Differ.generate(pathToFirstFile, pathToSecondFile, "unknown")).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("unknown format");
    }
}
