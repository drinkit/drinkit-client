/**
 * Created by Crabar on 19.07.2014.
 */
package utils
{
    public class ArrayUtil
    {
        public static function fromVectorToArray(sourceVector:*):Array
        {
            var ret:Array = [];
            for each (var elem:* in sourceVector) ret.push(elem);
            return ret;
        }
    }
}
