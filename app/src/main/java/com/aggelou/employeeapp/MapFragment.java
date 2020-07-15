package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import jsonModels.Location;


public class MapFragment extends Fragment implements OnMapReadyCallback{

    private PageViewModel mapPageViewModel;
    private ArrayList<Location> locations;


    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      // THEY WHERE HERE
    }

    @Override
    public void onStart() {
        super.onStart();
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        locations = new ArrayList<>();
        mapPageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        mapPageViewModel.getLocationRequest().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                locations.add(location);

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        locations.clear();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {


        mapPageViewModel.getLocationRequest().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {

                for(int i = 0; i < locations.size() ; i++){
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(locations.get(i).getLat(),locations.get(i).getLng()))
                            .title("Marker"+location.getLat()));;
                }

            }
        });
    }

}