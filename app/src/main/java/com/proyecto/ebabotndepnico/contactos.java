package com.proyecto.ebabotndepnico;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class contactos extends AppCompatActivity {


    TextView contNom1;
    TextView contNom2;
    TextView contNom3;
    TextView contNom4;
    TextView cont1;
    TextView cont2;
    TextView cont3;
    TextView cont4;


    EditText etNombreCon, etTel;
    FloatingActionButton btnCont1;
    FloatingActionButton btnCont2;
    FloatingActionButton btnCont3;
    FloatingActionButton btnCont4;

    Button btnGuardarCon;
    Button btnVolver;

    FloatingActionButton btnListo1;
    FloatingActionButton btnListo2;
    FloatingActionButton btnListo3;
    FloatingActionButton btnListo4;


    FirebaseFirestore bd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        this.setTitle("Configuración Aplicativo 2/4");


        etNombreCon = findViewById(R.id.etNombreCont);
        etTel = findViewById(R.id.etTel);

        btnCont1 = findViewById(R.id.btnCont1);

        btnCont1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirContacto();
            }
        });

        btnListo1 = findViewById(R.id.btnListo1);
        btnListo2 = findViewById(R.id.btnListo2);
        btnListo3 = findViewById(R.id.btnListo3);
        btnListo4 = findViewById(R.id.btnListo4);

        btnVolver = findViewById(R.id.btnVolver);


        bd = FirebaseFirestore.getInstance();

        Bundle bundle = this.getIntent().getExtras();
        String ID = bundle.getString("ID");
        String IDbtn = bundle.getString("IDbtn");
        volverBotones(ID);


        Contacto1(ID,IDbtn);
        Contacto2(ID,IDbtn);
        Contacto3(ID,IDbtn);
        Contacto4(ID,IDbtn);

    }

    public void Contacto1(String ID, String IDbtn) {

        cont1 = findViewById(R.id.cont1);
        contNom1 = findViewById(R.id.contNom1);

        String IDcontacto =  bd.collection("usuarios").document(ID)
                .collection("botones").document(IDbtn)
                .collection("contactos").document("gnAxPhqOWTeh5TxAx9z3")
                .getId();

        mostrarContacto1(ID,IDbtn,IDcontacto,contNom1,cont1);

       /* btnCont1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirContacto();
            }
        });*/

        btnListo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto1(ID,IDbtn,IDcontacto,contNom1,cont1);
            }
        });

    }

    public void Contacto2(String ID, String IDbtn) {

        cont2 = findViewById(R.id.cont2);
        contNom2 = findViewById(R.id.contNom2);


        String IDcontacto = bd.collection("usuarios").document(ID)
                .collection("botones").document(IDbtn)
                .collection("contactos").document("IqwbHuRrXp4dHkJEme3j")
                .getId();

        mostrarContacto1(ID,IDbtn,IDcontacto,contNom2,cont2);

        /*btnCont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirContacto();
            }
        });*/

        btnListo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto1(ID,IDbtn,IDcontacto,contNom2,cont2);
            }
        });


    }

    public void Contacto3(String ID, String IDbtn) {

        cont3 = findViewById(R.id.cont3);
        contNom3 = findViewById(R.id.contNom3);

        String IDcontacto = bd.collection("usuarios").document(ID)
                .collection("botones").document(IDbtn)
                .collection("contactos").document("f9CybwrIogVICsrYNVAO")
                .getId();

        mostrarContacto1(ID,IDbtn,IDcontacto,contNom3,cont3);

        /*btnCont3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirContacto();
            }
        });*/

        btnListo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto1(ID,IDbtn,IDcontacto,contNom3,cont3);
            }
        });


    }

    public void Contacto4(String ID, String IDbtn) {

        cont4 = findViewById(R.id.cont4);
        contNom4 = findViewById(R.id.contNom4);

        String IDcontacto = bd.collection("usuarios").document(ID)
                .collection("botones").document(IDbtn)
                .collection("contactos").document("NYLVhTBV7mTYuUwkcsRE")
                .getId();

        mostrarContacto1(ID,IDbtn,IDcontacto,contNom4,cont4);

        /*btnCont4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirContacto();
            }
        });*/

        btnListo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarContacto1(ID,IDbtn,IDcontacto,contNom4,cont4);
            }
        });
    }


    public void mostrarContacto1(String ID, String IDbtn,String IDcontacto, TextView contNom, TextView cont){

        Log.d("IDconMostrar" , IDcontacto);

        bd.collection("usuarios").document(ID)
                .collection("botones").document(IDbtn)
                .collection("contactos").document(IDcontacto)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()){
                    String nombre = documentSnapshot.getString("Nombre");
                    String telefono = documentSnapshot.getString("Telefono");
                    contNom.setText(nombre);
                    cont.setText(telefono);
                }
            }
        });

    }

    public void guardarContacto1(String ID, String IDbtn, String IDcontacto, TextView contNom, TextView cont) {

        Log.d("IDconguardarContacto", IDcontacto);

        if (etNombreCon.getText().toString().isEmpty() || etTel.getText().toString().isEmpty()) {

            msjErrorVacio();
        } else {

            Map<String, String> map = new HashMap<>();

            String nombre = etNombreCon.getText().toString();
            String telefono = etTel.getText().toString();

            map.put("Nombre",  nombre);
            map.put("Telefono", telefono);

            bd.collection("usuarios").document(ID)
                    .collection("botones").document(IDbtn)
                    .collection("contactos").document(IDcontacto)
                    .set(map).addOnSuccessListener(new OnSuccessListener<Void>() {

                @Override
                public void onSuccess(Void aVoid) {

                    msjContactoAñadido();
                    Log.d("Añadido", "si se añadio");


                }
            });
        }
        mostrarContacto1(ID,IDbtn,IDcontacto,contNom,cont);
    }

    public void añadirContacto() {

        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(i, 1);

    }

    public void volverBotones(String ID) {

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (contNom1.getText().toString().isEmpty()
                        && contNom2.getText().toString().isEmpty()
                        && contNom3.getText().toString().isEmpty()
                        && contNom4.getText().toString().isEmpty()){

                    Toast.makeText(contactos.this, "Debes tener mínimo un contacto registrado", Toast.LENGTH_SHORT).show();

                }

                else{

                    btn_edit(ID);
                }

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {

                int iNombre = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int iTelefono = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String nombre = cursor.getString(iNombre);
                String numero = cursor.getString(iTelefono);

                numero = numero.
                        replace(" ", "").
                        replace("(", "").
                        replace(")", "").
                        replace("+57", "").
                        replace("-", "");

                etNombreCon.setText(nombre);
                etTel.setText(numero);

            }
        }

    }
    public void msjErrorVacio() {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Hay espacios vacios", Toast.LENGTH_SHORT);

        toast1.show();
    }

    public void msjContactoAñadido() {
        Toast toastCont =
                Toast.makeText(getApplicationContext(),
                        "Contactos Añadido", Toast.LENGTH_SHORT);

        toastCont.show();
    }


    private void btn_edit(String ID) {

        Intent i = new Intent(this, btn_edit.class);
        i.putExtra("ID", ID);
        startActivity(i);

    }

}





