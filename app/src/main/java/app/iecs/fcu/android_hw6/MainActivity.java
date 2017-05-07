package app.iecs.fcu.android_hw6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button theNoteAdd;
    ListView theNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theNoteAdd = (Button)findViewById(R.id.bt_note_add);
        theNoteAdd.setOnClickListener(clickNoteAdd);

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
}
