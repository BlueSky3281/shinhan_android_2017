package com.shinhan.googlemapexam;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;
    int currentMarkerIdx = 0;

    class MyMarker{
        String name;
        LatLng latLng;
        MyMarker(String name , LatLng latLng){
            this.name = name;
            this.latLng = latLng;
        }
    }

    MyMarker[] markers = {
            new MyMarker("일산센터",new LatLng(37.662343,126.770699)),
            new MyMarker("대경빌딩",new LatLng(37.561103,126.974473)),
            new MyMarker("순화빌딩",new LatLng(37.561099, 126.972347)),
            new MyMarker("백년관",new LatLng(37.567775,126.984138)),
            new MyMarker("죽전센터",new LatLng(37.332019, 127.134579)),
            new MyMarker("역삼센터",new LatLng(37.498320, 127.043955)),
            new MyMarker("일본",new LatLng(35.684092, 139.765865)),
            new MyMarker("중국",new LatLng(40.079734, 116.603091)),
            new MyMarker("필리핀",new LatLng(14.511784, 121.016391)),
            new MyMarker("싱가포르",new LatLng(1.285656, 103.848526)),
            new MyMarker("베트남",new LatLng(10.784378, 106.696679)),
            new MyMarker("크메르",new LatLng(11.563342, 104.961684)),
            new MyMarker("미얀마",new LatLng(21.696933, 96.061811)),
            new MyMarker("카자흐스탄",new LatLng(51.025762, 71.762141)),
            new MyMarker("인도",new LatLng(19.089567, 72.866952)),
            new MyMarker("런던",new LatLng(51.542405, 0.369882)),
            new MyMarker("케나다",new LatLng(43.767864, -79.393109)),
            new MyMarker("미국",new LatLng(40.749174, -74.039525)),
            new MyMarker("뉴욕",new LatLng(40.749287, -73.975150)),
            new MyMarker("멕시코",new LatLng(19.435854, -99.071833)),
            new MyMarker("시드니",new LatLng(-33.939985, 151.175255))
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 지도
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void  onMapReady(GoogleMap googleMap){
                map = googleMap; //비동기 방식으로 구글지도 객체 얻기

                //폴리 라인
                PolylineOptions rectOptions = new PolylineOptions();
                rectOptions.color(Color.RED);

                //마커 출력
                for(int i=0 ; i < markers.length;i++){
                    MarkerOptions marker = new MarkerOptions();
                    marker.position(markers[i].latLng);
                    marker.title(markers[i].name);
                    map.addMarker(marker);

                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            showToastMessage(marker.getTitle()+"-");
                            map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15));
                            map.moveCamera(CameraUpdateFactory.zoomTo(18));
                            for(int j=0; j<markers.length;j++){
                                if(markers[j].name.equals(marker.getTitle())){
                                    currentMarkerIdx = j;
                                }
                            }
                            return false;
                        }
                    });
                    rectOptions.add(markers[i].latLng);
                }
                Polyline polyline = map.addPolyline(rectOptions);
            }
        });

        //권한 요청
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){ // 권한이 없다면
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this,"GPS 연동 권한 필요합니다.",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1 : {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"GPS권한 승인",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"GPS권한 거부",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void startLocationService(View view)
    {
        debug("startLocationService Call");
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //권한 요청
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location != null){
                TextView textView = (TextView)findViewById(R.id.location);
                textView.setText("내 위치 : "+location.getLatitude() + " // "+location.getLongitude());
                showToastMessage("Last Known Location 위도 : "+location.getLatitude()+" // "+location.getLongitude());
                setLocOnMap(location.getLatitude(),location.getLongitude());

                //37.6535093 , 126.7489213
            }
            GPSListener gpsListener = new GPSListener();
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0,gpsListener);
        }
    }

    public void onWorldMapClicked(View view){
        if(map != null){
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.moveCamera(CameraUpdateFactory.zoomTo(1));
        }
    }
    public void onTourButtonCliked(View view){
        if(currentMarkerIdx >= markers.length) currentMarkerIdx = 0;
        if(currentMarkerIdx <= 0) currentMarkerIdx = markers.length-1;

        if(view.getId() == R.id.button1){
            currentMarkerIdx++;
        }else{
            currentMarkerIdx--;
        }
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(markers[currentMarkerIdx].latLng,15));
    }
    /**
     * Location manager로 부터 GPS좌표가 변경이 되면 호출이 된다.
     */
    private class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if(location != null)
            {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                TextView textview = (TextView)findViewById(R.id.location);
                String string = "위도 : "+latitude+" // 경도 : "+longitude;
                textview.setText(string);
                showToastMessage(string);
                setLocOnMap(latitude,longitude);
            }else{
                debug("-------------위치가 없습니다.------------ ");
            }
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onProviderDisabled(String provider) {}
    }
    public void setLocOnMap(double latitude, double longitude){
        LatLng curPoint = new LatLng(latitude,longitude);
        if(map != null){ // 맵 객체가 null이 아니면 현재 위치로 맵 이동시킴
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        }
    }
    public void showToastMessage(String string){
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();
    }
    public void debug(String string){
        Log.d("GoogleManpExam",string);
    }
}
