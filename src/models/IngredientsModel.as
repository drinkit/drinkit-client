package models
{
    import flash.events.Event;
    import flash.events.EventDispatcher;

    import models.supportClasses.Ingredient;

    import mx.collections.ArrayCollection;

    public class IngredientsModel extends EventDispatcher
    {
        public static const INGREDIENTS_LOADED:String = "ingredientsLoaded";

        private static var _instance:IngredientsModel;

        public static function get instance():IngredientsModel
        {
            if (!_instance)
                _instance = new IngredientsModel();

            return _instance;
        }

        public function IngredientsModel()
        {
        }

        [Bindable]
        private var _ingredientsList:ArrayCollection;

        [Bindable(event="ingredientsLoaded")]
        public function get ingredientsList():ArrayCollection
        {
            return _ingredientsList;
        }

        public function set ingredientsList(value:ArrayCollection):void
        {
            if (_ingredientsList == value) return;
            _ingredientsList = value;
            dispatchEvent(new Event(INGREDIENTS_LOADED));
        }

        public function getIngredientById(id:Number):Ingredient
        {
            if (_ingredientsList)
            {
                for (var i:uint = 0; i < _ingredientsList.source.length; i++)
                {
                    if (Ingredient(_ingredientsList.source[i]).id == id)
                        return Ingredient(_ingredientsList.source[i]);
                }
            }

            return null;
        }
    }
}
