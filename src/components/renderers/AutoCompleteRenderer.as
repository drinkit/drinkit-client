package components.renderers {
import mx.events.FlexEvent;

import spark.components.RichText;
import spark.flextras.autoCompleteComboBox.renderers.DefaultAutoCompleteRenderer;
import spark.utils.TextFlowUtil;

public class AutoCompleteRenderer extends DefaultAutoCompleteRenderer {
    public function AutoCompleteRenderer() {
        super();
    }

    override protected function onDataChange(event:FlexEvent):void {
        var labelAsRichText:RichText = this.labelDisplay as RichText;

        var suffix:String = "";
        if (data.hasOwnProperty("locked")) {
            enabled = !data.locked;
            if (data.locked) {
                suffix = '<span fontStyle="italic"> - уже выбран</span>';
            }
        }

        if (this.typeAheadText != '') {
            var startIndex:int = this.autoCompleteComboBox.itemToLabel(data).toLowerCase().indexOf(typeAheadText.toLowerCase());

            if (startIndex > -1) {
                var len:int = typeAheadText.length;
                var matchedPart:String = this.autoCompleteComboBox.itemToLabel(data).substr(startIndex, len);
                labelAsRichText.textFlow = TextFlowUtil.importFromString(this.autoCompleteComboBox.itemToLabel(data).replace(matchedPart, '<span textDecoration="underline">' + matchedPart + '</span>') + suffix);
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
