package spark.flextras.autoCompleteComboBox.renderers
{
    import flash.display.DisplayObject;
    import flash.events.Event;

    import mx.events.FlexEvent;

    import spark.components.RichText;
    import spark.flextras.autoCompleteComboBox.AutoCompleteComboBoxLite;
    import spark.skins.spark.DefaultItemRenderer;
    import spark.utils.TextFlowUtil;

    /**
     * @private
     *
     * This is the default AutoComplete Renderer for the Flextras AutoCompleteComboBox
     * For now we're leaving it undocumented.
     *
     * @author jhouser
     *
     */
    public class DefaultAutoCompleteRenderer extends DefaultItemRenderer implements IAutoCompleteRenderer
    {
        /**
         * constructor.
         */
        public function DefaultAutoCompleteRenderer()
        {
            super();
            this.addEventListener(FlexEvent.DATA_CHANGE, onDataChange);
        }

        //--------------------------------------------------------------------------
        //
        //  Overridden methods: UIComponent
        //
        //--------------------------------------------------------------------------

        /**
         * @private
         */
        private var _autoCompleteComboBox:AutoCompleteComboBoxLite;


        //--------------------------------------------------------------------------
        //
        //  Properties
        //
        //--------------------------------------------------------------------------

        //----------------------------------
        //  autoCompleteComboBox
        //----------------------------------

        [Bindable("autoCompleteCombooxChanged")]
        /**
         * @inheritDoc
         */
        public function get autoCompleteComboBox():AutoCompleteComboBoxLite
        {
            return this._autoCompleteComboBox;
        }

        /**
         * @inheritDoc
         */
        public function set autoCompleteComboBox(value:AutoCompleteComboBoxLite):void
        {
            this._autoCompleteComboBox = value;
            this.dispatchEvent(new Event('autoCompleteCombooxChanged'));
        }

        /**
         * @private
         */
        private var _typeAheadText:String;


        //----------------------------------
        //  typeAheadText
        //----------------------------------

        [Bindable("typeAheadTextChanged")]
        /**
         * @inheritDoc
         */
        public function get typeAheadText():String
        {
            return this._typeAheadText;
        }

        /**
         * @inheritDoc
         */
        public function set typeAheadText(value:String):void
        {
            this._typeAheadText = value;
            this.dispatchEvent(new Event('typeAheadTextChanged'));

        }

        /**
         *  @private
         */
        override protected function createChildren():void
        {

            if (!labelDisplay)
            {
                labelDisplay = new RichText();
                addChild(DisplayObject(labelDisplay));
            }

            super.createChildren();

        }

        //--------------------------------------------------------------------------
        //
        //  Event handling
        //
        //--------------------------------------------------------------------------

        /**
         * @private
         * Helper function for changing the data and updating the
         */
        protected function onDataChange(event:FlexEvent):void
        {

            var labelAsRichText:RichText = this.labelDisplay as RichText;
            if (this.typeAheadText != '')
            {
                // adding G flag to the RegExp would replace all instances instead of just first one
                // removed it because it does not seem to be standard
                var regEx:RegExp = new RegExp(this.typeAheadText, 'i');

                labelAsRichText.textFlow = TextFlowUtil.importFromString(this.autoCompleteComboBox.itemToLabel(data).replace(regEx, '<span fontWeight="bold">$&</span>'));
            } else
            {
                if (this.autoCompleteComboBox)
                {
                    labelAsRichText.text = this.autoCompleteComboBox.itemToLabel(data);
                } else
                {
                    labelAsRichText.text = 'Unknown Error'
                }
            }


        }

    }
}