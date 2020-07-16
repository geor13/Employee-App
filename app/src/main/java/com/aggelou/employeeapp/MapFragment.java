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
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import jsonModels.Location;
import jsonModels.RoutesResults;


public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private PageViewModel mapPageViewModel;
//    private ArrayList<Location> locations;

    private GoogleMap theMap;
    private ArrayList<MarkerOptions> listLocations = new ArrayList<>();
    private ArrayList<LatLng> polylines = new ArrayList<>();


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
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map_container);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        locations = new ArrayList<>();
        mapPageViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        mapPageViewModel.getLocationRequest().observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
//                locations.add(location);
                listLocations.add(new MarkerOptions().position(new LatLng(location.getLat(), location.getLng())));
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        theMap = googleMap;

                  for(int i = 0; i < listLocations.size() ; i++){
                    googleMap.addMarker(listLocations.get(i));
                }

        //MAKE THE REQUEST FOR DIRECTIONS -- MIGHT BREAK !!!!!!!!!
        if(listLocations != null){
            mapPageViewModel.setDirectionRequest(listLocations.get(0).getPosition(), listLocations.get(1).getPosition(), getActivity());
        }

        //RECEIVE THE RESULTED DIRECTIONS
        mapPageViewModel.getDirections().observe(getViewLifecycleOwner(), new Observer<RoutesResults>() {
            @Override
            public void onChanged(RoutesResults routesResults) {

                for(int i = 0; i < routesResults.getRoutes().get(0).getLegs().get(0).getSteps().size(); i++) {

                   polylines.add(new LatLng(routesResults.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getStart_location().getLat(), routesResults.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getStart_location().getLng()));

                }

                PolylineOptions polylineOptions = new PolylineOptions();

                for (int z = 0; z < polylines.size(); z ++){
                    googleMap.addPolyline(polylineOptions.add(polylines.get(z)));
                }

            }
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}