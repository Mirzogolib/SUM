package inducesmile.com.suumme.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import inducesmile.com.suumme.ObjectClasses.UserInfo;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.Token.Token;
import inducesmile.com.suumme.model.UserDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test";
    EditText editTextName;
    EditText editTextPassword;
    private String token1;
    APIService apiService;
    TextView errorText;
    int backButtonCount=0;
    UserDB userDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDB = UserDB.getUser();

        if (userDB == null) {
            userDB = new UserDB();
            Log.d(TAG, userDB.getToken() + "");
            editTextName = (EditText) findViewById(R.id.editTextUsername);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            Button button = (Button) findViewById(R.id.buttonLogin);
            errorText = (TextView) findViewById(R.id.errorText);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();

                    final UserInfo userInfo = new UserInfo();
                    userInfo.username = editTextName.getText().toString();
                    userInfo.password = editTextPassword.getText().toString();
                    apiService = new APIService();
                    apiService.postUser(userInfo).enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            if (response.isSuccessful()) {
                                Token token = response.body();
                                progressDialog.dismiss();
                                token1 = token.access_token;
                                getDetail();
                                Log.d(TAG, "onResponse: success");
                                userDB.setUsername(userInfo.username);
                                userDB.setPassword(userInfo.password);
                                userDB.setToken(token1);
                                userDB.save();
                                Log.d(TAG, "username " + userDB.getUsername());
                                Log.d(TAG, "password " + userDB.getPassword());
                                Log.d(TAG, userDB.getToken());

                            } else {
                                progressDialog.dismiss();
                                String error = response.message();

                                if (error.equals("Bad Request")) {
                                    errorText.setText("Please enter each gap correctly!");

                                } else {

                                    errorText.setText("Please enter each gap correctly!");
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                            progressDialog.dismiss();
                            t.getMessage();
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        }else{
            token1 = userDB.getToken();
            getDetail();
        }
    }











    private void getDetail() {

        Intent intent = new Intent(this, StatisticsActivity.class);
//        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("token", token1);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        if(backButtonCount >= 1)
        {
            finish();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }

    }






}


