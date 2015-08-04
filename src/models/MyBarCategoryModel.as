/**
 * Created by Crabar on 29.06.2015.
 */
package models
{
    import flash.events.EventDispatcher;

    import mx.collections.ArrayList;

    [Bindable]
    public class MyBarCategoryModel extends EventDispatcher
    {
        public function MyBarCategoryModel()
        {
        }

        public var category:String;
        public var selectedIngredientsList:ArrayList;
    }
}
