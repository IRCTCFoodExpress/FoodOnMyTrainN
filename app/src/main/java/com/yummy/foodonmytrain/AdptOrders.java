package com.yummy.foodonmytrain;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class AdptOrders extends RecyclerView.Adapter<AdptOrders.CustHolder> {

    private final Context context;
    private ArrayList<Order> orderArrayListArrayList;

    public AdptOrders(Context context, ArrayList<Order> orderArrayListArrayList) {
        this.context = context;
        this.orderArrayListArrayList = orderArrayListArrayList;
    }

    @Override
    public CustHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CustHolder(LayoutInflater.from(context).inflate(R.layout.adapter_orderlist, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustHolder holder, int position) {
        Order order= orderArrayListArrayList.get(position);
        holder.edtAdptOrderListName.setText(order.customername);
        holder.edtAdptOrderListNumber.setText(order.customer_phone);
        holder.edtAdptOrderListSeatNumber.setText(order.customer_seatno);


        holder.txtAdptOrderListTeaCoffeeCount.setText(String.valueOf(order.tea_coffee));
        holder.txtAdptOrderListBreakfastCount.setText(String.valueOf(order.breakfast_veg));
        holder.txtAdptOrderListBreakfastNonCount.setText(String.valueOf(order.breakfast_nonveg));
        holder.txtAdptOrderListMealCount.setText(String.valueOf(order.meal_veg));
        holder.txtAdptOrderListMealEggCount.setText(String.valueOf(order.meal_egg));
        holder.txtAdptOrderListMealChickenCount.setText(String.valueOf(order.meal_chicken));
        holder.txtAdptOrderListBiryaniCount.setText(String.valueOf(order.biryani_veg));
        holder.txtAdptOrderListBiryaniEggCount.setText(String.valueOf(order.biryani_egg));
        holder.txtAdptOrderListBiryaniChickenCount.setText(String.valueOf(order.biryani_chicken));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                //LayoutInflater inflater = this.getLayoutInflater();
                dialogBuilder.setTitle("Order details")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.lay_alert_orderdetails, null);
                TextView txtCustname=dialogView.findViewById(R.id.alert_cust_name);
                TextView txtCustseat=dialogView.findViewById(R.id.alert_cust_seat);
                TextView teaCoffee_qty =dialogView.findViewById(R.id.tea_coffee_qty);
                TextView breakfastVeg_qty =dialogView.findViewById(R.id.breakfast_veg_qty);
                TextView breakfastNonveg_qty =dialogView.findViewById(R.id.breakfast_nonveg_qty);
                TextView mealVeg_qty =dialogView.findViewById(R.id.meal_veg_qty);
                TextView mealEgg_qty =dialogView.findViewById(R.id.meal_egg_qty);
                TextView mealChicken_qty = dialogView.findViewById(R.id.meal_chicken_qty);
                TextView biryaniVeg_qty =dialogView.findViewById(R.id.biryani_veg_qty);
                TextView biryaniEgg_qty =dialogView.findViewById(R.id.biryani_egg_qty);
                TextView biryaniChicken_qty =dialogView.findViewById(R.id.biryani_chicken_qty);

                txtCustname.setText(order.customername);
                txtCustseat.setText(order.customer_seatno);
                teaCoffee_qty.setText(String.valueOf(order.tea_coffee));
                breakfastVeg_qty.setText(String.valueOf(order.breakfast_veg));
                breakfastNonveg_qty.setText(String.valueOf(order.breakfast_nonveg));
                mealVeg_qty.setText(String.valueOf(order.meal_veg));
                mealEgg_qty.setText(String.valueOf(order.meal_egg));
                mealChicken_qty.setText(String.valueOf(order.meal_chicken));
                biryaniVeg_qty.setText(String.valueOf(order.biryani_veg));
                biryaniEgg_qty.setText(String.valueOf(order.biryani_egg));
                biryaniChicken_qty.setText(String.valueOf(order.biryani_chicken));

                dialogBuilder.setView(dialogView);
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orderArrayListArrayList != null) {
            return orderArrayListArrayList.size();
        }
        return 0;
    }

    public void notifyDataChanged(ArrayList<Order> emg) {
        orderArrayListArrayList=emg;
        notifyDataSetChanged();
    }

    class CustHolder extends RecyclerView.ViewHolder {

        EditText edtAdptOrderListName,edtAdptOrderListNumber,edtAdptOrderListTrainNumber,edtAdptOrderListSeatNumber;
        TextView txtAdptOrderListTeaCoffeeCount,txtAdptOrderListTeaCoffeePrice,
                txtAdptOrderListBreakfastCount,txtAdptOrderListBreakfastPrice,
                txtAdptOrderListBreakfastNonCount,txtAdptOrderListBreakfastNonPrice,
                txtAdptOrderListMealCount,txtAdptOrderListMealPrice,
                txtAdptOrderListMealEggCount,txtAdptOrderListMealEggPrice,
                txtAdptOrderListMealChickenCount,txtAdptOrderListMealChickenPrice,
                txtAdptOrderListBiryaniCount,txtAdptOrderListBiryaniPrice,
                txtAdptOrderListBiryaniEggCount,txtAdptOrderListBiryaniEggPrice,
                txtAdptOrderListBiryaniChickenCount,txtAdptOrderListBiryaniChickenPrice,
                txtAdptOrderListTotal,txtAdptOrderListStatus;
        Button btnAdptOrderListStatus;
        TextInputLayout txtlayAdptOrderListNameTitle,txtlayAdptOrderListNumberTitle,
                txtlayAdptOrderListTrainIdTitle,txtlayAdptOrderListSeatNoTitle;
        LinearLayout layAdptOrderListItem1,layAdptOrderListItem2,layAdptOrderListItem3,
                layAdptOrderListItem4,layAdptOrderListItem5,layAdptOrderListItem6,
                layAdptOrderListItem7,layAdptOrderListItem8;

        CustHolder(View itemView) {
            super(itemView);
            edtAdptOrderListName=itemView.findViewById(R.id.edtAdptOrderListName);
            edtAdptOrderListNumber=itemView.findViewById(R.id.edtAdptOrderListNumber);
            edtAdptOrderListTrainNumber=itemView.findViewById(R.id.edtAdptOrderListTrainNumber);
            edtAdptOrderListSeatNumber=itemView.findViewById(R.id.edtAdptOrderListSeatNumber);

            txtAdptOrderListTeaCoffeeCount=itemView.findViewById(R.id.txtAdptOrderListTeaCoffeeCount);
            txtAdptOrderListTeaCoffeePrice=itemView.findViewById(R.id.txtAdptOrderListTeaCoffeePrice);
            txtAdptOrderListBreakfastCount=itemView.findViewById(R.id.txtAdptOrderListBreakfastCount);
            txtAdptOrderListBreakfastPrice=itemView.findViewById(R.id.txtAdptOrderListBreakfastPrice);
            txtAdptOrderListBreakfastNonCount=itemView.findViewById(R.id.txtAdptOrderListBreakfastNonCount);
            txtAdptOrderListBreakfastNonPrice=itemView.findViewById(R.id.txtAdptOrderListBreakfastNonPrice);
            txtAdptOrderListMealCount=itemView.findViewById(R.id.txtAdptOrderListMealCount);
            txtAdptOrderListMealPrice=itemView.findViewById(R.id.txtAdptOrderListMealPrice);
            txtAdptOrderListMealEggCount=itemView.findViewById(R.id.txtAdptOrderListMealEggCount);
            txtAdptOrderListMealEggPrice=itemView.findViewById(R.id.txtAdptOrderListMealEggPrice);
            txtAdptOrderListMealChickenCount=itemView.findViewById(R.id.txtAdptOrderListMealChickenCount);
            txtAdptOrderListMealChickenPrice=itemView.findViewById(R.id.txtAdptOrderListMealChickenPrice);
            txtAdptOrderListBiryaniCount=itemView.findViewById(R.id.txtAdptOrderListBiryaniCount);
            txtAdptOrderListBiryaniPrice=itemView.findViewById(R.id.txtAdptOrderListBiryaniPrice);
            txtAdptOrderListBiryaniEggCount=itemView.findViewById(R.id.txtAdptOrderListBiryaniEggCount);
            txtAdptOrderListBiryaniEggPrice=itemView.findViewById(R.id.txtAdptOrderListBiryaniEggPrice);
            txtAdptOrderListTotal=itemView.findViewById(R.id.txtAdptOrderListTotal);
            txtAdptOrderListStatus=itemView.findViewById(R.id.txtAdptOrderListStatus);

            btnAdptOrderListStatus=itemView.findViewById(R.id.btnAdptOrderListStatus);

            txtlayAdptOrderListNameTitle=itemView.findViewById(R.id.txtlayAdptOrderListNameTitle);
            txtlayAdptOrderListNumberTitle=itemView.findViewById(R.id.txtlayAdptOrderListNumberTitle);
            txtlayAdptOrderListTrainIdTitle=itemView.findViewById(R.id.txtlayAdptOrderListTrainIdTitle);
            txtlayAdptOrderListSeatNoTitle=itemView.findViewById(R.id.txtlayAdptOrderListSeatNoTitle);

            layAdptOrderListItem1=itemView.findViewById(R.id.layAdptOrderListItem1);
            layAdptOrderListItem2=itemView.findViewById(R.id.layAdptOrderListItem2);
            layAdptOrderListItem3=itemView.findViewById(R.id.layAdptOrderListItem3);
            layAdptOrderListItem4=itemView.findViewById(R.id.layAdptOrderListItem4);
            layAdptOrderListItem5=itemView.findViewById(R.id.layAdptOrderListItem5);
            layAdptOrderListItem6=itemView.findViewById(R.id.layAdptOrderListItem6);
            layAdptOrderListItem7=itemView.findViewById(R.id.layAdptOrderListItem7);
            layAdptOrderListItem8=itemView.findViewById(R.id.layAdptOrderListItem8);
        }
    }
}
