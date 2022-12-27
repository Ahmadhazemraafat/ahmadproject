package com.example.bookstore;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookAdapter extends ArrayAdapter<String>
{

    private final Activity context;
    private final String[] title;
    private final String[] author;
    private final Integer[] imageid;

    public BookAdapter(Activity context, String[] title, String[] author, Integer[] imageid) {
        super(context,R.layout.row_list,title);
        this.context = context;
        this.title = title;
        this.author = author;
        this.imageid = imageid;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rootView=inflater.inflate(R.layout.row_list,null,true);

        TextView name=rootView.findViewById(R.id.book_name);
        TextView bauthor=rootView.findViewById(R.id.book_author);
        ImageView imageView =rootView.findViewById(R.id.image_id);

        name.setText(title[position]);
        bauthor.setText(author [position]);
        imageView.setImageResource(imageid[position]);

        return rootView;
    }
}
