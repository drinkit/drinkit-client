package ua.kiev.naiv.drinkit.cocktail.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("unused")
public abstract class RecipeInfoMixin {
    @JsonIgnore
    private byte[] thumbnail;

}
