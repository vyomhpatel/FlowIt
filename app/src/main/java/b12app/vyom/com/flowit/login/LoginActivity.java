package b12app.vyom.com.flowit.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;
import b12app.vyom.com.flowit.register.SignupActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static b12app.vyom.com.flowit.networkutils.RetrofitInstance.BASE_URL;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.btn_signup)
    Button btn_signup;

    @BindView(R.id.btnlogin)
    Button btnlogin;

    @BindView(R.id.emailTxtLogin)
    TextView emailTxt;

    @BindView(R.id.passwordTxtLogin)
     TextView pwTxt;

    Retrofit retrofit;

    public static final String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @OnClick(R.id.btn_signup)
    public void onViewClicked()
    {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnlogin)
    public void loginClicked()
    {

        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<User> userCall = apiService.getUser(emailTxt.getText().toString(),pwTxt.getText().toString());

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //Log.i("test", ""+response.body());
               User user = response.body();
               String username = response.body().getUserfirstname();
        Log.i("test", "username is "+response.body().getUserfirstname());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("","Login fail");
            }
        });

    }
}
