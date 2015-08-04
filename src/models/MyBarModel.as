/**
 * Created by Crabar on 29.06.2015.
 */
package models
{
    import flash.events.Event;
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

        private static var _instance:MyBarModel;

        public static function get instance():MyBarModel
        {
            if (!_instance)
            {
                _instance = new MyBarModel();
            }

            return _instance;
        }

        public function MyBarModel():void
        {
            addEventListener(MyBarModel.MODEL_CHANGED, onModelChanged);
        }

        private var _activeIngredients:ArrayList;

        public function get activeIngredients():ArrayList
        {
            return _activeIngredients;
        }

        private var _inactiveIngredients:ArrayList;

        public function get inactiveIngredients():ArrayList
        {
            return _inactiveIngredients;
        }

        private var _barItems:Vector.<BarItem>;

        public function get barItems():Vector.<BarItem>
        {
            return _barItems;
        }

        public function set barItems(value:Vector.<BarItem>):void
        {
            _barItems = value;
            dispatchEvent(new Event(MyBarModel.MODEL_CHANGED));
        }

        public function getIngredientsByCategory(category:String):ArrayList
        {
            var ingredients:Array = [];
            var curIngr:Ingredient;
            for each (var item:BarItem in _barItems)
            {
                if (!item.active)
                {
                    continue;
                }

                curIngr = IngredientsModel.instance.getIngredientById(item.ingredientId);

                if (curIngr && curIngr.category == category)
                {
                    ingredients.push(curIngr);
                }
            }
            return new ArrayList(ingredients);
        }

        public function addBarItemToBar(value:BarItem):void
        {
            if (value)
            {
                barItems.push(value);
                dispatchEvent(new Event(MyBarModel.MODEL_CHANGED));
            }
        }

        public function removeItemFromBar(ingredientId:Number):void
        {
            var barItem:BarItem = getBarItemById(ingredientId);

            if (barItem)
            {
                var index:int = barItems.indexOf(barItem);
                if (index >= 0)
                {
                    barItems.splice(index, 1);
                    dispatchEvent(new Event(MyBarModel.MODEL_CHANGED));
                }
            }
        }

        public function activateBarItem(ingredientId:Number):void
        {
            var barItem:BarItem = getBarItemById(ingredientId);

            if (barItem)
            {
                barItem.active = true;
                dispatchEvent(new Event(MyBarModel.MODEL_CHANGED));
            }
        }

        public function deactivateBarItem(ingredientId:Number):void
        {
            var barItem:BarItem = getBarItemById(ingredientId);

            if (barItem)
            {
                barItem.active = false;
                dispatchEvent(new Event(MyBarModel.MODEL_CHANGED));
            }
        }

        public function isIngredientInBar(ingredientId:Number):Boolean
        {
            for (var i:int = 0; i < barItems.length; i++)
            {
                if (barItems[i].ingredientId == ingredientId)
                {
                    return true;
                }
            }

            return false;
        }

        private function getBarItemById(ingredientId:Number):BarItem
        {
            for each (var item:BarItem in barItems)
            {
                if (item.ingredientId == ingredientId)
                    return item;
            }

            return null;
        }

        private function onModelChanged(event:Event):void
        {
            _activeIngredients = new ArrayList();
            _inactiveIngredients = new ArrayList();

            var curIngr:Ingredient;
            for each (var item:BarItem in barItems)
            {
                curIngr = IngredientsModel.instance.getIngredientById(item.ingredientId);
                if (item.active)
                {
                    _activeIngredients.addItem(curIngr);
                } else
                {
                    _inactiveIngredients.addItem(curIngr);
                }
            }
        }
    }
}
