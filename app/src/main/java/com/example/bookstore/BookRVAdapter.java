package com.example.bookstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.ViewHolder>
{
    private final ArrayList<BookModal> bookModalArrayList;
    private final Context context ;

    public BookRVAdapter(ArrayList<BookModal> bookModalArrayList,Context context)
    {
        this.bookModalArrayList=bookModalArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        BookModal modal = bookModalArrayList.get(position);
        holder.bookNameTV.setText(modal.getBookName());
        holder.bookPubTV.setText(modal.getBookPub());
        holder.bookCatTV.setText(modal.getBookCat());
        holder.bookAuthTV.setText(modal.getBookAuth());
        holder.bookQuanTV.setText(modal.getBookQuan());
        holder.bookPriceTV.setText(modal.getBookPrice());
        holder.bookDescTV.setText(modal.getBookDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,UpdateBookActivity.class);

                i.putExtra("name",modal.getBookName());
                i.putExtra("publisher",modal.getBookPub());
                i.putExtra("category",modal.getBookCat());
                i.putExtra("author",modal.getBookAuth());
                i.putExtra("quantity",modal.getBookQuan());
                i.putExtra("price",modal.getBookPrice());
                i.putExtra("description",modal.getBookDesc());

                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return bookModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookNameTV;
        private final TextView bookPubTV;
        private final TextView bookCatTV;
        private final TextView bookAuthTV;
        private final TextView bookQuanTV;
        private final TextView bookPriceTV;
        private final TextView bookDescTV ;

        public  ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            bookNameTV = itemView.findViewById(R.id.cardbookname);
            bookPubTV = itemView.findViewById(R.id.cardbookpub);
            bookCatTV = itemView.findViewById(R.id.cardbookcat);
            bookAuthTV = itemView.findViewById(R.id.cardbookauth);
            bookQuanTV = itemView.findViewById(R.id.cardbookquan);
            bookPriceTV = itemView.findViewById(R.id.cardbookprice);
            bookDescTV = itemView.findViewById(R.id.cardbookdesc);

        }


    }




}
