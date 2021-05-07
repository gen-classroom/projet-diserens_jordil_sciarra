package ch.heigvd.statique.utils;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateEngineTest {

    private final Path testFolder = Paths.get("src/main/resources/test");

    @BeforeEach
    void createTestFolder() throws IOException {
        Files.createDirectories(testFolder);
    }

    @AfterEach
    void removeTestFolder() throws IOException {
        FileUtils.deleteDirectory(new File(String.valueOf(testFolder)));
    }

    @Test
    void itShouldGenerateATemplatedPage() throws IOException {

        Files.copy(Paths.get("config/initFolder/index.md"), Paths.get(testFolder + "/actual.md"));
        Files.copy(Paths.get("config/initFolder/index.json"), Paths.get(testFolder + "/actual.json"));
        TemplateEngine.generatePage(new File(testFolder + "/actual.md"), String.valueOf(testFolder));
        File actual = new File(testFolder + "/actual.html");

        // expected output
        File expected = new File(testFolder + "/expected.html");
        PrintWriter expectedWriter = new PrintWriter(new FileWriter(expected, StandardCharsets.UTF_8));
        expectedWriter.write("<html lang=\"en\">\r\n" +
                "<head>\r\n" +
                "    <meta charset=\"utf-8\">\r\n" +
                "    <title>Website Generator | Accueil</title>\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "    &lt;ul&gt;\r\n" +
                "    &lt;li&gt;&lt;a href&#x3D;&quot;/index.html&quot;&gt;home&lt;/a&gt;&lt;/li&gt;\r\n" +
                "    &lt;li&gt;&lt;a href&#x3D;&quot;/content/page.html&quot;&gt;page&lt;/a&gt;&lt;/li&gt;\r\n" +
                "&lt;/ul&gt;\r\n" +
                "    &lt;h1&gt;Accueil&lt;/h1&gt;\r\n" +
                "&lt;h2&gt;Bienvenue&lt;/h2&gt;\r\n" +
                "&lt;p&gt;Bienvenue sur cette page générée automatiquement&lt;/p&gt;\r\n" +
                "\r\n" +
                "</body>\r\n" +
                "</html>");
        expectedWriter.close();

        assertTrue(FileUtils.contentEqualsIgnoreEOL(expected, actual, "UTF-8"));
    }
}
