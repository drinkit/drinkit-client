package guru.drinkit.cocktail.mapping;

import guru.drinkit.cocktail.persistence.entity.Ingredient;
import guru.drinkit.cocktail.persistence.entity.IngredientWithQuantity;
import guru.drinkit.cocktail.persistence.entity.Option;
import guru.drinkit.cocktail.persistence.entity.Recipe;
import guru.drinkit.cocktail.persistence.repository.IngredientRepository;
import guru.drinkit.cocktail.service.FileStoreService;
import guru.drinkit.cocktail.web.dto.RecipeDto;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pkolmykov
 */
public class DtoMapper extends ConfigurableMapper {

    @Resource
    private IngredientRepository ingredientRepository;

    @Resource
    private FileStoreService fileStoreService;

    @Override
    protected void configure(MapperFactory factory) {
        ConverterFactory converterFactory = factory.getConverterFactory();

        converterFactory.registerConverter(new CustomConverter<List<IngredientWithQuantity>, Integer[][]>() {
            @Override
            public Integer[][] convert(List<IngredientWithQuantity> ingredientWithQuantities, Type<? extends Integer[][]> destinationType) {
                return ingredientWithQuantities.stream()
                        .<Integer[]>map(ingredientWithQuantity -> new Integer[]{ingredientWithQuantity.getIngredient().getId(), ingredientWithQuantity.getQuantity()})
                        .toArray(Integer[][]::new);
            }

        });

        converterFactory.registerConverter(new BidirectionalConverter<int[], List<Option>>() {
            @Override
            public List<Option> convertTo(int[] source, Type<List<Option>> destinationType) {
                return Arrays.stream(source).<Option>mapToObj(Option::new).collect(Collectors.toList());
            }

            @Override
            public int[] convertFrom(List<Option> source, Type<int[]> destinationType) {
                return source.stream().mapToInt(Option::getId).toArray();
            }

        });


        factory.classMap(Recipe.class, RecipeDto.class)
                .field("cocktailType.id", "cocktailTypeId")
                .fieldAToB("ingredientsWithQuantities", "cocktailIngredients")
                .customize(new CustomMapper<Recipe, RecipeDto>() {
                    @Override
                    public void mapBtoA(RecipeDto recipeDto, Recipe recipe, MappingContext context) {
                        recipe.setIngredientsWithQuantities(Arrays.stream(recipeDto.getCocktailIngredients()).<IngredientWithQuantity>map(val -> {
                            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity();
                            ingredientWithQuantity.setQuantity(val[1]);
                            ingredientWithQuantity.setRecipe(recipe);
                            Ingredient ingredientById = ingredientRepository.findOne(val[0]);
                            Assert.notNull(ingredientById);
                            ingredientWithQuantity.setIngredient(ingredientById);
                            ingredientById.getCocktailIngredients().add(ingredientWithQuantity);
                            return ingredientWithQuantity;
                        }).collect(Collectors.toList()));
                    }

                    @Override
                    public void mapAtoB(Recipe recipe, RecipeDto recipeDto, MappingContext context) {
                        recipeDto.setThumbnailUrl(fileStoreService.getUrl(recipe.getThumbnailFileName()));
                        recipeDto.setImageUrl(fileStoreService.getUrl(recipe.getImageFileName()));
                    }
                })
                .byDefault()
                .register();
    }
}
