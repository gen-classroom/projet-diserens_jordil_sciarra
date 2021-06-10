package ch.heigvd.statique.utils;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TemplateEngine {

    private static final String CONFIG_FILE = "config/initFolder/config.json";
    private static final String MENU_FILE = "config/initFolder/template/menu.html";
    private static final String TEMPLATE_DIR = "config/initFolder/template";

    final static Logger logger = LogManager.getLogger(TemplateEngine.class.getName());

    public static void generatePage(File file, String destinationPath) throws IOException {

        FileWriter writer = null;
        BufferedReader config = null;

        try {
            // conversion du MD en HTML
            HtmlContent content = Md2Html.convert(file);

            // création de l'output HTML
            String fileName = FilenameUtils.removeExtension(file.getName());
            File output = new File(destinationPath + "/" + fileName + ".html");
            writer = new FileWriter(output, StandardCharsets.UTF_8);

            // récupération des données de configuration
            config = new BufferedReader(new FileReader(CONFIG_FILE, StandardCharsets.UTF_8));
            Map<?, ?> configFile = new Gson().fromJson(config, Map.class);

            // récupération des métadonnées
            Map<?, ?> metas = null;
            File header = new File(file.getParent() + "/" + fileName + ".json");
            if (header.exists()) {
                metas = new Gson().fromJson(Files.newBufferedReader(Paths.get(header.getPath())), Map.class);
            }

            // récupération du menu
            String menu = Files.readString(Path.of(MENU_FILE), StandardCharsets.UTF_8);

            // préparation du template avec Handlebars
            TemplateLoader loader = new FileTemplateLoader(TEMPLATE_DIR, ".html");
            Handlebars handlebars = new Handlebars(loader);
            handlebars.setPrettyPrint(true);
            Template template = handlebars.compile("layout");

            // injection avec Handlebars
            Map<String, String> parameterMap = new HashMap<>();
            parameterMap.put("siteTitre", configFile.get("title").toString());
            if (metas != null) {
                parameterMap.put("pageTitre", metas.get("title").toString());
            }
            parameterMap.put("menu", menu);
            parameterMap.put("content", content.getContent().toString());

            // écriture de l'output
            writer.write(template.apply(parameterMap));
            writer.close();
            config.close();
        }
        catch (IOException e) {
            logger.fatal("Context : ", e);
        }
        finally {
            if (writer != null)
                writer.close();
            if (config != null)
                config.close();
        }
    }
}
