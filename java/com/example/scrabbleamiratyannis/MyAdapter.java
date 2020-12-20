package com.example.scrabbleamiratyannis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String[] words;

    public MyAdapter(String[] words){
        this.words=words;
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        TextView word=holder.itemView.findViewById(R.id.word);
        if(words[position]!=null){
            //if(position>0){
                word.setText(words[position]);
            //}
            //else{
             //   word.setText(words.get(position));
           // }

        }

    }

    @Override
    public int getItemCount() {
        return words.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvRow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRow=itemView.findViewById(R.id.word);
        }
    }
}