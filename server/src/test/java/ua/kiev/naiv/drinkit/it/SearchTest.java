//package ua.kiev.naiv.it;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import ua.kiev.naiv.drinkit.cocktail.service.RecipeService;
//import ua.kiev.naiv.drinkit.springconfig.AppConfig;
//
///**
// * Created with IntelliJ IDEA.
// * User: Pavel Kolmykov
// * Date: 22.07.13
// * Time: 21:41
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class)
//public class SearchTest extends AbstractTransactionalJUnit4SpringContextTests{
//
//    private final static String CRITERIA_JSON_PATH = "json/request/search.json";
//
//    @Autowired
//    RecipeService ingredientService;
//
//    @Test
//    public void readAndSerializeJsonIntoCriteria() throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Criteria criteria = objectMapper.readValue(new File(CRITERIA_JSON_PATH), Criteria.class);
//        Assert.assertNotNull(criteria);
//        List<RecipeEntity> recipeEntities = cocktailService.findByCriteria(criteria);
//        Assert.assertNotNull(recipeEntities);
//    }
//
//    @Test
//    public void findCubaLibreByCriteria() throws IOException {
//        Criteria criteria = new Criteria(Collections.singleton(1), Collections.singleton(2), Collections.singleton(2));
//        List<RecipeEntity> recipeEntities = cocktailService.findByCriteria(criteria);
//        Assert.assertNotNull(recipeEntities);
//        Assert.assertTrue(recipeEntities.size() > 0);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.addMixInAnnotations(RecipeEntity.class, RecipeSearchResult.class);
//        objectMapper.writeValue(TestHelper.createIfNotExistJsonFileResponse("searchResult"), recipeEntities);
//    }
//
//    @Test public void testSearchService(){
//        Criteria criteria = new Criteria(Collections.singleton(1), Collections.singleton(2), Collections.singleton(2));
//        List<RecipeEntity> recipeEntities = cocktailService.findByCriteria(criteria);
//        recipeEntities = cocktailService.findByCriteria(criteria);
//        Iterator<Option> iter = recipeEntities.get(0).getOptions().iterator();
//        System.out.println(iter.next().getId());
//        System.out.println(iter.next().getId());
//        return;
//    }
//}
