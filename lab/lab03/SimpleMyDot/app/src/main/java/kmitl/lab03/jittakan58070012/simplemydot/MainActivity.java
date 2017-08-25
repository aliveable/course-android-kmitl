package kmitl.lab03.jittakan58070012.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.jittakan58070012.simplemydot.model.Dot;
import kmitl.lab03.jittakan58070012.simplemydot.view.DotView;

import static kmitl.lab03.jittakan58070012.simplemydot.R.id.dotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangeListener{

    private Dot dot;
    private DotView dotview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = new Dot(0, 0, 20);
        dot.setListener(this);

        dotview = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        dot.setCenterX(random.nextInt(this.dotview.getWidth()));

        dot.setCenterY(random.nextInt(this.dotview.getHeight()));


    }

    @Override
    public void onDotChanged(Dot dot) {
        System.out.print(dot.getCenterX());
        System.out.print(dot.getCenterY());

        TextView centerXTextView = (TextView) findViewById(R.id.centerXTextView);
        TextView centerYTextView = (TextView) findViewById(R.id.centerYTextView);

        centerXTextView.setText(String.valueOf(dot.getCenterX()));
        centerYTextView.setText(String.valueOf(dot.getCenterY()));

        dotview.setDot(dot);
        dotview.invalidate();
    }
}
