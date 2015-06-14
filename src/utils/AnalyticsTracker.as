/**
 * Created by ypoliakov on 21.04.2015.
 */
package utils
{
    import flash.system.Capabilities;

    import io.gh.cmodien.gua4fl.Gua4fl;

    import io.gh.cmodien.gua4fl.Gua4flConfig;
    import io.gh.cmodien.gua4fl.GuaRequest;
    import io.gh.cmodien.gua4fl.hitTypes.EventHitType;
    import io.gh.cmodien.gua4fl.hitTypes.PageViewHitType;

    public class AnalyticsTracker
    {

        private static var _instance:AnalyticsTracker;

        public static function get instance():AnalyticsTracker
        {
            if (!_instance)
            {
                _instance = new AnalyticsTracker();
            }

            return _instance;
        }

        private static function generate32bitRandom():int
        {
            return Math.round(Math.random() * 0x7fffffff);
        }

        private static function generateHash(input:String):int
        {
            var hash:int = 1;
            var leftMost7:int = 0;
            var pos:int;
            var current:int;

            if (input != null && input != "")
            {
                hash = 0;

                for (pos = input.length - 1; pos >= 0; pos--)
                {
                    current = input.charCodeAt(pos);
                    hash = ((hash << 6) & 0xfffffff) + current + (current << 14);
                    leftMost7 = hash & 0xfe00000;

                    if (leftMost7 != 0)
                    {
                        hash ^= leftMost7 >> 21;
                    }
                }
            }

            return hash;
        }

        private static function generateUserDataHash():Number
        {
            var data:String = "";
            data += Capabilities.cpuArchitecture;
            data += Capabilities.language;
            data += Capabilities.manufacturer;
            data += Capabilities.os;
            data += Capabilities.screenColor;
            data += Capabilities.screenDPI;
            data += Capabilities.screenResolutionX + "x" + Capabilities.screenResolutionY;

            return generateHash(data);
        }

        private static function generateClientID():Number
        {
            var cid:Number = (generate32bitRandom() ^ generateUserDataHash()) * 0x7fffffff;
            return cid;
        }

        public function AnalyticsTracker()
        {
            var clientId:String = CookieUtil.getCookie("clientId") as String;

            if (!clientId)
            {
                clientId = generateClientID().toString();
                CookieUtil.setCookie("clientId", clientId, 700);
            }

            _tracker = new Gua4fl(new Gua4flConfig("UA-62127609-1", clientId, true));
        }
        private var _tracker:Gua4fl;

        public function trackEvent(category:String, action:String):void
        {
            _tracker.send(new GuaRequest(EventHitType.create(category, action)));
        }

        public function trackPageView(page:String, title:String = ""):void
        {
            _tracker.send(new GuaRequest(PageViewHitType.create(page, title)));
        }
    }
}
