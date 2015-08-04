/**
 * Created by Crabar on 13.06.2015.
 */
package utils
{
    import controllers.supportClasses.Services;

    import flash.net.URLRequestMethod;

    import utils.supportClasses.JSRequest;

    public class StatsTracker
    {
        private static var _instance:StatsTracker;

        public static function get instance():StatsTracker
        {
            if (!_instance)
            {
                _instance = new StatsTracker();
            }

            return _instance;
        }

        public function recipeWasShowed(recipeId:Number):void
        {
            ServiceUtil.instance.sendRequest(Services.STATS_VIEWS + recipeId.toString(), new JSRequest("PATCH"), onStatsUpdated);
        }

        private function onStatsUpdated(response:String):void
        {
            //
        }
    }
}
