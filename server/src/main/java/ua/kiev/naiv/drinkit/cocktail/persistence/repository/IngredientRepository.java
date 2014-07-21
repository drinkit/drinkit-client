package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
