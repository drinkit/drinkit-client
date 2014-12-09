package guru.drinkit.repisitory;

import guru.drinkit.domain.Ingredient;
import guru.drinkit.repository.IngredientRepository;
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
        Ingredient firstIngredient = new Ingredient();
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