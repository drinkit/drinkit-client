/**
 * Created by Crabar on 29.06.2015.
 */
package models
{
    import flash.events.EventDispatcher;

    import models.supportClasses.BarItem;
    import models.supportClasses.Ingredient;

    import mx.collections.ArrayList;

    /**
     * @eventType models.MyBarModel.MODEL_CHANGED
     */
    [Event(name="modelChanged", type="flash.events.Event")]
    public class MyBarModel extends EventDispatcher
    {
        public static const MODEL_CHANGED:String = "modelChanged";

        public static function get instance():MyBarModel
        {
            if (!_instance)
            {
                _instance = new MyBarModel();
            }

            return _instance;
        }

        private static var _instance:MyBarModel;

        public var barItems:Vector.<BarItem>;

        public function getIngredientsByCategory(category:String):ArrayList
        {
            var ingredients:Array = [];
            var curIngr:Ingredient;
            for each (var item:BarItem in barItems) {
                if (!item.isActive) {
                   continue;
                }
                curIngr = IngredientsModel.instance.getIngredientById(item.ingredientId);
                if (curIngr.category == category) {
                    ingredients.push(curIngr);
                }
            }
            return new ArrayList(ingredients);
        }

        public function get inactiveIngredients():ArrayList
        {
            return new ArrayList();
        }
    }
}
