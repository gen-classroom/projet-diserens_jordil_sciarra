package ch.heigvd.statique.command;

import ch.heigvd.statique.utils.TemplateEngine;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the root directory for the website") String pathStr;

    final static Logger logger = LogManager.getLogger(TemplateEngine.class.getName());

    @Override public Integer call()
  {
      // Creation du chemin de fichier
      Path path = Paths.get(pathStr);
      try {
          Files.createDirectories(path);
      }
      catch (IOException e)
      {
          System.out.println("Error when create site folder");
          logger.fatal("Context : ", e);
      }

      // Copie du template
      try {
          File srcDir = new File("./config/initFolder");
          File destDir = new File(pathStr);

          FileUtils.copyDirectory(srcDir, destDir);
      }
      catch (IOException e)
      {
          System.out.println("Error when copy ");
          logger.fatal("Context : ", e);
      }

      return 0;
  }

}