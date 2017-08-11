package inducesmile.com.suumme.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import inducesmile.com.suumme.activity.company.CompanyOrderList;
import inducesmile.com.suumme.activity.company.CompanyProductList;
import inducesmile.com.suumme.activity.company.ProductFragmentCompany;
import inducesmile.com.suumme.activity.shop.ShopOrderList;
import inducesmile.com.suumme.activity.shop.ShopProductList;
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
    ProductFragmentCompany productFragmentCompany;

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
        images = (Button) findViewById(R.id.buttonImage);


        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateImage();
            }
        });

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

    private void updateImage() {
//        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
//        openGalleryIntent.setType("image/*");
//        startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

//        String path = Environment.getExternalStorageDirectory().toString()+"media";
//        File filew  = new File(path);
//        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
//        String path = Environment.getExternalStorageDirectory().toString();
//        Uri imageUri;
//
//        File file = new File("/storage/emulated/0/Download/1.jpg");
//        RequestBody requestFile =RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(StatisticsActivity.this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        File file = new File("/storage/emulated/0/Download/1.jpg");
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("uploaded_file", file.getName(), requestFile);
        apiService.uploadFile(token, body).enqueue(new Callback<UploadObject>() {
            @Override
            public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                Log.d(TAG, "ok");
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UploadObject> call, Throwable t) {

            }
        });


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

        if (id == R.id.nav_product) {
            setTitle("Product List");
            if (type.equals("0")) {

                fragmentCompanyProduct = CompanyProductList.newInstance(token);
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

                ShopOrderList shopOrderList = new ShopOrderList();
                fragmentManager.beginTransaction().replace(R.id.frameLayout, shopOrderList).commit();

            }
        } else if (id == R.id.nav_user) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra("token", token);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            userDB.delete();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {

            int idProduct = 4;
            productFragmentCompany = ProductFragmentCompany.newInstance(token, idProduct);
            fragmentManager.beginTransaction().replace(R.id.frameLayout, productFragmentCompany).commit();

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


}
