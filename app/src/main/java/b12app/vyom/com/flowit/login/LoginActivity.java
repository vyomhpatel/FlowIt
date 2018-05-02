package b12app.vyom.com.flowit.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.home.HomeActivity;
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

//import static b12app.vyom.com.flowit.networkutils.RetrofitInstance.BASE_URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //SHA1: 68:55:9C:36:9B:80:F6:FA:F2:C6:84:D2:2F:88:EA:91:57:09:35:81
 //   SHA256: 74:B7:05:7D:1D:EC:55:37:AE:C9:67:FD:9B:60:BD:30:9A:08:93:A5:0E:8D:CE:74:3B:31:4E:D9:2B:01:9D:49
    @BindView(R.id.btn_signup)
    Button btn_signup;

    @BindView(R.id.btnlogin)
    Button btnlogin;

    @BindView(R.id.emailTxtLogin)
    TextView emailTxt;

    @BindView(R.id.passwordTxtLogin)
     TextView pwTxt;

    @BindView(R.id.checkBox)
    CheckBox checkBox;

   // @BindView(R.id.sign_in_button)
    com.google.android.gms.common.SignInButton googlesignin;

    Retrofit retrofit;


    public static final String BASE_URL = "http://rjtmobile.com/aamir/pms/android-app/";




    private static final int RC_SIGN_IN = 007 ;
    private static final String TAG = "sign on test" ;
    private GoogleSignInClient mGoogleSignInClient;
    private RelativeLayout container;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);


        container = findViewById(R.id.relativeLt);


        sharedPreferences = getSharedPreferences("local_user", Context.MODE_PRIVATE);



         //Configure sign-in to request the user's ID, email address, and basic
         //profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googlesignin = findViewById(R.id.sign_in_button);


        googlesignin.setSize(SignInButton.SIZE_STANDARD);
        googlesignin.setOnClickListener(this);
        signOut();

//        String ischecked = sharedPreferences.getString("ischecked","");
//        if(ischecked.equals("true")){
//            emailTxt.setText(sharedPreferences.getString("email",""));
//            pwTxt.setText(sharedPreferences.getString("pass",""));
//        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
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
            String email  = emailTxt.getText().toString();
            String pass = pwTxt.getText().toString();


//        if(checkBox.isChecked()){
//            sharedPreferences.edit().putString("ischecked","true").commit();
//            sharedPreferences.edit().putString("email",email).commit();
//            sharedPreferences.edit().putString("pass",pass).commit();
//
//        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://rjtmobile.com/aamir/pms/android-app/pms_login.php?email=" + email + "&password=" + pass
                , null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response);

                try {
                    String userid =   response.getString("userid");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userid", userid);
                    editor.putString("email",response.getString("userfirstname"));
                    editor.putString("firstname",response.getString("useremail"));
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: "+error);
            }
        });


        Volley.newRequestQueue(this).add(request);
    }


    public void signIn(){
        Log.i("mytest","into signIn()");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {

            Log.i(TAG, "updateUI: "+"\n display name: "+account.getDisplayName()+"" +
                    "\n email: "+account.getEmail()+"" +
                    "n"+account.getFamilyName()
                    +"\n "+account.zzabc());


            String username = account.getEmail();
            String firstname = account.getGivenName();
            String lastname = account.getFamilyName();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email",username);
            editor.putString("firstname",firstname);
            editor.putString("lastname",lastname);
            editor.putString("userid","22");
            editor.commit();




//            Snackbar.make(container,"Display Name: "+account.getDisplayName() +
//                    "\n Email: "+account.getEmail()+"" +
//                    "\n Family Name: "+account.getFamilyName(),Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
//                }
//            }).show();


        } else {
            Snackbar.make(container,"Login fail", Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;

        }
    }

    public void signOut(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        updateUI(null);
                        // [END_EXCLUDE]
                    }
                });
    }



}
