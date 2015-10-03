/**
 * Created by Crabar on 03.10.2015.
 */
package models.supportClasses {
public class FakeIngredient {
    public function FakeIngredient(id:Number, name:String, caption:String, groupId:Number, isReal:Boolean, locked:Boolean) {
        this.id = id;
        this.name = name;
        this.caption = caption;
        this.groupId = groupId;
        this.isReal = isReal;
        this.locked = locked;
    }

    public var name:String;
    public var caption:String;
    public var id:Number;
    public var groupId:Number;
    public var isReal:Boolean;
    public var locked:Boolean;
}
}
