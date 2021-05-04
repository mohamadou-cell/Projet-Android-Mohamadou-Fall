package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.Test_COVID.R;

public class QCMActivity extends AppCompatActivity {
    public static final String R1="A", R2="B", R3="C", R4="D", R5="E";
    private Button suivant;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10;
    private ImageView icone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcm);

        icone = (ImageView) findViewById(R.id.im4);

        rb1 = (RadioButton) findViewById(R.id.rb1rg1);
        rb2 = (RadioButton) findViewById(R.id.rb2rg1);
        rb3 = (RadioButton) findViewById(R.id.rb1rg2);
        rb4 = (RadioButton) findViewById(R.id.rb2rg2);
        rb5 = (RadioButton) findViewById(R.id.rb1rg3);
        rb6 = (RadioButton) findViewById(R.id.rb2rg3);
        rb7 = (RadioButton) findViewById(R.id.rb1rg4);
        rb8 = (RadioButton) findViewById(R.id.rb2rg4);
        rb9 = (RadioButton) findViewById(R.id.rb1rg5);
        rb10 = (RadioButton) findViewById(R.id.rb2rg5);
        suivant = (Button) findViewById(R.id.btn_suivant);

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(QCMActivity.this, InformationActivity.class);
                startActivity(mo);
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mo = new Intent(QCMActivity.this, ScoreActivity.class);
                if (rb1.isChecked() && !rb2.isChecked()){
                    String reponse = rb1.getText().toString();
                    mo.putExtra(R1, reponse);
                }
                else if (!rb1.isChecked() && rb2.isChecked()){
                    String reponse = rb2.getText().toString();
                    mo.putExtra(R1, reponse);
                }
                if (rb3.isChecked() && !rb4.isChecked()){
                    String reponse = rb3.getText().toString();
                    mo.putExtra(R2, reponse);
                }
                else if (!rb3.isChecked() && rb4.isChecked()){
                    String reponse = rb4.getText().toString();
                    mo.putExtra(R2, reponse);
                }
                if (rb5.isChecked() && !rb6.isChecked()){
                    String reponse = rb5.getText().toString();
                    mo.putExtra(R3, reponse);
                }
                else if (!rb5.isChecked() && rb6.isChecked()){
                    String reponse = rb6.getText().toString();
                    mo.putExtra(R3, reponse);
                }
                if (rb7.isChecked() && !rb8.isChecked()){
                    String reponse = rb7.getText().toString();
                    mo.putExtra(R4, reponse);
                }
                else if (!rb7.isChecked() && rb8.isChecked()){
                    String reponse = rb8.getText().toString();
                    mo.putExtra(R4, reponse);
                }
                if (rb9.isChecked() && !rb10.isChecked()){
                    String reponse = rb9.getText().toString();
                    mo.putExtra(R5, reponse);
                }
                else if (!rb9.isChecked() && rb10.isChecked()){
                    String reponse = rb10.getText().toString();
                    mo.putExtra(R5, reponse);
                }

                startActivity(mo);
            }
        });


    }

}