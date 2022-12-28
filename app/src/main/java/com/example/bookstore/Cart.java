package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        int[] prices;
        DBHelper libraryDBHelper=new DBHelper(this);
        prices=libraryDBHelper.getOrder(0);

        ArrayList<String> arrayList=new ArrayList<String>();
        for(int s:prices)
        {
            arrayList.add(String.valueOf(s));
        }

        final ArrayAdapter listadapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        ListView listView=(ListView)findViewById(R.id.book_cart_view);
        listView.setAdapter(listadapter);

        int sum=0;
        for(int i=0;i<prices.length;i++)
        {
            sum+=prices[i];
        }

        TextView totalprice=findViewById(R.id.book_totalprice);
        totalprice.setText("Total Price: "+sum);

        Button checkout=findViewById(R.id.cartbtn);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Cart.this, "CheckOutComplete", Toast.LENGTH_SHORT).show();
            }
        });


    }
}