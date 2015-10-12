package models {
import flash.events.Event;

import models.supportClasses.FakeIngredient;
import models.supportClasses.Ingredient;

import mx.binding.utils.BindingUtils;
import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.events.CollectionEvent;

/**
 * @eventType models.CocktailBuilderModel.SELECTED_INGREDIENTS_CHANGED
 */
[Event(name="selectedIngredientsChanged", type="flash.events.Event")]
public class CocktailBuilderModel extends SearchResultsModel {
    public static const SELECTED_INGREDIENTS_CHANGED:String = "selectedIngredientsChanged";

    public function CocktailBuilderModel() {
        IngredientsModel.instance.addEventListener("ingredientsLoaded", onIngredientsLoaded);
        BindingUtils.bindProperty(this, "ingredientsList", IngredientsModel.instance, "ingredientsList");
        selectedIngredientsList = new ArrayList();
        cocktailsList = new ArrayCollection();
        //
        selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
    }

    [Bindable]
    public var ingredientsList:ArrayCollection;
    [Bindable]
    public var selectedIngredientsList:ArrayList;
    public var selectedIngredients:Array = [];
    public var selectedCocktailTypes:Array = [];
    public var selectedOptions:Array = [];

    override public function isIngredientSelected(id:Number):Boolean {
        for (var i:uint = 0; i < selectedIngredients.length; i++) {
            if (selectedIngredients[i] == id)
                return true;
        }

        return false;
    }

    private function expandSynonyms(ingredients:ArrayCollection):ArrayCollection {
        var expandedIngredients:Array = [];
        var fakeIngredient:FakeIngredient;
        var counter:uint = 0;

        for (var i:int = 0; i < ingredients.length; i++) {
            counter++;
            var ingredient:Ingredient = ingredients.getItemAt(i) as Ingredient;
            expandedIngredients.push(new FakeIngredient(counter, ingredient.name, ingredient.name, ingredient.id, true, false));

            if (ingredient.alias) {
                for (var j:int = 0; j < ingredient.alias.length; j++) {
                    counter++;
                    var synonym:String = ingredient.alias[j];
                    expandedIngredients.push(new FakeIngredient(counter, synonym, synonym + " (" + ingredient.name + ")", ingredient.id, false, false));
                }
            }
        }

        return new ArrayCollection(expandedIngredients);
    }

    private function itemToId(item:*, index:int, array:Array):uint {
        return item.id;
    }

    private function upgradeIngredientsCaptions():void {
        for each (var fakeIngr:FakeIngredient in ingredientsList) {
            fakeIngr.locked = isIngredientSelected(fakeIngr.groupId);
            ingredientsList.itemUpdated(fakeIngr);
        }
    }

    private function onIngredientsLoaded(event:Event):void {
        ingredientsList = expandSynonyms(IngredientsModel.instance.ingredientsList);
    }

    private function onIngredientsQueryListChange(event:CollectionEvent):void {
        ingredientsList.refresh();
        selectedIngredients = selectedIngredientsList.source.map(itemToId);
        upgradeIngredientsCaptions();
        dispatchEvent(new Event(SELECTED_INGREDIENTS_CHANGED));
    }
}
}