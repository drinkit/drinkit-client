/**
 * Created by ypoliakov on 21.04.2015.
 */
package utils {
    import com.google.analytics.GATracker;

    import flash.display.DisplayObject;

    import mx.core.FlexGlobals;

    public class AnalyticsTracker extends GATracker {

        private static var _instance:AnalyticsTracker;

        public static function get instance():AnalyticsTracker {
            if (!_instance) {
                _instance = new AnalyticsTracker();
            }

            return _instance;
        }

        public function AnalyticsTracker() {
            super(FlexGlobals.topLevelApplication as DisplayObject, "UA-62127609-1", "AS3", true);
        }
    }
}
