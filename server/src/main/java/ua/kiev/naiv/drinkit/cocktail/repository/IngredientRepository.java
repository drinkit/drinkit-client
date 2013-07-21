package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.repository.Repository;
import ua.kiev.naiv.drinkit.cocktail.model.Ingredient;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 20:11
 */
public interface IngredientRepository extends Repository<Ingredient, Integer>{

    public List<Ingredient> findAll();

}
