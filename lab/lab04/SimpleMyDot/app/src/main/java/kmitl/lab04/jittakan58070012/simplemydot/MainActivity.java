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


public class MainActivity extends AppCompatActivity implements Dots.OnDotsChangeListener, View.OnClickListener{

    private Dots dots;
    private DotView dotview;
    private ShareActionProvider myShareActionProvider;
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
        switch (item.getItemId()){
            case R.id.menu_item_share2:
                this.onShareClick();
                return super.onOptionsItemSelected(item);
            case R.id.undo:
                if(dots.getKeepDot().isEmpty()){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Nothing to Undo");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }else{
                    this.dots.removeLastDot();
                }
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Bitmap Createbitmap(){
        view1 = this.dotview.getRootView();
        Bitmap screenshot = Bitmap.createBitmap(view1.getWidth(), view1.getHeight(), Bitmap.Config.ARGB_8888);
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
        clearbutton = (Button) findViewById(R.id.button2);
        clearbutton.setOnClickListener(this);
        dotview = (DotView) findViewById(R.id.dotView);

        dots = new Dots();
        dots.setListener(this);
    }





    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // you may need the x/y location
        boolean check = true;
        int Coordinate[] = new int[2];
        dotview.getLocationOnScreen(Coordinate);
        if(event.getAction() == MotionEvent.ACTION_UP){
            int CenterX = (int) event.getX();
            int CenterY = (int) event.getY();
            CenterX = CenterX - Coordinate[0];
            CenterY = CenterY - Coordinate[1];

            final String[] array = {"Delete","Edit"};

            if (dots.getKeepDot() != null){

                for(final Dot point: dots.getKeepDot()) {

                    if (dotRangeCheck(point, CenterX, CenterY)) {
                        check = false;
                        final ColorPicker cp = new ColorPicker(MainActivity.this, newintR, newintG, newintB);
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Pick a color");
                        builder.setItems(array, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]
                                if(which == 0){
                                    dots.deleteDot(point);

                                }else if(which ==1 ){


                                    cp.show();

                                    cp.setCallback(new ColorPickerCallback() {
                                        @Override
                                        public void onColorChosen(@ColorInt int color) {
                                            // Do whatever you want
                                            // Examples
                                            point.setIntR(cp.getRed());
                                            point.setIntG(cp.getGreen());
                                            point.setIntB(cp.getBlue());
                                            cp.dismiss();

                                                dots.changeColor();

                                        }
                                    });
                                }
                            }

                        });
                        builder.show();

                        break;
                    }

                }
            }

            if (check){
                Random random = new Random();

                intR = random.nextInt(255);
                intG = random.nextInt(255);
                intB = random.nextInt(255);

                Dot dot = new Dot(CenterX, CenterY, 50, intR, intG, intB);
                dots.addDot(dot);
            }

        }
        return super.onTouchEvent(event);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int CenterX = random.nextInt(this.dotview.getWidth());
        int CenterY = random.nextInt(this.dotview.getHeight());
        intR = random.nextInt(255);
        intG = random.nextInt(255);
        intB = random.nextInt(255);

        dots.addDot(new Dot(CenterX, CenterY, 50, intR, intG, intB));
    }


    @Override
    public void onClick(View v) {
        dots.clearDot();
        dotview.invalidate();
    }

    public boolean dotRangeCheck(Dot dot, int x, int y){
        double dis2P = Math.pow(Math.pow( (dot.getCenterX()- x) , 2 ) + Math.pow( (dot.getCenterY() - y) , 2) , 0.5);
        if(dot != null){
            if (dis2P<=dot.getRadius()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDotsChange(Dots dots) {
        dotview.setDots(dots);
        dotview.invalidate();
    }

    public boolean requestWriteExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                return false;
        } else {
                return true;

        }

    }

}
