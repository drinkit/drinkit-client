/**
 * Created by Crabar on 29.06.2015.
 */
package models.supportClasses
{
    public class BarItem
    {
        public function BarItem(ingredientId:Number = NaN, isActive:Boolean = false)
        {
            this.ingredientId = ingredientId;
            this.isActive = isActive;
        }

        public var ingredientId:Number;
        public var isActive:Boolean;
    }
}
