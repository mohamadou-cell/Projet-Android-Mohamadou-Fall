package Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Test_COVID.Database;
import com.example.Test_COVID.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.SSLEngineResult;

import Model.Personne;

public class InformationActivity extends AppCompatActivity  {
    private EditText nom, prenom, adresse, profil;
    private Button btn2;
    private Personne p1;
    private boolean verif;
    private ImageView icone;
    private ImageButton locate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        adresse = (EditText) findViewById(R.id.email);
        profil = (EditText) findViewById(R.id.profil);
        icone = (ImageView) findViewById(R.id.im2);
        locate = (ImageButton) findViewById(R.id.im9);



        Places.initialize(getApplicationContext(), "AIzaSyD80Is3lmK-wjOgp4ej45qYydjYedq30Rs");
        locate.setFocusable(false);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG, Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                        fieldList).build(InformationActivity.this);
                startActivityForResult(intent, 100);
            }
        });

        Intent intent = this.getIntent();
        this.p1 = (Personne) intent.getSerializableExtra("p1");

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrer();
            }
        });

        icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(InformationActivity.this, ChoiceActivity.class);
                startActivity(mo);
            }
        });
    }

    public void enregistrer() {
        Database db = new Database(this);

        String name = this.nom.getText().toString();
        String first = this.prenom.getText().toString();
        String mail = this.adresse.getText().toString();
        String descript = this.profil.getText().toString();

        if(name.isEmpty() && first.isEmpty() && mail.isEmpty() && descript.isEmpty()) {
            Toast.makeText(InformationActivity.this,
                    "Svp! Remplissez les champs vides", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            this.p1 = new Personne(name, first, mail, descript);
            db.ajoutInfos(p1);
        }

        this.verif = true;
        Intent mo = new Intent(InformationActivity.this, QCMActivity.class);
        startActivity(mo);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            adresse.setText(place.getAddress());
            adresse.setText(String.format("Locality Name : %s",place.getName()));
            adresse.setText(String.valueOf(place.getLatLng()));

        }
    }

}