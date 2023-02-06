package com.example.shareva.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shareva.R;
import com.example.shareva.SuccessAddActivty;
import com.example.shareva.SuccessfulListingActivity;
import com.example.shareva.WatchDataBaseHelper;
import com.google.android.material.button.MaterialButton;

public class ShareFragment extends Fragment {

    private Context mContext;

    private ImageView btn_uploadImg1, btn_uploadImg2, btn_uploadImg3, btn_uploadImg4;
    private EditText editTxt_listingTitle, editTXt_shareItemDesc;

    private com.google.android.material.button.MaterialButton btn_34, btn_35, btn_36, btn_37, btn_38, btn_39, btn_40, btn_41;
    private com.google.android.material.button.MaterialButton btn_automatic, btn_quarts, btn_diver, btn_solar, btn_cronograph, btn_spring, btn_military, btn_gmt, btn_aviator;
    com.google.android.material.button.MaterialButton btn_trueVerifyShareListing, btn_falseVerifyShareListing, btn_listItem;

    private static final int requestCamera1 = 12;
    private static final int requestCamera2 = 13;
    private static final int requestCamera3 = 14;
    private static final int requestCamera4 = 15;

    private String listingTitle = " ", watchSize = " ", watchType =" ", desc = " ", verification = " ";

    Drawable drawable1, drawable2, drawable3, drawable4;

    WatchDataBaseHelper watchDataBaseHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        btn_listItem = view.findViewById(R.id.btn_listItem);


        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        //ImageView
        btn_uploadImg1 = v.findViewById(R.id.btn_uploadImg1);
        btn_uploadImg2 = v.findViewById(R.id.btn_uploadImg2);
        btn_uploadImg3 = v.findViewById(R.id.btn_uploadImg3);
        btn_uploadImg4 = v.findViewById(R.id.btn_uploadImg4);

        //EditText
        editTxt_listingTitle = v.findViewById(R.id.editTxt_listingTitle);
        editTXt_shareItemDesc = v.findViewById(R.id.editTXt_shareItemDesc);

        //MaterialButton
        btn_34 = v.findViewById(R.id.btn_34);
        btn_35 = v.findViewById(R.id.btn_35);
        btn_36 = v.findViewById(R.id.btn_36);
        btn_37 = v.findViewById(R.id.btn_37);
        btn_38 = v.findViewById(R.id.btn_38);
        btn_39 = v.findViewById(R.id.btn_39);
        btn_40 = v.findViewById(R.id.btn_40);
        btn_41 = v.findViewById(R.id.btn_41);

        btn_automatic = v.findViewById(R.id.btn_automatic);
        btn_quarts = v.findViewById(R.id.btn_quarts);
        btn_diver = v.findViewById(R.id.btn_diver);
        btn_solar = v.findViewById(R.id.btn_solar);
        btn_cronograph = v.findViewById(R.id.btn_cronograph);
        btn_spring = v.findViewById(R.id.btn_spring);
        btn_military = v.findViewById(R.id.btn_military);
        btn_gmt = v.findViewById(R.id.btn_gmt);
        btn_aviator = v.findViewById(R.id.btn_aviator);

        btn_trueVerifyShareListing = v.findViewById(R.id.btn_trueVerifyShareListing);
        btn_falseVerifyShareListing = v.findViewById(R.id.btn_falseVerifyShareListing);
        btn_listItem = v.findViewById(R.id.btn_listItem);

        initUI();

