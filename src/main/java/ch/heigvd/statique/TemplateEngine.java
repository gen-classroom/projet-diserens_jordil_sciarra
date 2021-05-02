package ch.heigvd.statique;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class TemplateEngine {

    private static String CONFIG_FILE = "config/initFolder/config.json";
//    private static String INDEX_FILE = "config/initFolder/index.json";
    private static String MENU_FILE = "config/initFolder/template/menu.html";
    private static String TEMPLATE_DIR = "config/initFolder/template";

//    public static void generatePage(String fileName, HtmlContent content, String destinationPath) throws IOException {
    public static void generatePage(File file, HtmlContent content, String destinationPath) throws IOException {

        String fileName = FilenameUtils.removeExtension(file.getName());
        File output = new File(destinationPath + "/" + fileName + ".html");
        FileWriter writer = new FileWriter(output);
        BufferedReader config = new BufferedReader(new FileReader(CONFIG_FILE, StandardCharsets.UTF_8));
        Map configFile = new Gson().fromJson(config, Map.class);

        Map<?, ?> metas = null;
        File header = new File(file.getParent() + "/" + fileName + ".json");
//        File header = new File("./" + fileName + ".json");
        if (header.exists()) {
            metas = new Gson().fromJson(Files.newBufferedReader(Paths.get(header.getPath())), Map.class);
        }
//        BufferedReader index = new BufferedReader(new FileReader(INDEX_FILE, StandardCharsets.UTF_8));
//        Map indexFile = new Gson().fromJson(index, Map.class);
        // ou extraction à partir du HtmlContent ?

        String menu = Files.readString(Path.of(MENU_FILE), StandardCharsets.UTF_8);

        TemplateLoader loader = new FileTemplateLoader(TEMPLATE_DIR, ".html");
        Handlebars handlebars = new Handlebars(loader);
        handlebars.setPrettyPrint(true);
        Template template = handlebars.compile("layout");

        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("siteTitre", configFile.get("title").toString());
        if (metas != null) {
            parameterMap.put("pageTitre", metas.get("title").toString());
        }
        parameterMap.put("menu", menu);
        parameterMap.put("content", content.getContent().toString());

        writer.write(template.apply(parameterMap));
        writer.flush();
        writer.close();
    }
}
