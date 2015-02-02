package utils
{
    import flash.display.Loader;

    import mx.controls.ProgressBar;
    import mx.core.IVisualElementContainer;

    public class LoadProgressUtil
    {
        private static var progressBars:Object;

        public static function showProgress(parent:IVisualElementContainer, loader:Loader = null):String
        {
            var progressBar:ProgressBar = new ProgressBar();

            if (loader)
                progressBar.source = loader;
            else
                progressBar.indeterminate = true;

            progressBar.verticalCenter = 0;
            progressBar.horizontalCenter = 0;
            parent.addElement(progressBar);
            progressBars[progressBar.uid] = progressBar;
            return progressBar.uid;
        }

        public static function hideProgress(uid:String):void
        {
            if (progressBars[uid])
                progressBars[uid].parent.removeChild(progressBars[uid]);
        }

        public function LoadProgressUtil()
        {
        }
    }
}