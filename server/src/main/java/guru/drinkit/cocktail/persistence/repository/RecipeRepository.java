package guru.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import guru.drinkit.cocktail.persistence.entity.Recipe;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>, JpaSpecificationExecutor<Recipe> {

    List<Recipe> findByNameContaining(String namePart);

}
