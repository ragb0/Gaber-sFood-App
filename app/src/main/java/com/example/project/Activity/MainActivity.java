package com.example.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.Adaptor.CategoryAdaptor;
import com.example.project.Activity.Adaptor.PopularAdaptor;
import com.example.project.Activity.Domain.CategoryDomain;
import com.example.project.Activity.Domain.FoodDomain;
import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory () {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.CartView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Pizza" , "cat_1"));
        category.add(new CategoryDomain("Burger" , "cat_2"));
        category.add(new CategoryDomain("Hotdog" , "cat_3"));
        category.add(new CategoryDomain("Drink" , "cat_4"));
        category.add(new CategoryDomain("Donut" , "cat_5"));

        adapter=new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Pepperoni pizza" , "pizza","slices perpperoni,mozzerella chesse , fresh oregano," +
                " ground black pepper,pizza sauce", 14.1));
        foodlist.add(new FoodDomain("Cheese Burger","pop_2","beef,Gouda Cheese,Special Sauce,Lettuce,tomato",7.9));
        foodlist.add(new FoodDomain("Vegetable pizza","pop_3","olive oil,Vegetable oil" +
                ",pitted kalamata,cherry tomatoes,fresh oregano, basil",5.9));

        adapter2=new PopularAdaptor(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);

    }
    public void Start(View view) {
        startActivity(new Intent(this,ShowDetailActivity.class));

    }

}

