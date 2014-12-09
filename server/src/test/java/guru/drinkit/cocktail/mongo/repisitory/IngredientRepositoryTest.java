package guru.drinkit.cocktail.mongo.repisitory;

import guru.drinkit.cocktail.persistence.repository.IngredientRepository;
import guru.drinkit.cocktail.web.dto.IngredientDto;
import guru.drinkit.springconfig.MongoConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    private MongoOperations operations;

    @Test
    public void testInsert(){
        IngredientDto firstIngredient = new IngredientDto();
        firstIngredient.setId(10);
        firstIngredient.setDescription("firstIngredient");
        firstIngredient.setName("First");
        firstIngredient.setVol(30);
        ingredientRepository.save(firstIngredient);
    }

    @Test
    public void testFindMaxId() throws Exception {
        System.out.println(ingredientRepository.findFirstByOrderByIdDesc());
    }
}