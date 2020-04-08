package com.example.todolist;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CheckBox;
import java.util.ArrayList;
import com.example.todolist.ListScreen;
import com.example.todolist.R;



class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<Item> sheets;
    public MainAdapter(ArrayList<Item> sheet) {
        sheets = sheet;
    }
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i)
    {
        viewHolder.checkBox.setOnCheckedChangeListener(null);


        //if true, your checkbox will be selected, else unselected

        viewHolder.checkBox.setChecked(sheets.get(i).isSelected());

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sheets.get(viewHolder.getAdapterPosition()).setSeclected(isChecked);
            }
        });
        viewHolder.tItem.setText(sheets.get(i).str);
    }

    @Override
    public int getItemCount() {
        return sheets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tItem;
        public CheckBox checkBox;
        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tItem =  itemView.findViewById(R.id.item);
            checkBox = (CheckBox) itemView.findViewById(R.id.check);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int postion = getAdapterPosition();
                        if(postion != RecyclerView.NO_POSITION){
                            listener.onItemClick(postion);
                        }
                    }
                }
            });
        }
    }
}
