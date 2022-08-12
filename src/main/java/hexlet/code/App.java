package hexlet.code;

import hexlet.code.serivces.ProjectInfoConst;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


@Command(name = ProjectInfoConst.NAME, mixinStandardHelpOptions = true,
        version = ProjectInfoConst.NAME + " " + ProjectInfoConst.VERSION,
        description = ProjectInfoConst.DESCRIPTION)
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String pathToFirstFile;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String pathToSecondFile;

    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        String diff = Differ.generate(pathToFirstFile, pathToSecondFile, format);
        System.out.println(diff);
        return 0;
    }
}



