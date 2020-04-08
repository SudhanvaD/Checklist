package com.example.todolist;


import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListScreen extends AppCompatActivity {
    ArrayList<Item> sheet = new ArrayList<>();
    int num = 0;
    RecyclerView list2;
    RecyclerView.LayoutManager layoutM;
    MainAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        //List<ArrayList<SpannableString>> lists = new ArrayList<ArrayList<SpannableString>>();
        list2 = findViewById(R.id.list);
        final EditText item = findViewById(R.id.editText);
        //final ArrayAdapter<SpannableString> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sheet);
        layoutM = new LinearLayoutManager(this);
        mAdapter = new MainAdapter(sheet);
        list2.setLayoutManager(layoutM);
        list2.setAdapter(mAdapter);
        new ItemTouchHelper(itemTHC).attachToRecyclerView(list2);
        item.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                SpannableString x;
                if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (item.getText().toString().equals("ganesh is gay")) {
                        x = new SpannableString("Sudhanva is gay");

                        ForegroundColorSpan textColor = new ForegroundColorSpan(Color.WHITE);
                        StyleSpan bold = new StyleSpan(Typeface.BOLD);
                        AbsoluteSizeSpan sizeText = new AbsoluteSizeSpan(35);
                        x.setSpan(textColor, 0, item.length() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        x.setSpan(bold, 0, item.length() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        x.setSpan(sizeText, 0, item.length() + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Item temp = new Item(x);
                        sheet.add(0, temp);

                        mAdapter.notifyDataSetChanged();
                        item.getText().clear();
                        return true;
                    } else {
                        x = new SpannableString(item.getText());
                    }
                    ForegroundColorSpan textColor = new ForegroundColorSpan(Color.WHITE);
                    StyleSpan bold = new StyleSpan(Typeface.BOLD);
                    AbsoluteSizeSpan sizeText = new AbsoluteSizeSpan(35);
                    x.setSpan(textColor, 0, item.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    x.setSpan(bold, 0, item.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    x.setSpan(sizeText, 0, item.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                    Item temp = new Item(x);
                    sheet.add(0, temp);

                    mAdapter.notifyDataSetChanged();
                    item.getText().clear();
                    return true;

                }

                return false;
            }
        });

//        list2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                final int wh_item = position;
//
//                new AlertDialog.Builder(ListScreen.this)
//                        .setIcon(android.R.drawable.ic_delete)
//                        .setTitle("Are you sure?")
//                        .setMessage("Do you want to delete this")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                sheet.remove(wh_item);
//
//
//                                adapt.notifyDataSetChanged();
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//                adapt.notifyDataSetChanged();
//
//                return true;
//            }
//        });









        Button alpha = findViewById(R.id.alpha);
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ListScreen.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you want to CLEAR ALL?")
                        .setMessage("Do you want to delete this")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sheet.clear();


                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                mAdapter.notifyDataSetChanged();


            }
        });

    }

    SpannableString temp = null;
    ItemTouchHelper.SimpleCallback itemTHC = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            int from = viewHolder.getAdapterPosition();
            int to = viewHolder1.getAdapterPosition();
            Collections.swap(sheet, from, to);
            mAdapter.notifyDataSetChanged();
            return false;
        }


        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            final int pos = viewHolder.getAdapterPosition();
            temp =  sheet.get(pos).str;
            sheet.remove(viewHolder.getAdapterPosition());



            mAdapter.notifyDataSetChanged();
            Snackbar.make(list2, "Deleted "+temp.toString(), Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            Item x = new Item(temp);
                            x.setSeclected(true);
                            sheet.add(pos, x);
                            mAdapter.notifyDataSetChanged();
                        }
                    }).show();

        }
    };
}
