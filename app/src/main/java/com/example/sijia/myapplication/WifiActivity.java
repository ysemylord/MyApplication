package com.example.sijia.myapplication;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class WifiActivity extends AppCompatActivity {


    @butterknife.Bind(R.id.nearWifi)
    ListView nearWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        butterknife.ButterKnife.bind(this);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();
        ArrayList<ScanResult> results = (ArrayList<ScanResult>) wifiManager.getScanResults();
//        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.)

     /*   WifiAdmin wifiAdmin = new WifiAdmin(this);*/
        //  wifiAdmin.openWifi();
        //  wifiAdmin.addNetwork(wifiAdmin.CreateWifiInfo("beidouapp", "ytxycdfgs", 3));
 /*       wifiAdmin.startScan();
        ArrayList<ScanResult>  results= (ArrayList<ScanResult>) wifiAdmin.getWifiList();
        for(ScanResult scanResult:results){
            Log.i("BSSID", scanResult.SSID);
        }*/

   /*     WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();
        ArrayList<ScanResult> results = (ArrayList<ScanResult>) wifiManager.getScanResults();
        for (ScanResult scanResult : results) {
            Log.i("scanRes", scanResult.SSID + " " + scanResult.capabilities);

        }*/

        ArrayList<WifiConfiguration> configurations = (ArrayList<WifiConfiguration>) wifiManager.getConfiguredNetworks();
        for (WifiConfiguration wifiConfiguration : configurations) {
            Log.i("configured", wifiConfiguration.SSID);
        }
    }
}
