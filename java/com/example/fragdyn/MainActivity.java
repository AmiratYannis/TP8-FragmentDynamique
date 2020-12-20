package com.example.fragdyn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button createFrag1;
    private  Button createFrag2;
    private Button ActivityToFrag1;
    private Button ActivityToFrag2;
    private Button ActivityFromFrag2;
    private EditText inputActivity;

    private String textFrag1;
    private String textFrag2;
    private FragmentDynamique1 frag1;
    private FragmentDynamique2 frag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFrag1=findViewById(R.id.createFrag1);
        createFrag2=findViewById(R.id.createFrag2);
        ActivityToFrag1=findViewById(R.id.toFrag1);
        ActivityToFrag2=findViewById(R.id.toFrag2);
        //ActivityFromFrag2=findViewById(R.id)
        inputActivity=findViewById(R.id.editTextMainAct);
        textFrag1="";
        textFrag2="";
        final View fragView1=getLayoutInflater().inflate(R.layout.frag_dyn1,null);


        frag1=new FragmentDynamique1();
        frag2=new FragmentDynamique2();
        createFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame1,frag1);
                ft.commit();
            }
        });
        createFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.frame2,frag2);
                ft.commit();
            }
        });

        ActivityToFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("testTOFrag1","toFrag1");
                if(inputActivity.getText().toString()!=""){
                    Bundle bundle=new Bundle();
                    bundle.putString("editViewMainAct",inputActivity.getText().toString());
                    frag1.setArguments(bundle);

                    //Log.e("estInput: ",textFrag1);
                }
            }
        });

        ActivityToFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputActivity.getText().toString()!=""){
                    Bundle bundle=new Bundle();
                    bundle.putString("editViewMainAct",inputActivity.getText().toString());
                    frag2.setArguments(bundle);
                }
            }
        });




    }




}