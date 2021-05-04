package Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.Test_COVID.R;

public class ContactActivity extends AppCompatActivity {
    public static String CLE = "mess";
    private EditText objet, message, numero;
    private Button envoyer;
    private ImageView icone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        objet = (EditText) findViewById(R.id.etobjet);
        message = (EditText) findViewById(R.id.etmess);
        numero = (EditText) findViewById(R.id.etnum);
        envoyer = (Button) this.findViewById(R.id.btnenv);
        icone = (ImageView) findViewById(R.id.im7);

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(ContactActivity.this, ChoiceActivity.class);
                startActivity(mo);
            }
        });

        envoyerSms();
    }

    private void envoyerSms(){
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage(numero.getText().toString(), null, message.getText().toString(), null, null);
            }
        });
    }


}