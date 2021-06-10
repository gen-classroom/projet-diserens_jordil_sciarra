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


class VersionTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void shouldReturnCorrectVersionOfApplication() {
        new CommandLine(new Statique()).execute("-version");
        String result = outputStreamCaptor.toString().trim();

        Pattern pattern = Pattern.compile("\\d*\\.\\d*\\.\\d*.*");
        Matcher matcher = pattern.matcher(result);
        assertTrue(matcher.matches());
    }

    @Test
    void shouldReturnCorrectExitCode() {
        int result = new CommandLine(new Statique()).execute("-version");
        assertEquals(0, result);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

}
