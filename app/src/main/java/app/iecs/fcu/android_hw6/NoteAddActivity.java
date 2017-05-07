package app.iecs.fcu.android_hw6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;

public class NoteAddActivity extends AppCompatActivity {

    EditText theTitle,theText;
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

    }
    private OnClickListener clickDelete = new OnClickListener() {
        @Override
        public void onClick(View v) {

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
