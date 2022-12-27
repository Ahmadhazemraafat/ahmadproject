package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Category extends AppCompatActivity{

    ListView categories;

    String[] category ={"Romance","SC-FI","Horror","Thriller","Young Adult"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categories=findViewById(R.id.books_list);
        ArrayAdapter<String> categ_arr;
        categ_arr=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,category);
        categories.setAdapter(categ_arr);

        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Category.this,CategorizedBooks.class);
                i.putExtra("Category",position);
                startActivity(i);

            }
        });

    }
}