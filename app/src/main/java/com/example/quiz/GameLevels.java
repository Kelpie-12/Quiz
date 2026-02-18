package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.Level1;
import com.example.quiz.MainActivity;
import com.example.quiz.R;

import java.util.ArrayList;
import java.util.List;

public class GameLevels extends AppCompatActivity {

    private List<TextView> levels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_level);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v-> {

                Intent intent = new Intent(GameLevels.this, MainActivity.class);
                startActivity(intent);

        });
        for (int i = 0; i < 15; i++) {
            int resId=getResources().getIdentifier("selectLevel"+(i+1),"id",getPackageName());
            if (resId != 0) {
                TextView tmp = findViewById(resId);
                if (tmp != null) {
                    final int  index=i;
                    tmp.setOnClickListener(v-> {
                            try {
                                Class<?> cls = Class.forName("com.example.quiz.Level"+(index+1));
                                Intent intent = new Intent(GameLevels.this, cls);
                                startActivity(intent);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                                Log.e("DynamicStart", "Класс " + "Level" +(index+1)+ " не найден");
                            }
                        });
                    levels.add(tmp);
                }
                else {
                    Log.e("Level", "TextView с id selectLevel" + (i+1) + " не найден");
                }
            } else {
                Log.e("Level", "ResID для selectLevel" + (i+1) + " не найден");
        }
        }

    }
}