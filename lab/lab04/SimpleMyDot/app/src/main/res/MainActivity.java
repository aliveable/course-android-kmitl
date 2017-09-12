package kmitl.lab03.jittakan58070012.simplemydot;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements kmitl.lab03.jittakan58070012.simplemydot.model.Dots.OnDotsChangeListener, View.OnClickListener{

    private kmitl.lab03.jittakan58070012.simplemydot.model.Dot dot;
    private kmitl.lab03.jittakan58070012.simplemydot.model.Dots dots;
    private kmitl.lab03.jittakan58070012.simplemydot.view.DotView dotview;
    private ShareActionProvider myShareActionProvider;
    private Uri myImageURI;
    Button clearbutton;
    Button chnButton;
    Button shareButton;
    Intent intent;
    View view1;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu,menu);

        //getMenuInflater().inflate(R.menu.share_menu, menu);

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


        //shareButton = (Button) findViewById(R.id.Share);
        //shareButton.setOnClickListener(this);
        ///////////////////////////////////////////////////////////////
        final DotSerializable dotSerializable = new DotSerializable();
        final DotParcelable dotparcelable = new DotParcelable(150,150,50);
        dotSerializable.setCenterX(150);
        dotSerializable.setCenterY(150);
        dotSerializable.setRadius(50);
        dotSerializable.setColor("red");

        chnButton = (Button) findViewById(R.id.opnAct);
        chnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(kmitl.lab03.jittakan58070012.simplemydot.MainActivity.this, SecondActivity.class);

                intent.putExtra("xValue",30);

                intent.putExtra("dotserializable", dotSerializable);

                intent.putExtra("dotparceleble", dotparcelable);

                startActivity(intent);

            }
        });

        ///////////////////////////////////////////////////////////////
        dotview = (kmitl.lab03.jittakan58070012.simplemydot.view.DotView) findViewById(R.id.dotView);

        dots = new kmitl.lab03.jittakan58070012.simplemydot.model.Dots();
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

            if (dots.getKeepDot() != null){
                for(kmitl.lab03.jittakan58070012.simplemydot.model.Dot point: dots.getKeepDot()) {
                    if (dotRangeCheck(point, CenterX, CenterY)) {
                        dots.deleteDot(point);
                        check = false;
                        break;
                    }
                }
            }

            if (check){
                Random random = new Random();

                int intR = random.nextInt(255);
                int intG = random.nextInt(255);
                int intB = random.nextInt(255);

                kmitl.lab03.jittakan58070012.simplemydot.model.Dot dot = new kmitl.lab03.jittakan58070012.simplemydot.model.Dot(CenterX, CenterY, 50, intR, intG, intB);
                dots.addDot(dot);
            }

        }
        return super.onTouchEvent(event);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int CenterX = random.nextInt(this.dotview.getWidth());
        int CenterY = random.nextInt(this.dotview.getHeight());
        int intR = random.nextInt(255);
        int intG = random.nextInt(255);
        int intB = random.nextInt(255);

        dots.addDot(new kmitl.lab03.jittakan58070012.simplemydot.model.Dot(CenterX, CenterY, 50, intR, intG, intB));
    }


    @Override
    public void onClick(View v) {
        dots.clearDot();
        dotview.invalidate();
    }

    public boolean dotRangeCheck(kmitl.lab03.jittakan58070012.simplemydot.model.Dot dot, int x, int y){
        double dis2P = Math.pow(Math.pow( (dot.getCenterX()- x) , 2 ) + Math.pow( (dot.getCenterY() - y) , 2) , 0.5);
        if(dot != null){
            if (dis2P<=dot.getRadius()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDotsChange(kmitl.lab03.jittakan58070012.simplemydot.model.Dots dots) {
        dotview.setDots(dots);
        dotview.invalidate();
    }

    public boolean requestWriteExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                // Show an explanation to the user *asynchronously* — don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                return false;
        } else {

                // No explanation needed, we can request the permission.
                return true;
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
        }

    }

}
