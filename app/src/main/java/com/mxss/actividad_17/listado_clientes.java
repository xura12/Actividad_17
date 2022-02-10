package com.mxss.actividad_17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class listado_clientes extends AppCompatActivity {

    ListView lvClientes;
    ArrayList<cliente> aClientes;
            ArrayAdapter<cliente>adaptador;

            DAOCliente objDAO;
            Button btRetornar;
            @Override
            protected  void onPostResume(){
                super.onPostResume();
                cargaListado();
            }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_clientes);

        btRetornar=(Button) findViewById(R.id.btRetornar);
        btRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(listado_clientes.this, MainActivity.class));
            }
        });
        lvClientes=(ListView) findViewById(R.id.lvClientes);
        cargaListado();

        lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(listado_clientes.this, objDAO.listaClientes().get(position).toString(), Toast.LENGTH_SHORT).show();

                int codigo = Integer.parseInt(objDAO.listaClientes().get(position).toString().split(" ")[0]);
                String nombres = objDAO.listaClientes().get(position).toString().split(" ")[1];
                String apellidos = objDAO.listaClientes().get(position).toString().split(" ")[2];
                String telefono = objDAO.listaClientes().get(position).toString().split(" ")[3];

                Intent intent = new Intent(listado_clientes.this, modifica_cliente.class);
                intent.putExtra("cod", codigo);
                intent.putExtra("nom", nombres);
                intent.putExtra("ape", apellidos);
                intent.putExtra("tel", telefono);
                startActivity(intent);

            }
        });

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        }
        @Override
    public boolean onOptionsItemSelected(MenuItem item){
                if(item.getItemId() == android.R.id.home) {
                    finish();
                }
                return super.onOptionsItemSelected(item);

    }

    private void cargaListado() {
                objDAO=new DAOCliente(this);
                aClientes=objDAO.listaClientes();
                objDAO.close();

                adaptador=new ArrayAdapter<cliente>(this, android.R.layout.simple_list_item_1,aClientes);
                lvClientes.setAdapter(adaptador);
    }

}