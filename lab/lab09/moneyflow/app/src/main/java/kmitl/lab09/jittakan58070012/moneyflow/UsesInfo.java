package kmitl.lab09.jittakan58070012.moneyflow;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.text.Editable;

/**
 * Created by 58070012 on 11/6/2017.
 */
@Entity(tableName = "USES_INFO")
class UsesInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Type;
    private String Item;
    private int Amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

}
