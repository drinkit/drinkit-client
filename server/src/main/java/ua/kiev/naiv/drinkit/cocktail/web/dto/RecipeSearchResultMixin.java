package ua.kiev.naiv.drinkit.cocktail.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("unused")
public abstract class RecipeSearchResultMixin {
    @JsonIgnore
    private byte[] image;
    @JsonIgnore
    private String description;
}
