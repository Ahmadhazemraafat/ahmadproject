package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Cursor bookCursor;
    Cursor authorCursor;
    ArrayAdapter<String> booksNamesAdapter;
    Boolean isSearchForBook=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper libraryDBHelper=new DBHelper(this);
        final EditText libraryNameText= findViewById(R.id.SearchText);
        Button searchBtn= findViewById(R.id.SearchBtn);
        final ListView booksNamesList= findViewById(R.id.list);

        booksNamesAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        booksNamesList.setAdapter(booksNamesAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String libraryName=libraryNameText.getText().toString();

                isSearchForBook=true;
                bookCursor=libraryDBHelper.getBook(libraryName);
                authorCursor=libraryDBHelper.getAuthor(libraryName);

                booksNamesAdapter.clear();

                if(bookCursor!=null)
                {
                    while(!bookCursor.isAfterLast())
                    {
                        booksNamesAdapter.add(bookCursor.getString(1));
                        bookCursor.moveToNext();
                    }
                }
                else if(authorCursor!=null)
                {
                    isSearchForBook=false;
                    booksNamesAdapter.add(authorCursor.getString(4));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Try Again! No matched Books and Authors.",Toast.LENGTH_LONG).show();
                }
            }
        });

        booksNamesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position,long id)
            {
                String name=(((TextView)view).getText().toString());
                Intent i;

                if(isSearchForBook)
                {
                    i=new Intent(MainActivity.this,ShowBooks.class);
                }
                else{
                    i=new Intent(MainActivity.this,ShowAuthorBooks.class);
                }

                i.putExtra("name",name);
                startActivity(i);
            }
        });

        //Bottom navigation buttons
        Button homeBtn= findViewById(R.id.homeBtn);
        Button categoryBtn= findViewById(R.id.categoryBtn);
        Button cartBtn= findViewById(R.id.cartBtn);
        Button adminBtn= findViewById(R.id.adminBtn);
        Button ProfileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        categoryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Category.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Cart.class);
                startActivity(i);
            }
        });

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Admin.class);
                startActivity(i);
            }
        });

        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}