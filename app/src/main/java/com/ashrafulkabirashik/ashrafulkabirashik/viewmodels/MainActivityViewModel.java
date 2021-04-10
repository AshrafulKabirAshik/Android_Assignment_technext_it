package com.ashrafulkabirashik.ashrafulkabirashik.viewmodels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ashrafulkabirashik.ashrafulkabirashik.convarter.ImageConverter;
import com.ashrafulkabirashik.ashrafulkabirashik.models.Blog;
import com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB.RoomDB;
import com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB.RoomModels;
import com.ashrafulkabirashik.ashrafulkabirashik.repositories.ApiService;
import com.ashrafulkabirashik.ashrafulkabirashik.repositories.RetroInstance;
import com.ashrafulkabirashik.ashrafulkabirashik.response.BlogResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Blog>> blogList;
    private RoomDB database;

    public MainActivityViewModel() {
        blogList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Blog>> getBlogListObserver() {
        return blogList;
    }

    public void makeApiCall(Context context) {

        ApiService apiService = RetroInstance.getRetroClient().create(ApiService.class);

        database = RoomDB.getInstance(context);

        Call<BlogResponse> call = apiService.getBlogList();

        call.enqueue(new Callback<BlogResponse>() {
            @Override
            public void onResponse(Call<BlogResponse> call, Response<BlogResponse> response) {
                blogList.postValue(response.body().getBlogList());

                if (response.body() != null) {

                    for (int i = 0; i < response.body().getBlogList().size(); i++) {

                        RoomModels roomModels = new RoomModels();

                        int id = response.body().getBlogList().get(i).getId();
                        String title = response.body().getBlogList().get(i).getTitle();
                        String description = response.body().getBlogList().get(i).getDescription();
                        String author_name = response.body().getBlogList().get(i).getAuthor().getName();
                        String author_profession = response.body().getBlogList().get(i).getAuthor().getProfession();

                        roomModels.setID(id);
                        roomModels.setTitle(title);
                        roomModels.setDescription(description);
                        roomModels.setAuthor_name(author_name);
                        roomModels.setAuthor_profession(author_profession);

                        Uri cover = Uri.parse(response.body().getBlogList().get(i).getCover_photo());
                        Picasso.get().load(cover)
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        if (bitmap != null) {
                                            byte[] cover = ImageConverter.convertImageToByteArray(bitmap);
                                            roomModels.setCover_photo(cover);
                                        }
                                    }

                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                    }
                                });

                        Uri avatar = Uri.parse(response.body().getBlogList().get(i).getAuthor().getAvatar());
                        Picasso.get().load(avatar)
                                .into(new Target() {
                                    @Override
                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                        if (bitmap != null) {
                                            byte[] avatar = ImageConverter.convertImageToByteArray(bitmap);
                                            roomModels.setAuthor_avatar(avatar);
                                        }
                                    }

                                    @Override
                                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                                    }

                                    @Override
                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                    }
                                });

                        database.roomDao().insert(roomModels);

                    }
                }
            }

            @Override
            public void onFailure(Call<BlogResponse> call, Throwable t) {
                blogList.postValue(null);
            }
        });
    }

    public void addInformation(Context context) {
        database = RoomDB.getInstance(context);
        RoomModels roomModels = new RoomModels();

        int id = 1;
        String name = "John Doe";
        String profession = "Content Writer";
        roomModels.setTitle("title");
        roomModels.setDescription("description");
        roomModels.setID(id);
        roomModels.setAuthor_name(name);
        roomModels.setAuthor_profession(profession);

        Uri avatar = Uri.parse("https://i.pravatar.cc/250");
        Picasso.get().load(avatar)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        if (bitmap != null) {
                            byte[] avatar = ImageConverter.convertImageToByteArray(bitmap);
                            roomModels.setAuthor_avatar(avatar);
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        database.roomDao().insert(roomModels);
        Toast.makeText(context, "Blog one replaced with new data !", Toast.LENGTH_LONG).show();
    }
}
