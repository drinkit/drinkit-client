package guru.drinkit.repository;

import guru.drinkit.domain.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, Integer>, RecipeRepositoryCustom {

    List<Recipe> findByNameContainingIgnoreCase(String namePart);

    Recipe findFirstByOrderByIdDesc();
}
