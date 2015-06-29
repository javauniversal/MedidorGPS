package com.appgestor.medidorgps.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;


//Francisco Javier Marin Ochoa.
//Aplicaciones Móviles
//Módulo  Tecnología Android I., Actividad: Medidor de distancias con GPS
public class MyService extends Service implements LocationListener {

    private Context ctx;
    double latitud;
    double longitud;
    Location location;
    boolean gpsActivo;
    TextView posicion, bogota, bucaramanga, cali;
    LocationManager locationManager;
    float distance, distanceD, distanceC;

    public MyService(){
        super();
        this.ctx = getApplicationContext();
    }

    public MyService(Context c) {
        super();
        this.ctx = c;
        getLocation();
    }

    public void setView(View v, View vb, View vbu, View vc){
        posicion = (TextView)v;
        posicion.setText("Mi posición actual: "+latitud+", "+longitud);

        bogota = (TextView)vb;
        bogota.setText("Distancia: "+distance+" Km");

        bucaramanga = (TextView)vbu;
        bucaramanga.setText("Distancia: "+distanceC+" Km");

        cali = (TextView)vc;
        cali.setText("Distancia: "+distanceD+" Km");
    }

    public void getLocation(){
        try {
            locationManager = (LocationManager)this.ctx.getSystemService(LOCATION_SERVICE);
            gpsActivo = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch (Exception e){}
        if(gpsActivo){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,1000*60,10,this);
            location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

            latitud = location.getLatitude();
            longitud = location.getLongitude();

            //Bogota.
            Location locationB = new Location("point B");
            locationB.setLatitude(4.6097100);
            locationB.setLongitude(-74.0817500);

            //Bucaramanga.
            Location locationC = new Location("point C");
            locationC.setLatitude(7.165023);
            locationC.setLongitude(-73.1082449);

            //Cali.
            Location locationD = new Location("point D");
            locationD.setLatitude(3.2637938);
            locationD.setLongitude(-76.4824053);

            //Bogota
            distance = Math.round(location.distanceTo(locationB)/1000);
            //Bucaramanga
            distanceC = Math.round(location.distanceTo(locationC)/1000);
            //Cali
            distanceD = Math.round(location.distanceTo(locationD)/1000);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public float distance (double lat_a, double lng_a, double lat_b, double lng_b )
    {
        double earthRadius = 3958.75;
        double latDiff = Math.toRadians(lat_b-lat_a);
        double lngDiff = Math.toRadians(lng_b-lng_a);
        double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
                Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
                        Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        int meterConversion = 1609;

        return new Float(distance * meterConversion).floatValue();
    }

}
