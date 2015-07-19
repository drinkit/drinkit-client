/**
 * Created by Crabar on 29.06.2015.
 */
package models.supportClasses
{
    public class BarItem
    {
        public function BarItem(ingredientId:Number = NaN, active:Boolean = false)
        {
            this.ingredientId = ingredientId;
            this.active = active;
        }

        public var ingredientId:Number;
        public var active:Boolean;
    }
}
