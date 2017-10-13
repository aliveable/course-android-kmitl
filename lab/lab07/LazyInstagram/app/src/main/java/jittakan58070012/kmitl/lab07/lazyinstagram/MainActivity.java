package jittakan58070012.kmitl.lab07.lazyinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jittakan58070012.kmitl.lab07.lazyinstagram.adapter.PostAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<String> userList = new ArrayList<>(Arrays.asList("android", "cartoon", "nature"));
    private PostAdapter postAdapter;
    private String user;
    private int viewOption=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String user = getIntent().getStringExtra("user");
        this.viewOption = getIntent().getIntExtra("viewOption", 0);

        if (user != null) {
            getUserProfile(user);
        } else {
            getUserProfile(userList.get(0));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        SubMenu subMenu = menuItem.getSubMenu();

        for (int i=0; i<this.userList.size(); i++) {
            subMenu.add(R.id.user_option, i, 0, this.userList.get(i)).setIcon(R.drawable.ic_user);
        }

    }

    private void getUserProfile(String name) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
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

                    //UserProfile user = response.body();
                    display(response.body());

                }

            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case 0:
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("user", "android")
                        .putExtra("viewOption", this.viewOption));
                break;
            case 1:
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("user", "cartoon")
                        .putExtra("viewOption", this.viewOption));
                break;
            case 2:
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("user", "nature")
                        .putExtra("viewOption", this.viewOption));
                break;
            case R.id.grid_view:
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("user", this.user)
                        .putExtra("viewOption", 0));
                this.viewOption = 0;
                break;
            case R.id.list_view:
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("user", this.user)
                        .putExtra("viewOption", 1));
                break;
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
        }
        finish();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void display(UserProfile userProfile){

        this.user = userProfile.getUser();

        ImageView imageProfile = findViewById(R.id.imageProfile);
        ImageView imageProfileSidebar = findViewById(R.id.imageProfileSidebar);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfileSidebar);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);

        TextView sidebarUserDiscription = findViewById(R.id.sideBarUserDiscription);
        sidebarUserDiscription.setText(userProfile.getBio());

        TextView textIdSideBar = findViewById(R.id.sidebarID);
        textIdSideBar.setText("@"+userProfile.getUser());

        TextView textUser = findViewById(R.id.textUser);
        textUser.setText("@"+userProfile.getUser());

        TextView textPost = findViewById(R.id.textPost);
        textPost.setText("Post\n"+userProfile.getPost());

        TextView textFollower = findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+userProfile.getFollower());

        TextView textFollwing = findViewById(R.id.textFollwing);
        textFollwing.setText("Following\n"+userProfile.getFollowing());

        TextView textBio = findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());

        postAdapter = new PostAdapter(this, userProfile.getPosts());

        RecyclerView recyclerView = findViewById(R.id.list);
        if (this.viewOption == 0){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }else if (this.viewOption == 1){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        recyclerView.setAdapter(postAdapter);

    }


}
