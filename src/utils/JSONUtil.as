/**
 * Created by Crabar on 28.07.2014.
 */
package utils
{
    public class JSONUtil
    {
        public static function escapeSpecialChars(source:String):String
        {
            return source.replace(/\\n/g, "\\\\n")
                    .replace(/\\'/g, "\\\\'")
                    .replace(/\\"/g, '\\\\"')
                    .replace(/\\&/g, "\\\\&")
                    .replace(/\\r/g, "\\\\r")
                    .replace(/\\t/g, "\\\\t")
                    .replace(/\\b/g, "\\\\b")
                    .replace(/\\f/g, "\\\\f");
        }
    }
}
