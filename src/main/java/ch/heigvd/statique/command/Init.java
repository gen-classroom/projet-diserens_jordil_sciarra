package ch.heigvd.statique.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

import ch.heigvd.statique.Statique;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

    public static class metaFileGson{
        public final String title, authors, creationDate;

        public metaFileGson(String title, String authors, String creationDate)
        {
            this.title = title;
            this.authors = authors;
            this.creationDate = creationDate;
        }
    }

  public static class MyConfigGson{
      public final String url, title, authors, date, filesPath, buildPath;

      public MyConfigGson(String url, String title, String authors, String date, String filesPath, String buildPath)
      {
          this.url = url;
          this.title = title;
          this.authors = authors;
          this.date = date;
          this.filesPath = filesPath;
          this.buildPath = buildPath;
      }
  }

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
              String json = new GsonBuilder().setPrettyPrinting().create().toJson(new MyConfigGson(pathStr,"Website Generator",
                      "Diserens Lois, Jordil Kevin, Sciarra Daniel",
                      String.valueOf(java.time.LocalDate.now()),
                      "./contenuMD", "./build"));


              Writer writer = Files.newBufferedWriter(Paths.get(configFile.getPath()));

              writer.write(json);

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

          File metaIndexFile = new File(path + Statique.SEPARATOR + "index.json");
          if (metaIndexFile.createNewFile())
          {
              System.out.println("File created: " + metaIndexFile.getName());
              String json = new GsonBuilder().setPrettyPrinting().create().toJson(new metaFileGson("Accueil",
                                                                                "Diserens Lois, Jordil Kevin, Sciarra Daniel",
                                                                                        String.valueOf(java.time.LocalDate.now())));

              Writer writer = Files.newBufferedWriter(Paths.get(metaIndexFile.getPath()));
              writer.write(json);
              writer.close();
          }
          else
          {
              System.out.println("File already exists("+ metaIndexFile.getName() +").");
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