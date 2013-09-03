package models.supportClasses
{
	public class ViewInformation
	{
		[Bindable]
		public var id:int;
		public var title:String;
		
		public function ViewInformation(id:int, title:String)
		{
			this.id = id;
			this.title = title;
		}
		
	}
}