        pageDirectories();
    }

    private void initUI() {

    }

    private void pageDirectories() {

        watchSizeSelection();

        watchTypeSelection();

        btn_trueVerifyShareListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification = btn_trueVerifyShareListing.getText().toString();
            }
        });

        btn_falseVerifyShareListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification = btn_falseVerifyShareListing.getText().toString();
            }
        });

        btn_listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listingTitle = editTxt_listingTitle.getText().toString();
                desc = editTXt_shareItemDesc.getText().toString();

                validatePic();
                validateListing();
                validateWatchSize();
                validateWatchType();
                validateWatchDesc();
                validateVerification();

                validateInput();
            }
        });

        btn_uploadImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, requestCamera1);
            }
        });

        btn_uploadImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, requestCamera2);
            }
        });

        btn_uploadImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, requestCamera3);
            }
        });

        btn_uploadImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, requestCamera4);
            }
        });
    }

    private void validateInput() {

        if (!validatePic() | !validateListing() | !validateWatchSize() | !validateWatchType() | !validateWatchDesc() | !validateVerification())
        {
            return;
        }
        else
        {
            //Add data to a SQLite database
            watchDataBaseHelper = new WatchDataBaseHelper(mContext);
            boolean isInserted = watchDataBaseHelper.insertData(listingTitle, watchSize, watchType, desc, verification);

            if (isInserted) {
                Toast.makeText(mContext, "Listing inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SuccessAddActivty.class);
                getActivity().startActivity(intent);
            } else {
                Toast.makeText(mContext, "Listing not inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateVerification() {

        if (verification.isEmpty() | verification.equals(" "))
        {
            Toast.makeText(mContext, "Please select a verification type to proceed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean validateWatchDesc() {

        if (desc.isEmpty())
        {
            editTXt_shareItemDesc.setError("Required");
            return false;
        }
        else
            return true;
    }

    private boolean validateWatchType() {

        if (watchType.isEmpty() | watchType.equals(" "))
        {
            Toast.makeText(mContext, "Please select a watch type to proceed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean validateWatchSize() {

        if (watchSize.isEmpty() | watchSize.equals(" "))
        {
            Toast.makeText(mContext, "Please select a watch size to proceed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean validateListing() {

        if (listingTitle.isEmpty())
        {
            editTxt_listingTitle.setError("Required");
            return false;
        }
        else
            return true;
    }

    private boolean validatePic() {

        if ((btn_uploadImg1.getDrawable().equals(R.drawable.upload_picture)) | (btn_uploadImg2.getDrawable().equals(R.drawable.upload_picture)) | (btn_uploadImg3.getDrawable().equals(R.drawable.upload_picture)) | (btn_uploadImg4.getDrawable().equals(R.drawable.upload_picture)))
        {
            Toast.makeText(mContext, "An image is required to proceed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private void watchTypeSelection() {

        btn_automatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_automatic.getText().toString();
            }
        });

        btn_quarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_quarts.getText().toString();
            }
        });

        btn_diver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_diver.getText().toString();
            }
        });

        btn_solar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_solar.getText().toString();
            }
        });

        btn_cronograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_cronograph.getText().toString();
            }
        });

        btn_spring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_spring.getText().toString();
            }
        });

        btn_military.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_military.getText().toString();
            }
        });

        btn_gmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_gmt.getText().toString();
            }
        });

        btn_aviator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchType = btn_aviator.getText().toString();
            }
        });
    }

    private void watchSizeSelection() {

        btn_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_34.getText().toString();
            }
        });

        btn_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_35.getText().toString();
            }
        });

        btn_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_36.getText().toString();
            }
        });

        btn_37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_37.getText().toString();
            }
        });

        btn_38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_38.getText().toString();
            }
        });

        btn_39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_39.getText().toString();
            }
        });

        btn_40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_40.getText().toString();
            }
        });

        btn_41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchSize = btn_41.getText().toString();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCamera1)
        {
            Bitmap imgBitMap = (Bitmap)data.getExtras().get("data");
            btn_uploadImg1.setImageBitmap(imgBitMap);
        }
        else if (requestCode == requestCamera2)
        {
            Bitmap imgBitMap = (Bitmap)data.getExtras().get("data");
            btn_uploadImg2.setImageBitmap(imgBitMap);
        }
        else if (requestCode == requestCamera3)
        {
            Bitmap imgBitMap = (Bitmap)data.getExtras().get("data");
            btn_uploadImg3.setImageBitmap(imgBitMap);
        }
        else if (requestCode == requestCamera4)
        {
            Bitmap imgBitMap = (Bitmap)data.getExtras().get("data");
            btn_uploadImg4.setImageBitmap(imgBitMap);
        }

    }
}