package kmitl.lab03.jittakan58070012.simplemydot;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private Button bckButton;
    private Intent intent;
    private TextView textview;
    private TextView viewserial;
    private TextView viewparcel;
    private DotSerializable dotserializable;
    private DotParcelable dotparceleble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ///////////////////////////////////////////////////////////////

        bckButton = (Button) findViewById(R.id.btnBack);
        bckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ///////////////////////////////////////////////////////////////
        int x = getIntent().getIntExtra("xValue", 0);

        dotserializable = (DotSerializable) getIntent().getSerializableExtra("dotserializable");
        dotparceleble = (DotParcelable) getIntent().getParcelableExtra("dotparceleble");

        viewserial = (TextView) findViewById(R.id.SerialView);
        viewserial.setText("Center X :" + String.valueOf(dotserializable.getCenterX()) +
                           "\nCenter Y :" + String.valueOf(dotserializable.getCenterY()) +
                           "\nColor :" + dotserializable.getColor() +
                           "\nRadius : " + String.valueOf(dotserializable.getRadius()));

        viewparcel = (TextView) findViewById(R.id.TxtParcel);
        viewparcel.setText("Center X: "+String.valueOf(dotparceleble.getCenterX()) +
                           "\nCenter Y: " + String.valueOf(dotparceleble.getCenterY()) +
                           "\nRadius : "+ String.valueOf(dotparceleble.getRadius()));

        textview = (TextView) findViewById(R.id.txtDisplay);
        textview.setText(String.valueOf(x));

    }
}
