/**
 * Created by Crabar on 26.10.2014.
 */
package models
{
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
        public var cocktailsList:ArrayCollection;

        public function isIngredientSelected(id:Number):Boolean
        {
            return false;
        }
    }
}
