package ch.heigvd.statique.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.Callable;

import ch.heigvd.statique.Statique;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Path of the root directory for the website") String pathStr;

  @Override public Integer call()
  {
      // Creation du chemin de fichier
      Path path = Paths.get(pathStr);
      try
      {
          Files.createDirectories(path);
      }
      catch (IOException e)
      {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }

      // Creation du fichier de config
      try
      {
          File configFile = new File(path + Statique.SEPARATOR + "config.json");
          if (configFile.createNewFile())
          {
              System.out.println("File created: " + configFile.getName());

              // Ecriture des éléments de base
              String contenuConfig = "" +
                      "{\n"+
                      "    \"url\": \""+ pathStr + "\",\n"+
                      "    \"Title\": \"Website Generator\",\n"+
                      "    \"Author(s)\": \"Diserens Loïs, Jordil Kevin, Sciarra Daniel\",\n"+
                      "    \"Creation date\": \""+ java.time.LocalDate.now() + "\",\n"+
                      "    \"Dossier des fichiers md:\"./contenuMD\",\n"+
                      "    \"Dossier du site généré:\"./build\"\n"+
                      "}";

              FileWriter writer = new FileWriter(configFile.getPath());
              writer.write(contenuConfig);
              writer.close();
          }
          else
          {
              System.out.println("File already exists("+ configFile.getName() +").");
          }
      }
      catch (IOException e)
      {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }

      // Creation de la page d'accueil
      try
      {
          File indexFile = new File(path + Statique.SEPARATOR +"index.md");
          if (indexFile.createNewFile())
          {
              System.out.println("File created: " + indexFile.getName());

              // Ecriture des éléments de base
              String contenuIndex = "" +
                      "titre: Acceuil\n" +
                      "auteur: Diserens Lois\n" +
                      "date de creation: 2021-18-03\n" +
                      "---\n" +
                      "# Accueil\n" +
                      "## Bienvenue\n" +
                      "Bienvenue sur cette page générée automatiquement";

              FileWriter writer = new FileWriter(indexFile.getPath());
              writer.write(contenuIndex);
              writer.close();

          }
          else
          {
              System.out.println("File already exists("+ indexFile.getName() +").");
          }
      }
      catch (IOException e)
      {
          System.out.println("An error occurred.");
          e.printStackTrace();
      }

      return 1;
  }

}