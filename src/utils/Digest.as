package utils
{
    import com.adobe.crypto.MD5;

    public class Digest
    {
        public function Digest(login:String, password:String, requestMethod:String)
        {
            _login = login;
            _password = password;
            _requestMethod = requestMethod;
        }

        private var _login:String;
        private var _password:String;
        private var _requestMethod:String;

        public function getHA1(realm:String):String
        {
            return MD5.hash(_login + ":" + realm + ":" + _password);
        }

        public function generateAuthHeader(responseHeader:String):Object
        {
            var digest:Object;

            // Convert the array to an object for easy access to each member.
            var responseObject:Object = parseDigestValues(responseHeader);

            // Choose QOP based on available options; auth-int is always preferred.
            var qop:String = ("qop" in responseObject) ? (responseObject.qop === "auth,auth-int" || responseObject.qop === "auth-int,auth") ?
                    "auth-int" : responseObject.qop : "default";

            // Nonce Count - incremented by the client.
            var nc:String = "00000001";

            // Generate a client nonce value for auth-int protection.
            var cnonce:String = MD5.hash(Math.random().toString());

            // Remove the realm to get just the uri
            var uri:String = "";
            digest = {
                "name": "Authorization",
                "value": "Digest " + "username=\"" + _login /* Username we are using to gain access. */ + "\", " + "realm=\"" + responseObject.realm /* Same value we got from the server.    */ + "\", " + "nonce=\"" + responseObject.nonce /* Same value we got from the server.    */ + "\", " + "uri=\"" + uri /* URI that we are attempting to access. */ + "\", " + // TODO URI?! vs URL?!
                "qop=" + qop /* QOP as decided upon above.            */ + ", " + "nc=" + nc /* Nonce Count as decided upon above.    */ + ", " + "cnonce=\"" + cnonce /* Client generated nonce value.         */ + "\", " + "response=\"" + this.generateResponse(responseObject, qop, nc, cnonce, uri) /* Generate a hashed response based on HTTP Digest specifications. */ + "\", " + "opaque=\"" + responseObject.opaque /* Same value we got from the server.    */ + "\""
            };

            return digest;
        }

        private function generateResponse(responseObject:Object, qop:String, nc:String, cnonce:String, uri:String):String
        {
            var hash:String;
            var HA1:String;
            var HA2:String;

            HA1 = getHA1(responseObject.realm);

            // HA2
            switch (qop)
            {
                //				case "auth-int":
                //				{
                //					HA2 = MD5.hash(this.urlRequest.method + ":" + uri + ":" + MD5.hash(this.urlRequest.data.toString()));
                //					break;
                //				}
                case "auth":
                default:
                {
                    HA2 = MD5.hash(_requestMethod + ":" + uri);
                    break;
                }
            }

            // Response Hash
            switch (qop)
            {
                case "auth":
                case "auth-int":
                {
                    hash = MD5.hash(HA1 + ":" + responseObject.nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + HA2);
                    break;
                }
                default:
                {
                    hash = MD5.hash(HA1 + ":" + responseObject.nonce + ":" + HA2);
                    break;
                }
            }

            return hash;
        }

        private function parseDigestValues(authHeader:String):Object
        {
            var obj:Object = {};
            var digestString:String = authHeader;
            var digestArray:Array;
            var i:uint;

            // First, remove "Digest " from the begining of the string.
            digestArray = digestString.split(" ");
            digestArray.shift();

            // Join the remaining elements back together.
            digestString = digestArray.join(" ");

            // Next, split on ", " to get strings of key="value" pairs.
            digestArray = digestString.split(/,\s+/);

            // Finally, we break each item in the array on "="
            for (i = 0; i < digestArray.length; i++)
            {
                var item:Array = digestArray[i].split("=");
                item[1] = item[1].replace(/"/g, '');
                obj[item[0]] = item[1];
            }

            return obj;
        }
    }
}
