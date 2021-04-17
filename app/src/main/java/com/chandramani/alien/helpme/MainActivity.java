package com.chandramani.alien.helpme;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DatabaseManager dm;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private int backpresss=0;


 /*   public void onclickalarmimage(View v)
    {

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.emergency_alert);
        ImageView alarmm = (ImageView) v;
        if(mp.isPlaying())
        {
            mp.pause();
            mp.stop();
        }
        else if(!mp.isPlaying()){
       mp.start();
        }
     //   setContentView(R.layout.alarm_layout);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {                //onceate method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
      dm= new DatabaseManager(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerr_layout1);
     actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
       drawerLayout.addDrawerListener(actionBarDrawerToggle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_drawer);
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
       tabHost.setup();
     TabHost.TabSpec tabSpec;
     tabSpec= tabHost.newTabSpec("tab1");
     tabSpec.setIndicator("HOME");
     tabSpec.setContent(R.id.tab11);
   tabHost.addTab(tabSpec);//
        tabSpec=tabHost.newTabSpec("tab2");
        tabSpec.setIndicator("ISSUES");
        tabSpec.setContent(R.id.tab12);
        tabHost.addTab(tabSpec);
        tabSpec=tabHost.newTabSpec("tab3");
        tabSpec.setIndicator("CONTACT");
        tabSpec.setContent(R.id.tab13);
        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);


        final ImageView alarmbuttonn = (ImageView) findViewById(R.id.alarmvieww);
         final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.emergency_alert);
        alarmbuttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.stop();
                }
                else {
                    mp.start();
                }
            }
        });
    }  /// oncreate ends here.

    public void onclickIssues(View v){
        Button be = (Button) v;
        Intent iissuses = new Intent(MainActivity.this,LocationActivity.class);
        startActivity(iissuses);
    }

    public void onclickcallAmbulance(View v){
        try {
            String ambulance = "123456";
            Button call = (Button) v;
            Intent innt = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ambulance));
            startActivity(innt);
        }catch (SecurityException ex){
            Toast.makeText(getApplicationContext(),"call Failed",Toast.LENGTH_LONG).show();
        }
    }

    public void onclickcallpolice(View v){
        try {
            String police = "123456";
            Button call = (Button) v;
            Intent innt1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + police));
            startActivity(innt1);
        }catch (SecurityException ex){
            Toast.makeText(getApplicationContext(),"call Failed",Toast.LENGTH_LONG).show();
        }
    }

    public void onEmergengyQuicksend(View v){
        Button b= (Button) v;
        try {
            String womenhelpline = "123456"; //1091 women helpline all   india
            Button call = (Button) v;
            Intent innt2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + womenhelpline));
            startActivity(innt2);
        }catch (SecurityException ex){
            Toast.makeText(getApplicationContext(),"call Failed",Toast.LENGTH_LONG).show();
        }
     //Intent innttt = new Intent(MainActivity.this , LocationActivity.class);
   //  startActivity(innttt);
      //  LocationActivity loccc = new LocationActivity();
      //  loccc.onclicksendbtn(v);

      //  Toast.makeText(getApplicationContext(),"this is working", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {          //Todo : set activities for Navigation items
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerr_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

@Override
    public void onBackPressed() {
    backpresss+=1;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerr_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            backpresss=0;
        }
        else if(!drawer.isDrawerOpen(GravityCompat.START) && backpresss==1)
        {
            Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
        }
        else if(!drawer.isDrawerOpen(GravityCompat.START) && backpresss>1){
            this.finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {               //Todo : set activities for menu item
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Not yet available",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public void addcontactsbutton(View v){

       Button b1 = (Button) v;

       long countvalue = dm.getcountt();

       EditText et1 = (EditText) findViewById(R.id.phone1);
       String s1= et1.getText().toString();

       String testtt = valueOf(countvalue);
       if(s1 != null && countvalue<3){
       dm.insertt(s1);
       }
       else if( s1 != null && countvalue>3)
       {
           Toast.makeText(getApplicationContext(),"No more than three contacts be added",Toast.LENGTH_LONG);
       }


       EditText et2 = (EditText) findViewById(R.id.phone2);
       String s2= et1.getText().toString();
       if(s2 != null && countvalue<3)
       dm.insertt(s2);
       else
       {
           Toast.makeText(getApplicationContext(),"No more than three contacts be added",Toast.LENGTH_LONG);
       }

       EditText et3 = (EditText) findViewById(R.id.phone3);
       String s3= et1.getText().toString();

       if(s3 != null && countvalue<3)
       dm.insertt(s3);
       else
       {
           Toast.makeText(getApplicationContext(),"No more than three contacts be added",Toast.LENGTH_LONG);
       }

       if(s1 != null || s2 != null || s3 != null ) {

         Toast.makeText(getApplicationContext(),testtt,Toast.LENGTH_LONG).show();
       }
   }

   public void onclickviewcontact(View v){                          //todo : this is a test method , needs to be modified
       Button button = (Button) v;
       long countvalue = dm.getcountt();
       if(countvalue ==0)
       {
           Toast.makeText(getApplicationContext(),"No saved contacts",Toast.LENGTH_LONG).show();
       }

       else {
       Cursor cur = dm.getcontact();
       cur.moveToFirst();
       String data = cur.getString(cur.getColumnIndex("number"));
       Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
       }
   }

}
