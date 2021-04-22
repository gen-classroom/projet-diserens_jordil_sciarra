package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ServeTest
{
    public void shouldReturnCorrectExitCode() throws IOException {
        // Créé une arborscence de test
        String pathStr = "./mon/site";
        File indexHTML= new File(pathStr + "/index.html");

        int result = new CommandLine(new Statique()).execute("./mon/site/index.html");
        assertEquals(result, 0);

        // Supprimme l'arborscence de test
        FileUtils.deleteDirectory(new File("./mon"));
    }
}