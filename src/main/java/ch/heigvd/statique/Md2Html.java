package ch.heigvd.statique;

import org.apache.commons.io.FilenameUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public final class Md2Html {

    public static void convert(File mdFile, String destinationPath) throws IOException {

        if (!FilenameUtils.getExtension(mdFile.getName()).equals("md"))
            throw new RuntimeException("Not a md file");

        Parser parser = Parser.builder().build();
        Node document = parser.parse(Files.readString(mdFile.toPath()));
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        File output = new File(destinationPath + "/" + FilenameUtils.getBaseName(mdFile.getName()) + ".html");
        FileWriter writer = new FileWriter(output);
        writer.write(renderer.render(document));
        writer.close();
    }

}
