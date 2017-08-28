package inducesmile.com.suumme.activity.company.product;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import inducesmile.com.suumme.ObjectClasses.ChoicesAll;
import inducesmile.com.suumme.ObjectClasses.ResultsProd;
import inducesmile.com.suumme.ObjectClasses.UploadObject;
import inducesmile.com.suumme.R;
import inducesmile.com.suumme.Service.APIService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProduct extends Fragment {
    APIService apiService;
    String token;
    EditText editTextProductName, editTextproductType,editTextProductPrice, editTextProductMeasurement, editTextProductDefinition;
    Button buttonProductImage, buttonCreate;
    ImageView productImage;
    public static final int PICK_IMAGE = 100;
    String TAG = "Test";
    String value;

    public static CreateProduct newInstance(String token) {
        CreateProduct createProduct = new CreateProduct();
        Bundle args = new Bundle();
        args.putString("token", token);
        createProduct.setArguments(args);
        return createProduct;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_product, container, false);
        apiService = new APIService();
        token = getArguments().getString("token");
        editTextProductName =(EditText) view.findViewById(R.id.editTextProductName);
//        editTextproductType =(EditText) view.findViewById(R.id.editTextproductType);
        editTextProductPrice =(EditText) view.findViewById(R.id.editTextProductPrice);
        editTextProductMeasurement =(EditText) view.findViewById(R.id.editTextProductMeasurement);
        editTextProductDefinition =(EditText) view.findViewById(R.id.editTextProductDefinition);
        buttonProductImage = (Button) view.findViewById(R.id.buttonProductImage);
        buttonCreate =(Button)view.findViewById(R.id.buttonCreateProduct);
        ArrayList<String> choice = new ArrayList<>();
        choice.add("product_type");
        choice.add("measurements");
        choice.toArray();
        apiService.getAllChoices(token, choice).enqueue(new Callback<ChoicesAll>() {
            @Override
            public void onResponse(Call<ChoicesAll> call, Response<ChoicesAll> response) {
                Log.d(TAG, "parent id" + response.body().getProductTypeList().get(0).getIdOfProduct());
            }

            @Override
            public void onFailure(Call<ChoicesAll> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });

        buttonProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });


        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiService.createProduct(token).enqueue(new Callback<ResultsProd>() {
                    @Override
                    public void onResponse(Call<ResultsProd> call, Response<ResultsProd> response) {
                        response.body().setNameOfProduct(editTextProductName.getText().toString());
                    }

                    @Override
                    public void onFailure(Call<ResultsProd> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        });
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            final File file = new File(filePath);


            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(CreateProduct.this.getContext());
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
                    value = String.valueOf(response.body().getId());
                    Log.d(TAG, "product image id" + value);
                }

                @Override
                public void onFailure(Call<UploadObject> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }
    }
}
