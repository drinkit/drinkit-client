package components
{
    import flash.display.CapsStyle;
    import flash.display.JointStyle;
    import flash.display.LineScaleMode;

    import spark.components.Image;

    [Style(name="borderSize", type="Number", inherit="no")]
    [Style(name="borderColor", format="Color", type="uint", inherit="no")]
    public class ImageWithBorder extends Image
    {

        public function ImageWithBorder()
        {
            super();
        }

        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
        {
            super.updateDisplayList(unscaledWidth, unscaledHeight);

            if (imageDisplay && imageDisplay.bitmapData)
            {
                var borderSize:Number = getStyle("borderSize") || 0;
                var borderColor:Number = getStyle("borderColor") || 0xffffff;

                var half:Number = int(borderSize / 2);
                imageDisplay.left = imageDisplay.top = imageDisplay.right = imageDisplay.bottom = borderSize;

                graphics.clear();
                graphics.lineStyle(borderSize, borderColor, 1, false, LineScaleMode.NONE, CapsStyle.NONE, JointStyle.MITER);

                graphics.moveTo(0, half);
                graphics.lineTo(unscaledWidth - half - 1, half);
                graphics.lineTo(unscaledWidth - half - 1, unscaledHeight - half - 2);
                graphics.lineTo(half, unscaledHeight - half - 2);
                graphics.lineTo(half, half);

            }
        }
    }
}