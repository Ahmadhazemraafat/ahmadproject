package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateBookActivity extends AppCompatActivity
{
    private EditText booknameedt, bookpubedt, bookcatedt, bookauthedt,bookquanedt,bookpriceedt,bookdescedt;
    private Button updateBookBtn, deleteBookBtn;
    private DBHelper dbHelper;
    String bookName, bookPub, bookCat,bookAuth, bookQuan,bookPrice,bookDesc ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        booknameedt=findViewById(R.id.booknametxt);
        bookpubedt = findViewById(R.id.bookpubtxt);
        bookcatedt = findViewById(R.id.bookcattxt);
        bookauthedt=findViewById(R.id.bookauthtxt);
        bookquanedt=findViewById(R.id.bookquantxt);
        bookpriceedt=findViewById(R.id.bookpricetxt);
        bookdescedt=findViewById(R.id.bookdesctxt);
        updateBookBtn=findViewById(R.id.updateBookBtn);
        deleteBookBtn=findViewById(R.id.deleteBookBtn);

        dbHelper = new DBHelper(UpdateBookActivity.this);

        bookName = getIntent().getStringExtra("name");
        bookPub = getIntent().getStringExtra("publisher");
        bookCat = getIntent().getStringExtra("category");
        bookAuth = getIntent().getStringExtra("author");
        bookQuan = getIntent().getStringExtra("quantity");
        bookPrice = getIntent().getStringExtra("price");
        bookDesc = getIntent().getStringExtra("description");


        booknameedt.setText(bookName);
        bookpubedt.setText(bookPub);
        bookcatedt.setText(bookCat);
        bookauthedt.setText(bookAuth);
        bookquanedt.setText(bookQuan);
        bookpriceedt.setText(bookPrice);
        bookdescedt.setText(bookDesc);

        updateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateBook(bookName,booknameedt.getText().toString(),bookpubedt.getText().toString(),bookcatedt.getText().toString(),bookauthedt.getText().toString(),bookquanedt.getText().toString(),bookpriceedt.getText().toString(),bookdescedt.getText().toString());
                Toast.makeText(UpdateBookActivity.this, "Book Updated..", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateBookActivity.this,MainActivity.class);
                startActivity(i);


            }
        });


        deleteBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteBook(bookName);
                Toast.makeText(UpdateBookActivity.this,"Deleted the Book",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateBookActivity.this,MainActivity.class);
                startActivity(i);
            }
        });





    }





}

