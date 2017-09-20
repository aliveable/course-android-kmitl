package kmitl.lab05.jittakan58070012.a01_lab05_workshop;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kmitl.lab05.jittakan58070012.a01_lab05_workshop.Fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rpb = (Button) findViewById(R.id.button);
        rpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentmanager = getSupportFragmentManager();
                fragmentmanager.beginTransaction().replace(R.id.FragmentContainer,new MainFragment().newInstance("Alive is Back"),"MainFragment").commit();
            }
        });

        FragmentManager fragmentmanager = getSupportFragmentManager();
        fragmentmanager.beginTransaction().add(R.id.FragmentContainer,new MainFragment().newInstance("My name is Alive"),"MainFragment").commit();

    }
}
