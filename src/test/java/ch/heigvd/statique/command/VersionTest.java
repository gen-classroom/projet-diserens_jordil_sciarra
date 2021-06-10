package ch.heigvd.statique.command;


import ch.heigvd.statique.Statique;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class VersionTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void shouldReturnCorrectVersionOfApplication() {
        new CommandLine(new Statique()).execute("-version");
        String result = outputStreamCaptor.toString().trim();

        Pattern pattern = Pattern.compile("\\d*\\.\\d*\\.\\d*.*");
        Matcher matcher = pattern.matcher(result);
        assertTrue(matcher.matches());
    }

    @Test
    public void shouldReturnCorrectExitCode() {
        int result = new CommandLine(new Statique()).execute("-version");
        assertEquals(result, 0);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
