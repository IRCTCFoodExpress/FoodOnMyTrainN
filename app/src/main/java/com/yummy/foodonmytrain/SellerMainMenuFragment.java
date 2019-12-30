package com.yummy.foodonmytrain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerMainMenuFragment extends Fragment implements View.OnClickListener {


    private static final String ARG_PARAM="trainno" ;
    private DatabaseReference mFirebaseDatabaseRef;
    ArrayList<Order> mOrder=new ArrayList<>();
    AdptOrders mAdptOrders;
    RecyclerView mCustomerOrderRecyc;


    public static SellerMainMenuFragment newInstance(String typeOfRequest) {
        SellerMainMenuFragment fragment = new SellerMainMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, typeOfRequest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_seller_main_menu, container, false);
        ((DrawerLocker)getActivity()).setDrawerEnabled(true,"Home");
        mAdptOrders= new AdptOrders(getContext(),mOrder);
        mCustomerOrderRecyc=view.findViewById(R.id.cust_order_recycler);
        mCustomerOrderRecyc.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mCustomerOrderRecyc.setAdapter(mAdptOrders);
        if(getArguments() != null) {
            if(mFirebaseDatabaseRef==null)
                mFirebaseDatabaseRef = FirebaseDatabase.getInstance().getReference();

            mFirebaseDatabaseRef.child("Orders").child(getArguments().getString(ARG_PARAM)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    mOrder.clear();
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        Order order = postSnapshot.getValue(Order.class);
                        mOrder.add(order);
                    }
                    mAdptOrders.notifyDataChanged(mOrder);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });

        }
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
