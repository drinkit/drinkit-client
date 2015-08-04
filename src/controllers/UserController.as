/**
 * Created by Crabar on 21.06.2014.
 */
package controllers
{
    import com.adobe.crypto.SHA256;

    public class UserController
    {
        public function UserController()
        {

        }

        protected function encryptPassword(password:String):String
        {
            return SHA256.hash("drinkIt" + password);
        }
    }
}
