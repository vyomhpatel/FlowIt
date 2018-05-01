package b12app.vyom.com.flowit.register;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.malinkang.rxvalidator.RxValidator;
import com.malinkang.rxvalidator.ValidationFail;
import com.malinkang.rxvalidator.ValidationResult;
import com.malinkang.rxvalidator.annotations.MaxLength;
import com.malinkang.rxvalidator.annotations.MinLength;
import com.malinkang.rxvalidator.annotations.NotEmpty;
import com.malinkang.rxvalidator.annotations.RegExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.login.LoginActivity;
import b12app.vyom.com.flowit.model.CommonResBody;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.functions.Action1;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    @NotEmpty(order = 1, message = "firstname can't be empty")
    EditText firstnameEt;
    TextInputLayout firstnameTextInputLayout;

    @NotEmpty(order = 2, message = "lastname can't be empty")
    EditText lastnameEt;
    TextInputLayout lastnameTextInputLayout;



    @NotEmpty(order = 3, message = "mobile can't be empty")
    @RegExp(order = 4, message = "Please input valid phone number", regexp = "^(?:\\+?1[-.●]?)?\\(?([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})$")
    EditText mobileTxt;
    TextInputLayout mobileTextInputLayout;

    @NotEmpty(order = 5, message = "password can't be empty")
    @MinLength(order = 6, length = 6, message = "password length can't <6")
    @MaxLength(order = 7, length = 12, message = "password length can't >12")
    EditText passwordTxt;
    TextInputLayout passwordTextInputLayout;


    @NotEmpty(order = 8, message = "company size can't be empty")
    EditText companyEt;
    TextInputLayout companyTextInputLayout;

    @NotEmpty(order = 9, message = "email can't be empty")
    @RegExp(order = 10, message = "Please input valid email!", regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")
    EditText email;
    TextInputLayout emailTextInputLayout;

    Map<EditText, TextInputLayout> inputLayoutMap = new HashMap<>();
    Button sinupbtn;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        firstnameEt = findViewById(R.id.firstTxt);
        lastnameEt = findViewById(R.id.lastnameTxt);
        sinupbtn = findViewById(R.id.signupbtn);
        mobileTxt = findViewById(R.id.mobileTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        companyEt = findViewById(R.id.companysizeTxt);
        email = findViewById(R.id.emailTxt);
        spinner = findViewById(R.id.spinner);

        firstnameTextInputLayout = findViewById(R.id.first_name_text_input_layout);
        lastnameTextInputLayout = findViewById(R.id.last_name_text_input_layout);
        mobileTextInputLayout = findViewById(R.id.mobile_text_input_layout);
        passwordTextInputLayout = findViewById(R.id.password_text_input_layout);
        companyTextInputLayout = findViewById(R.id.companysize_text_input_layout);
        emailTextInputLayout = findViewById(R.id.email_text_input_layout);

        inputLayoutMap.put(lastnameEt, lastnameTextInputLayout);
        inputLayoutMap.put(firstnameEt, firstnameTextInputLayout);
        inputLayoutMap.put(mobileTxt, mobileTextInputLayout);
        inputLayoutMap.put(passwordTxt, passwordTextInputLayout);
        inputLayoutMap.put(companyEt, companyTextInputLayout);
        inputLayoutMap.put(email, emailTextInputLayout);

/*
        Observable<CharSequence> firstnameObservable = RxTextView.textChanges(firstnameEt);
        Observable<CharSequence> lastnameObservable = RxTextView.textChanges(lastnameEt);
        Observable<CharSequence> mobileObservable = RxTextView.textChanges(mobileTxt);
        Observable<CharSequence> passwordObservable = RxTextView.textChanges(passwordTxt);
        Observable<CharSequence> companyObservable = RxTextView.textChanges(companyEt);
        Observable<CharSequence> emailObservable = RxTextView.textChanges(email);

        rx.Observable.combineLatest(firstnameObservable, lastnameObservable, mobileObservable,
                passwordObservable, companyObservable, emailObservable,
                new Func6<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {


                    @Override
                    public Boolean call(CharSequence firstnameObservable, CharSequence lastnameObservable, CharSequence mobileObservable
                            , CharSequence passwordObservable, CharSequence companyObservable, CharSequence emailObservable) {
                    if(!TextUtils.isEmpty(firstnameObservable) && !TextUtils.isEmpty(lastnameObservable))
                    {
                    return true;
                    }
                    else if(TextUtils.isEmpty(firstnameObservable))
                    {

                    }
                    else if(TextUtils.isEmpty(lastnameObservable))
                    {

                    }
                    return false;
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean isBtnAvailalbe) {
                RxView.enabled(sinupbtn).call(isBtnAvailalbe);
            }
        })
        ;

*/


        sinupbtn.setOnClickListener(this);



    }


    Subscription subscription;
    private boolean isValid;
    @Override
    public void onClick(View v) {
        if (subscription == null || subscription.isUnsubscribed()) {
            subscription = RxValidator.validate(this).subscribe(new Action1<ValidationResult>() {
                @Override
                public void call(ValidationResult validationResult) {
                    isValid = validationResult.isValid;
                    for (EditText editText : inputLayoutMap.keySet()) {
                        TextInputLayout textInputLayout = inputLayoutMap.get(editText);
                        textInputLayout.setErrorEnabled(false);
                    }
                    if (!validationResult.isValid) {
                        ArrayList<ValidationFail> errors = validationResult.getFails();
                        for (ValidationFail fail : errors) {
                            TextInputLayout textInputLayout = inputLayoutMap.get(fail.getView());
                         //   textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError(fail.getMessage());

                        }
                    }
                }
            });
        }
        if (isValid) {

            String firstname = firstnameEt.getText().toString();
            String lastname = lastnameEt.getText().toString();

            String mobile = mobileTxt.getText().toString();
            String password = passwordTxt.getText().toString();
            String companysize = companyEt.getText().toString();
            String email1 = email.getText().toString();
            String role = spinner.getSelectedItem().toString();

            ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
            Call<String> call = apiService.postUser(firstname,lastname,email1,mobile,password,companysize,role);
            Log.i("ceshi", firstname + lastname + mobile + password + companysize + email1);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.i("test",""+response.body());
                    String result = String.valueOf(response.body());
                    if(result.contains("successfully")){
                      Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                      startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                     Log.i("test","error "+ t);
                }
            });

        }
    }
}
