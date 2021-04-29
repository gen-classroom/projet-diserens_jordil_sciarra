package ch.heigvd.statique.command;

import org.apache.commons.io.FileUtils;
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
          e.printStackTrace();
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
          e.printStackTrace();
      }

      return 0;
  }

}