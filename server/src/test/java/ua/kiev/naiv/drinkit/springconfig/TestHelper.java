package ua.kiev.naiv.drinkit.springconfig;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 26.07.13
 * Time: 1:02
 */
public class TestHelper {
    public static String JSON_PATH="json/";

    public File createIfNotExistJsonFileRequest(String fileName) throws IOException {
        return createIfNotExistJsonFile(fileName, "request");
    }

    public File createIfNotExistJsonFileResponse(String fileName) throws IOException {
        return createIfNotExistJsonFile(fileName, "response");
    }

    private File createIfNotExistJsonFile(String fileName, String dir) throws IOException {
        File file = new File(JSON_PATH + dir + "/" + fileName + ".json");
        System.out.println(file.getAbsolutePath());
        file.createNewFile();
        return file;
    }
}
