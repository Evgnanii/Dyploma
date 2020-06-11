package com.example.dyploma.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.dyploma.R;
import com.example.dyploma.activities.GameActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseRoleFragment extends DialogFragment implements View.OnClickListener {
    Button buttonPresenter;
    Button buttonPlayer;
    String lobbyName;
    DatabaseReference databaseReferencePassword;
    DatabaseReference databaseReferencePresenter;
    DatabaseReference databaseReferenceCount;
    String password;
    EditText etPassword;
    String presenter;
    String count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_set_role, container, false);
        buttonPlayer = view.findViewById(R.id.btn_player_role);
        buttonPresenter = view.findViewById(R.id.btn_presenter_role);
        buttonPresenter.setOnClickListener(this);
        buttonPlayer.setOnClickListener(this);
        Bundle arguments = getArguments();
        etPassword = view.findViewById(R.id.et_lobby_password);
        lobbyName = arguments.getString("LobbyName");
        databaseReferencePresenter = FirebaseDatabase.getInstance().getReference().child("Lobbies").child(lobbyName).child("presenter");
        databaseReferenceCount = FirebaseDatabase.getInstance().getReference().child("Lobbies").child(lobbyName).child("count");
        databaseReferenceCount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReferencePresenter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                presenter = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                presenter = null;
            }
        });
        databaseReferencePassword = FirebaseDatabase.getInstance().getReference().child("Lobbies").child(lobbyName).child("password");
        databaseReferencePassword.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                password = dataSnapshot.getValue().toString();
                Log.d("password", password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        if (!password.equals(etPassword.getText().toString())) {
            Toast.makeText(getActivity(), "Неверный пароль!", Toast.LENGTH_LONG).show();
        } else {
            switch (v.getId()) {
                case R.id.btn_presenter_role:
                    if (presenter.equals("empty")) {
                        intent.putExtra("Role", "Presenter");
                        intent.putExtra("LobbyName", lobbyName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Ведущий уже есть!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btn_player_role:
                    if (Integer.parseInt(count) <= 2) {
                        FirebaseDatabase.getInstance().getReference().child("Lobbies")
                                .child(lobbyName).child("count").setValue(String.valueOf(Integer.parseInt(count) + 1));
                        intent.putExtra("Role", "Player");
                        intent.putExtra("LobbyName", lobbyName);
                        intent.putExtra("Count", count);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Не свободных слотов для игроков", Toast.LENGTH_LONG).show();
                        break;
                    }
            }
        }
    }


    public static class PlayerFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_player_fragment, container, true);

        }


    }
}

