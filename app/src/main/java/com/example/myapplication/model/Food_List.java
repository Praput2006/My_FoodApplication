package com.example.myapplication.model;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import java.util.ArrayList;

public class Food_List extends AppCompatActivity {

    RecyclerView recyclerView;
    Food_Manu adapter;
    ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodList = new ArrayList<>();
        // URL จริงของภาพอาหาร
        foodList.add(new Food("ข้าวมันไก่", "50 บาท",
                "https://thainipponfoods.com/wp-content/uploads/2022/05/rice-steamed-with-chicken-breast-2-1024x609.jpg"));
        foodList.add(new Food("ส้มตำ", "40 บาท",
                "https://static.thairath.co.th/media/4DQpjUtzLUwmJZZSGolu8o6GGG8vX35u7WHz4BYbj0SO.jpg"));
        foodList.add(new Food("ผัดไทย", "60 บาท",
                "https://www.ajinomoto.co.th/storage/photos/shares/Recipe/Menu/lot2-2/6172a01f7aadd.jpg"));
        foodList.add(new Food("ก๋วยเตี๋ยว", "40 บาท",
                "https://cheewajit.com/app/uploads/2021/04/image-130-edited.png"));

        adapter = new Food_Manu(this, foodList);
        recyclerView.setAdapter(adapter);
    }
}
