/**
 * Created by Crabar on 29.06.2015.
 */
package utils
{
    public class StringUtil
    {
        public static function insertParameters(value:String, params:Object):String
        {
            var res:String = "";

            for (var param:String in params) {
                if (value.indexOf("{" + param + "}") >= 0) {
                    res = value.replace("{" + param + "}", params[param]);
                }
            }

            return res;
        }
    }
}
