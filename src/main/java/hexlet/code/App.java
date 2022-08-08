package hexlet.code;

import hexlet.code.serivces.ProjectInfoConst;
import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command(name = ProjectInfoConst.NAME, mixinStandardHelpOptions = true,
        version = ProjectInfoConst.NAME + " " + ProjectInfoConst.VERSION,
        description = ProjectInfoConst.DESCRIPTION)
public class App implements Runnable {
    public static void main(String[] args) {
         int exitCode = new CommandLine(new App()).execute(args);
         System.exit(exitCode);
    }


    @Override
    public void run() {

    }
}
