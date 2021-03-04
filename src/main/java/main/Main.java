package main;


import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "pgen")
public class Main implements Callable<Integer> {

    @CommandLine.Command(name = "new")
    public void cnew() {
        System.out.println("New !");
    }

    @CommandLine.Command(name = "clean")
    public Integer cclean() {
        System.out.println("Clean !");
        return 0;
    }

    @CommandLine.Command(name = "build")
    public Integer cbuild() {
        System.out.println("Build !");
        return 0;
    }

    @CommandLine.Command(name = "serve")
    public void cserve() {
        System.out.println("Serve !");
    }

    @Override
    public Integer call() {
        System.out.println("Subcommand needed: 'new', 'clean', 'build' or 'serve'");
        return 0;
    }

    public static void main(String[] args) {
        int rc = new CommandLine(new Main()).execute(args);
        System.exit(rc);
    }

}