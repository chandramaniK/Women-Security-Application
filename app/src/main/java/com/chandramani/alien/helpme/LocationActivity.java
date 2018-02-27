package com.chandramani.alien.helpme;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.SEND_SMS;

public class LocationActivity extends AppCompatActivity implements LocationListener {

   public Location locc;
  public   LocationManager locationManager;
   public TextView tlong, tlat;
    boolean isGPS = false;
    boolean isNetwork = false;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;
    private static final long MIN_TIME_BW_UPDATES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tlat = (TextView) findViewById(R.id.testlatitude);
        tlong = (TextView) findViewById(R.id.testlongitude);
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPS && !isNetwork) {

            Toast.makeText(getApplicationContext(), "Please Turn on Gps", Toast.LENGTH_LONG).show();
        } else {
            getlocationn();

        }


    }
  //   EditText e1 = (EditText)findViewById(R.id.user_message);
    public void onclicksendbtn(View v) {
        EditText e1 = (EditText)findViewById(R.id.user_message);
        String msg=e1.getText().toString() + "\n";
        Button button = (Button) v;
    //    String stt = "test";
        String phonenumber = "9162322846";
        // double phonenumber = 1234;
      //   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +00000 ));
      //  intent.putExtra("sms_body", "stt"+"Latitude:"+tlat.getText().toString()+"\n"+"Longitude:"+tlong.getText().toString());
        //  startActivity(intent);

        if (ActivityCompat.checkSelfPermission(LocationActivity.this,SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+phonenumber ));
                intent.putExtra("sms_body", msg+"Latitude:"+tlat.getText().toString()+"\n"+"Longitude:"+tlong.getText().toString());
                startActivity(intent);
             //   SmsManager smsManager = SmsManager.getDefault();
             //   smsManager.sendTextMessage(phonenumber, null,stt+"Latitude:"+tlat.getText().toString()+"\n"+"Longitude:"+tlong.getText().toString(), null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
                        Toast.LENGTH_LONG).show();
            }

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{SEND_SMS}, 10);
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        tlong.setText(Double.toString(location.getLongitude()));
        tlat.setText(Double.toString(location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
        getlocationn();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }


    private void getlocationn() {
        try {

            if (isNetwork) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (locationManager != null) {
                    locc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (locc != null) {
                        tlong.setText(Double.toString(locc.getLongitude()));
                        tlat.setText(Double.toString(locc.getLatitude()));
                   //     Toast.makeText(getApplicationContext(), tlong.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }


            }
            else if (isGPS) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                if (locationManager != null) {
                    locc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (locc != null) {
                        tlong.setText(Double.toString(locc.getLongitude()));
                        tlat.setText(Double.toString(locc.getLatitude()));
         //               Toast.makeText(getApplicationContext(), tlong.getText().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            else {
                locc.setLatitude(0);
                locc.setLongitude(0);
                Toast.makeText(getApplicationContext(),"not working,",Toast.LENGTH_LONG).show();
                tlong.setText(Double.toString(locc.getLongitude()));
                tlat.setText(Double.toString(locc.getLatitude()));
            }

    }catch(SecurityException e){
            e.printStackTrace();

        }
    }
}