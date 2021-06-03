package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import ch.heigvd.statique.utils.TemplateEngine;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the root directory for the website") String pathStr;

  final static Logger logger = LogManager.getLogger(TemplateEngine.class.getName());

  @Override public Integer call() {

    try
    {
      FileUtils.deleteDirectory(new File(pathStr + Statique.SEPARATOR + "build"));
      System.out.printf("cleaning finished !");
    }
    catch (IOException e)
    {
      System.out.println("An error occurred.");
      logger.fatal("Context : ", e);

      return 1;
    }

    return 0;
  }

}