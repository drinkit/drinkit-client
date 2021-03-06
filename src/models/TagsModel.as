package models
{
    import flash.display.Bitmap;

    import models.supportClasses.CocktailTypes;
    import models.supportClasses.OptionParameters;

    import spark.components.Image;

    import utils.ToolTipProvider;

    public class TagsModel
    {
        public static const COCKTAIL_OPTION_TAG:String = "cocktailOptionTag";
        public static const COCKTAIL_TYPE_TAG:String = "cocktailTypeTag";

        private static var _instance:TagsModel;

        public static function get instance():TagsModel
        {
            if (!_instance)
                _instance = new TagsModel;

            return _instance;
        }

        public function TagsModel()
        {
            tagCache = {};
            //
            addTagToCache(new longBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.LONG), CocktailTypes.LONG_DRINK, COCKTAIL_TYPE_TAG);
            addTagToCache(new shortBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.SHORT), CocktailTypes.SHORT_DRINK, COCKTAIL_TYPE_TAG);
            addTagToCache(new shotBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.SHOOTER), CocktailTypes.SHOOTER, COCKTAIL_TYPE_TAG);
            addTagToCache(new fireBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.BURNING), OptionParameters.BURNING, COCKTAIL_OPTION_TAG);
            addTagToCache(new iceBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.WITH_ICE), OptionParameters.WITH_ICE, COCKTAIL_OPTION_TAG);
            addTagToCache(new checkedBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.CHECKED), OptionParameters.CHECKED, COCKTAIL_OPTION_TAG);
            addTagToCache(new ibaBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.IBA), OptionParameters.IBA, COCKTAIL_OPTION_TAG);
            addTagToCache(new layerBitmap(), ToolTipProvider.getToolTip(ToolTipProvider.FLACKY), OptionParameters.FLACKY, COCKTAIL_OPTION_TAG);
        }

        private var tagCache:Object;

        [Embed(source="/../assets/tags/fire-32.png")]
        private var fireBitmap:Class;

        [Embed(source="/../assets/tags/iba-32.png")]
        private var ibaBitmap:Class;

        [Embed(source="/../assets/tags/ice-32.png")]
        private var iceBitmap:Class;

        [Embed(source="/../assets/tags/layer-32.png")]
        private var layerBitmap:Class;

        [Embed(source="/../assets/tags/long-32.png")]
        private var longBitmap:Class;

        [Embed(source="/../assets/tags/recommend-32.png")]
        private var checkedBitmap:Class;

        [Embed(source="/../assets/tags/shot-32.png")]
        private var shotBitmap:Class;

        [Embed(source="/../assets/tags/short-32.png")]
        private var shortBitmap:Class;

        public function getTagByIdAndType(id:Number, type:String, size:Number):Image
        {
            var tag:Image;

            if (size <= 0)
                return tag;

            var cachedTag:Image = tagCache[type + id];

            if (cachedTag)
            {
                tag = new Image();
                tag.toolTip = cachedTag.toolTip;
                tag.source = cachedTag.source;
                tag.smooth = true;
                tag.cacheAsBitmap = true;
                tag.width = size;
                tag.height = size;
            }

            return tag;
        }

        private function addTagToCache(image:Bitmap, title:String, id:Number, type:String):Image
        {
            var newTag:Image = new Image();
            newTag.source = image;
            newTag.toolTip = title;
            tagCache[type + id] = newTag;
            return newTag;
        }
    }
}
