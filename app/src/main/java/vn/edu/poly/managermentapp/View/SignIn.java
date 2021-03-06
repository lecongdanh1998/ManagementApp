package vn.edu.poly.managermentapp.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.edu.poly.managermentapp.Component.BaseActivity;
import vn.edu.poly.managermentapp.R;
import vn.edu.poly.managermentapp.Server.ApiConnect;
import vn.edu.poly.managermentapp.Util.ValidateForm;

public class SignIn extends BaseActivity implements View.OnClickListener {
    private Button btn_signin;
    private EditText edt_password_signIn, edt_user_signIn;
    private String useremail;
    private String userpassword;
    SharedPreferences dataLogin;
    SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    private RelativeLayout layout_your_site;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initView();
        initData();
        initEventButton();
    }
    private void initView() {
        checkInternet(this);
        btn_signin = findViewById(R.id.btn_signIn);
        edt_user_signIn = findViewById(R.id.edt_user_signIn);
        edt_password_signIn = findViewById(R.id.edt_password_signIn);
        layout_your_site = findViewById(R.id.layout_your_site);
        progressDialog = new ProgressDialog(this);
    }

    private void setContentDialog(String title, String messager) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(messager);
    }

    private void initData(){
        dataLogin = getSharedPreferences("data_login", MODE_PRIVATE);
        editor = dataLogin.edit();
    }

    private void initEventButton() {
        btn_signin.setOnClickListener(this);
        layout_your_site.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_signIn:
                SingIn();
                break;
            case R.id.layout_your_site:
                //add website url
                break;
            default:
                break;
        }
    }

    private void intentView(Class c) {
        Intent intent = new Intent(SignIn.this, c);
        startActivity(intent);
        finish();
    }

    private void SingIn(){
        setContentDialog("Sign in", "Please wait...");
        progressDialog.show();
        useremail = edt_user_signIn.getText().toString().trim();
        userpassword = edt_password_signIn.getText().toString().trim();
        RequestQueue requestSignIn = Volley.newRequestQueue(this);
        StringRequest signInRequest = new StringRequest(Request.Method.POST, ApiConnect.URL_SIGNIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("SUCCESS_SIGNIN", response + "");
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    editor.putString("useremail", useremail);
                    editor.putString("userpassword", userpassword);
                    editor.putString("usertoken", jsonObject.getString("token_type") + " " +
                            jsonObject.getString("access_token"));
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(SignIn.this, "Login success!", Toast.LENGTH_SHORT).show();
                intentView(MainActivity.class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("ERROR_SIGNIN", error + "");
                Toast.makeText(SignIn.this, "Vui lòng nhập đúng email và password", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", useremail);
                params.put("password", userpassword);
                params.put("grant_type", "password");
                params.put("client_id", "2");
                params.put("client_secret", "rOU4FPWpj36XDlWvNZBn1S39BZaxFpGyAEtBHBLH");
                return params;
            }
        };

        int errorCode = 0;

        if (new ValidateForm().validateTextEmpty(useremail)){
            edt_user_signIn.setHint("Please enter your email!");
            edt_user_signIn.setHintTextColor(getResources().getColor(R.color.colorPrimary));
            errorCode ++;
        }

        if (new ValidateForm().validateTextEmpty(userpassword)){
            edt_password_signIn.setHint("Please enter your password!");
            edt_password_signIn.setHintTextColor(getResources().getColor(R.color.colorPrimary));
            errorCode ++;
        }

        if (errorCode == 0){
            requestSignIn.add(signInRequest);
        }
//        intentView(MainActivity.class);
    }
}
