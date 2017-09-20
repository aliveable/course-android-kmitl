package kmitl.lab04.jittakan58070012.simplemydot;

import android.Manifest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Random;

import kmitl.lab04.jittakan58070012.simplemydot.model.Dot;
import kmitl.lab04.jittakan58070012.simplemydot.model.Dots;
import kmitl.lab04.jittakan58070012.simplemydot.view.DotView;


public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener{

    private Dots dots;
    private DotView dotview;
    private ShareActionProvider myShareActionProvider;
    private MainFragment mainfragment;
    private int newintR = 200;
    private int newintG = 200;
    private int newintB = 200;
    private int intR;
    private int intG;
    private int intB;
    Button clearbutton;
    View view1;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu,menu);

        MenuItem item = menu.findItem(R.id.menu_item_share2);
        myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MainFragment mainFragment = new MainFragment();
        switch (item.getItemId()){
            case R.id.menu_item_share2:
                this.onShareClick();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.FragmentContainer,new MainFragment()).commit();
    }


    private Bitmap Createbitmap(){
        Bitmap screenshot = Bitmap.createBitmap(view1.getRootView().getWidth(), view1.getRootView().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenshot);
        view1.draw(canvas);
        return screenshot;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void createShareIntent(Uri uri){
        Intent ShareIntent = new Intent();
        ShareIntent.setAction(Intent.ACTION_SEND);
        ShareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        ShareIntent.setType("image/*");
        startActivity(Intent.createChooser(ShareIntent, "Share Screenshot"));
    }

    private void onShareClick() {
        if(requestWriteExternalStoragePermission()){
            Bitmap screenshot = this.Createbitmap();
            Uri uri = getImageUri(this.getApplicationContext(), screenshot);
            createShareIntent(uri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1 = getWindow().getDecorView().getRootView();
        if (savedInstanceState == null) {
            initialFragment();

            dots = new Dots();
            dots.setListener(this);

        }
    }


    public boolean requestWriteExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                return false;
        } else {
                return true;

        }

    }

    @Override
    public void onDotsChange(Dots dots) {

    }

}
