package ua.kiev.naiv.drinkit.cocktail.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("unused")
public abstract class RecipeInfoMixin {
    @JsonIgnore
    private byte[] thumbnail;

}
