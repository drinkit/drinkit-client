/**
 * Created by Crabar on 19.04.2015.
 */
package utils
{
    public class ToolTipProvider
    {
        {
            _tooltips[LONG] = "лонги";
            _tooltips[SHORT] = "короткие";
            _tooltips[SHOOTER] = "шоты";
            _tooltips[CHECKED] = "провереный модераторами";
            _tooltips[FLACKY] = "слоенный";
            _tooltips[BURNING] = "горящий";
            _tooltips[IBA] = "утвержденный международной ассоциацией барменов (IBA)";
            _tooltips[WITH_ICE] = "со льдом";
        }
        public static const LONG:String = "long";
        public static const SHORT:String = "short";
        public static const SHOOTER:String = "shooter";
        public static const CHECKED:String = "checked";
        public static const FLACKY:String = "flacky";
        public static const BURNING:String = "burning";
        public static const IBA:String = "iba";
        public static const WITH_ICE:String = "withIce";
        private static var _tooltips:Object = {};

        public static function getToolTip(key:String):String
        {
            if (_tooltips.hasOwnProperty(key))
            {
                return _tooltips[key];
            } else
            {
                throw new Error("No tooltip for '" + key + "'");
            }
        }
    }
}
