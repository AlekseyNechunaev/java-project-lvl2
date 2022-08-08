package hexlet.code;

import hexlet.code.serivces.ProjectInfoConst;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;


@Command(name = ProjectInfoConst.NAME, mixinStandardHelpOptions = true,
        version = ProjectInfoConst.NAME + " " + ProjectInfoConst.VERSION,
        description = ProjectInfoConst.DESCRIPTION)
public class App implements Runnable {

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File pathToFirstFile;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File pathToSecondFile;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
    public static void main(String[] args) {
         int exitCode = new CommandLine(new App()).execute(args);
         System.exit(exitCode);
    }


    @Override
    public void run() {

    }
}
