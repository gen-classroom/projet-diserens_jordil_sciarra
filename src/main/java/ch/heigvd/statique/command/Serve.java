package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import ch.heigvd.statique.utils.TemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "serve", description = "Serve a static site")
public class Serve implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the build directory for the website") Path pathStr;

    final static Logger logger = LogManager.getLogger(TemplateEngine.class.getName());

    @Override public Integer call()
  {
      try
      {
          File fileToOpen = new File(pathStr + Statique.SEPARATOR + "build" + Statique.SEPARATOR + "index.html");
          Desktop.getDesktop().open(fileToOpen);
      } catch (IOException e)
      {
          logger.fatal("Context : ", e);
      }

      return 0;
  }

}