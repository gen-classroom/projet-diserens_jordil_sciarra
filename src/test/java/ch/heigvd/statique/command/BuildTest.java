package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildTest
{
    @Test
    public void shouldBuildCorrectly() throws IOException {
        // Test la création du config.json, index.md et index.json
        String pathStr = "./monSite";
        new CommandLine(new Statique()).execute("init", pathStr);

        // Appel à la commande à tester
        new CommandLine(new Statique()).execute("build", pathStr);

        boolean result = false;

        File configJson = new File(pathStr + "/build/config.json");
        File indexMd = new File(pathStr + "/build/index.md");
        File indexHtml = new File(pathStr + "/build/index.html");
        File indexJson = new File(pathStr + "/build/index.json");
        File templateFolder = new File(pathStr + "/build/templateFolder");

        if (indexHtml.exists() && !configJson.exists() && !indexMd.exists() && !indexJson.exists() && !templateFolder.exists()){
            result = true;
        }
        assertTrue(result);

        // Supprimme l'arborscence de test
        FileUtils.deleteDirectory(new File("./monSite"));
    }
}