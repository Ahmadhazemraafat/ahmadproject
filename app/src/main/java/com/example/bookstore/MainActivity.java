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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    Cursor bookCursor;
    Cursor authorCursor;
    ArrayAdapter<String> books_Or_AuthorAdapter;
    Boolean isSearchForBook=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper libraryDBHelper=new DBHelper(this);
        final EditText libraryNameText= findViewById(R.id.SearchText);
        Button searchBtn= findViewById(R.id.SearchBtn);
        final ListView book_Or_AuthorList= findViewById(R.id.list);

        books_Or_AuthorAdapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        book_Or_AuthorList.setAdapter(books_Or_AuthorAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String libraryName=libraryNameText.getText().toString();

                isSearchForBook=true;
                bookCursor=libraryDBHelper.getBook(libraryName);
                authorCursor=libraryDBHelper.getAuthor(libraryName);

                books_Or_AuthorAdapter.clear();

                if(bookCursor!=null)
                {
                    while(!bookCursor.isAfterLast())
                    {
                        books_Or_AuthorAdapter.add(bookCursor.getString(1));
                        bookCursor.moveToNext();
                    }
                }
                else if(authorCursor!=null)
                {
                    isSearchForBook=false;
                    books_Or_AuthorAdapter.add(authorCursor.getString(4));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Try Again! No matched Books and Authors.",Toast.LENGTH_LONG).show();
                }
            }
        });

        book_Or_AuthorList.setOnItemClickListener(new AdapterView.OnItemClickListener()
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

        String myArr[]=getResources().getStringArray(R.array.grid);
        ArrayList<String>myList=new ArrayList<String>(Arrays.asList(myArr));
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);

        GridView myGrid=findViewById(R.id.myGrid);
        myGrid.setAdapter(adapter);

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=myList.get(position);
                Intent i=new Intent(MainActivity.this,ShowBooks.class);
                i.putExtra("name",name);
                startActivity(i);
            }

        });


        //Bottom navigation buttons
        Button homeBtn= findViewById(R.id.homeBtn);
        Button categoryBtn= findViewById(R.id.categoryBtn);
        Button cartBtn= findViewById(R.id.cartBtn);
        Button ProfileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newIntent(MainActivity.class);
            }
        });

        categoryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newIntent(Category.class);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                newIntent(Cart.class);
            }
        });

        ProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent(LoginActivity.class);
            }
        });
    }

    public void newIntent(Class anyCLass)
    {
        Intent i = new Intent(MainActivity.this,anyCLass);
        startActivity(i);
    }
}