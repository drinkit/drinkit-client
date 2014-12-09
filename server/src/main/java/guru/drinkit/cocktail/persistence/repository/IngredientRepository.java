package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.web.dto.IngredientDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by pkolmykov on 12/8/2014.
 */
public interface IngredientRepository extends MongoRepository<IngredientDto, Integer> {

    IngredientDto findFirstByOrderByIdDesc();

}
