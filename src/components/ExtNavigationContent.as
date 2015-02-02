package components
{
    import flash.events.Event;

    import spark.components.NavigatorContent;

    public class ExtNavigationContent extends NavigatorContent
    {
        public function ExtNavigationContent()
        {
            super();
        }

        private var _includeInBar:Boolean = true;

        public function get includeInBar():Boolean
        {
            return _includeInBar;
        }

        public function set includeInBar(value:Boolean):void
        {
            _includeInBar = value;
            dispatchEvent(new Event("labelChanged"));
        }

    }
}