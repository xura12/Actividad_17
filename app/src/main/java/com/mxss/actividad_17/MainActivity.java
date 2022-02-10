package com.mxss.actividad_17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if (id == R.id.registrar)
        startActivity(new Intent(MainActivity.this, registrar_cliente.class));

        else if (id == R.id.listar) {
            startActivity(new Intent(MainActivity.this, listado_clientes.class));
        }
            return true;
        }
}
