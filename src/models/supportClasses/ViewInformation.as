package models.supportClasses
{

    public class ViewInformation
    {

        public function ViewInformation(id:int, title:String)
        {
            this.id = id;
            this.title = title;
        }

        [Bindable]
        public var id:int;
        public var title:String;
    }
}
