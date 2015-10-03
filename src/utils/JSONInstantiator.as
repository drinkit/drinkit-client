package utils
{
    import flash.utils.describeType;
    import flash.utils.getDefinitionByName;

    public class JSONInstantiator
    {
        public static function createInstance(json:String, classType:Class, strict:Boolean = true):Object
        {
            if (!json || json == "") {
                return null;
            }

            var jsonObject:Object = JSON.parse(json);
            var returnObject:Object;

            if (jsonObject is Array)
            {
                returnObject = [];

                for (var i:uint = 0; i < jsonObject.length; i++)
                {
                    returnObject.push(createSingleObject(jsonObject[i], classType, strict));
                }
            }
            else
                returnObject = createSingleObject(jsonObject, classType, strict);

            return returnObject;
        }

        private static function createSingleObject(singleJSONObject:Object, classType:Class, strict:Boolean):Object
        {
            if (!singleJSONObject)
                return null;

            var returnObject:Object = new classType();
            var propertyMap:XML = describeType(returnObject);
            var propertyTypeClass:Class;

            for each (var property:XML in propertyMap.variable)
            {
                if (singleJSONObject.hasOwnProperty(property.@name))
                {
                    propertyTypeClass = getDefinitionByName(property.@type) as Class;

                    if (singleJSONObject[property.@name] is (propertyTypeClass))
                        returnObject[property.@name] = singleJSONObject[property.@name];
                    else
                        throw new Error("Property '" + property.@name + "' must be '" + property.@type + "'");
                }
                else
                {
                    if (strict)
                        throw new Error("Property '" + property.@name + "' is not exist in json-string!");
                }
            }

            for each (var accessor:XML in propertyMap.accessor)
            {
                if (accessor.access == "readonly")
                    continue;

                if (singleJSONObject.hasOwnProperty(accessor.@name))
                {
                    propertyTypeClass = getDefinitionByName(accessor.@type) as Class;

                    if (singleJSONObject[accessor.@name] == null)
                    {
                        returnObject[accessor.@name] = null;
                        continue;
                    }

                    if (singleJSONObject[accessor.@name] is (propertyTypeClass))
                        returnObject[accessor.@name] = singleJSONObject[accessor.@name];
                    else
                        throw new Error("Accessor '" + accessor.@name + "' must be '" + accessor.@type + "'");
                }
                else
                {
                    if (strict)
                        throw new Error("Accessor '" + accessor.@name + "' is not exist in json-string!");
                }
            }

            return returnObject;
        }
    }
}
