package ch.heigvd.statique.command;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import ch.heigvd.statique.Statique;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "serve", description = "Serve a static site")
public class Serve implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the build directory for the website") Path pathStr;

  @Override public Integer call()
  {
      try
      {
          File fileToOpen = new File(pathStr + Statique.SEPARATOR + "build" + Statique.SEPARATOR + "index.html");
          Desktop.getDesktop().open(fileToOpen);
      } catch (IOException e)
      {
          e.printStackTrace();
      }

      return 0;
  }

}