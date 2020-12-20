package com.example.fragdyn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class FragmentDynamique1 extends Fragment {
    private Button Frag1FromActivity;
    private TextView tvFrag1;
    private Button Frag1FromFrag2;
    private Button Frag1ToFrag2;
    private EditText editTextFrag1;
    private FragmentDynamique2 frag2;
    public FragmentDynamique1(){}



    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("messageFromFrag2");
            tvFrag1.setText(message);
        }
    };
    @Override
    public View onCreateView(LayoutInflater layoutI, ViewGroup container, Bundle savedInstanceState){
        View view=layoutI.inflate(R.layout.frag_dyn1,container,false);
        Frag1FromActivity=view.findViewById(R.id.frag1FromActivity);
        Frag1FromFrag2=view.findViewById(R.id.frag1FromFrag2);
        Frag1ToFrag2=view.findViewById(R.id.frag1ToFrag2);
        editTextFrag1=view.findViewById(R.id.editTextTextPersonNameF1);
        tvFrag1=view.findViewById(R.id.tvFrag1);
        frag2=new FragmentDynamique2();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-nameFrag2"));



        Frag1FromActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMainAct=getArguments().getString("editViewMainAct");
                tvFrag1.setText(textMainAct);
            }
        });
        Frag1FromFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-event-nameFrag2"));
            }
        });

        Frag1ToFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void sendMessage() {
        if(editTextFrag1.getText().toString()!=""){
            Log.d("sender", "Broadcasting message");
            Intent intent = new Intent("custom-event-nameFrag1");
            intent.putExtra("messageFromFrag1", editTextFrag1.getText().toString());
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }

    }







}
