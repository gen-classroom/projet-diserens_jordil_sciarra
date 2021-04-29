package ch.heigvd.statique;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class TemplateEngine {

    public static void generatePage(String fileName, HtmlContent content, String destinationPath) {

        File output = new File(destinationPath + "/" + fileName + ".html");

    }
}
