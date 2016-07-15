package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

/**
 * Created by andeski on 7/15/16.
 */
public class DBHelper extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String TABLE_NAME = "SHOPPING_LIST";
    public static final String COL_ID ="_id";
    public static final String ITEM_NAME = "ITEM_NAME";
    public static final String ITEM_DESCRIPTION = "DESCRIPTION";
    public static final String PRICE = "PRICE";
    public static final String TYPE = "TYPE";
    public static final String[] ALL_COLUMNS = {COL_ID,ITEM_NAME,ITEM_DESCRIPTION,PRICE,TYPE};

    private static DBHelper instance;

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_NUMBERS_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ITEM_NAME + " TEXT, "+
                    ITEM_DESCRIPTION + " TEXT, " +
                    PRICE + " TEXT, " +
                    TYPE + " TEXT, " +
                    ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NUMBERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public Cursor AllTableValues(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME,null,null,null,null,null,null,null);
    }

    public Cursor search_a_table(String query){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                ALL_COLUMNS,
                COL_ID + "= ?",
                new String[]{query + ""},
        null,
        null,
        null
                );
        return cursor;
    }
}
