package jp.mydns.sys1yagi.android.androidvalidatorsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.throrinstudio.android.common.libs.validator.Form;
import com.throrinstudio.android.common.libs.validator.Validate;
import com.throrinstudio.android.common.libs.validator.validator.EmailValidator;
import com.throrinstudio.android.common.libs.validator.validator.NotEmptyValidator;

public class AndroidValidatorSampleActivity extends Activity {
    public AndroidValidatorSampleActivity This() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_validator_sample);

        EditText nameEdit = (EditText) findViewById(R.id.name);
        Validate nameValidate = new Validate(nameEdit);
        nameValidate.addValidator(new MinimumLengthValidator(this, 5));

        EditText mailEdit = (EditText) findViewById(R.id.email);
        Validate emailValidate = new Validate(mailEdit);
        NotEmptyValidator emptyValidator = new NotEmptyValidator(this);
        EmailValidator emailValidator = new EmailValidator(this);
        emailValidator.setDomainName("gmail.com");
        emailValidate.addValidator(emailValidator);
        emailValidate.addValidator(emptyValidator);

        final Form form = new Form();
        form.addValidates(nameValidate);
        form.addValidates(emailValidate);
        
        findViewById(R.id.ok_button).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                form.validate();
            }
        });
        
    }
}
