package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.service.FileStoreService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 02.11.2014
 * Time: 14:01
 */
@Service
public class LocalFSFileStoreService implements FileStoreService {
    @Resource
    Environment environment;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public String save(int recipeId, byte[] media, String mediaType) throws IOException {
        long time = new Date().getTime();
        String folderName = environment.getProperty("media.folder") + File.separator + mediaType;
        String fileName = recipeId + "_" + time + ".jpg";
        String filePath = folderName + File.separator + fileName;

        File folder = new File(folderName);
        folder.mkdirs();
        File[] files = folder.listFiles((dir, name) -> name.startsWith(recipeId + "_"));
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(media);
        }
        return fileName;
    }
}
