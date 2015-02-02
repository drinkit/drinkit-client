/**
 * Created by Crabar on 26.10.2014.
 */
package models
{
    import flash.events.Event;
    import flash.events.EventDispatcher;

    import mx.collections.ArrayCollection;

    [Event(name="change", type="flash.events.Event")]
    public class SearchResultsModel extends EventDispatcher
    {
        public function SearchResultsModel()
        {
        }

        [Bindable]
        public var isNoCocktailsFound:Boolean;

        [Bindable(event="change")]
        private var _cocktailsList:ArrayCollection;

        public function isIngredientSelected(id:Number):Boolean
        {
            return false;
        }

        public function get cocktailsList():ArrayCollection
        {
            return _cocktailsList;
        }

        public function set cocktailsList(value:ArrayCollection):void
        {
            _cocktailsList = value;
            signalizeAboutChange();
        }

        public function signalizeAboutChange():void
        {
            dispatchEvent(new Event(Event.CHANGE));
        }
    }
}
