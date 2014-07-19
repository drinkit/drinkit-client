package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pkolmykov
 */
public abstract class RecipeSearchResultMixin {
    @JsonIgnore
    private byte[] image;
    @JsonIgnore
    private String description;
}
