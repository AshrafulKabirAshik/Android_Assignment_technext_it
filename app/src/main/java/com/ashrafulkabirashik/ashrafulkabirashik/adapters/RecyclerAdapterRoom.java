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
import com.ashrafulkabirashik.ashrafulkabirashik.convarter.ImageConverter;
import com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB.RoomModels;

import java.util.List;

public class RecyclerAdapterRoom extends RecyclerView.Adapter<RecyclerAdapterRoom.MyViewHolder> {

    private Context context;
    private List<RoomModels> blogList;

    public RecyclerAdapterRoom(Context context, List<RoomModels> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    public void setBlogList(List<RoomModels> blogList) {
        this.blogList = blogList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapterRoom.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterRoom.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(this.blogList.get(position).getID()));
        holder.title.setText(this.blogList.get(position).getTitle().toString());
        holder.description.setText(this.blogList.get(position).getDescription().toString());
        holder.author_name.setText(this.blogList.get(position).getAuthor_name().toString());
        holder.author_profession.setText(this.blogList.get(position).getAuthor_profession().toString());

        if (this.blogList.get(position).getCover_photo() != null) {
            holder.cover_photo.setImageBitmap(ImageConverter.convertByteArrayToImage(this.blogList.get(position).getCover_photo()));
        } else {
            holder.cover_photo.setImageResource(R.drawable.error_image);
        }
        if (this.blogList.get(position).getAuthor_avatar() != null) {
            holder.author_avatar.setImageBitmap(ImageConverter.convertByteArrayToImage(this.blogList.get(position).getAuthor_avatar()));
        } else {
            holder.author_avatar.setImageResource(R.drawable.error_image);
        }
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
