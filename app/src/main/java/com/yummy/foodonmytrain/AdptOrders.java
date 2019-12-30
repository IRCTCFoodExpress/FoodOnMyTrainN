package com.yummy.foodonmytrain;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

        return new CustHolder(LayoutInflater.from(context).inflate(R.layout.lay_adpt_orders_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustHolder holder, int position) {
        Order order= orderArrayListArrayList.get(position);
        holder.cust_name.setText(order.customername);
        holder.cust_phone.setText(order.customer_phone);
        holder.cust_seat.setText(order.customer_seatno);

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
        TextView cust_name;
        TextView cust_phone;
        TextView cust_seat;
        CardView cardViewContact;

        CustHolder(View itemView) {
            super(itemView);
            cust_name = itemView.findViewById(R.id.cust_name);
            cust_phone = itemView.findViewById(R.id.cust_phone);
            cust_seat = itemView.findViewById(R.id.cust_seat);
            cardViewContact = itemView.findViewById(R.id.cardView);
        }
    }
}
