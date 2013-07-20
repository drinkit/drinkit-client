package components
{
	import design.skins.FlatRadioButtonSkin;
	
	import mx.core.ClassFactory;
	
	import spark.components.RadioButton;
	
	[Style(name="selectedColor", inherit="no", type="int")]
	public class IconRadioButton extends RadioButton
	{
		[Bindable]
		public var icon:Object;
		
		public function IconRadioButton()
		{
			super();
			setStyle("skinClass", Class(FlatRadioButtonSkin));
		}
	}
}