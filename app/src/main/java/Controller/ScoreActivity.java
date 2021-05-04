package Controller;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Test_COVID.R;

import java.security.Permission;

public class ScoreActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 555;
    private static final String LOG_TAG = "AndroidExample";
    private int score=0;
    private EditText r1, r2, r3, r4, r5;
    private Button btn3;
    private ImageView icone;
    private ImageButton appel;
    private EditText numero;
    private EditText res;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        String rep1 = getIntent().getStringExtra(QCMActivity.R1);
        String rep2 = getIntent().getStringExtra(QCMActivity.R2);
        String rep3 = getIntent().getStringExtra(QCMActivity.R3);
        String rep4 = getIntent().getStringExtra(QCMActivity.R4);
        String rep5 = getIntent().getStringExtra(QCMActivity.R5);

        r1 = findViewById(R.id.et1);
        r1.setText(rep1);
        r2 = findViewById(R.id.et2);
        r2.setText(rep2);
        r3 = findViewById(R.id.et3);
        r3.setText(rep3);
        r4 = findViewById(R.id.et4);
        r4.setText(rep4);
        r5 = findViewById(R.id.et5);
        r5.setText(rep5);

        if (rep1.equals("OUI")){
            score = score + 1;
        }
        if (rep2.equals("OUI")){
            score = score + 1;
        }
        if (rep3.equals("OUI")){
            score = score + 1;
        }
        if (rep4.equals("OUI")){
            score = score + 1;
        }
        if (rep5.equals("OUI")){
            score = score + 1;
        }

        if (score > 3){
            Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        }
        res = (EditText) findViewById(R.id.et6);
        res.setText(String.valueOf(score));

        numero = (EditText) findViewById(R.id.et7);
        appel = (ImageButton) findViewById(R.id.im8);
        icone = (ImageView) findViewById(R.id.im5);
        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(ScoreActivity.this, QCMActivity.class);
                startActivity(mo);
            }
        });
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demandePermissionAppel();
            }
        });

    }

    private void vibrer() {
    }

    private void demandePermissionAppel() {



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int sendSmsPermisson = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE);

            if (sendSmsPermisson != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CALL
                );
                return;
            }
        }
        this.callNow();
    }

    @SuppressLint("MissingPermission")
    private void callNow() {
        String phoneNumber = this.numero.getText().toString();
        if (phoneNumber.trim().length() > 0) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            try {
                this.startActivity(callIntent);
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "Votre appel a échoué... " + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }
        else {
            Toast.makeText(ScoreActivity.this, "Entrer un numéro téléphone", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CALL: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i(LOG_TAG, "Permission accordée!");
                    Toast.makeText(this, "Permission accordée!", Toast.LENGTH_LONG).show();

                    this.callNow();
                }
                // Cancelled or denied.
                else {
                    Log.i(LOG_TAG, "Permission refusée!");
                    Toast.makeText(this, "Permission refusée!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CALL) {
            if (resultCode == RESULT_OK) {
                // Do something with data (Result returned).
                Toast.makeText(this, "Action OK", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action annulée", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action échoué", Toast.LENGTH_LONG).show();
            }
        }

    }


}