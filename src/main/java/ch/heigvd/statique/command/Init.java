package ch.heigvd.statique.command;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the root directory for the website") String path;

  @Override public Integer call() {
    System.out.printf("init");
    return 1;
  }

}