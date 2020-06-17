package vn.edu.ntu.quangloc.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String sharedRef = "shared_ref";
    public static final String keyName = "Name";
    public static final String keyDate = "Birthday";
    public static final String keyPhone = "Number";
    public static final String keyMale = "Nam";
    public static final String keyFemale = "Nu";
    EditText edtName, edtDate, edtPhone;
    RadioButton rdbNam, rdbNu;
    Button btnAdd, btnRemove, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }

    public void addView(){
        edtName = findViewById(R.id.edtName);
        edtDate = findViewById(R.id.edtDate);
        edtPhone = findViewById(R.id.edtPhone);
        rdbNam = findViewById(R.id.rdbNam);
        rdbNu = findViewById(R.id.rdbNu);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnRemove = findViewById(R.id.btnRemove);

        btnAdd.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAdd: Add(); break;
            case R.id.btnEdit: Edit(); break;
            case R.id.btnRemove: Remove(); break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Edit();
    }

    private void Add(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedRef, MODE_PRIVATE);
        if(sharedPreferences != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(keyName, edtName.getText().toString());
            editor.putString(keyDate, edtDate.getText().toString());
            editor.putString(keyPhone, edtPhone.getText().toString());
            editor.putBoolean(keyMale, rdbNam.isChecked());
            editor.putBoolean(keyFemale, rdbNu.isChecked());
            editor.commit();
        }
    }
    private void Edit(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedRef, MODE_PRIVATE);
        if(sharedPreferences != null){
            String name = sharedPreferences.getString(keyName, "Unknown");
            String date = sharedPreferences.getString(keyDate, "Unknown");
            String phone = sharedPreferences.getString(keyPhone, "Unknown");
            Boolean male = sharedPreferences.getBoolean(keyMale, true);
            Boolean female = sharedPreferences.getBoolean(keyFemale, false);

            edtName.setText(name);
            edtDate.setText(date);
            edtPhone.setText(phone);
            rdbNam.setChecked(male);
            rdbNu.setChecked(female);
        }
    }
    private void Remove(){
        edtName.setText("");
        edtDate.setText("");
        edtPhone.setText("");
        rdbNam.setChecked(true);
        rdbNu.setChecked(false);
    }
}