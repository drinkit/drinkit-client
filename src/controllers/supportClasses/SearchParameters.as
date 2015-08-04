package controllers.supportClasses
{
    public class SearchParameters
    {
        public function SearchParameters()
        {
        }

        public var cocktailTypes:Array;
        public var ingredients:Array;
        public var options:Array;

        public function toString():String
        {
            return JSON.stringify(this);
        }
    }
}