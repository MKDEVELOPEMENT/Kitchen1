package com.example.muaaz.kitchen;

import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.muaaz.kitchen.classes.MenItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SweetActivity extends AppCompatActivity {

    LinearLayout mParentLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet);

        mParentLL = (LinearLayout) findViewById(R.id.sweet_parent_ll);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sweets = database.getReference("sweets");

        sweets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    MenItem thisItem;
                    try {
                        thisItem = data.getValue(MenItem.class);

                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.menu_item_layout, mParentLL);

                        TextView itemNameTv =(TextView) view.findViewById(R.id.m_i_name);
                        TextView itemPriceTv =(TextView) view.findViewById(R.id.m_i_price);

                        itemNameTv.setText(thisItem.itemName);
                        itemPriceTv.setText("R" + thisItem.itemPrice);



                    }catch (NullPointerException e){

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
