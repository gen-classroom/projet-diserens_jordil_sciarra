package ch.heigvd.statique.command;

import ch.heigvd.statique.Statique;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServeTest
{
    @Ignore("Test is ignored because GitHub doesn t have a desktop")
    @Test
    public void shouldReturnCorrectExitCode() throws IOException {
        // Créé une arborscence de test
        String pathStr = "./monSite";
        new CommandLine(new Statique()).execute("init", pathStr);
        new CommandLine(new Statique()).execute("build", pathStr);

        int result = new CommandLine(new Statique()).execute("serve", "./monSite");
        assertEquals(0, result);

        // Supprimme l'arborscence de test
        FileUtils.deleteDirectory(new File("./monSite"));
    }
}
