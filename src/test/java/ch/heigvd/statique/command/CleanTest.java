package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CleanTest
{
    @Test
    public void shouldCleanCorrectly() throws IOException {
        // Créé une arborscence de test
        String pathStr = "./mon/site";
        Path path = Paths.get(pathStr + "/build");
        Files.createDirectories(path);

        // Appel à la commande à tester
        new CommandLine(new Statique()).execute("clean", "./mon/site/build");

        boolean result = false;
        File dossier=new File(pathStr + "/build");
        if (dossier.exists() && dossier.isDirectory()){
            result = true;
        }
        assertTrue(result);

        // Supprimme l'arborscence de test
        FileUtils.deleteDirectory(new File("./mon"));
    }
}