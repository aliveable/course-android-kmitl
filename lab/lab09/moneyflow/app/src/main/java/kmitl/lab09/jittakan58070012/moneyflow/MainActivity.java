package kmitl.lab09.jittakan58070012.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import static kmitl.lab09.jittakan58070012.moneyflow.Constant.FIRST_COLUMN;
import static kmitl.lab09.jittakan58070012.moneyflow.Constant.SECOND_COLUMN;
import static kmitl.lab09.jittakan58070012.moneyflow.Constant.THIRD_COLUMN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ArrayList<HashMap> list;
    public static moneyInfoDB moneyInfoDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moneyInfoDB = Room.databaseBuilder(this, moneyInfoDB.class, "USES_DB").allowMainThreadQueries().build();
        ListView lview = (ListView) findViewById(R.id.listview);
        populateList();
        listviewAdapter adapter = new listviewAdapter(this, list);
        lview.setAdapter(adapter);


        Button add_button = findViewById(R.id.AddButton);
        add_button.setOnClickListener(this);



    }

    private void populateList() {
        list = new ArrayList<HashMap>();
        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN,"Type");
        temp.put(SECOND_COLUMN, "Items");
        temp.put(THIRD_COLUMN, "Amount");
        list.add(temp);
        new AsyncTask<Void, Void, List<UsesInfo>>() {
                @Override
                protected List<UsesInfo> doInBackground(Void... voids) {
                    List<UsesInfo> result = moneyInfoDB.usesInfoDAO().allItem();
                    return result;
                }

                @Override
                protected void onPostExecute(List<UsesInfo> usesInfos) {
                    for (UsesInfo items: usesInfos){
                        HashMap temp = new HashMap();
                        if (items.getType().equals("Income")) {
                            temp.put(FIRST_COLUMN, "+");
                        }else{
                            temp.put(FIRST_COLUMN, "-");
                        }
                        temp.put(SECOND_COLUMN, items.getItem());
                        temp.put(THIRD_COLUMN, items.getAmount());
                        list.add(temp);
                    }
                }

                @Override
                protected void onProgressUpdate(Void... values) {
                }
            }.execute();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.AddButton){
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
            Constant.activity = this;
        }

    }
}
