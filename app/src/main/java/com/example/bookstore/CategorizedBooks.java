package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CategorizedBooks extends AppCompatActivity {

    Integer newbook=R.drawable.i1;
    ListView listView;
    Integer[] Rimageid={R.drawable.rb1,R.drawable.rb2,newbook,newbook};
    Integer[] Simageid={R.drawable.scfi1,R.drawable.scfi2,newbook,newbook};
    Integer[] Himageid={R.drawable.h1,R.drawable.h2,newbook,newbook};
    Integer[] Timageid={R.drawable.t1,R.drawable.t2,newbook,newbook};


    String[] matchedbooks={};
    String[]matchedauthors={};


    public void FillList(String[]books,String[]authors,Integer[]images)
    {
        BookAdapter adapter=new BookAdapter(CategorizedBooks.this,books,authors,images);
        listView=findViewById(R.id.book_list_view);
        listView.setAdapter(adapter);
    }

    public void StartActivity(String bookname,String bookauthor,Integer image)
    {
        Intent i = new Intent(CategorizedBooks.this,SelectedBook.class);
        i.putExtra("Name",bookname);
        i.putExtra("Author",bookauthor);
        i.putExtra("Image",image);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorized_books);
        final DBHelper dbHelper=new DBHelper(this);
        int num=getIntent().getExtras().getInt("Category");
        if(num==0)
        {
            matchedbooks=dbHelper.getBooksname("Romance");
            matchedauthors=dbHelper.getBooksauthors("Romance");
            FillList(matchedbooks,matchedauthors,Rimageid);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    StartActivity(matchedbooks[position],matchedauthors[position],Rimageid[position]);
                }
            });

        }
        if(num==1)
        {
            matchedbooks=dbHelper.getBooksname("SC-FI");
            matchedauthors=dbHelper.getBooksauthors("SC-FI");
            FillList(matchedbooks,matchedauthors,Simageid);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    StartActivity(matchedbooks[position],matchedauthors[position],Simageid[position]);
                }
            });

        }
        if(num==2)
        {
            matchedbooks=dbHelper.getBooksname("Horror");
            matchedauthors=dbHelper.getBooksauthors("Horror");
            FillList(matchedbooks,matchedauthors,Himageid);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    StartActivity(matchedbooks[position],matchedauthors[position],Himageid[position]);
                }
            });
        }
        if(num==3)

        {
            matchedbooks=dbHelper.getBooksname("Thriller");
            matchedauthors=dbHelper.getBooksauthors("Thriller");
            FillList(matchedbooks,matchedauthors,Timageid);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    StartActivity(matchedbooks[position],matchedauthors[position],Timageid[position]);
                }
            });
        }



    }
}