package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectedBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_book);


        DBHelper libraryDBHelper=new DBHelper(this);


        ImageView image=findViewById(R.id.imageid);
        TextView bookname=findViewById(R.id.bookname);
        TextView bookauthor=findViewById(R.id.bookauthor);
        TextView publisher=findViewById(R.id.book_publisher);
        TextView price=findViewById(R.id.book_price);
        TextView description=findViewById(R.id.book_des);
        Button cartbtn=findViewById(R.id.cart);

        String Name=getIntent().getStringExtra("Name");
        String author=getIntent().getStringExtra("Author");
        Integer imag= getIntent().getIntExtra("Image",1);

        Cursor cursor=libraryDBHelper.getBook(Name);

        int userid=0;
        int cartprice=cursor.getInt(6);
        int id= cursor.getInt(0);
        image.setImageResource(imag);
        bookname.setText(Name);
        bookauthor.setText("By: "+author);
        publisher.setText("Publisher: "+cursor.getString(2));
        price.setText(cursor.getString(6)+" LE");
        description.setText(cursor.getString(7));

        cartbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                libraryDBHelper.createanOrder(cartprice,id,userid);
                Intent i=new Intent(SelectedBook.this,Cart.class);
                startActivity(i);
            }
        });

    }
}