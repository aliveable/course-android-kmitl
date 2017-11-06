package kmitl.lab09.jittakan58070012.moneyflow;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;

/**
 * Created by 58070012 on 11/6/2017.
 */

@Database(entities = {UsesInfo.class}, version = 1)
public abstract class moneyInfoDB extends RoomDatabase {
    public abstract UsesInfoDAO usesInfoDAO();
}