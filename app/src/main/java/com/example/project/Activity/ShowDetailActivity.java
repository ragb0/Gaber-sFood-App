package com.example.project.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project.Activity.Domain.FoodDomain;
import com.example.project.Activity.Helper.MangementCart;
import com.example.project.R;

public class ShowDetailActivity extends AppCompatActivity {
private TextView addToCartBtn;
private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
private ImageView plusBtn,MinusBtn,picFood;
private FoodDomain object;
int numberOrder=1;
private MangementCart mangementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        mangementCart=new MangementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object=(FoodDomain) getIntent().getSerializableExtra("object");


        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);
    titleTxt.setText(object.getTitle());
    feeTxt.setText("$"+object.getFee());
    descriptionTxt.setText(object.getDescription());
    numberOrderTxt.setText(String.valueOf(numberOrder));


    plusBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            numberOrder=numberOrder+1;
            numberOrderTxt.setText(String.valueOf(numberOrder));
        }
    });
        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){ numberOrder=numberOrder-1;}
                numberOrderTxt.setText(String.valueOf(numberOrder));

            }

        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           object.setNumberInCart(numberOrder);
           mangementCart.insertFood(object);
            }
        });

    }


    private void initView(){
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        plusBtn=findViewById(R.id.plusBtn);
        MinusBtn=findViewById(R.id.minusBtn);
        picFood=findViewById(R.id.picfood);


    }


}