package ch.heigvd.statique;

import ch.heigvd.statique.command.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "statique",
    description = "A brand new static site generator.",
    subcommands = {Init.class, Clean.class, Build.class, Serve.class, Version.class})
public class Statique implements Callable<Integer>
{
  public static final String SEPARATOR = "/";

  public static void main(String... args) {
    int exitCode = new CommandLine(new Statique()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    CommandLine.usage(this, System.out);
    return 0;
  }
}