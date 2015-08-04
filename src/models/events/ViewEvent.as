/**
 * Created by Crabar on 26.10.2014.
 */
package models.events
{
    import flash.events.Event;

    public class ViewEvent extends Event
    {
        public static const VIEW_CHANGED:String = "viewChanged";

        public function ViewEvent(type:String, viewId:uint, viewData:Object = null, bubbles:Boolean = false, cancelable:Boolean = false)
        {
            super(type, bubbles, cancelable);
            _viewData = viewData;
            _viewId = viewId;
        }

        private var _viewData:Object;

        public function get viewData():Object
        {
            return _viewData;
        }

        private var _viewId:uint;

        public function get viewId():uint
        {
            return _viewId;
        }

        override public function clone():Event
        {
            return new ViewEvent(type, viewId, viewData, bubbles, cancelable);
        }
    }
}
