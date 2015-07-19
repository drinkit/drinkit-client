/**
 * Created by Crabar on 15.01.2015.
 */
package design
{
    import flash.display.Bitmap;
    import flash.display.Sprite;
    import flash.events.Event;
    import flash.events.ProgressEvent;

    import mx.events.FlexEvent;
    import mx.preloaders.IPreloaderDisplay;

    public class SplashScreen extends Sprite implements IPreloaderDisplay
    {
        public function SplashScreen()
        {
            super();
//            source = new splashImage();
//            fillMode = BitmapFillMode.REPEAT;
//            alpha = 0.7;
        }
        [Embed(source="/../assets/new-pattern.png")]
        private var splashImage:Class;

        public function get backgroundAlpha():Number
        {
            return 1;
        }

        public function set backgroundAlpha(value:Number):void
        {
        }

        public function get backgroundColor():uint
        {
            return 0xffff00;
        }

        public function set backgroundColor(value:uint):void
        {
        }

        public function get backgroundImage():Object
        {
            return null;
        }

        public function set backgroundImage(value:Object):void
        {
        }

        public function get backgroundSize():String
        {
            return "";
        }

        public function set backgroundSize(value:String):void
        {
        }

        public function set preloader(obj:Sprite):void
        {
            obj.addEventListener(
                    ProgressEvent.PROGRESS, handleProgress);
            obj.addEventListener(
                    Event.COMPLETE, handleComplete);
            obj.addEventListener(
                    FlexEvent.INIT_PROGRESS, handleInitProgress);
            obj.addEventListener(
                    FlexEvent.INIT_COMPLETE, handleInitComplete);
        }

        private var _stageWidth:Number;

        public function get stageWidth():Number
        {
            return _stageWidth;
        }

        public function set stageWidth(value:Number):void
        {
            _stageWidth = value;
        }

        private var _stageHeight:Number;

        public function get stageHeight():Number
        {
            return _stageHeight;
        }

        public function set stageHeight(value:Number):void
        {
            _stageHeight = value;
        }

        public function initialize():void
        {
            alpha = 0.7;
            var splash:Bitmap = new splashImage();
            graphics.beginBitmapFill(splash.bitmapData, null, true, false);
            graphics.drawRect(0, 0, stageWidth, stageHeight);
            graphics.endFill();
        }

        private function dispatchComplete():void
        {
            dispatchEvent(new Event(Event.COMPLETE));
        }

        private function handleProgress(event:ProgressEvent):void
        {
        }

        private function handleComplete(event:Event):void
        {
        }

        private function handleInitProgress(event:Event):void
        {
        }

        private function handleInitComplete(event:Event):void
        {
            dispatchComplete();
        }
    }
}
