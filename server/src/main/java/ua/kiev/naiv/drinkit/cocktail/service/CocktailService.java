package ua.kiev.naiv.drinkit.cocktail.service;

import ua.kiev.naiv.drinkit.cocktail.model.Cocktail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:52
 */
public interface CocktailService {

    Cocktail create(Cocktail cocktail);

    Cocktail delete(int id);

    List<Cocktail> findAll();

    Cocktail update(Cocktail cocktail);

    Cocktail findById(int id);
}
