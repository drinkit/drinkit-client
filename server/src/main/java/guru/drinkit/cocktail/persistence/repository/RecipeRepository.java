package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.web.dto.RecipeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<RecipeDto, Integer>, RecipeRepositoryCustom{

    List<RecipeDto> findByNameContainingIgnoreCase(String namePart);

    RecipeDto findFirstByOrderByIdDesc();
}
