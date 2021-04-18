package com.proyecto.ebabotndepnico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class contactos extends AppCompatActivity {

    EditText etNombreCon, etTel;
    FloatingActionButton btnañadir;
    Button btnGuardarCon, btnSiguienteRS, btnEnlistar;
    Spinner lista;
    List<String> listaCont;
    ArrayAdapter AA;

    FirebaseFirestore bdContactos;

    //List<Contacto>listaContactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);


        etNombreCon = findViewById(R.id.etNombreCont);
        etTel = findViewById(R.id.etTel);

        btnSiguienteRS = findViewById(R.id.btnSigRS);
        btnGuardarCon = findViewById(R.id.btnGuardarCont);

        btnañadir = findViewById(R.id.btnAddCont);

        lista = findViewById(R.id.listaCont);
        listaCont = new ArrayList<>();
        AA =  new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaCont);
        lista.setAdapter(AA);



        //bdContactos = FirebaseDatabase.getInstance().getReference();
        bdContactos = FirebaseFirestore.getInstance();
        añadirContacto();

        Bundle bundle = this.getIntent().getExtras();
        String correo = bundle.getString("correo");
        guardarContacto(correo);

    }

     public void añadirContacto(){
         btnañadir.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 Intent i = new Intent(Intent.ACTION_PICK);
                 i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                 startActivityForResult(i, 1);
             }

         });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode==RESULT_OK){
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null,null,null,null);

            if (cursor != null && cursor.moveToFirst()){

                int iNombre= cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int iTelefono = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String nombre = cursor.getString(iNombre);
                String numero = cursor.getString(iTelefono);

                numero = numero.
                        replace(" ","").
                        replace("(","").
                        replace(")", "").
                        replace("+57", "").
                        replace("-","");

                etNombreCon.setText(nombre);
                etTel.setText(numero);
            }
        }
    }

    public void guardarContacto(String correo){

     btnGuardarCon.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
            if (etNombreCon.getText().toString().isEmpty() || etTel.getText().toString().isEmpty()){

                msjErrorVacio();
            }

            else
            {
                Map<String, Object> map = new HashMap<>();

                map.put("Nombre", etNombreCon.getText().toString());
                map.put("Telefono", etTel.getText().toString());

                bdContactos
                        .collection("usuarios").document(correo)
                        .collection("contactos").document(etNombreCon.getText().toString()).set(map);

                Toast.makeText(contactos.this, "Contacto Añadido", Toast.LENGTH_LONG).show();



                enlistar();


            }


         }


     });



    }

    public void msjErrorVacio(){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Hay espacios vacios", Toast.LENGTH_SHORT);

        toast1.show();
    }

    public void enlistar(){

        boolean existe = false;
        listaCont.add(etNombreCon.getText().toString());
        AA.notifyDataSetChanged();


        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position).toString() ,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}