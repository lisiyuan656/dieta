package edu.osu.cse5236.group9.dieta;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class NewFoodFragment extends Fragment {
    private static final String CLOUD_VISION_API_KEY = "AIzaSyDPXTUYwMv0sm4DqbNZsP-pqgiueXftSpI";
    public static final String FILE_NAME = "temp.jpg";
    private static final int GALLERY_IMAGE_REQUEST = 1;
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;
    public static final int INTERNET_REQUEST = 4;
    private EditText mNameField;
    private Button mButton_Camera;
    private Button mButton_AddFood;
    private Button mButton_Confirm;
    private List<String> mFoodList;
    private Meal mMeal;
    private Food mFood;
    private ImageView mFoodImage;
    private ProgressDialog mProgress;
    private int mAsyncTaskState;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_food, container, false);
        mFoodList = new ArrayList<>();
        mMeal = new Meal();
        mAsyncTaskState = 0;
        mFoodImage = (ImageView) v.findViewById(R.id.food_image);
        mNameField = (EditText) v.findViewById(R.id.food_name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFood = new Food(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank
            }
        });
        mButton_Camera = (Button) v.findViewById(R.id.camera_button);
        mButton_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder
                        .setMessage(R.string.picture_select_prompt)
                        .setPositiveButton(R.string.picture_select_gallery, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startGalleryChooser();
                            }
                        })
                        .setNegativeButton(R.string.picture_select_camera, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startCamera();
                            }
                        });
                builder.create().show();
            }
        });

        mButton_AddFood = (Button) v.findViewById(R.id.add_food_button);
        mButton_AddFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mFood!=null) mMeal.addFood(mFood);
            }
        });
        mButton_Confirm = (Button) v.findViewById(R.id.confirm_food_button);
        mButton_Confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mAsyncTaskState==0) {
                    Intent i = new Intent(getActivity(), ConfirmActivity.class);
                    i.putStringArrayListExtra("mFoodList", (ArrayList<String>) mFoodList);
                    i.putExtra("mMeal", mMeal);
                    startActivity(i);
                }
                else {
                    if (mProgress==null) {
                        mProgress = new ProgressDialog(getActivity());
                        mProgress.setTitle("Vision");
                        mProgress.setMessage("Still recognizing...");
                        mProgress.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                        mProgress.show();
                    }
                    else mProgress.show();
                }
            }
        });

        return v;
    }

    private void startGalleryChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a photo"), GALLERY_IMAGE_REQUEST);
    }

    private void startCamera() {
        if (PermissionUtils.requestPermission(
                getActivity(),
                CAMERA_PERMISSIONS_REQUEST,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getCameraFile()));
            startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
        } else {
            Toast.makeText(getActivity(), "Permission denied, please choose allow:(", Toast.LENGTH_LONG).show();
        }
    }

    private File getCameraFile() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null) {
            uploadImage(data.getData());
        } else if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            uploadImage(Uri.fromFile(getCameraFile()));
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.permissionGranted(
                requestCode,
                CAMERA_PERMISSIONS_REQUEST,
                grantResults)) {
            startCamera();
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save bandwidth
                Bitmap bitmap = scaleBitmapDown(
                  MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri), 1200);
                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    callCloudVision(bitmap, getActivity());
                    mFoodImage.setImageBitmap(bitmap);
                } else {
                        Toast.makeText(getActivity(), "Network connection not available.", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                Toast.makeText(getActivity(), "Something is wrong with the image", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getActivity(), "Something is wrong with the image", Toast.LENGTH_LONG).show();
        }
    }

    private void callCloudVision(final Bitmap bitmap, final Activity UIActivity) throws IOException {
        // Make a toast:)
        // Toast.makeText(getActivity(), "Uploading image...", Toast.LENGTH_SHORT).show();

        // Do the real work in an async task
        new AsyncTask<Object, Void, List<String>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // DIALOG_DOWNLOAD_PROGRESS is defined as 0 at start of class
                mAsyncTaskState = mAsyncTaskState+1;
            }

            @Override
            protected List<String> doInBackground(Object... params) {
                try {
                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
                    builder.setVisionRequestInitializer(new
                            VisionRequestInitializer(CLOUD_VISION_API_KEY));
                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                            new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
                        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

                        // Add the image
                        Image base64EncodedImage = new Image();
                        // Convert the bitmap to a JPEG
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();

                        // Base64 encode the JPEG
                        base64EncodedImage.encodeContent(imageBytes);
                        annotateImageRequest.setImage(base64EncodedImage);

                        // add the features
                        annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                            Feature labelDetection = new Feature();
                            labelDetection.setType("LABEL_DETECTION");
                            labelDetection.setMaxResults(20);
                            add(labelDetection);
                        }});

                        // Add the list of one thing to the request
                        add(annotateImageRequest);
                    }});

                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchAnnotateImagesRequest);
                    // Due to a bug: requests to Vision containing large images fail when GZipped.
                    annotateRequest.setDisableGZipContent(true);
                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    return convertResponseToList(response);
                } catch (GoogleJsonResponseException e) {
                    UIActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Failed to make API request because ", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    UIActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Failed to make API request because of IOException ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return new ArrayList<String>();
            }
            @Override
            protected void onPostExecute(List<String> result) {
                super.onPostExecute(result);
                // Update food list
                mFoodList.addAll(result);
                mAsyncTaskState = mAsyncTaskState-1;
                if (mAsyncTaskState==0&&mProgress!=null) mProgress.dismiss();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {
        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private List<String> convertResponseToList(BatchAnnotateImagesResponse response) {
        List<String> res = new ArrayList<>();
        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
                res.add(label.getDescription());
            }
        }
        return res;
    }
}
