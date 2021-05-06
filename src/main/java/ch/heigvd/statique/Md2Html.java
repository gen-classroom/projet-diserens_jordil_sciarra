package ch.heigvd.statique;

import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public final class Md2Html {

    public static HtmlContent convert(File mdFile) throws IOException {

        if (!FilenameUtils.getExtension(mdFile.getName()).equals("md"))
            throw new RuntimeException("Not a md file");

        Parser parser = Parser.builder().build();
        Node document = parser.parse(Files.readString(mdFile.toPath()));
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        // remplacer par HtmlContent
        HtmlContent htmlContent = new HtmlContent();
        htmlContent.append("<!DOCTYPE html>\n<html>\n");

        // write html header if provided
        File header = new File(mdFile.getParent() + "/" + FilenameUtils.removeExtension(mdFile.getName()) + ".json");
        if (header.exists()) {
            Map<?, ?> metas = new Gson().fromJson(Files.newBufferedReader(Paths.get(header.getPath())), Map.class);
            htmlContent.append("<head>\n");
            metas.forEach((name, content) -> {
                if (name.equals("title"))
                    htmlContent.append("<title>" + content + "</title>\n");
                else
                    htmlContent.append("<meta name=\"" + name + "\" content=\"" + content + "\">\n");
            });
            htmlContent.append("</head>\n");
        }

        // write md content
        htmlContent.append("<body>\n" + renderer.render(document) + "</body>\n");

        htmlContent.append("</html>\n");
        return htmlContent;
    }

}
