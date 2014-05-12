package components.renderers
{
	import flash.geom.Point;
	
	import mx.events.FlexEvent;
	
	import spark.components.RichText;
	import spark.flextras.autoCompleteComboBox.renderers.DefaultAutoCompleteRenderer;
	import spark.utils.TextFlowUtil;
	
	public class AutoCompleteRenderer extends DefaultAutoCompleteRenderer
	{
		public function AutoCompleteRenderer()
		{
			super();
		}
		
		override protected function onDataChange(event:FlexEvent):void
		{
			var labelAsRichText : RichText = this.labelDisplay as RichText;
			if (this.typeAheadText != '')
			{
				var startIndex:int = this.autoCompleteComboBox.itemToLabel(data).toLowerCase().indexOf(typeAheadText.toLowerCase());
				
				if (startIndex > -1)
				{
					var len:int = typeAheadText.length;
					var matchedPart:String = this.autoCompleteComboBox.itemToLabel(data).substr(startIndex, len);
					
					labelAsRichText.textFlow = TextFlowUtil.importFromString(this.autoCompleteComboBox.itemToLabel(data).replace(matchedPart, '<span textDecoration="underline">' + matchedPart + '</span>'));
					return;
				}
			} 

			if (this.autoCompleteComboBox)
				labelAsRichText.text = this.autoCompleteComboBox.itemToLabel(data);
			else 
				labelAsRichText.text = 'Unknown Error';

		}
	}
}
