package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewBooks extends AppCompatActivity {

    private ArrayList<BookModal> bookModalArrayList;
    private DBHelper dbhelper ;
    private BookRVAdapter bookRVAdapter;
    private RecyclerView booksRV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        bookModalArrayList = new ArrayList<>();
        dbhelper = new DBHelper(ViewBooks.this);

        bookModalArrayList=dbhelper.readBooks();

        bookRVAdapter= new BookRVAdapter(bookModalArrayList,ViewBooks.this);
        booksRV=findViewById(R.id.idRVBooks);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ViewBooks.this,RecyclerView.VERTICAL,false);
        booksRV.setLayoutManager(linearLayoutManager);

        booksRV.setAdapter(bookRVAdapter);
    }
}