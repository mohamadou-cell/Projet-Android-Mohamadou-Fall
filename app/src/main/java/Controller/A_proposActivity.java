package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.Test_COVID.R;

public class A_proposActivity extends AppCompatActivity {
    private ImageView icone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        icone = (ImageView) findViewById(R.id.im6);

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(A_proposActivity.this, ChoiceActivity.class);
                startActivity(mo);
            }
        });

    }
}