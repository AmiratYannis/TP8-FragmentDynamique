package com.example.fragdyn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class FragmentDynamique2 extends Fragment {
    private Button Frag2FromActivity;
    private TextView tvFrag2;
    private Button Frag2ToActivity;
    private EditText editTextFrag2;
    private Button Frag2FromFrag1;
    private Button Frag2ToFrag1;
    private FragmentDynamique1 frag1;
    public FragmentDynamique2(){}




    @Override
    public View onCreateView(LayoutInflater layoutI, ViewGroup container, Bundle savedInstanceState){
        View view= layoutI.inflate(R.layout.frag_dyn2,container,false);
        final View mainview=getLayoutInflater().inflate(R.layout.activity_main, null);
        Frag2FromActivity=view.findViewById(R.id.frag2FromActivity);
        Frag2ToActivity=view.findViewById(R.id.frag2ToActivity);
        Frag2FromFrag1=view.findViewById(R.id.frag2fromFrag1);
        Frag2ToFrag1=view.findViewById(R.id.frag2ToFrag1);
        frag1=new FragmentDynamique1();
        tvFrag2=view.findViewById(R.id.tvFrag2);
        editTextFrag2=view.findViewById(R.id.editTextTextPersonNameF2);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-nameFrag1"));
        Frag2FromActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMainAct=getArguments().getString("editViewMainAct");
                tvFrag2.setText(textMainAct);
            }
        });

        Frag2ToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextFrag2.getText().toString()!=""){
                    Bundle bundle=new Bundle();
                    bundle.putString("editViewFrag2",editTextFrag2.getText().toString());
                    Intent intent=getActivity().getIntent();
                    intent.putExtras(bundle);
                }

            }
        });
        Frag2ToFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        Frag2FromFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-event-nameFrag1"));
            }
        });

        return view;
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void sendMessage() {
        if(editTextFrag2.getText().toString()!=""){
            Log.d("sender", "Broadcasting message");
            Intent intent = new Intent("custom-event-nameFrag2");
            intent.putExtra("messageFromFrag2", editTextFrag2.getText().toString());
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }

    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("messageFromFrag1");
            tvFrag2.setText(message);
        }
    };


}
