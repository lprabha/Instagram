package com.prabha.instagram.adapters;

import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prabha.instagram.Url;
import com.prabha.instagram.models.PostModel;
import com.prabha.instagram.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private List<PostModel> listPost;

    public PostAdapter(List<PostModel> list) {
        this.listPost = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.insta_post_card, parent, false);

        return new PostViewHolder(view);
    }

    private void strictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        PostModel post = listPost.get(position);
        String postImgPath = Url.BASE_URL + "uploads/" + post.getPost();
        String profileImgPath = Url.BASE_URL + "uploads/" + post.getAuthorPic();
        strictMode();

        try{
            URL url;
            url = new URL(postImgPath);
            holder.imgPost.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            url = new URL(profileImgPath);
            holder.imgProfilePicPost.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            holder.imgProfilepicComment.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.tvUsername.setText(post.getPostBy());
        holder.tvSubHead.setText(post.getSubHead());
        holder.tvCaptionBy.setText(post.getPostByName());
        holder.tvCaption.setText(post.getCaption());

    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfilePicPost, imgProfilepicComment;
        ImageView imgPost;
        TextView tvUsername, tvCaption, tvCaptionBy, tvSubHead;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfilePicPost = itemView.findViewById(R.id.imgProfilePicPost);
            imgProfilepicComment = itemView.findViewById(R.id.imgMyProfileComment);
            imgPost = itemView.findViewById(R.id.imgPost);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvCaptionBy = itemView.findViewById(R.id.tvCaptionBy);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvSubHead = itemView.findViewById(R.id.tvAddress);

        }
    }

}
