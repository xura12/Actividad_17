package com.mxss.actividad_17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registrar_cliente extends AppCompatActivity {

    Button btGuardar,btRetorno;
    EditText etCodigo, etNombres,etApellidos, etFono;
    DAOCliente objDAO =new DAOCliente(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        etCodigo=(EditText)findViewById(R.id.etCodigo);
        etNombres=(EditText)findViewById(R.id.etNombres);
        etApellidos=(EditText)findViewById(R.id.etApellidos);
        etFono =(EditText)findViewById(R.id.etFono);

        btGuardar=(Button) findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabar(Integer.parseInt(etCodigo.getText().toString()),etNombres.getText().toString(),etApellidos.getText().toString(), etFono.getText().toString());
                startActivity(new Intent(registrar_cliente.this,MainActivity.class));
            }
        });

        btRetorno=(Button) findViewById(R.id.btRetorno);
        btRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registrar_cliente.this, MainActivity.class));

            }
        });
    }

    private void grabar(int codigo, String nombres, String apellidos, String telefono) {


        cliente objC=new cliente();
        objC.setCodigo(codigo);
        objC.setNombres(nombres);
        objC.setApellidos(apellidos);
        objC.setTelefono(telefono);
        objDAO.nuevoCliente(objC);
        Toast.makeText(this,"Cliente registrado..!!",Toast.LENGTH_SHORT).show();
    }


}