package com.example.myapplication.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Food_Manu extends RecyclerView.Adapter<Food_Manu.ViewHolder> {

    private Context context;
    private ArrayList<Food> foodList;

    public Food_Manu(Context context, ArrayList<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice());

        Glide.with(context)
                .load(food.getImageUrl())
                .placeholder(R.drawable.images)
                .error(R.drawable.images)
                .into(holder.imgFood);

        holder.itemView.setOnClickListener(v -> {
            // แสดง Toast ก่อน
            Toast.makeText(context, "คุณเลือก: " + food.getName(), Toast.LENGTH_SHORT).show();

            // เปิดหน้าใหม่
            Intent i = new Intent(context, Food_Detail.class);
            i.putExtra("foodName", food.getName());
            i.putExtra("foodPrice", food.getPrice());
            i.putExtra("foodImage", food.getImageUrl());

            // ใส่ FLAG เผื่อ context ไม่ใช่ Activity (ป้องกัน crash)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView imgFood;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgFood = itemView.findViewById(R.id.imgFood);
        }
    }
}
