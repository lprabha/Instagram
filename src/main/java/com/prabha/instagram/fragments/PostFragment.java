package com.prabha.instagram.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.prabha.instagram.PostApi;
import com.prabha.instagram.StoryApi;
import com.prabha.instagram.Url;
import com.prabha.instagram.adapters.PostAdapter;
import com.prabha.instagram.R;
import com.prabha.instagram.adapters.StoryAdapter;
import com.prabha.instagram.models.PostModel;
import com.prabha.instagram.models.StoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {


    //Story my add and retrieve
    //Post love count username manage profile pic

    SwipeRefreshLayout refresh;
    RecyclerView recyclerView;
    RecyclerView rvStories;
    LinearLayoutManager layoutManager;
    ImageView ivCamera;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        recyclerView = view.findViewById(R.id.postRecyclerView);
        rvStories = view.findViewById(R.id.rvStories);
        refresh = view.findViewById(R.id.swipe);
        ivCamera = view.findViewById(R.id.ivCamera);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        loadPage();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPage();
            }
        });

        return view;
    }

    private void loadPage() {
        setStories();
        loadPosts();
    }


    private void setStories(){

        try{
            StoryApi story = Url.getInstance().create(StoryApi.class);
            Call<List<StoryModel>> storyCall = story.getStories();

            storyCall.enqueue(new Callback<List<StoryModel>>() {
                @Override
                public void onResponse(Call<List<StoryModel>> call, Response<List<StoryModel>> response) {
                    List<StoryModel> storyModel = response.body();

                    rvStories.setLayoutManager(layoutManager);
                    StoryAdapter adapter = new StoryAdapter(storyModel, getContext());
                    rvStories.setAdapter(adapter);
                    refresh.setRefreshing(false);

                }

                @Override
                public void onFailure(Call<List<StoryModel>> call, Throwable t) {
                    Toast.makeText(getContext(), "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(getContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void loadPosts(){

        PostApi postApi = Url.getInstance().create(PostApi.class);
        Call<List<PostModel>> postCalls = postApi.getPosts();

        postCalls.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Code: " + response.body(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<PostModel> list = response.body();

                PostAdapter adapter = new PostAdapter(list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                refresh.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
