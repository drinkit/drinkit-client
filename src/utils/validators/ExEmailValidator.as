/**
 * Created by Crabar on 12.09.2015.
 */
package utils.validators {
import mx.validators.EmailValidator;
import mx.validators.ValidationResult;

public class ExEmailValidator extends EmailValidator {
    public function ExEmailValidator() {
        super();
    }

    override protected function doValidation(value:Object):Array {
        var results:Array =  super.doValidation(value);
        var email:String = String(value);
        var regexp:RegExp = /[^\x00-\x7F]+/;
        if (regexp.test(email)) {
            results.push(new ValidationResult(
                    true, null, "invalidChar",
                    this.invalidCharError));
        }

        return results;
    }
}
}
