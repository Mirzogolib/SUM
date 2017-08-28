package inducesmile.com.suumme.activity.all;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import inducesmile.com.suumme.ObjectClasses.UploadObject;
import inducesmile.com.suumme.ObjectClasses.User;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import inducesmile.com.suumme.activity.company.order.CompanyOrderList;
import inducesmile.com.suumme.activity.company.product.CompanyProductList;
import inducesmile.com.suumme.activity.company.product.ProductFragmentCompany;
import inducesmile.com.suumme.activity.shop.order.ShopOrderList;
import inducesmile.com.suumme.activity.shop.product.ShopProductList;
import inducesmile.com.suumme.model.UserDB;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    APIService apiService;
    private TextView firstName, email, address, firstNameText, id1, userType;
    private Button update, images;
    private String token;
    ImageView personPhoto;
    int backButtonCount = 0;
    String type;

    int idProfile;
    String TAG = "test";
    UserDB userDB;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int REQUEST_GALLERY_CODE = 200;
    ShopProductList fragmentShopProduct;
    CompanyProductList fragmentCompanyProduct;
    CompanyOrderList fragmentCompanyOrder;
    ShopOrderList fragmentShopOrder;
    ProductFragmentCompany productFragmentCompany;

    public static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        userDB = UserDB.getUser();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        apiService = new APIService();
        update = (Button) findViewById(R.id.buttonUpdate);
        firstName = (TextView) findViewById(R.id.person_name);
        firstNameText = (TextView) findViewById(R.id.firstName);
        email = (TextView) findViewById(R.id.person_email);
        address = (TextView) findViewById(R.id.address);
        id1 = (TextView) findViewById(R.id.id);

        personPhoto = (ImageView) findViewById(R.id.person_photo);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeView1);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendInfo();
            }
        });

        Intent intent = getIntent();
        token = "Token " + intent.getExtras().getString("token");
        apiService.getDetail(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {


                idProfile = response.body().getId();
                type = response.body().getUser_type();
                //   PHOTO
                if (response.body().getPhoto() == null) {
                    personPhoto.setImageResource(R.drawable.sum);
                } else {
                    Glide.with(StatisticsActivity.this).load(response.body().getPhoto()).into(personPhoto);
                }
                personPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
                    }
                });

                firstName.setText(response.body().getFirst_name());
                firstNameText.setText(response.body().getFirst_name() + " " + response.body().getLast_name() + " " + response.body().getMiddle_name());
                email.setText(response.body().getEmail());
                address.setText("Address: " + response.body().getAddress());
                id1.setText("Your id number is: " + response.body().getId());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                Toast.makeText(StatisticsActivity.this, "Sorry you have to check internet connection", Toast.LENGTH_LONG);

            }
        });


    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        frameLayout.removeAllViews();

        int id = item.getItemId();

        if(id==R.id.nav_home){
            Intent intent = new Intent(this, StatisticsActivity.class);
            intent.putExtra("token", token);
            startActivity(intent);
        }else if(id == R.id.nav_product) {
            setTitle("Product List");
            if (type.equals("0")) {
                Log.d(TAG, "with id "+ idProfile);
                fragmentCompanyProduct = CompanyProductList.newInstance(token, idProfile);
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentCompanyProduct).commit();

            } else {

                fragmentShopProduct = ShopProductList.newInstance(token);
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentShopProduct).commit();

            }

        } else if (id == R.id.nav_order) {
            setTitle("Order List");
            if (type.equals("0")) {
                Log.d(TAG, "Success it is in companyOrder");
                fragmentCompanyOrder = CompanyOrderList.newInstance(token);
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentCompanyOrder).commit();

            } else {
                Log.d(TAG, "Success it is in shopOrder");
                fragmentShopOrder = ShopOrderList.newInstance(token);
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentShopOrder).commit();
            }
        } else if (id == R.id.nav_user) {

        } else if (id == R.id.nav_logout) {
            userDB.delete();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void sendInfo() {
        Intent intent = new Intent(this, UpdateInfoActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);


    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        Log.d(TAG, "refreshed");
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                apiService.getDetail(token).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {


                        idProfile = response.body().getId();
                        type = response.body().getUser_type();
                        //photo
                        if (response.body().getPhoto() == null) {
                            personPhoto.setImageResource(R.drawable.sum);
                        } else {
                            Glide.with(StatisticsActivity.this).load(response.body().getPhoto()).into(personPhoto);
                        }
                        firstName.setText(response.body().getFirst_name());
                        firstNameText.setText(response.body().getFirst_name() + " " + response.body().getLast_name() + " " + response.body().getMiddle_name());
                        email.setText(response.body().getEmail());
                        address.setText("Address: " + response.body().getAddress());
                        id1.setText("Your id number is: " + response.body().getId());


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(StatisticsActivity.this, "Sorry you have to check internet connection", Toast.LENGTH_LONG);

                    }
                });
            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            final File file = new File(filePath);


            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(StatisticsActivity.this);
            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            final MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

            Log.d("THIS", data.getData().getPath());

            apiService.uploadFile(token, body).enqueue(new Callback<UploadObject>() {
                @Override
                public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                    Log.d(TAG, "ok");
                    progressDialog.dismiss();
                    String value = String.valueOf(response.body().getId());
                    Glide.with(StatisticsActivity.this).load(response.body().getFile()).into(personPhoto);
                    Log.d(TAG, "id is "+ value);
                    final User user = new User();
                    user.setPhoto(value);
                    apiService = new APIService();
                    apiService.updatePatch(token, idProfile, user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.d(TAG, response.body().getPhoto());
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });


                }

                @Override
                public void onFailure(Call<UploadObject> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }
    }

}
