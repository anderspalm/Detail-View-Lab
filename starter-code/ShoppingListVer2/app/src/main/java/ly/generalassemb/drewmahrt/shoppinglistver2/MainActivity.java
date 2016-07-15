package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

import ly.generalassemb.drewmahrt.shoppinglistver2.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {


    ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlistview = (ListView) findViewById(R.id.shopping_list_view);

        //Ignore the two lines below, they are for setup
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        DBAssetHelper dbAssetHelper = new DBAssetHelper(MainActivity.this);
        dbAssetHelper.getReadableDatabase();

        Cursor cursor = DBHelper.getInstance(MainActivity.this).AllTableValues();
        if(cursor.moveToFirst()) {
            CursorAdapter cursorAdapter = new CursorAdapter(MainActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup parent) {
                    return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView name = (TextView) view.findViewById(android.R.id.text1);
                    name.setText(cursor.getString(cursor.getColumnIndex(DBHelper.ITEM_NAME)));
                }
            };
            mlistview.setAdapter(cursorAdapter);

            mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, ReceiverActivity.class);
                    intent.putExtra(DBHelper.COL_ID, (int) id);
                    startActivity(intent);
                }
            });
        }

    }
}
