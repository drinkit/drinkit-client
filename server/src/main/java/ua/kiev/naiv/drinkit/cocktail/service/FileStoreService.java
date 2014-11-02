package ua.kiev.naiv.drinkit.cocktail.service;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 02.11.2014
 * Time: 14:00
 */
public interface FileStoreService {
    String save(int recipeId, byte[] thumbnail, String thumbnail1) throws IOException;
}
