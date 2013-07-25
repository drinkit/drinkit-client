package utils
{
	import flash.utils.describeType;
	import flash.utils.getDefinitionByName;

	public class JSONInstantiator
	{
		public static function createInstance(json:String, classType:Class):Object
		{
			var jsonObject:Object = JSON.parse(json);
			var returnObject:Object;
			
			if (jsonObject is Array)
			{
				returnObject = [];
				
				for (var i:uint = 0; i < jsonObject.length; i++)
				{
					returnObject.push(createSingleObject(jsonObject[i], classType));
				}
			}
			else
				returnObject = createSingleObject(jsonObject, classType);
			
			return returnObject;
		}
		
		private static function createSingleObject(singleJSONObject:Object, classType:Class):Object
		{
			var returnObject:Object = new classType();
			var propertyMap:XML = describeType(returnObject);
			var propertyTypeClass:Class;
			
			for each (var property:XML in propertyMap.variable) 
			{
				if (singleJSONObject.hasOwnProperty(property.@name)) 
				{
					propertyTypeClass = getDefinitionByName(property.@type) as Class;
					
					if (singleJSONObject[property.@name] is (propertyTypeClass)) 
					{
						returnObject[property.@name] = singleJSONObject[property.@name];
					}
					else
						throw new Error("Property '" + property.@name + "' must be '" +  property.@type + "'");
				}
				else
					throw new Error("Property '" + property.@name + "' is not exist in json-string!");
			}
			
			return returnObject;
		}
	}
}