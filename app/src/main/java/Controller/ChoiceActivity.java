package Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.Test_COVID.R;

public class ChoiceActivity extends AppCompatActivity {

    private Button btn_test;
    private Button btn_res;
    private ImageView icone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        btn_test = (Button) findViewById(R.id.btn_test);
        btn_res = (Button) findViewById(R.id.btn_res);
        icone = (ImageView) findViewById(R.id.im1);

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ma = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(ma);
            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ma = new Intent(ChoiceActivity.this, InformationActivity.class);
                startActivity(ma);
            }
        });
        btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(ChoiceActivity.this, Dernniers_ResultatsActivity.class);
                startActivity(mo);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1){
            Intent mo = new Intent(ChoiceActivity.this, A_proposActivity.class);
            startActivity(mo);
            return true;
        }
        else
        if (id == R.id.item2){
            Intent mo = new Intent(ChoiceActivity.this, ContactActivity.class);
            startActivity(mo);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}