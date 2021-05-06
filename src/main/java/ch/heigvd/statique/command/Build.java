package ch.heigvd.statique.command;

import ch.heigvd.statique.TemplateEngine;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Path of the build directory for the website") Path pathStr;

    @Override
    public Integer call() {

        // Creation du chemin de fichier
        pathStr = pathStr.resolve("build");
        try {
            Files.createDirectories(pathStr);
            buildSite(new File(String.valueOf(pathStr.getParent())), String.valueOf(pathStr));
        } catch (IOException e) {
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