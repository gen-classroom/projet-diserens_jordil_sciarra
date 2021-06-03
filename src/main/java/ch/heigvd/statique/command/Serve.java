package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import ch.heigvd.statique.utils.TemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Callable;

@Command(name = "serve", description = "Serve a static site")
public class Serve implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the build directory for the website") Path pathStr;
    @CommandLine.Option(names = "--watch", description = "Watch option", negatable = true) Boolean isWatchDemanded = false;

    final static Logger logger = LogManager.getLogger(TemplateEngine.class.getName());

    @Override public Integer call()
  {
      try
      {
          File fileToOpen = new File(pathStr + Statique.SEPARATOR + "build" + Statique.SEPARATOR + "index.html");
          Desktop.getDesktop().open(fileToOpen);

          // Si l'option n'est pas entrée ou mal entrée, ne la prend pas en compte
          if(isWatchDemanded)
          {
              WatchService watchService = FileSystems.getDefault().newWatchService();

              pathStr.register(
                      watchService,
                      StandardWatchEventKinds.ENTRY_CREATE,
                      StandardWatchEventKinds.ENTRY_DELETE,
                      StandardWatchEventKinds.ENTRY_MODIFY);

              WatchKey key;
              while ((key = watchService.take()) != null) {
                  for (WatchEvent<?> event : key.pollEvents()) {
                      // Action a faire quand un event est déclenché
                      File fileUpdatedToOpen = new File(pathStr + Statique.SEPARATOR + "build" + Statique.SEPARATOR + event.context());
                      Desktop.getDesktop().open(fileUpdatedToOpen);
                  }
                  key.reset();
              }
          }
      } catch (IOException | InterruptedException e)
      {
          logger.fatal("Context : ", e);
      }

      return 0;
  }

}