package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.persistence.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
