/**
 * Created by Crabar on 28.06.2014.
 */
package controllers
{
    public interface IOAuthController
    {
        function init():void;

        function login(provider:String):void;
    }
}
