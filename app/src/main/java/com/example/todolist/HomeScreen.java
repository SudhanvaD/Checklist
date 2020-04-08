package com.example.todolist;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeScreen extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        button = findViewById(R.id.toList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }
    public void openMain(){
        Intent intent = new Intent(this, ListScreen.class);
        startActivity(intent);

    }
}
