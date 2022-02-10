package com.mxss.actividad_17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class modifica_cliente extends AppCompatActivity {

    Button btActualizar,btEliminar,btRetorna;
    EditText etCodigo,etNombres,etApellidos, etFon;
    DAOCliente objDAO;
    int cod;
    String nom,ape,tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_cliente);

        btRetorna = (Button) findViewById(R.id.btRetorna);
        btRetorna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(modifica_cliente.this, MainActivity.class));

            }
        });
        Bundle b = getIntent().getExtras();
        if (b != null) {
            cod = b.getInt("cod");
            nom = b.getString("nom");
            ape = b.getString("ape");
            tel = b.getString("tel");

        }
        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etFon = (EditText) findViewById(R.id.etFono);

        etCodigo.setText(""+cod);
        etNombres.setText(nom);
        etApellidos.setText(ape);
        etFon.setText(tel);

        btActualizar = (Button) findViewById(R.id.btActualizar);
        btActualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                modificar(cod, etNombres.getText().toString(), etApellidos.getText().toString(), etFon.getText().toString());
            }
        });

        btEliminar=(Button) findViewById(R.id.btEliminar);
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar(cod);
            }
        });
    }

            private void modificar(int codigo, String nombres, String apellidos, String telefono) {

                cliente objC=new cliente();

                objC.setCodigo(codigo);
                objC.setNombres(nombres);
                objC.setApellidos(apellidos);
                objC.setTelefono(telefono);


                new DAOCliente(this).actualizaCliente(objC);
                Toast.makeText(this,"Actualizado correctamente",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(modifica_cliente.this,listado_clientes.class));


            }
            public  void eliminar(int codigo){
        cliente objC=new cliente();
        objC.setCodigo(codigo);

        new DAOCliente(this).eliminarCliente(objC);
        Toast.makeText(this,"Eliminado correctamente",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(modifica_cliente.this,listado_clientes.class));

    }
}