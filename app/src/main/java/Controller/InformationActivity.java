package Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.SSLEngineResult;

import Model.Personne;

public class InformationActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationProviderClient;
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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(InformationActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(InformationActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
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

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null){

                    try {
                        Geocoder geocoder = new Geocoder(InformationActivity.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        adresse.setText(addresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

}

