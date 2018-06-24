package com.example.soundloneteamcomp.searcharticle.Fragment_Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.soundloneteamcomp.searcharticle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterFragment extends DialogFragment {
    View root;

    @BindView(R.id.date)
    EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.activity_filter,container,false);
        ButterKnife.bind(this,root);
        setListener();
        return root;
    }

    private void setListener(){
        editText.setFocusableInTouchMode(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Fragment fragment = manager.findFragmentByTag("secondOne");            }
        });

    }

}
