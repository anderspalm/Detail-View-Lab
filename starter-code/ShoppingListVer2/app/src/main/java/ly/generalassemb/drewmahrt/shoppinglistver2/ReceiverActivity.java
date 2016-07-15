package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        int query = getIntent().getIntExtra(DBHelper.COL_ID, 0);
        String temp = query + "";

        TextView textView_name = (TextView) findViewById(R.id.name);
        TextView textView_desc = (TextView) findViewById(R.id.description);
        TextView textView_price = (TextView) findViewById(R.id.price);
        TextView textView_type = (TextView) findViewById(R.id.type);

        Cursor cursor = DBHelper.getInstance(this).search_a_table(temp);

        if(cursor.moveToNext()){
            textView_name.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_NAME)));
            textView_desc.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_DESCRIPTION)));
            textView_price.setText(cursor.getString(cursor.getColumnIndex(DBHelper.PRICE)));
            textView_type.setText(cursor.getString(cursor.getColumnIndex(DBHelper.TYPE)));

        }
        cursor.close();
    }


}
