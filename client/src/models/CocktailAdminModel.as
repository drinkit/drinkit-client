/**
 * Created by Crabar on 10.07.2014.
 */
package models
{
    import flash.events.EventDispatcher;

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
            selectedOptions = new <Object>[];
            //
            selectedIngredientsList.addEventListener(CollectionEvent.COLLECTION_CHANGE, onIngredientsQueryListChange);
        }

        [Bindable]
        public var ingredientsList:ArrayCollection = IngredientsModel.instance.ingredientsList;

        [Bindable]
        public var selectedIngredientsList:ArrayList;

        [Bindable]
        public var selectedOptions:Vector.<Object>;

        [Bindable]
        public var cocktailTypeId:uint;

        [Bindable]
        public var name:String;

        [Bindable]
        public var description:String;

        private function onIngredientsQueryListChange(event:CollectionEvent):void
        {
            ingredientsList.refresh();
        }
    }
}
