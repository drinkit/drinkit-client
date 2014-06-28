/**
 * Created by Crabar on 28.06.2014.
 */
package models.supportClasses
{
    import controllers.supportClasses.SocialNets;

    public class SocialNetParser
    {
        public static function getSocialNetUserId(provider:String, raw:Object):Number
        {
            var id:Number = NaN;

            switch (provider)
            {
                case SocialNets.VKONTAKTE:
                    id = getVKId(raw);
                    break;
                case SocialNets.FACEBOOK:
                    id = getFacebookId(raw);
                    break;
                case SocialNets.GOOGLE:
                    id = getGoogleId(raw);
                    break;
                default:
                    throw new Error("Unknown social network:" + provider);
            }

            return id;
        }

        private static function getVKId(raw:Object):Number
        {
            if (raw && raw.hasOwnProperty("response") && raw.response.length > 0)
            {
                return Number(raw.response[0].uid);
            }
            else
            {
                return NaN;
            }
        }

        private static function getFacebookId(raw:Object):Number
        {
            return NaN;
        }

        private static function getGoogleId(raw:Object):Number
        {
            return NaN;
        }

        public function SocialNetParser()
        {
        }
    }
}
