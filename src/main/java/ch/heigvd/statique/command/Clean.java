package ch.heigvd.statique.command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import ch.heigvd.statique.Statique;
import org.apache.commons.io.FileUtils;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the root directory for the website") String pathStr;

  @Override public Integer call() {

    try
    {
      FileUtils.deleteDirectory(new File(pathStr + Statique.SEPARATOR + "build"));
    }
    catch (IOException e)
    {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


    System.out.printf("cleaning finished !");

    return 0;
  }

}