/**
 * Created by Crabar on 24.07.2015.
 */
package utils
{
    public class PerformanceAnalyzer
    {
        private static var _snapshotTime:Number;

        public static function startTimer():void
        {
            _snapshotTime = new Date().time;
        }

        public static function getElapsedTime():Number
        {
            return new Date().time - _snapshotTime;
        }
    }
}
