package inducesmile.com.suumme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import inducesmile.com.suumme.ObjectClasses.User;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {

    APIService apiService;

    private TextView firstName, email, address, firstNameText, id, userType;
    private Button update;
    private String token;
    ImageView personPhoto;
    String userComp = "0";
    String userShop = "1";
    String TAG = "LoG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


    apiService = new APIService();

        update = (Button) findViewById(R.id.buttonUpdate);
        firstName = (TextView) findViewById(R.id.person_name);
        firstNameText = (TextView)findViewById(R.id.firstName);
        email = (TextView)findViewById(R.id.person_email);
        address = (TextView)findViewById(R.id.address);
        id = (TextView)findViewById(R.id.id);

        personPhoto = (ImageView) findViewById(R.id.person_photo1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInfo();
            }
        });

        Intent intent = getIntent();

        token = intent.getExtras().getString("token");
//        token = "Token " + intent.getExtras().getString("token");

        apiService.getDetail(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                Log.d(TAG, response.body().getUser_type());
                if (response.body().getUser_type().equals(userComp)) {

                    userType.setText("You are Company! Welcome");
                } else if(response.body().getUser_type().equals(userShop)) {
                    userType.setText("You are Shop! Welcome");
                }
                switch (response.body().getId()){
                    case 5:
                        personPhoto.setImageResource(R.drawable.person1);
                        break;
                    case 2:
                        personPhoto.setImageResource(R.drawable.person2);
                        break;
                    case 4:
                        personPhoto.setImageResource(R.drawable.mine);
                        break;
                    default:
                        personPhoto.setImageResource(R.drawable.sum);
                }

                firstName.setText(response.body().getFirst_name());
                firstNameText.setText(response.body().getFirst_name() + " " + response.body().getLast_name() + " " + response.body().getMiddle_name());
                email.setText(response.body().getEmail());
                address.setText("Address: " + response.body().getAddress());
                id.setText("Your id number is: " + response.body().getId());

//                Log.d( "onResponse: " , response.body().getFirst_name());
//                Toast.makeText(getApplicationContext(),"" + user.getId(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


                Toast.makeText(UserInfoActivity.this, "Sorry you have to check internet connection", Toast.LENGTH_LONG);

            }
        });


    }

    private void sendInfo() {

      Intent intent = new Intent(this, UpdateInfoActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}
