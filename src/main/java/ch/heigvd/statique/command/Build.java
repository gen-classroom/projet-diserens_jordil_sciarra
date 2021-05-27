package ch.heigvd.statique.command;

import ch.heigvd.statique.utils.TemplateEngine;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Callable;

@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Path of the build directory for the website") Path pathStr;
    @CommandLine.Option(names = "--watch", description = "Watch option", negatable = true) Boolean isWatchDemanded = false;

    @Override
    public Integer call() {

        // Creation du chemin de fichier
        pathStr = pathStr.resolve("build");
        try {
            Files.createDirectories(pathStr);
            buildSite(new File(String.valueOf(pathStr.getParent())), String.valueOf(pathStr));

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
                        File toDelete = new File(String.valueOf(pathStr) + event.context());

                        if(event.kind() != StandardWatchEventKinds.ENTRY_DELETE) {
                            FileUtils.deleteQuietly(toDelete);
                            TemplateEngine.generatePage((File) event.context(), String.valueOf(pathStr));
                        }
                    }
                    key.reset();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void buildSite(File rootDirectory, String buildPath) throws IOException {

        if (rootDirectory != null) {
            File[] listFiles = rootDirectory.listFiles();
            if (listFiles != null)
                for (File file : listFiles) {
                    String fileName = file.getName();
                    // build récursif sur les dossiers (sauf dossier build et template)
                    if (file.isDirectory() && !fileName.equals("build") && !fileName.equals("template")) {
                        Path newPath = Paths.get(buildPath + "/" + fileName);
                        Files.createDirectories(newPath);
                        buildSite(file, String.valueOf(newPath));
                    }
                    // génère la page si c'est un fichier md
                    else if (FilenameUtils.getExtension(fileName).equals("md"))
                        TemplateEngine.generatePage(file, buildPath);
                        // copie les autres fichiers (.json exclus)
                    else if (!FilenameUtils.getExtension(fileName).equals("json") && !file.isDirectory())
                        FileUtils.copyFile(file, new File(buildPath + "/" + fileName));
                }
        }
    }
}