package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowBooks extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);

        final DBHelper libraryDBHelper=new DBHelper(this);
        String RetrievedName= getIntent().getStringExtra("name");
        Cursor cursor=libraryDBHelper.getBook(RetrievedName);

        TextView name=findViewById(R.id.BookName);
        TextView author=findViewById(R.id.Author);
        TextView publisher=findViewById(R.id.Publisher);
        TextView category=findViewById(R.id.Category);
        TextView quantity=findViewById(R.id.Quantity);
        TextView price=findViewById(R.id.Price);
        TextView description=findViewById(R.id.Description);

        //setting TextBoxes
        name.setText(RetrievedName);
        author.setText(cursor.getString(4));
        publisher.setText(cursor.getString(2));
        category.setText(cursor.getString(3));
        quantity.setText(cursor.getString(5));
        price.setText(cursor.getString(6)+" LE");
        description.setText(cursor.getString(7));

        //Bottom navigation buttons
        Button homeBtn= findViewById(R.id.homeBtn);
        Button categoryBtn= findViewById(R.id.categoryBtn);
        Button cartBtn= findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowBooks.this,MainActivity.class);
                startActivity(i);
            }
        });

        categoryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowBooks.this,Category.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowBooks.this,Cart.class);
                startActivity(i);
            }
        });

    }
}