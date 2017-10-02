package com.example.wli5_countbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ${WeiLi5} on ${12}.
 */

public class CounterArrayAdapter extends RecyclerView.Adapter<CounterArrayAdapter.ViewHolder> {
    private ArrayList<Counter> counterArrayList;
    public static final int EDIT_EXIST_COUNTER_CODE=2;
    public ItemDeleteListener deleteListener;
    public Context mcontext;


    public CounterArrayAdapter(ArrayList<Counter> counterArrayList, Context context, ItemDeleteListener deleteListener){
        this.counterArrayList= counterArrayList;
        this.mcontext=context;
        this.deleteListener=deleteListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView date;
        private TextView current;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);


            name = itemView.findViewById(R.id.Name);
            date = itemView.findViewById(R.id.changeDate);
            current = itemView.findViewById(R.id.currentValue);

        }

        @Override
        public void onClick(View view){}

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.array_layout,parent,false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CounterArrayAdapter.ViewHolder holder, final int position) {
        final Counter counter = counterArrayList.get(position);

        String counterName=counter.getName();
        String date =counter.getDate();
        int currentValue=counter.getCurrentValue();

        Button increaseOne = holder.itemView.findViewById(R.id.increaseOne);
        Button decreaseOne = holder.itemView.findViewById(R.id.decreaseOne);
        Button view = holder.itemView.findViewById(R.id.viewCounter);
        Button delete = holder.itemView.findViewById(R.id.deleteCounter);

        increaseOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.incrementCount();
                notifyItemChanged(position);

            }
        });
        decreaseOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.decrementCount();
                notifyItemChanged(position);

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext,editCounterActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("s_name",counter.getName());
                intent.putExtra("s_comment",counter.getComment());
                intent.putExtra("s_init_value",counter.getInitialValue());
                intent.putExtra("s_curr_value",counter.getCurrentValue());
                ((Activity)mcontext).startActivityForResult(intent,EDIT_EXIST_COUNTER_CODE);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterArrayList.remove(position);
                deleteListener.onItemDeleted();
                notifyDataSetChanged();

            }
        });

        holder.name.setText(counterName);
        holder.current.setText(Integer.toString(currentValue));
        holder.date.setText(date);


    }

    @Override
    public int getItemCount(){
        return counterArrayList.size();

    }

    public void onDateReady(Counter counter,int position){
        notifyItemChanged(position);
    }



}
