package models
{
    import flash.events.Event;
    import flash.events.EventDispatcher;

    import mx.binding.utils.BindingUtils;
    import mx.collections.ArrayCollection;
    import mx.collections.ArrayList;
    import mx.events.CollectionEvent;

    /**
     * @eventType controllers.CocktailController.COCKTAIL_DATA_LOADED
     */
    [Event(name="selectedIngredientsChanged", type="flash.events.Event")]
    public class CocktailBuilderModel extends SearchResultsModel
    {
        public static const SELECTED_INGREDIENTS_CHANGED:String = "selectedIngredientsChanged";

        public function CocktailBuilderModel()
        {
            BindingUtils.bindProperty(this, "ingredientsList", IngredientsModel.instance, "ingredientsList");
            selectedIngredientsList = new ArrayList();
            cocktailsList = new ArrayCollection();
            //
            selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
        }

        [Bindable]
        public var ingredientsList:ArrayCollection = IngredientsModel.instance.ingredientsList;

        [Bindable]
        public var selectedIngredientsList:ArrayList;

        public var selectedIngredients:Array = [];
        public var selectedCocktailTypes:Array = [];
        public var selectedOptions:Array = [];

        override public function isIngredientSelected(id:Number):Boolean
        {
            for (var i:uint = 0; i < selectedIngredients.length; i++)
            {
                if (selectedIngredients[i] == id)
                    return true;
            }

            return false;
        }

        private function itemToId(item:*, index:int, array:Array):uint
        {
            return item.id;
        }

        private function onIngredientsQueryListChange(event:CollectionEvent):void
        {
            ingredientsList.refresh();
            selectedIngredients = selectedIngredientsList.source.map(itemToId);
            dispatchEvent(new Event(SELECTED_INGREDIENTS_CHANGED));
        }

    }
}