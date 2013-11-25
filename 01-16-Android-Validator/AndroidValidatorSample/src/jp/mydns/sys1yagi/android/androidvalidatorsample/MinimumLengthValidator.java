/**
 * 
 */
package jp.mydns.sys1yagi.android.androidvalidatorsample;

import android.content.Context;

import com.throrinstudio.android.common.libs.validator.AbstractValidator;
import com.throrinstudio.android.common.libs.validator.ValidatorException;

/**
 * 最低限必要な文字列の長さをチェックする
 * @author yagitoshihiro
 */
public class MinimumLengthValidator extends AbstractValidator {
    private int mMinimumLength = 0;
    public MinimumLengthValidator(Context c, int minimumLength) {
        super(c);
        mMinimumLength = minimumLength;
    }
    @Override
    public boolean isValid(String value) throws ValidatorException {
        if (value != null) {
            return value.length() >= mMinimumLength;
        }
        return false;
    }
    @Override
    public String getMessage() {
        return "Please enter " + mMinimumLength + " or more characters.";
    }
}
