package jittakan58070012.kmitl.lab07.classretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jittakan58070012.kmitl.lab07.classretrofit.Adapter.PostAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(String name) {
        OkHttpClient client = new OkHttpClient.Builder().build();  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<UserProfile> call = api.getProfile(name);
        call.enqueue(new retrofit2.Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile user = response.body();
                    display(user);
                    ImageView imageProfile = findViewById(R.id.imageProfile);  Glide.with(MainActivity.this).load(user.getUrlProfile()).into(imageProfile);
                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }


    private void display(UserProfile userProfile){
        TextView textUser = (TextView) findViewById(R.id.textUser);
        textUser.setText("@"+userProfile.getUser());

        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText("Post\n"+userProfile.getPost());

        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+userProfile.getFollower());

        TextView textFollwing = (TextView) findViewById(R.id.textFollwing);
        textFollwing.setText("Following\n"+userProfile.getFollowing());

        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

    }


}
