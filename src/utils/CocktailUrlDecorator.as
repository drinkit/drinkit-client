/**
 * Created by ypoliakov on 17.02.2015.
 */
package utils {
    import models.CocktailModel;

    public class CocktailUrlDecorator {
        public static function decorate(cocktail:CocktailModel):CocktailModel {
            cocktail.imageUrl = cocktail.imageUrl ? ServiceUtil.instance.serviceAddress + cocktail.imageUrl: null;
            cocktail.thumbnailUrl = cocktail.thumbnailUrl ? ServiceUtil.instance.serviceAddress + cocktail.thumbnailUrl: null;
            return cocktail;
        }
    }
}
