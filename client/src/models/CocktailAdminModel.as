/**
 * Created by Crabar on 10.07.2014.
 */
package models
{
    import flash.display.Bitmap;
    import flash.events.EventDispatcher;
    import flash.geom.Rectangle;

    import mx.binding.utils.BindingUtils;
    import mx.collections.ArrayCollection;
    import mx.collections.ArrayList;
    import mx.events.CollectionEvent;

    [Event(name="modelUpdated", type="flash.events.Event")]
    public class CocktailAdminModel extends EventDispatcher
    {
        public function CocktailAdminModel()
        {
            BindingUtils.bindProperty(this, "ingredientsList", IngredientsModel.instance, "ingredientsList");
            selectedIngredientsList = new ArrayList();
            selectedOptions = [];
            //
            selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
        }

        public var cocktailId:Number;

        [Bindable]
        public var ingredientsList:ArrayCollection = IngredientsModel.instance.ingredientsList;

        [Bindable]
        public var selectedIngredientsList:ArrayList;

        [Bindable]
        public var selectedOptions:Array;

        [Bindable]
        public var cocktailTypeId:uint;

        [Bindable]
        public var name:String;

        [Bindable]
        public var description:String;

        [Bindable]
        public var image:Bitmap;

        public var imageClipRect:Rectangle;


        private function onIngredientsQueryListChange(event:CollectionEvent):void
        {
            ingredientsList.refresh();
        }
    }
}
