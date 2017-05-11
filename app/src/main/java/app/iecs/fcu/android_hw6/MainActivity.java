package app.iecs.fcu.android_hw6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button theNoteAdd;
    ListView theNoteList;
    SQLiteDatabase db;
    ArrayList<String> titlelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theNoteAdd = (Button)findViewById(R.id.bt_note_add);
        theNoteAdd.setOnClickListener(clickNoteAdd);

        theNoteList = (ListView)findViewById(R.id.lv_note_list);
        theNoteList.setOnItemClickListener(clickNoteList);
        theNoteList.setOnItemLongClickListener(longclickNoteList);

    }
    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }
    @Override
    protected void onResume() {
        super.onResume();

        DBOpenHelper openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();

        titlelist = NoteDB.getTitleList(db);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, titlelist);
        theNoteList.setAdapter(adapter);
    }
    @Override
    public void onStop(){
        super.onStop();

    }
    private OnClickListener clickNoteAdd = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent theIntent = new Intent().setClass(MainActivity.this,NoteAddActivity.class);
            startActivity(theIntent);
        }
    };
    private OnItemClickListener clickNoteList = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,NoteAddActivity.class);
            intent.putExtra("NOTEPOS", position);
            startActivity(intent);
        }
    };
    private OnItemLongClickListener longclickNoteList = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
            String title = titlelist.get(position);
            NoteDB.delNote(db, title);
            titlelist = NoteDB.getTitleList(db);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (MainActivity.this, android.R.layout.simple_list_item_1, titlelist);
            theNoteList.setAdapter(adapter);
            return false;
        }

    };
}
