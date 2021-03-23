package ch.heigvd.statique.command;


import ch.heigvd.statique.Statique;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        assertEquals("0.0.1-SNAPSHOT", result);
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
