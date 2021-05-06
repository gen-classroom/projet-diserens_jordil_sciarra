package ch.heigvd.statique;

import org.apache.commons.io.FilenameUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class Md2Html {

    public static HtmlContent convert(File mdFile) throws IOException {

        // vérification qu'on a bien un fichier md
        if (!FilenameUtils.getExtension(mdFile.getName()).equals("md"))
            throw new RuntimeException("Not a md file");

        // Cération du parser md et render html
        Parser parser = Parser.builder().build();
        Node document = parser.parse(Files.readString(mdFile.toPath()));
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        HtmlContent htmlContent = new HtmlContent();
        // écriture du contenu md en html
        htmlContent.append(renderer.render(document));

        return htmlContent;
    }
}
