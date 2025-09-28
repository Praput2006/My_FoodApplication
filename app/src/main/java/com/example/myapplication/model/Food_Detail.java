package com.example.myapplication.model;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class Food_Detail extends AppCompatActivity {

    TextView tvFoodName, tvFoodPrice;
    ImageView imgFood;
    RadioGroup rgSpicy;
    Spinner spOption;
    Button btnOrder, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        tvFoodName = findViewById(R.id.tvFoodName);
        tvFoodPrice = findViewById(R.id.tvFoodPrice);
        imgFood = findViewById(R.id.imgFood);
        rgSpicy = findViewById(R.id.rgSpicy);
        spOption = findViewById(R.id.spOption);
        btnOrder = findViewById(R.id.btnOrder);

        // ปุ่มกลับ
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish(); // ปิดหน้า Detail กลับไป MainActivity
        });







        // รับค่า Intent
        String name = getIntent().getStringExtra("foodName");
        String priceStr = getIntent().getStringExtra("foodPrice");
        String imageUrl = getIntent().getStringExtra("foodImage");

        // แปลงราคาเป็นตัวเลข
        int basePrice = Integer.parseInt(priceStr.replaceAll("[^0-9]", ""));

        tvFoodName.setText(name);
        tvFoodPrice.setText(priceStr);
        Glide.with(this).load(imageUrl).into(imgFood);

        // สร้าง options ของ Spinner (ตามชื่ออาหาร)
        String[] options;
        if ("ส้มตำ".equals(name)) {
            options = new String[]{"ไม่เผ็ด", "เผ็ดน้อย", "เผ็ดกลาง", "เผ็ดมาก"};
        } else if ("ผัดไทย".equals(name)) {
            options = new String[]{"ธรรมดา", "เพิ่มเส้น", "เพิ่มกุ้ง"};
        } else if ("ก๋วยเตี๋ยว".equals(name)) {
            options = new String[]{"เส้นเล็ก", "เส้นใหญ่", "บะหมี่"};
        } else {
            options = new String[]{"ตัวเลือกมาตรฐาน"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, options);
        spOption.setAdapter(adapter);

        // ปุ่มสั่งอาหาร
        btnOrder.setOnClickListener(v -> {
            int selectedId = rgSpicy.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "กรุณาเลือกระดับ", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton rb = findViewById(selectedId);
            String spicyLevel = rb.getText().toString();

            int finalPrice = basePrice;

            if ("พิเศษ".equals(spicyLevel)) {
                finalPrice += 5;
            } else if ("ชาเลนจ์".equals(spicyLevel)) {
                finalPrice += 50;
            }

            String option = spOption.getSelectedItem().toString();

            // อัปเดต TextView แสดงราคาจริง
            tvFoodPrice.setText(finalPrice == 0 ? "กินหมดกินฟรี" : finalPrice + " บาท");

            Toast.makeText(this,
                    "สั่ง: " + name +
                            "\nระดับ: " + spicyLevel +
                            "\nตัวเลือก: " + option +
                            "\nราคา: " + (finalPrice == 0 ? "กินหมดกินฟรี" : finalPrice + " บาท"),
                    Toast.LENGTH_LONG).show();
        });

    }
}
