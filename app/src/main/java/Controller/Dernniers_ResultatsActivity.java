package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.Test_COVID.R;

public class Dernniers_ResultatsActivity extends AppCompatActivity {
    private ImageView icone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dernniers__resultats);

        icone = (ImageView) findViewById(R.id.im3);

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(Dernniers_ResultatsActivity.this, ChoiceActivity.class);
                startActivity(mo);
            }
        });
    }
}