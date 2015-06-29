/**
 * Created by Crabar on 29.06.2015.
 */
package models
{
    import flash.events.EventDispatcher;

    import models.supportClasses.BarItem;

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

        public var ingredients:Vector.<BarItem>;

        public function getIngredientsByCategory(category:String):ArrayList
        {
            return new ArrayList();
        }

        public function get inactiveIngredients():ArrayList
        {
            return new ArrayList();
        }
    }
}
