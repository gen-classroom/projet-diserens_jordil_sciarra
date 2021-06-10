package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InitTest
{
    @Test
    void shouldInitCorrectly() throws IOException {
        // Test la création du config.json, index.md et index.json
        String pathStr = "./monSite";

        // Appel à la commande à tester
        new CommandLine(new Statique()).execute("init", pathStr);

        boolean result = false;

        File configJson = new File(pathStr + "/config.json");
        File indexMd = new File(pathStr + "/index.md");
        File indexJson = new File(pathStr + "/index.json");

        if (configJson.exists() && indexMd.exists() && indexJson.exists()){
            result = true;
        }
        assertTrue(result);

        // Supprimme l'arborscence de test
        FileUtils.deleteDirectory(new File("./monSite"));
    }
}