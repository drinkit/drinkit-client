/**
 * Created by Crabar on 28.07.2014.
 */
package controllers {
import controllers.supportClasses.Services;

import flash.net.URLRequestMethod;

import models.supportClasses.Ingredient;

import mx.controls.Alert;

import utils.JSONUtil;
import utils.ServiceUtil;
import utils.supportClasses.JSRequest;

public class IngredientAdminController {
    public function IngredientAdminController(model:Ingredient) {
        _model = model;
    }

    [Bindable]
    private var _model:Ingredient;

    public function createNewIngredient():void {
        var createRequest:JSRequest = new JSRequest(URLRequestMethod.POST);
        _model.id = NaN;
        createRequest.bodyParams = JSONUtil.escapeSpecialChars(JSON.stringify(_model));
        createRequest.contentType = "application/json;charset=UTF-8";
        createRequest.expectedStatus = 201;
        ServiceUtil.instance.sendRequest(Services.INGREDIENTS, createRequest, onCreateIngredient);
    }

    public function saveCurrentIngredient():void {
        var saveRequest:JSRequest = new JSRequest(URLRequestMethod.PUT);
        saveRequest.bodyParams = JSONUtil.escapeSpecialChars(JSON.stringify(_model));
        saveRequest.contentType = "application/json;charset=UTF-8";
        saveRequest.expectedStatus = 204;
        ServiceUtil.instance.sendRequest(Services.INGREDIENTS + _model.id, saveRequest, onSaveIngredient);
    }

    public function loadIngredient(ingredient:Ingredient):void {
        _model.id = ingredient.id;
        _model.name = ingredient.name;
        _model.description = ingredient.description;
        _model.category = ingredient.category;
        _model.vol = ingredient.vol;
        _model.alias = ingredient.alias;
    }

    public function deleteIngredient():void {
        var deleteRequest:JSRequest = new JSRequest(URLRequestMethod.DELETE);
        deleteRequest.expectedStatus = 204;
        deleteRequest.expectedErrorStatus = 409;
        ServiceUtil.instance.sendRequest(Services.INGREDIENTS + _model.id, deleteRequest, onDeleteIngredient, onDeleteIngredientError);
    }

    private function onDeleteIngredientError(response:String):void {
        Alert.show("Вы не можете удалить ингредиент, так как он используется в существующих коктейлях!");
    }

    private function onCreateIngredient(response:String):void {
        Alert.show("Ингредиент успешно создан");
        MainController.instance.requestIngredients();
    }

    private function onSaveIngredient(response:String):void {
        Alert.show("Ингредиент успешно обновлен");
        MainController.instance.requestIngredients();
    }

    private function onDeleteIngredient(response:String):void {
        Alert.show("Ингредиент успешно удален");
        MainController.instance.requestIngredients();
    }
}
}
