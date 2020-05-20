package com.example.dyploma.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.dyploma.R;
import com.example.dyploma.activities.GameActivity;

public class ChooseRoleFragment extends DialogFragment implements View.OnClickListener {
    Button buttonPresenter;
    Button buttonPlayer;
    String lobbyName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_set_role, container, false);
        buttonPlayer = view.findViewById(R.id.btn_player_role);
        buttonPresenter = view.findViewById(R.id.btn_presenter_role);
        buttonPresenter.setOnClickListener(this);
        buttonPlayer.setOnClickListener(this);
        Bundle arguments = getArguments();
        lobbyName = arguments.getString("LobbyName");
        Log.d("qweq", lobbyName);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), GameActivity.class);
        switch (v.getId()) {
            case R.id.btn_player_role:
                intent.putExtra("Role", "Player");
            case R.id.btn_presenter_role:
                intent.putExtra("Role", "Presenter");
        }
        intent.putExtra("LobbyName", lobbyName);
        startActivity(intent);
    }

    public static class PlayerFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.activity_player_fragment, container, true);

        }


    }
}

