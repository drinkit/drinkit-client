package ua.kiev.naiv.drinkit.cocktail.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;
import ua.kiev.naiv.drinkit.cocktail.web.model.Recipe;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:52
 */
public interface RecipeService {

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    int save(Recipe recipe);

    @PreAuthorize("hasRole(ROLE_ADMIN)")
    void delete(int id);

    List<Recipe> findAll();

    List<Recipe> findByCriteria(Criteria criteria);

    Recipe getRecipeById(int id);

//    CocktailType findCocktailTypeById(int id);

//    List<CocktailType> findAllCocktailType();


//    List<CocktailType> getCocktailTypes();
}
