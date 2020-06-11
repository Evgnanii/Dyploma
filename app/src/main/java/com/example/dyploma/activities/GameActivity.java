package com.example.dyploma.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dyploma.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class GameActivity extends AppCompatActivity implements ValueEventListener {
    DatabaseReference mRef;
    DatabaseReference users;
    ImageView presenterImage;
    ImageView player1Image;
    ImageView player2Image;
    ImageView player3Image;
    TextView tvPresenterName;
    TextView tvPlayer1Name;
    TextView tvPlayer2Name;
    TextView tvPlayer3Name;
    TextView answer;
    TextView question;
    TextView tvplayer1Points;
    TextView tvplayer2Points;
    TextView tvplayer3Points;
    String presenterName;
    String player1Name;
    String player2Name;
    String player3Name;
    String player1Id;
    String player2Id;
    String player3Id;
    String lobbyName;
    String getPresenterURL;
    DatabaseReference userReference;
    String count;
    String role;
    int currentCount;
    String currentRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        presenterImage = findViewById(R.id.presenter_image);

        presenterName = "empty";
        player1Image = findViewById(R.id.image_player1);
        player2Image = findViewById(R.id.image_player2);
        player3Image = findViewById(R.id.image_player3);
        tvPlayer1Name = findViewById(R.id.tv_player1_nickname);
        tvPlayer2Name = findViewById(R.id.tv_player2_nickname);
        tvPlayer3Name = findViewById(R.id.tv_player3_nickname);
        tvPresenterName = findViewById(R.id.presenter_name);
        tvplayer1Points = findViewById(R.id.tv_player1_points);
        tvplayer2Points = findViewById(R.id.tv_player2_points);
        tvplayer3Points = findViewById(R.id.tv_player3_points);
        Log.d("count before", String.valueOf(count) + "qwe");
        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        Bundle args = getIntent().getExtras();
        role = args.get("Role").toString();
        lobbyName = args.get("LobbyName").toString();
        mRef = FirebaseDatabase.getInstance().getReference().child("Lobbies").child(lobbyName);
        mRef.child("count").addValueEventListener(this);
        mRef.child("player3").addValueEventListener(this);
        mRef.child("player2").addValueEventListener(this);
        mRef.child("player1").addValueEventListener(this);
        mRef.child("presenter").addValueEventListener(this);
        if (role.equals("Presenter")) {
            mRef.child("presenter").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

        } else if (role.equals("Player")) {
            count = args.get("Count").toString();
            Log.d("count before", String.valueOf(count));
            switch (currentCount) {
                case 0:
                    Log.d("here", String.valueOf(count) + "case 0");
                    mRef.child("player1").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    Log.d("correct", role);
                    break;
                case 1:
                    Log.d("here", String.valueOf(count) + "case 1");
                    mRef.child("player2").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    Log.d("correct", role);
                    break;
                case 2:
                    Log.d("here", String.valueOf(count) + "case 2");
                    mRef.child("player3").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    Log.d("correct", role);
                    break;
            }
        }

    }

    @Override
    protected void onStop() {
        Log.d("log", "on stop" + currentRole);
        switch (currentRole) {
            case "presenter":
                mRef.child("presenter").setValue("empty");
            case "player1":
                mRef.child("player1").setValue("empty");
                mRef.child("count").setValue(String.valueOf(currentCount - 1));
            case "player2":
                mRef.child("player2").setValue("empty");
                mRef.child("count").setValue(String.valueOf(currentCount - 1));
            case "player3":
                mRef.child("player3").setValue("empty");
                mRef.child("count").setValue(String.valueOf(currentCount - 1));
        }
        super.onStop();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        switch (dataSnapshot.getKey()) {
            case "count":

                currentCount = Integer.valueOf(dataSnapshot.getValue().toString());

                if (dataSnapshot.getValue().equals("3") && !presenterName.equals("empty")) {
                    startGame();
                }
                break;
            case "presenter":
                Glide.with(getBaseContext()).using(new FirebaseImageLoader())
                        .load(FirebaseStorage.getInstance().getReference().child(dataSnapshot.getValue().toString()))
                        .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.ic_boy)
                        .into(presenterImage);
                currentRole = "presenter";
                Log.d("log", currentRole);
                userReference.child(dataSnapshot.getValue().toString()).child("playerName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (!dataSnapshot.getValue().toString().equals("empty")) {
                            Log.d("log", dataSnapshot1.getValue().toString());
                            presenterName = dataSnapshot1.getValue().toString();
                            tvPresenterName.setText(presenterName);
                            if (currentCount == 3) {
                                startGame();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "player1":
                Glide.with(getBaseContext()).using(new FirebaseImageLoader())
                        .load(FirebaseStorage.getInstance().getReference().child(dataSnapshot.getValue().toString()))
                        .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.ic_boy)
                        .into(player1Image);
                currentRole = "player1";
                userReference.child(dataSnapshot.getValue().toString()).child("playerName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                        if (!dataSnapshot.getValue().toString().equals("empty")) {
                            player1Name = dataSnapshot1.getValue().toString();
                            tvPlayer1Name.setText(player1Name);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "player2":
                currentRole = "player2";
                Glide.with(getBaseContext()).using(new FirebaseImageLoader())
                        .load(FirebaseStorage.getInstance().getReference().child(dataSnapshot.getValue().toString()))
                        .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.ic_boy)
                        .into(player2Image);

                userReference.child(dataSnapshot.getValue().toString()).child("playerName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (!dataSnapshot.getValue().toString().equals("empty")) {
                            player2Name = dataSnapshot1.getValue().toString();
                            tvPlayer2Name.setText(player2Name);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "player3":
                Glide.with(getBaseContext()).using(new FirebaseImageLoader())
                        .load(FirebaseStorage.getInstance().getReference().child(dataSnapshot.getValue().toString()))
                        .skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .error(R.drawable.ic_boy)
                        .into(player3Image);
                currentRole = "player3";
                userReference.child(dataSnapshot.getValue().toString()).child("playerName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                        if (!dataSnapshot.getValue().toString().equals("empty")) {
                            player3Name = dataSnapshot1.getValue().toString();
                            tvPlayer3Name.setText(player3Name);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                break;
        }
    }

    private void startGame() {
        Log.d("qwe", "StartGame!");
        if (role.equals("Presenter")) {
            View.OnClickListener clickToChooseFirst = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("qwe", "WORk!");
                    switch (v.getId()) {
                        case R.id.image_player1:
                            mRef.child("CreatorName").setValue("player1");
                            break;
                        case R.id.image_player2:
                            mRef.child("CreatorName").setValue("player2");
                            break;
                        case R.id.image_player3:
                            mRef.child("CreatorName").setValue("player3");
                            break;
                    }
                }
            };
            player1Image.setOnClickListener(clickToChooseFirst);
            player2Image.setOnClickListener(clickToChooseFirst);
            player3Image.setOnClickListener(clickToChooseFirst);
        }
        mRef.child("CreatorName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (currentRole.equals(dataSnapshot.getValue())) setActive();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setActive() {
        Log.d("qwe", "you are active");

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
