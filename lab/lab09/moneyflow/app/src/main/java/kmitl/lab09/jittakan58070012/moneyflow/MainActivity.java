package kmitl.lab09.jittakan58070012.moneyflow;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ArrayList<HashMap> list;
    public static moneyInfoDB moneyInfoDB;
    private AlertDialog.Builder builderSingle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Select One Name:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Edit");
        arrayAdapter.add("Delete");
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        moneyInfoDB = Room.databaseBuilder(this, moneyInfoDB.class, "USES_DB").allowMainThreadQueries().build();
        ListView lview = (ListView) findViewById(R.id.listview);
        populateList();
        listviewAdapter adapter = new listviewAdapter(this, list);
        lview.setAdapter(adapter);
        Constant.activity = this;

        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            Log.d("Check", "onClick: IN edit");

                            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                            intent.putExtra("position", position-1);
                            Constant.Checkedit = 1;
                            startActivity(intent);

                        }else{

                            new AsyncTask<Void, Void, List<UsesInfo>>() {
                                @Override
                                protected List<UsesInfo> doInBackground(Void... voids) {
                                    List<UsesInfo> result = moneyInfoDB.usesInfoDAO().allItem();
                                    return result;
                                }

                                @Override
                                protected void onPostExecute(List<UsesInfo> usesInfos) {
                                    moneyInfoDB.usesInfoDAO().deletecolumn(usesInfos.get(position-1).getItem());
                                    reload();
                                }

                                @Override
                                protected void onProgressUpdate(Void... values) {
                                }
                            }.execute();
                            reload();
                        }

                    }
                });
                builderSingle.show();






            }
        });


        Button add_button = findViewById(R.id.AddButton);
        add_button.setOnClickListener(this);



    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
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
        }

    }
}
