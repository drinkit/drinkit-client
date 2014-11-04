package ua.kiev.naiv.drinkit.cocktail.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.kiev.naiv.drinkit.cocktail.service.FileStoreService;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 02.11.2014
 * Time: 14:07
 */
@Service
@Primary
@Profile("test")
public class MockFileStoreService implements FileStoreService {


    private String mockFileName;

    public MockFileStoreService() {
        mockFileName = "defaultFileName.ext";
    }

    @Override
    public String save(int recipeId, byte[] thumbnail, String thumbnail1) throws IOException {
        return mockFileName;
    }
}
