package ch.heigvd.statique;

import org.apache.commons.io.FilenameUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public final class Md2Html
{
    public static void convert(File mdFile, String destinationPath) throws IOException {

        if (!FilenameUtils.getExtension(mdFile.getName()).equals("md"))
            throw new RuntimeException("Not a md file");

        Parser parser = Parser.builder().build();
        Node document = parser.parse(Files.readString(mdFile.toPath()));
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        File json = new File(mdFile.getParent() + "/" + FilenameUtils.removeExtension(mdFile.getName()) + ".json");

        File output = new File(destinationPath + "/" + FilenameUtils.getBaseName(mdFile.getName()) + ".html");
        FileWriter writer = new FileWriter(output);
        // write html header -> writer.write(ObjHtml.toString)
        if (json.exists()) {
            Node jsonNode = parser.parse(Files.readString(json.toPath()));
            writer.write(renderer.render(document));
        }
        writer.write(renderer.render(document));
        // write html footer
        writer.close();
    }

}
