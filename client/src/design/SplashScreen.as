/**
 * Created by Crabar on 15.01.2015.
 */
package design
{
    import flash.display.Bitmap;
    import flash.display.Sprite;
    import flash.events.Event;
    import flash.events.ProgressEvent;
    import flash.events.TimerEvent;
    import flash.utils.Timer;

    import mx.events.FlexEvent;

    import mx.graphics.BitmapFillMode;
    import mx.preloaders.IPreloaderDisplay;

    import spark.primitives.BitmapImage;

    public class SplashScreen extends Sprite implements IPreloaderDisplay
    {
        [Embed(source="/../assets/new-pattern.png")]
        private var splashImage:Class;

        public function SplashScreen()
        {
            super();
//            source = new splashImage();
//            fillMode = BitmapFillMode.REPEAT;
//            alpha = 0.7;
        }

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

        private function handleProgress(event:ProgressEvent):void {}
        private function handleComplete(event:Event):void {}
        private function handleInitProgress(event:Event):void {}

        private function handleInitComplete(event:Event):void {
            dispatchComplete();
        }

        private function dispatchComplete():void {
            dispatchEvent(new Event(Event.COMPLETE));
        }

        public function get stageHeight():Number
        {
            return _stageHeight;
        }

        private var _stageWidth:Number;
        private var _stageHeight:Number;

        public function set stageHeight(value:Number):void
        {
            _stageHeight = value;
        }

        public function get stageWidth():Number
        {
            return _stageWidth;
        }

        public function set stageWidth(value:Number):void
        {
            _stageWidth = value;
        }

        public function initialize():void
        {
            alpha = 0.7;
            var splash:Bitmap = new splashImage();
            graphics.beginBitmapFill(splash.bitmapData, null, true, false);
            graphics.drawRect(0, 0, stageWidth, stageHeight);
            graphics.endFill();
        }
    }
}
