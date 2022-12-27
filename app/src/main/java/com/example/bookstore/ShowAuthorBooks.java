package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowAuthorBooks extends AppCompatActivity{
    ArrayAdapter<String> booksNamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_author_books);

        final ListView authorBooksList= findViewById(R.id.AuthorBooksList);
        booksNamesAdapter=new ArrayAdapter<String>(ShowAuthorBooks.this, android.R.layout.simple_list_item_1);
        authorBooksList.setAdapter(booksNamesAdapter);

        final DBHelper libraryDBHelper=new DBHelper(this);
        String RetrievedName= getIntent().getStringExtra("name");

        TextView AuthorName= findViewById(R.id.AuthorName);
        AuthorName.setText(RetrievedName);

        Cursor cursor=libraryDBHelper.getAuthor(RetrievedName);

        booksNamesAdapter.clear();

        if(cursor!=null)
        {
            while(!cursor.isAfterLast())
            {
                booksNamesAdapter.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }

        authorBooksList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String name=(((TextView)view).getText().toString());
                Intent i=new Intent(ShowAuthorBooks.this,ShowBooks.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });


        //Bottom navigation buttons
        Button homeBtn= findViewById(R.id.homeBtn);
        Button categoryBtn= findViewById(R.id.categoryBtn);
        Button cartBtn= findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowAuthorBooks.this,MainActivity.class);
                startActivity(i);
            }
        });

        categoryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowAuthorBooks.this,Category.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowAuthorBooks.this,Cart.class);
                startActivity(i);
            }
        });
    }
}