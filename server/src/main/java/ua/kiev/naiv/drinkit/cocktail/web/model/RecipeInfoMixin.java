package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pkolmykov
 */
public abstract class RecipeInfoMixin {
    @JsonIgnore
    private byte[] thumbnail;

}
