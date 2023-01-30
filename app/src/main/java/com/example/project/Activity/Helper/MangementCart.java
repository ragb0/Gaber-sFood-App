package com.example.project.Activity.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.project.Activity.Domain.FoodDomain;
import com.example.project.Activity.Interface.ChangeNumberItemsListner;

import java.util.ArrayList;

public class MangementCart {
    private Context context;

    private TinyDB tinyDB;

    public MangementCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood= getListCart();
                boolean existAlready = false;
                int n=0;
                for(int i=0 ; i<listFood.size() ; i++){
                    if(listFood.get(i).getTitle().equals(item.getTitle())){
                        existAlready=true;
                        n=i;
                        break;
                    }
                }
            if(existAlready){
                listFood.get(n).setNumberInCart(item.getNumberInCart());
            }else{
                listFood.add(item);
            }
            tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Add To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public  ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");

    }
    public void plusNumberFood(ArrayList<FoodDomain>listFood, int position, ChangeNumberItemsListner changeNumberItemsListner){

        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listFood);
        changeNumberItemsListner.change();
    }
    public  void minusNumberFood(ArrayList<FoodDomain>listfood,int position,ChangeNumberItemsListner changeNumberItemsListner) {
        if (listfood.get(position).getNumberInCart() == 1) {

            listfood.remove(position);
        } else {

            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listfood);
        changeNumberItemsListner.change();
    }
    public Double getTotalfee(){
        ArrayList<FoodDomain>listfood=getListCart();
        double fee = 0;
        for (int i = 0 ; i<listfood.size(); i ++){

            fee=fee+(listfood.get(i).getFee()*listfood.get(i).getNumberInCart());
        }
        return fee;
    }
    }

