package com.prabha.instagram.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prabha.instagram.R;
import com.prabha.instagram.Url;
import com.prabha.instagram.models.StoryModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private List<StoryModel> listStories;
    private Context mContext;

    public StoryAdapter(List<StoryModel> listStories, Context mContext) {
        this.listStories = listStories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.insta_story_card, parent, false);

        return new StoryViewHolder(view, mContext, listStories);
    }


    private void strictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {

        StoryModel model = listStories.get(position);
        int getMy = holder.getAdapterPosition();
        String imgPath = Url.BASE_URL + "uploads/" + model.getDailyPhoto();
        strictMode();
        try{
            URL url;
            url = new URL(imgPath);
            holder.story.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.name.setText(model.getPostByName());

//        if(getMy == 0){
//            try{
//                url = new URL(imgPath);
//                holder.story.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                holder.story.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            holder.name.setText("Your Story");
//            holder.addStory.setVisibility(View.VISIBLE);
//
//        }else{
//            StoryModel story = listStories.get(position);
//            try {
//                holder.story.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            holder.name.setText(story.getPostByName());
//            holder.addStory.setVisibility(View.INVISIBLE);
        }

//        String imgPath = Url.BASE_URL + "uploads/" + model.getDailyPhoto();
//        strictMode();
//
//        try{
//            URL url = new URL(imgPath);
//            holder.story.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holder.name.setText(model.getPostByName());


//    }

    @Override
    public int getItemCount() {
        return listStories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{

        CircleImageView story, addStory;
        TextView name;
        Context mContext;
        List<StoryModel> list;

        public StoryViewHolder(@NonNull View itemView, final Context mContext, final List<StoryModel> list) {
            super(itemView);
            story = itemView.findViewById(R.id.imgInstaStory);
            name = itemView.findViewById(R.id.tvStoryBy);
            addStory = itemView.findViewById(R.id.imgInstaStoryAdd);
            this.mContext = mContext ;
            this.list = list;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StoryModel story = list.get(getAdapterPosition());
                    Toast.makeText(mContext, "" + story.getPostByName(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}
