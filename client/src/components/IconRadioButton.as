package components
{
    import design.skins.FlatRadioButtonSkin;

    import spark.components.RadioButton;

    [Style(name="selectedColor", inherit="no", type="int")]
    public class IconRadioButton extends RadioButton
    {
        public function IconRadioButton()
        {
            super();
            setStyle("skinClass", Class(FlatRadioButtonSkin));
        }
        [Bindable]
        public var icon:Object;
    }
}