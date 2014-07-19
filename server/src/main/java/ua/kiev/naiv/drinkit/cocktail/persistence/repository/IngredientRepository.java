package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 20:11
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
