package com.example.todolist;

import android.text.SpannableString;

//
public class Item {
    public boolean isSelceted;
    public SpannableString str;


    public boolean isSelected() {
        return isSelceted;
    }
    public void setSeclected(boolean check) {
        isSelceted = check;
    }

    public SpannableString getStr() {
        return str;
    }
    public Item (SpannableString x){
        str = x;
    }
}
