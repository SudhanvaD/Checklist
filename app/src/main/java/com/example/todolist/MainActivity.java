package com.example.todolist;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ArrayList<SpannableString> sheet = new ArrayList<>();
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<ArrayList<SpannableString>> lists = new ArrayList<ArrayList<SpannableString>>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText item = findViewById(R.id.editText);
        final ListView list2 = findViewById(R.id.list);
        final ArrayAdapter<SpannableString> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sheet);
        list2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list2.setAdapter(adapt);

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

                        sheet.add(0, x);

                        adapt.notifyDataSetChanged();
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

                    sheet.add(0, x);

                    adapt.notifyDataSetChanged();
                    item.getText().clear();
                    return true;

                }

                return false;
            }
        });

        list2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int wh_item = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sheet.remove(wh_item);


                                adapt.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                adapt.notifyDataSetChanged();

                return true;
            }
        });

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            SpannableString temp;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                num++;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (num == 1) {
                            temp = sheet.get(pos);

                            temp.setSpan(new StrikethroughSpan(), 0, sheet.get(pos).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            sheet.add(temp);
                            sheet.remove(pos);
                            adapt.notifyDataSetChanged();
                        } else if (num == 2) {
                            String x = sheet.get(pos).toString();
                            SpannableString c = new SpannableString(x);
                            ForegroundColorSpan textColor = new ForegroundColorSpan(Color.WHITE);
                            StyleSpan bold = new StyleSpan(Typeface.BOLD);
                            AbsoluteSizeSpan sizeText = new AbsoluteSizeSpan(25);
                            c.setSpan(textColor, 0, c.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            c.setSpan(bold, 0, c.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            c.setSpan(sizeText, 0, c.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            sheet.remove(pos);
                            sheet.add(0, c);


                            adapt.notifyDataSetChanged();

                        }
                        num = 0;

                    }
                }, 500);


            }
        });

        Button alpha = findViewById(R.id.alpha);
        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Are you want to CLEAR ALL?")
                        .setMessage("Do you want to delete this")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sheet.clear();


                                adapt.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                adapt.notifyDataSetChanged();


            }
        });

    }

}
