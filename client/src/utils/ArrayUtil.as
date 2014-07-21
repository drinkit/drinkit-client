/**
 * Created by Crabar on 19.07.2014.
 */
package utils
{
    public class ArrayUtil
    {
        public static function fromVectorToArray(source:Vector.<*>):Array
        {
            var result:Array = [];

            for (var i:int = 0; i < source.length; i++)
            {
                result[result.length] = source[i];
            }

            return result;
        }
    }
}
