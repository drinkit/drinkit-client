/**
 * Created by Crabar on 10.07.2014.
 */
package controllers {

    import com.adobe.images.PNGEncoder;

    import controllers.supportClasses.Services;

    import flash.display.Bitmap;

    import flash.display.BitmapData;
    import flash.display.Shape;
    import flash.events.Event;
    import flash.geom.Matrix;
    import flash.geom.Point;
    import flash.geom.Rectangle;
    import flash.net.URLRequestMethod;
    import flash.utils.ByteArray;

    import models.CocktailAdminModel;
    import models.CocktailModel;
    import models.IngredientsModel;
    import models.supportClasses.Ingredient;

    import mx.binding.utils.BindingUtils;

    import mx.collections.ArrayList;

    import mx.controls.Alert;
    import mx.utils.Base64Encoder;

    import utils.ArrayUtil;
    import utils.JSONInstantiator;
    import utils.JSONUtil;
    import utils.ServiceUtil;
    import utils.supportClasses.JSRequest;

    public class CocktailAdminController {
        public function CocktailAdminController(model:CocktailAdminModel) {
            _model = model;
        }

        private var _model:CocktailAdminModel;
        private var _lastCocktailModel:CocktailModel;

        public function updateCocktailId(value:Number):void {
            _model.cocktailId = value;
        }

        public function addIngredientToCocktail(ingredient:Ingredient):void {
            _model.selectedIngredientsList.addItem({id: ingredient.id, name: ingredient.name, quantity: ""});
        }

        public function removeIngredientFromCocktail(ingredientId:Number):void {
            for (var i:int = 0; i < _model.selectedIngredientsList.length; i++) {
                var object:Object = _model.selectedIngredientsList.getItemAt(i);

                if (object.id == ingredientId) {
                    _model.selectedIngredientsList.removeItemAt(i);
                    return;
                }
            }
        }

        public function updateOptions(selectedOptions:Vector.<Object>):void {
            _model.selectedOptions = ArrayUtil.fromVectorToArray(selectedOptions.map(itemToId));
        }

        public function setCocktailType(selectedType:Object):void {
            _model.cocktailTypeId = selectedType.value;
        }

        public function loadCocktailInfo(id:Number):void {
            _model.clear();
            var request:JSRequest = new JSRequest();
            ServiceUtil.instance.sendRequest(Services.RECIPES + id, request, onCocktailInfoLoad);
        }

        public function updateImageClipRect(clipRect:Rectangle):void {
            _model.imageClipRect = clipRect;
        }

        public function saveNewCocktailToDB():void {
            if (!validateCurrentCocktail())
                return;

            var cocktail:Object = {};
            cocktail.name = _model.name;
            cocktail.description = _model.description;
            cocktail.options = _model.selectedOptions;
            cocktail.cocktailIngredients = convertSelectedIngredientsToIngredientsWithQuantities(_model.selectedIngredientsList);
            cocktail.cocktailTypeId = _model.cocktailTypeId;
            var images:Array = cropAndSerializeCocktailImage(_model.image, _model.imageClipRect);
            cocktail.thumbnail = images[0];
            cocktail.image = images[1];
            var request:JSRequest = new JSRequest(URLRequestMethod.POST);
            request.bodyParams = JSONUtil.escapeSpecialChars(JSON.stringify(cocktail));
            request.contentType = "application/json;charset=UTF-8";
            ServiceUtil.instance.sendRequest(Services.RECIPES, request, onCocktailSave);

        }

        public function saveCocktailToDB():void {
            if (!validateCurrentCocktail())
                return;

            var cocktail:Object = {};
            cocktail.id = _model.cocktailId;
            cocktail.name = _model.name;
            cocktail.description = _model.description;
            cocktail.options = _model.selectedOptions;
            cocktail.cocktailIngredients = convertSelectedIngredientsToIngredientsWithQuantities(_model.selectedIngredientsList);
            cocktail.cocktailTypeId = _model.cocktailTypeId;
            var images:Array = cropAndSerializeCocktailImage(_model.image, _model.imageClipRect);
            cocktail.thumbnail = images[0];
            cocktail.image = images[1];
            var request:JSRequest = new JSRequest(URLRequestMethod.PUT);
            request.bodyParams = JSONUtil.escapeSpecialChars(JSON.stringify(cocktail));
            request.expectedStatus = 204;
            request.contentType = "application/json;charset=UTF-8";
            ServiceUtil.instance.sendRequest(Services.RECIPES + _model.cocktailId, request, onCocktailSave);
        }

        public function updateImage(content:Bitmap):void {
            _model.image = content;
        }

        public function deleteCocktail():void {
            var deleteRequest:JSRequest = new JSRequest(URLRequestMethod.DELETE);
            deleteRequest.expectedStatus = 204;
            ServiceUtil.instance.sendRequest(Services.RECIPES + _model.cocktailId, deleteRequest, onCocktailDelete);
        }

        private function itemToId(item:*, index:int, array:Vector.<Object>):uint {
            return item.value;
        }

        private function onCocktailInfoLoad(response:String):void {
            if (response == "")
                return;

            _lastCocktailModel = JSONInstantiator.createInstance(response, CocktailModel, false) as CocktailModel;
            _model.cocktailId = _lastCocktailModel.id;
            _model.name = _lastCocktailModel.name;
            _model.description = _lastCocktailModel.description;
            _model.cocktailTypeId = _lastCocktailModel.cocktailTypeId;
            _model.selectedOptions = _lastCocktailModel.options;
            BindingUtils.bindProperty(_model, "image", _lastCocktailModel, "bigImage");
            _model.selectedIngredientsList = convertIngredientsWithQuantitiesToSelectedIngredients(_lastCocktailModel.cocktailIngredients);
            _model.dispatchEvent(new Event("modelUpdated"));
        }

        private function convertIngredientsWithQuantitiesToSelectedIngredients(source:Array):ArrayList {
            var result:Array = [];
            var curIngredient:Object;
            var ingredient:Array;

            for (var i:int = 0; i < source.length; i++) {
                ingredient = source[i];
                curIngredient = {id: ingredient[0], name: IngredientsModel.instance.getIngredientNameById(ingredient[0]), quantity: ingredient[1]};
                result.push(curIngredient)
            }

            return new ArrayList(result);
        }

        private function convertSelectedIngredientsToIngredientsWithQuantities(source:ArrayList):Array {
            var result:Array = [];
            var curIngredient:Object;
            var ingredient:Array;

            for (var i:int = 0; i < source.length; i++) {
                curIngredient = source.getItemAt(i);
                ingredient = [curIngredient.id, curIngredient.quantity];
                result.push(ingredient);
            }

            return result;
        }

        private function validateCurrentCocktail():Boolean {
            var errorString:String = "";

            if (_model.name == "")
                errorString += "Нет имени коктейля\n";

            if (_model.description == "")
                errorString += "Нет описания коктейля\n";

            if (isNaN(_model.cocktailTypeId))
                errorString += "Не выбран тип коктейля\n";

            if (_model.selectedIngredientsList.length == 0)
                errorString += "Нет ингредиентов\n";

            if (errorString == "") {
                return true;
            }
            else {
                Alert.show(errorString);
                return false;
            }
        }

        private function onCocktailSave(response:String):void {
            _lastCocktailModel = JSONInstantiator.createInstance(response, CocktailModel, false) as CocktailModel;
            _model.cocktailId = _lastCocktailModel.id;
            Alert.show("Коктейль успешно сохранен.");
        }

        private function cropAndSerializeCocktailImage(image:Bitmap, clipRect:Rectangle):Array {
            if (!image) {
                return [null, null];
            }

            var bigImageBD:BitmapData = new BitmapData(CocktailModel.BIG_IMAGE_WIDTH, CocktailModel.BIG_IMAGE_HEIGHT);
            var smallImageBD:BitmapData = new BitmapData(CocktailModel.SMALL_IMAGE_WIDTH,
                                                         CocktailModel.SMALL_IMAGE_HEIGHT);
            //
            var croppedImageBD:BitmapData = new BitmapData(clipRect.width, clipRect.height);
            croppedImageBD.copyPixels(image.bitmapData, clipRect, new Point());
            //
            var bigScaleFactor:Number = CocktailModel.BIG_IMAGE_WIDTH / clipRect.width;
            var smallScaleFactor:Number = CocktailModel.SMALL_IMAGE_WIDTH / CocktailModel.BIG_IMAGE_WIDTH;

            bigImageBD = scaleImage(croppedImageBD, bigScaleFactor, CocktailModel.BIG_IMAGE_WIDTH,
                                    CocktailModel.BIG_IMAGE_HEIGHT);// .draw(croppedImageBD, new Matrix(bigScaleFactor, 0, 0, bigScaleFactor), null, null, null, true);
            smallImageBD = scaleImage(bigImageBD, smallScaleFactor, CocktailModel.SMALL_IMAGE_WIDTH,
                                      CocktailModel.SMALL_IMAGE_HEIGHT);

            var base64encoder:Base64Encoder = new Base64Encoder();
            var bigImageBytes:ByteArray = PNGEncoder.encode(bigImageBD);
            var smallImageBytes:ByteArray = PNGEncoder.encode(smallImageBD);

            base64encoder.insertNewLines = false;
            base64encoder.encodeBytes(smallImageBytes);
            var smallImageEncoded:String = base64encoder.toString();
            base64encoder.reset();
            base64encoder.encodeBytes(bigImageBytes);
            var bigImageEncoded:String = base64encoder.toString();

            return [smallImageEncoded, bigImageEncoded];
        }

        private function scaleImage(source:BitmapData, scaleFactor:Number, width:Number, height:Number):BitmapData {
            var result:BitmapData = new BitmapData(width, height);
            var sh:Shape = new Shape();
            sh.graphics.beginBitmapFill(source, new Matrix(scaleFactor, 0, 0, scaleFactor), false, true);
            sh.graphics.lineStyle(0, 0, 0);
            sh.graphics.drawRect(0, 0, width, height);
            sh.graphics.endFill();
            result.draw(sh, null, null, null, null, false);
            return result;
        }

        private function onCocktailDelete(response:String):void {
            Alert.show("Коктейль успешно удален!");
        }
    }
}
