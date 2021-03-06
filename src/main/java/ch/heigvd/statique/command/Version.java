package ch.heigvd.statique.command;

import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Callable;

@Command(name = "-version", description = "Get version of static site generator")
public class Version implements Callable<Integer> {

  @Override public Integer call() throws IOException {
    final Properties properties = new Properties();
    properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
    System.out.println(properties.getProperty("version"));
    return 0;
  }

}