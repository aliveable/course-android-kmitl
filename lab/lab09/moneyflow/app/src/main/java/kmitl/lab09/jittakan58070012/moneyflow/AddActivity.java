package kmitl.lab09.jittakan58070012.moneyflow;


import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{


    private Button Save_bt;
    private EditText item;
    private EditText amount;
    private int position;
    private int SQL_ID;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Constant.Checkedit = 0;
            Intent intent = new Intent(this, MainActivity.class);
            Constant.activity.finish();
            startActivity(intent);
            finish();
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpage);

        Constant.ADD_STATE = "Income";
       Save_bt = findViewById(R.id.Save);
       item = findViewById(R.id.Items_add);
       amount = findViewById(R.id.Add_Amount);
       Save_bt.setOnClickListener(this);

       Intent intent = getIntent();

        position = intent.getIntExtra("position",0);
        Log.d("addcheckposition", String.valueOf(position));

        if (Constant.Checkedit == 1){
            new AsyncTask<Void, Void, List<UsesInfo>>() {
                @Override
                protected List<UsesInfo> doInBackground(Void... voids) {
                    List<UsesInfo> result = MainActivity.moneyInfoDB.usesInfoDAO().allItem();
                    return result;
                }

                @Override
                protected void onPostExecute(List<UsesInfo> usesInfos) {
                    item.setText(usesInfos.get(position).getItem());
                    amount.setText(String.valueOf(usesInfos.get(position).getAmount()));

                    SQL_ID = usesInfos.get(position).getId();

                    Log.d("check ID", "onPostExecute: " + usesInfos.get(position).getId());
                }
            }.execute();



        }


        final SingleSelectToggleGroup single = (SingleSelectToggleGroup) findViewById(R.id.group_choices);
        single.setOnCheckedChangeListener(new SingleSelectToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SingleSelectToggleGroup group, int checkedId) {
                if (group.getCheckedId() == R.id.choice_a) {
                    //Income
                    Constant.ADD_STATE = "Income";
                    Log.d("state",Constant.ADD_STATE);
                } else if (group.getCheckedId() == R.id.choice_b) {
                    //Payout
                    Constant.ADD_STATE = "Payout";
                    Log.d("state",Constant.ADD_STATE);
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Save) {

            if (Constant.Checkedit == 1){
                new AsyncTask<Void, Void, List<UsesInfo>>() {
                    @Override
                    protected List<UsesInfo> doInBackground(Void... voids) {
                        List<UsesInfo> result = MainActivity.moneyInfoDB.usesInfoDAO().allItem();
                        return result;
                    }

                    @Override
                    protected void onPostExecute(List<UsesInfo> usesInfos) {
                        MainActivity.moneyInfoDB.usesInfoDAO().UpdateColumn(Constant.ADD_STATE,
                                item.getText().toString(),
                                Integer.valueOf(amount.getText().toString()), SQL_ID);
                    }
                }.execute();

            }else{
                final UsesInfo usesInfo = new UsesInfo();
                usesInfo.setItem(item.getText().toString());
                usesInfo.setAmount(Integer.valueOf(amount.getText().toString()));
                usesInfo.setType(Constant.ADD_STATE);
                new AsyncTask<Void, Void, List<UsesInfo>>() {
                    @Override
                    protected List<UsesInfo> doInBackground(Void... voids) {
                        List<UsesInfo> result = MainActivity.moneyInfoDB.usesInfoDAO().allItem();
                        return result;
                    }

                    @Override
                    protected void onPostExecute(List<UsesInfo> usesInfos) {
                        MainActivity.moneyInfoDB.usesInfoDAO().Insert(usesInfo);
                    }
                }.execute();
            }


            Constant.Checkedit = 0;


        }


        Intent intent = new Intent(this, MainActivity.class);
        Constant.activity.finish();
        startActivity(intent);
        finish();
    }
}
