package com.example.scrabbleamiratyannis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.Data;
import model.Dictionary;
import model.ScrabbleComparator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button search;
    private EditText editLetters;
    private Dictionary dictionary;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=findViewById(R.id.search);
        editLetters=findViewById(R.id.editLetters);
        recyclerView=findViewById(R.id.recyclerViewResult);
        InputStream is=this.getResources().openRawResource(R.raw.frutf8);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        try {
            dictionary = new Dictionary(is);

            Context c=this;

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(editLetters.getText().toString()!=""){
                        //List<String> words=dictionary.getWordsThatCanBeComposed(editLetters.getText().toString().toCharArray());
                        String[] result=dictionary.resultToArray(editLetters.getText().toString().toCharArray());
                        //Log.e("word",words.get(0));
                        //words.add(0,words.size()+" word(s) found:");
                        MyAdapter myAdapter=new MyAdapter(result);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(myAdapter);
                    }
                    /*new Thread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });*/
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }



        //boolean[]
        /*try {

            String myString="eeelv";
            if(dictionary.mayBeComposed("élève",myString.toCharArray())){
                Log.e("Test","élève may be composed with letters e,e,e,l,v");
            }
            myString="cehin";
            if(dictionary.mayBeComposed("Chine",myString.toCharArray())){
                Log.e("Test","Chine may be composed with letters c,e,h,i,n");
            }

           /* if(dictionary.mayBeComposed("Xe",myString.toCharArray())){
                Log.e("test","web");
            }*/
          /*  myString="bai*";
            List<String> words=dictionary.getWordsThatCanBeComposed(myString.toCharArray());
            Log.e("test","be* may be composed by:");
            for(int i=0;i<words.size();++i){
                Log.e("test",""+words.get(i));
            }

            //Log.e("Dictionnary",dictionary.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }


}