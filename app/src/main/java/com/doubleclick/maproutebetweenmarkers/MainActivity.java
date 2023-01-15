package com.doubleclick.maproutebetweenmarkers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private LatLng fkip;
    private LatLng monas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fkip = new LatLng(-6.3037978, 106.8693713);
        monas = new LatLng(-6.1890511, 106.8251573);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps_view);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        MarkerOptions markerFkip = new MarkerOptions()
                .position(fkip)
                .title("FKIP");
        MarkerOptions markerMonas = new MarkerOptions()
                .position(monas)
                .title("Monas");

        map.addMarker(markerFkip);
        map.addMarker(markerMonas);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(monas, 11.6f));

        String fromFKIP = String.valueOf(fkip.latitude) + "," + String.valueOf(fkip.longitude);
        String toMonas = String.valueOf(monas.latitude) + "," + String.valueOf(monas.longitude);

        ApiServices apiServices = RetrofitClient.apiServices(this);
        apiServices.getDirection(fromFKIP, toMonas, "AIzaSyCsNE4JNKvA6uR-TBaNXuw6NKkDv-JiAYQ")
                .enqueue(new Callback<DirectionResponses>() {
                    @Override
                    public void onResponse(@NonNull Call<DirectionResponses> call, @NonNull Response<DirectionResponses> response) {
//                        drawPolyline(response);
                        assert response.body() != null;
                        Log.e("MainActivity", response.body().toString());
                    }

                    @Override
                    public void onFailure(@NonNull Call<DirectionResponses> call, @NonNull Throwable t) {
                        Log.e("MainActivity", t.getLocalizedMessage());
                    }
                });
    }

    private void drawPolyline(@NonNull Response<DirectionResponses> response) {
        if (response.body() != null) {
            String shape = response.body().getRoutes().get(0).getOverviewPolyline().getPoints();
            PolylineOptions polyline = new PolylineOptions()
                    .addAll(PolyUtil.decode(shape))
                    .width(8f)
                    .color(Color.RED);
            map.addPolyline(polyline);
        }
    }

    private interface ApiServices {
        @GET("maps/api/directions/json")
        Call<DirectionResponses> getDirection(@Query("origin") String origin,
                                              @Query("destination") String destination,
                                              @Query("key") String apiKey);
    }

    private static class RetrofitClient {
        static ApiServices apiServices(Context context) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.getResources().getString(R.string.base_url))
                    .build();

            return retrofit.create(ApiServices.class);
        }
    }
}