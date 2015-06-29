package com.appgestor.medidorgps.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.appgestor.medidorgps.R;
import com.appgestor.medidorgps.Services.MyService;

//Francisco Javier Marin Ochoa.
//Aplicaciones Móviles
//Módulo  Tecnología Android I., Actividad: Medidor de distancias con GPS
public class ActMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        MyService services = new MyService(this);
        services.setView(findViewById(R.id.coordenada),
                         findViewById(R.id.txtBogota),
                         findViewById(R.id.txtBucaramanga),
                         findViewById(R.id.txtCali));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
