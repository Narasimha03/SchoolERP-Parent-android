package com.example.harish.proschoolforparentadvanced.suresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harish.proschoolforparentadvanced.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JANI on 03-06-2017.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Books> arrayListBook;
    private List<Books> listBooks;

    public BookAdapter(Context ctx, ArrayList<Books> arrayListBook){
        this.mContext = ctx;
        this.listBooks = arrayListBook;
        this.arrayListBook = new ArrayList<Books>();
        this.arrayListBook.addAll(listBooks);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_list_display_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Books books = listBooks.get(position);
        if (books != null){
            holder.title.setText(books.getBook_title());
            holder.date.setText(books.getInward_date());
            holder.subject.setText(books.getSubject());
            holder.author.setText(books.getAuthor_name());
            holder.price.setText(books.getBook_price());
            holder.qty.setText(books.getQty());
            holder.rackNo.setText(books.getRack_number());
        }
    }

    @Override
    public int getItemCount() {
        return listBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public TextView title, date, subject, author, price, qty, rackNo;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.student);
            date = (TextView) itemView.findViewById(R.id.exam);
            subject = (TextView) itemView.findViewById(R.id.subject);
            author = (TextView) itemView.findViewById(R.id.paper);
            price = (TextView) itemView.findViewById(R.id.marks);
            qty = (TextView) itemView.findViewById(R.id.percent);
            rackNo = (TextView) itemView.findViewById(R.id.conduct);
        }
    }
}
