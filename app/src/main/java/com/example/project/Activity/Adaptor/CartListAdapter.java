package com.example.project.Activity.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Activity.Domain.FoodDomain;
import com.example.project.Activity.Helper.MangementCart;
import com.example.project.Activity.Interface.ChangeNumberItemsListner;
import com.example.project.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>
{
private ArrayList<FoodDomain> foodDomains;
private MangementCart mangementCart;
private ChangeNumberItemsListner changeNumberItemsListner;

    public CartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListner changeNumberItemsListner) {
        this.foodDomains = foodDomains;
        this.mangementCart = new MangementCart(context);
        this.changeNumberItemsListner = changeNumberItemsListner;
    }


    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.title.setText(foodDomains.get(position).getTitle());
    holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
    holder.totalEachitem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart()*foodDomains.get(position).getFee())*100)/100));
    holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

    int  drawableReource = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic()
            ,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableReource)
                .into(holder.pic);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemsListner() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListner.change();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mangementCart.minusNumberFood(foodDomains, position, new ChangeNumberItemsListner() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListner.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView title,feeEachItem;
ImageView pic,plusItem,minusItem;
TextView totalEachitem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItem= itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachitem=itemView.findViewById(R.id.totalEachItem);
            num =itemView.findViewById(R.id.numberItemTxt);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);

        }
    }
}
