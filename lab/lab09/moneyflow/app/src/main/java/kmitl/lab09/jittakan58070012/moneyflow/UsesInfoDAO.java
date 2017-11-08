package kmitl.lab09.jittakan58070012.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by 58070012 on 11/6/2017.
 */
@Dao
interface UsesInfoDAO {

    @Insert
    void Insert(UsesInfo usesInfo);

    @Query("SELECT * FROM USES_INFO")
    List<UsesInfo> allItem();

    @Query("DELETE FROM USES_INFO WHERE id like :delete")
    int deletecolumn(int delete);

    @Query("UPDATE USES_INFO SET Type = :type,Item = :item ,Amount = :amount WHERE id =:ids")
    int UpdateColumn(String type, String item, int amount, int ids);
}
