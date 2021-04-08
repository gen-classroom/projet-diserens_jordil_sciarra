package ch.heigvd.statique;

import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public final class Md2Html {

    public static void convert(File mdFile, String destinationPath) throws IOException {

        if (!FilenameUtils.getExtension(mdFile.getName()).equals("md"))
            throw new RuntimeException("Not a md file");

        Parser parser = Parser.builder().build();
        Node document = parser.parse(Files.readString(mdFile.toPath()));
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        File output = new File(destinationPath + "/" + FilenameUtils.getBaseName(mdFile.getName()) + ".html");
        FileWriter writer = new FileWriter(output);
        writer.write("<!DOCTYPE html>\n<html>\n");

        // write html header if provided
        File header = new File(mdFile.getParent() + "/" + FilenameUtils.removeExtension(mdFile.getName()) + ".json");
        if (header.exists()) {
            Map<?, ?> metas = new Gson().fromJson(Files.newBufferedReader(Paths.get(header.getPath())), Map.class);
            writer.write("<head>\n");
            metas.forEach((name, content)  -> {
                try {
                    if(name.equals("title"))
                        writer.write("<title>" + content + "</title>\n");
                    else
                        writer.write("<meta name=\"" + name + "\" content=\"" + content + "\">\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.write("</head>\n");
        }

        // write md content
        writer.write("<body>\n" + renderer.render(document) + "</body>\n");

        writer.write("</html>\n");
        writer.flush();
        writer.close();
    }

}
