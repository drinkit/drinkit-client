/**
 * Created by Crabar on 10.07.2014.
 */
package models
{
    import flash.display.Bitmap;
    import flash.display.BitmapData;
    import flash.events.Event;
    import flash.events.EventDispatcher;
    import flash.geom.Rectangle;

    import models.supportClasses.CocktailTypes;

    import mx.binding.utils.BindingUtils;
    import mx.collections.ArrayCollection;
    import mx.collections.ArrayList;
    import mx.events.CollectionEvent;

    [Event(name="modelUpdated", type="flash.events.Event")]
    public class CocktailAdminModel extends EventDispatcher
    {
        public var imageData:BitmapData;
        public function CocktailAdminModel()
        {
            BindingUtils.bindProperty(this, "ingredientsList", IngredientsModel.instance, "ingredientsList");
            selectedIngredientsList = new ArrayList();
            selectedOptions = [];
            //
            selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
        }

        [Bindable]
        public var imageUrl:String;

        [Bindable]
        public var cocktailId:Number;

        [Bindable]
        public var ingredientsList:ArrayCollection = IngredientsModel.instance.ingredientsList;

        [Bindable]
        public var selectedIngredientsList:ArrayList;

        [Bindable]
        public var selectedOptions:Array;

        [Bindable]
        public var cocktailTypeId:int = CocktailTypes.SHOOTER;

        [Bindable]
        public var name:String;

        [Bindable]
        public var description:String;

        public var imageClipRect:Rectangle;

        public function clear():void
        {
            selectedOptions = [];
            name = "";
            description = "";
            imageData = null;
            imageUrl = "";
            selectedIngredientsList = new ArrayList();
            imageClipRect = null;
            cocktailTypeId = CocktailTypes.SHOOTER;
            dispatchEvent(new Event("modelUpdated"));
        }

        private function onIngredientsQueryListChange(event:CollectionEvent):void
        {
            ingredientsList.refresh();
        }
    }
}
