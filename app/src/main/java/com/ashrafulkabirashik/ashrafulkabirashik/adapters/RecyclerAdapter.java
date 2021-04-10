package com.ashrafulkabirashik.ashrafulkabirashik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashrafulkabirashik.ashrafulkabirashik.R;
import com.ashrafulkabirashik.ashrafulkabirashik.models.Blog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Blog> blogList;

    public RecyclerAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(this.blogList.get(position).getId()));
        holder.title.setText(this.blogList.get(position).getTitle().toString());
        holder.description.setText(this.blogList.get(position).getDescription().toString());
        holder.author_name.setText(this.blogList.get(position).getAuthor().getName().toString());
        holder.author_profession.setText(this.blogList.get(position).getAuthor().getProfession().toString());
        Picasso.get().load(this.blogList.get(position).getCover_photo()).error(R.drawable.error_image).placeholder(R.drawable.loading_image).into(holder.cover_photo);
        Picasso.get().load(this.blogList.get(position).getAuthor().getAvatar()).error(R.drawable.error_image).placeholder(R.drawable.loading_image).into(holder.author_avatar);
    }

    @Override
    public int getItemCount() {
        if (this.blogList != null) {
            return this.blogList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, title, description, author_name, author_profession;
        ImageView cover_photo, author_avatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            cover_photo = itemView.findViewById(R.id.cover_photo);
            author_name = itemView.findViewById(R.id.author_name);
            author_avatar = itemView.findViewById(R.id.author_avatar);
            author_profession = itemView.findViewById(R.id.author_profession);
        }
    }
}
