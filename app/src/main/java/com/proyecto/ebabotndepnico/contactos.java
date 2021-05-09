package com.proyecto.ebabotndepnico;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Boton_de_panico.btn_edit;

public class contactos extends AppCompatActivity {


    EditText etNombreCon, etTel;
    FloatingActionButton btnañadir;
    Button btnGuardarCon, btnSiguienteRS;
    Spinner lista;
    List<String> listaCont;
    ArrayAdapter AA;

    FirebaseFirestore bd;

    List<Contacto>listaContactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        this.setTitle("Configuración Aplicativo 2/4");

        etNombreCon = findViewById(R.id.etNombreCont);
        etTel = findViewById(R.id.etTel);

        btnGuardarCon = findViewById(R.id.btnGuardarCont);

        btnañadir = findViewById(R.id.btnAddCont);

        lista = findViewById(R.id.listaCont);
        listaCont = new ArrayList<>();
        AA =  new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listaCont);
        lista.setAdapter(AA);



        //bdContactos = FirebaseDatabase.getInstance().getReference();
        bd = FirebaseFirestore.getInstance();
        añadirContacto();

        Bundle bundle = this.getIntent().getExtras();
        String correo = bundle.getString("correo");
        String ID = bundle.getString("ID");
        String IDbtn = bundle.getString("IDbtn");
        guardarContacto(ID,IDbtn);

        etNombreCon.setText(ID);
        etTel.setText(IDbtn);

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

    public void guardarContacto(String ID, String IDbtn){

     btnGuardarCon.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
            if (etNombreCon.getText().toString().isEmpty() || etTel.getText().toString().isEmpty()){

                msjErrorVacio();
            }

            else
            {
                Map<String, String> map = new HashMap<>();

                String nombre = etNombreCon.getText().toString();

                map.put("Nombre", nombre );
                map.put("Telefono", etTel.getText().toString());

                bd.collection("usuarios").document(ID)
                        .collection("botones").document(IDbtn)
                        .collection("contactos").document().set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Añadido", "si se añadio");
                    }
                });


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

        listaCont.add(etNombreCon.getText().toString() + " " + etTel.getText().toString());
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

    private void btn_edit(String correo, String ID){

        Intent i = new Intent(this, btn_edit.class);
        i.putExtra("correo" , correo);
        i.putExtra("ID" , ID);
        startActivity(i);

    }
}


