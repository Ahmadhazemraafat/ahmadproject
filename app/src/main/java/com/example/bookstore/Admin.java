package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Admin extends AppCompatActivity
{
    private EditText booknameedt , bookpubedt ,bookcatedt , bookauthedt, bookquanedt,bookpriceedt,bookdescedt ;
    private Button addBookBtn,showBookBtn;
    private DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        booknameedt = findViewById(R.id.booknametxt2);
        bookpubedt = findViewById(R.id.bookpubtxt2);
        bookcatedt = findViewById(R.id.bookcattxt2);
        bookauthedt = findViewById(R.id.bookauthtxt2);
        bookquanedt = findViewById(R.id.bookquantxt2);
        bookpriceedt = findViewById(R.id.bookpricetxt2);
        bookdescedt = findViewById(R.id.bookdesctxt2);
        addBookBtn = findViewById(R.id.deleteBookBtnn);
        showBookBtn=findViewById(R.id.updateBookBtnn);

        dbhelper = new DBHelper(Admin.this);



        addBookBtn.setOnClickListener(v -> {
            String bookname = booknameedt.getText().toString();
            String bookpub = bookpubedt.getText().toString();
            String bookcat = bookcatedt.getText().toString();
            String bookauth = bookauthedt.getText().toString();
            String bookquan = bookquanedt.getText().toString();
            String bookprice = bookpriceedt.getText().toString();
            String bookdesc = bookdescedt.getText().toString();



            if (bookname.isEmpty() && bookpub.isEmpty() && bookcat.isEmpty() && bookauth.isEmpty() && bookquan.isEmpty() && bookprice.isEmpty() && bookdesc.isEmpty())
            {
                Toast.makeText(Admin.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }
            int quantityint = Integer.parseInt(bookquanedt.getText().toString());
            int priceint = Integer.parseInt(bookpriceedt.getText().toString());

            dbhelper.createNewBook(bookname, bookpub, bookcat, bookauth,quantityint,priceint,bookdesc);

            // after adding the data we are displaying a toast message.
            Toast.makeText(Admin.this, "Book has been added.", Toast.LENGTH_SHORT).show();
            booknameedt.setText("");
            bookpubedt.setText("");
            bookcatedt.setText("");
            bookauthedt.setText("");
            bookquanedt.setText("");
            bookpriceedt.setText("");
            bookdescedt.setText("");



        });


        showBookBtn.setOnClickListener(view -> {
            Intent i = new Intent(Admin.this,ViewBooks.class);
            startActivity(i);

        });

    }




}
