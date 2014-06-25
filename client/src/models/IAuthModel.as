package models
{
	import flash.display.Stage;

	public interface IAuthModel
	{
		function init(stage:Stage):void;
		function login(provider:String):void;
	}
}