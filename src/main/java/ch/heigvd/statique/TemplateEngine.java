package ch.heigvd.statique;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TemplateEngine {

    private static String CONFIG_FILE = "config/initFolder/config.json";
    private static String INDEX_TITLE = "config/initFolder/index.json";
    private static String TEMPLATE_DIR = "config/initFolder/template";

    public static void generatePage(String fileName, HtmlContent content, String destinationPath) throws IOException {

        File output = new File(destinationPath + "/" + fileName + ".html");
        FileWriter writer = new FileWriter(output);
        BufferedReader config = new BufferedReader(new FileReader(CONFIG_FILE, StandardCharsets.UTF_8));
        Map configFile = new Gson().fromJson(config, Map.class);
        BufferedReader index = new BufferedReader(new FileReader(CONFIG_FILE, StandardCharsets.UTF_8));
        Map indexFile = new Gson().fromJson(index, Map.class);


        TemplateLoader loader = new ClassPathTemplateLoader("TEMPLATE_DIR", ".html");
        Handlebars handlebars = new Handlebars(loader);
        Template template = handlebars.compile("layout");

        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("siteTitre", configFile.get("title").toString());
        parameterMap.put("pageTitre", indexFile.get("title").toString());
        parameterMap.put("content", content.getContent().toString());

        writer.write(template.apply(parameterMap));
        writer.flush();
        writer.close();
    }
}
