package ch.heigvd.statique.command;

import ch.heigvd.statique.Md2Html;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    private static final String PATH = "src/main/resources/site";

    @Override
    public Integer call() {

        File buildDir = new File(PATH + "/build");
        buildDir.mkdir();

        try {
            buildSite(new File(PATH), buildDir.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    private void buildSite(File rootDirectory, String buildPath) throws IOException {

        if (rootDirectory != null) {
            File[] listFiles = rootDirectory.listFiles();
            if (listFiles != null)
                for (File file : listFiles) {
                    String fileName = file.getName();
                    if (file.isDirectory() && !fileName.equals("build")) {
                        File newDir = new File(buildPath + "/" + fileName);
                        newDir.mkdir();
                        buildSite(file, newDir.getPath());
                    } else if (FilenameUtils.getExtension(fileName).equals("md"))
                        Md2Html.convert(file, buildPath);
                    else if (!FilenameUtils.getExtension(fileName).equals("json") && !file.isDirectory())
                        FileUtils.copyFile(file, new File(buildPath + "/" + fileName));
                }
        }
    }
}