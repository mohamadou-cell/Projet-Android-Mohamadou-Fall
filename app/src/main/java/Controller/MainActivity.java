package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Test_COVID.R;

public class MainActivity extends AppCompatActivity {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast t = Toast.makeText(MainActivity.this,"Bonjour, veillez appuyer sur commencer pour poursuivre",Toast.LENGTH_SHORT);

        t.setGravity(Gravity.CENTER,0,0);
        t.show();

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(MainActivity.this,ChoiceActivity.class);
                startActivity(mo);
            }
        });
    }


}