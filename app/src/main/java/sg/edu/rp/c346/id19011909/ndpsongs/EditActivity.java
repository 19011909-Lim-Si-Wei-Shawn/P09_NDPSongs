package sg.edu.rp.c346.id19011909.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    //Creating Variable,
    EditText ETID;
    EditText ETSongEdit;
    EditText ETSingerEdit;
    EditText ETYearEdit;

    RadioGroup RBEdit;
    RadioButton RB1, RB2, RB3, RB4, RB5;

    Button BtnUpdate;
    Button BtnDelete;
    Button BtnCancel;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Linking Variable,
        ETID = findViewById(R.id.ETID);
        ETSongEdit = findViewById(R.id.ETSongEdit);
        ETSingerEdit = findViewById(R.id.ETSingerEdit);
        ETYearEdit = findViewById(R.id.ETYearEdit);

        RBEdit = findViewById(R.id.RBGroupEdit);
        RB1 = findViewById(R.id.RB1);
        RB2 = findViewById(R.id.RB2);
        RB3 = findViewById(R.id.RB3);
        RB4 = findViewById(R.id.RB4);
        RB5 = findViewById(R.id.RB5);

        BtnUpdate = findViewById(R.id.BtnUpdate);
        BtnDelete = findViewById(R.id.BtnDelete);
        BtnCancel = findViewById(R.id.BtnCancel);

        //Setting Intent.
        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        ETID.setText(String.valueOf(data.get_id()));
        ETSongEdit.setText(data.getTitle());
        ETSingerEdit.setText(data.getSingers());
        ETYearEdit.setText(String.valueOf(data.getYear()));

        int TempData = data.getStars();

        if(TempData == 1)
        {   RB1.setChecked(true);       }

        else if(TempData == 2)
        {   RB2.setChecked(true);       }

        else if(TempData == 3)
        {   RB3.setChecked(true);       }

        else if(TempData == 4)
        {   RB4.setChecked(true);       }

        else
        {   RB5.setChecked(true);       }

        //Setting Action,
        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                int RadioChoice;

                //Getting RadioCheck Result,
                int RadioChecker = RBEdit.getCheckedRadioButtonId();
                if(RadioChecker == R.id.RB1)
                {   RadioChoice = 1;      }

                else if(RadioChecker == R.id.RB2)
                {   RadioChoice = 2;      }

                else if(RadioChecker == R.id.RB3)
                {   RadioChoice = 3;      }

                else if(RadioChecker == R.id.RB4)
                {   RadioChoice = 4;      }

                else
                {   RadioChoice = 5;      }

                data.setTitle(ETSongEdit.getText().toString());
                data.setSingers(ETSingerEdit.getText().toString());
                data.setYear(Integer.parseInt(ETYearEdit.getText().toString()));
                data.setStars(RadioChoice);

                dbh.updateSongs(data);
                dbh.close();

                Intent i = new Intent(EditActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.get_id());

                Intent i = new Intent(EditActivity.this, DisplayActivity.class);
                startActivity(i);

            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

    }
}