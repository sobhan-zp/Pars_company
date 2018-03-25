package com.pars.company.user.user_fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.pars.company.Network.AppController;
import com.pars.company.NextActivityFile;
import com.pars.company.R;
import com.pars.company.SavePref;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class User_FragmentSendFile extends Fragment {


    private Context contInst;
    private View view;
    private LinearLayoutManager mLayoutManager;

    Bitmap bitmap;
    SavePref save;

    ImageView imgFileSendfile, imgGallerySendfile, imgCameraSendfile, viewImage;
    Button btnNextSendFile;
    Bitmap bitMap;
    static int TAKE_PICTURE = 1;

    int PERMISSION_ALL = 1;
    int CAMERA_PIC_REQUEST = 150;
    int GALLERY_PIC_REQUEST = 151;

    private FilePickerDialog dialog;

    private boolean is_image;


    private FragmentActivity myContext;


    public static User_FragmentSendFile newInstance() {

        Bundle args = new Bundle();
        User_FragmentSendFile fragment = new User_FragmentSendFile();
        fragment.setArguments(args);
        return fragment;
    }






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment_sendfile, container, false);
        contInst = getActivity();
        //java code



        save = new SavePref(getContext());

        /*if(!checkPermission()){
            requestPermission();
        }*/


        String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        if (!hasPermissions(getActivity(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }

        imgFileSendfile = (ImageView) view.findViewById(R.id.img_file_sendfile);
        imgGallerySendfile = (ImageView) view.findViewById(R.id.img_gallrty_sendfile);
        imgCameraSendfile = (ImageView) view.findViewById(R.id.img_camera_sendfile);
        viewImage = (ImageView) view.findViewById(R.id.viewImage);
        btnNextSendFile = (Button) view.findViewById(R.id.btn_next_sendFile);

        btnNextSendFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (is_image) {
                    uploadImage(bitmap);
                } else {
                    Toast.makeText(getActivity(), "لطفا عکس را وارد کنید", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //Create a DialogProperties object.
        final DialogProperties properties = new DialogProperties();

        dialog = new FilePickerDialog(contInst, properties);
        dialog.setTitle("Select a File");
        dialog.setPositiveBtnName("Select");
        dialog.setNegativeBtnName("Cancel");
        //Setting selection mode to multiple selection.
        properties.selection_mode = DialogConfigs.MULTI_MODE;
        //properties.selection_mode = DialogConfigs.SINGLE_MODE;
        //Setting selection type to files and directories.
        properties.selection_type = DialogConfigs.FILE_AND_DIR_SELECT;
        properties.error_dir = new File("/mnt");
        //Set new properties of dialog.
        dialog.setProperties(properties);

//                Pre marking of files in Dialog
//                ArrayList<String> paths=new ArrayList<>();
//                paths.add("/mnt/sdcard/.VOD");
//                paths.add("/mnt/sdcard/.VOD/100.jpg");
//                paths.add("/mnt/sdcard/.VOD/1000.jpg");
//                paths.add("/mnt/sdcard/.VOD/1010.jpg");
//                paths.add("/mnt/sdcard/.VOD/1020.jpg");
//                paths.add("/mnt/sdcard/.VOD/1070.jpg");
//                dialog.markFiles(paths);


        imgFileSendfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

            }
        });


        imgCameraSendfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

        imgGallerySendfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, GALLERY_PIC_REQUEST);
            }
        });

        ///java code
        return view;
    }



    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == GALLERY_PIC_REQUEST && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            bitmap = BitmapFactory.decodeFile(picturePath);

            is_image = !is_image;
            viewImage.setImageBitmap(bitmap);
        }


        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK && null != data) {
            bitmap = (Bitmap) data.getExtras().get("data");
            /*bitmap = rotate(bitmap, )
            viewImage.setImageBitmap(bitmap);*/

            int angleToRotate = getRoatationAngle(getActivity(), 0);
            // Solve image inverting problem
            angleToRotate = angleToRotate + 180;
            bitmap = rotate(bitmap, angleToRotate);
            is_image = !is_image;
            viewImage.setImageBitmap(bitmap);
        }


    }


    public static int getRoatationAngle(Activity mContext, int cameraId) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = mContext.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == 0) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        return result;
    }


    public String getStringImage(Bitmap bmp) {  //This is what converts the image to 64encoded format string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, baos);  //Thos is what compresses image hence loosing bytes
        byte[] imageBytes = baos.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void uploadImage(final Bitmap bmp) {
        // Showing the progress dialog
        try {
            final ProgressDialog loading = ProgressDialog.show(getActivity(),
                    "درحال ارسال تصویر", "لطفا منتظر بمانید...", false, false);
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, AppController.URL_UPLOAD_IMAGE_DATA,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            // Disimissing the progress dialog
                            loading.dismiss();

                            Intent intent = new Intent(getActivity(), NextActivityFile.class);
                            startActivity(intent);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    try { // Dismissing the progress dialog
                        loading.dismiss();
                        // Showing toast

                        Intent intent = new Intent(getActivity(), NextActivityFile.class);
                        startActivity(intent);

                        //Toast.makeText(getContext(), "volleyError: "+volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (Exception r) {
                        Toast.makeText(getContext(), "onerrorresponse: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    // Converting Bitmap to String
                    String image = getStringImage(bmp);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                    String currentDateandTime = sdf.format(new Date());

                    save.save(AppController.SAVE_IMAGE_NAME, currentDateandTime + ".jpg");

                    // Creating parameters
                    Map<String, String> params = new Hashtable<String, String>();

                    // Adding parameters
                    params.put("myimage", image);
                    params.put("date", currentDateandTime);

                    return params;
                }
            };
            // Creating a Request Queue
            stringRequest.setShouldCache(false);
            //myRequestQueue.getCache().clear();
            AppController.getInstance().addToRequestQueue(stringRequest, "UPLOAD");
        } catch (Exception r) {
            Toast.makeText(getContext(), "out: " + r.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public static Bitmap rotate(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        //       mtx.postRotate(degree);
        mtx.setRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }


}

