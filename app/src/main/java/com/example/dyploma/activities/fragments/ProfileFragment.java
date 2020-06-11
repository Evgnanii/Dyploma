package com.example.dyploma.activities.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dyploma.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends DialogFragment {
    Button btnSave;
    ImageView imageProfile;
    TextView tvName;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("qwe", "created");
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        userId = FirebaseAuth.getInstance().getUid();
        tvName = view.findViewById(R.id.et_player_nickname);
        imageProfile = view.findViewById(R.id.profile_image);
        btnSave = view.findViewById(R.id.btn_save_nickname);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("playerName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvName.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    if (tvName.getText().equals(null)) {
                        Toast.makeText(getActivity(), "Введите имя", Toast.LENGTH_LONG).show();
                    } else
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).child("playerName").setValue(tvName.getText().toString());
                }
            }
        });


        imageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });
        storageReference = FirebaseStorage.getInstance().getReference();
        Log.d("qwe", String.valueOf(storageReference.child(FirebaseAuth.getInstance().getUid())));
        Glide.with(getActivity()).using(new FirebaseImageLoader())
                .load(FirebaseStorage.getInstance().getReference().child(FirebaseAuth.getInstance().getUid()))
                .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.ic_boy)
                .into(imageProfile);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                uploadImage(imageUri);
            }
        }

    }

    private void uploadImage(Uri imageUri) {
        StorageReference fileReference = storageReference.child(FirebaseAuth.getInstance().getUid());
        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageProfile);
                        FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(FirebaseAuth.getInstance()
                                        .getUid())
                                .child("ImageURL")
                                .setValue(FirebaseStorage
                                        .getInstance()
                                        .getReference()
                                        .child(FirebaseAuth.getInstance()
                                                .getUid())
                                        .toString());
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Ошибка, попробуйте позже.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
