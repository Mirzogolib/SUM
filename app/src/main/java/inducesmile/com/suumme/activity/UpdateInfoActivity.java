package inducesmile.com.suumme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import inducesmile.com.suumme.ObjectClasses.User;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInfoActivity extends AppCompatActivity {


    EditText editTextFirstName, editTextLastName, editTextMiddleName, editTextAddress, editTextPhoneNumber, editTextEmail;
    APIService apiService;
    String token, region;
    Button update;
    int id1;
    String TAG = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        final Intent intent = getIntent();

        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextMiddleName = (EditText) findViewById(R.id.editTextMiddleName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail1);


        update = (Button) findViewById(R.id.buttonUpdate);


        token = intent.getExtras().getString("token");
        apiService = new APIService();

        apiService.getDetail(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                editTextFirstName.setText(response.body().getFirst_name());
                editTextLastName.setText(response.body().getLast_name());
                editTextMiddleName.setText(response.body().getMiddle_name());
                editTextAddress.setText(response.body().getAddress());
                editTextPhoneNumber.setText(response.body().getPhone_number());
                editTextEmail.setText(response.body().getEmail());
                id1 = response.body().getId();
                region = response.body().getRegion();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final User user = new User();
                user.setFirst_name(editTextFirstName.getText().toString());
                user.setLast_name(editTextLastName.getText().toString());
                user.setMiddle_name(editTextMiddleName.getText().toString());
                user.setAddress(editTextAddress.getText().toString());
                user.setPhone_number(editTextPhoneNumber.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setRegion(region);

                apiService.update(token, id1, user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d(TAG, response.body().getFirst_name());
                        Log.d(TAG, response.body().getLast_name());
                        Log.d(TAG, response.body().getMiddle_name());
                        Log.d(TAG, response.body().getAddress());
                        Log.d(TAG, response.body().getPhone_number());
                        Log.d(TAG, response.body().getEmail());

                        Snackbar.make(view, "Successfully saved", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();





                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });


            }
        });
    }


}
