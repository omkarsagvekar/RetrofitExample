package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    private JsonPlaceholderApi jsonPlaceholderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
//        getPost();
        getComment();
    }

    private void getComment() {
        Call<List<Comment>> call = jsonPlaceholderApi.getComment();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()){
                    tvResult.setText("Code: "+response.message());
                }
                List<Comment> comments = response.body();
                for (Comment comment :comments){
                    String content = "";
                    content += "postId: " + comment.getPostId() + "\n";
                    content += "id: " + comment.getId() + "\n";
                    content += "name: " + comment.getName() + "\n";
                    content += "body: " + comment.getText() + "\n\n";

                    tvResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }

    private void getPost() {
        Call<List<Post>> call = jsonPlaceholderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    tvResult.setText("Code: "+response.code());
                }
                List<Post> posts = response.body();
                for(Post post : posts){
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    tvResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText(t.getMessage());

            }
        });
    }
}