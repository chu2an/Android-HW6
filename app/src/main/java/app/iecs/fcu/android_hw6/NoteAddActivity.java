package app.iecs.fcu.android_hw6;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteAddActivity extends AppCompatActivity {

    EditText theTitle,theText;
    ArrayList<String> titlelist;
    SQLiteDatabase db;
    int notepos;
    Button theDelete,theFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        theTitle = (EditText)findViewById(R.id.et_title);
        theText = (EditText)findViewById(R.id.et_text);
        theDelete = (Button)findViewById(R.id.bt_delete);
        theDelete.setOnClickListener(clickDelete);
        theDelete.setOnLongClickListener(longclickDelete);
        theFinish = (Button)findViewById(R.id.bt_finish);
        theFinish.setOnClickListener(clickFinish);
        theFinish.setOnLongClickListener(longclickFinish);

        Intent intent = getIntent();
        notepos = intent.getIntExtra("NOTEPOS", -1);

    }
    @Override
    protected void onResume() {
        super.onResume();
        DBOpenHelper openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();

        titlelist = NoteDB.getTitleList(db);

        if (notepos != -1) {
            String title = titlelist.get(notepos);
            theTitle.setText(title);
            theText.setText( NoteDB.getBody(db, title) );
        } else {
            theTitle.setText("");
            theText.setText("");
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        String title = theTitle.getText().toString();
        if (title.length() == 0) {
            Toast.makeText(this, "標題不能為空白，便條無儲存",
                    Toast.LENGTH_LONG).show();
        } else {
            NoteDB.addNote(db, theTitle.getText().toString(), theText.getText().toString());
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        String title = theTitle.getText().toString();
        if (title.length() == 0) {
            Toast.makeText(this, "標題不能為空白，便條無儲存",
                    Toast.LENGTH_LONG).show();
        } else {
            NoteDB.addNote(db, theTitle.getText().toString(), theText.getText().toString());
        }
    }
    boolean isTitleExist(String title) {
        for (int i = 0; i < titlelist.size(); i++)
            if (title.equalsIgnoreCase(titlelist.get(i)))
                return true;
        return false;
    }

    private OnClickListener clickDelete = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(NoteAddActivity.this, "去上一個畫面長按此Note即可刪除", Toast.LENGTH_SHORT).show();
        }
    };
    private OnLongClickListener longclickDelete = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(NoteAddActivity.this, "按人家那麼久幹嘛", Toast.LENGTH_SHORT).show();
            return false;
        }
    };
    private  OnClickListener clickFinish = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if( theTitle.getText().toString().length() == 0){
                Toast.makeText(NoteAddActivity.this, "筆記標題不可為空", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(NoteAddActivity.this, "筆記: " + theTitle.getText().toString() + " 已儲存", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };
    private  OnLongClickListener longclickFinish = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(NoteAddActivity.this, "按人家那麼久幹嘛", Toast.LENGTH_SHORT).show();
            return false;
        }
    };
}
