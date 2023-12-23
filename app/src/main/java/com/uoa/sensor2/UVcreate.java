package com.uoa.sensor2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.slider.RangeSlider;
import com.uoa.sensor2.ViewModel1;

import org.jetbrains.annotations.NotNull;
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link UVcreate#newInstance} factory method to
// * create an instance of this fragment.
// */

////The alternative to a ViewModel is a plain class that holds the data you display in your UI. This can become a problem when navigating between activities or Navigation destinations. Doing so destroys that data if you don't store it using the saving instance state mechanism. ViewModel provides a convenient API for data persistence that resolves this issue.
////
////        The key benefits of the ViewModel class are essentially two:
////
////        It allows you to persist UI state.
////        It provides access to business logic.
//
//
////ViewModel allows persistence through both the state that a ViewModel holds, and the operations that a ViewModel triggers.
//// This caching means that you don’t have to fetch data again through
//// common configuration changes, such as a screen rotation.
//
////When the fragment or activity to which the ViewModel is scoped is destroyed, asynchronous work continues in the ViewModel
//// that is scoped to it. This is the key to persistence.
public class UVcreate extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ViewModel1 myViewModel;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public interface UVSensorListener {
        void onCreateUVSensor();
    }

    private UVSensorListener listener;


    public UVcreate() {
        // Required empty public constructor
    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment UVcreate.
//     */
    // TODO: Rename and change types and number of parameters
//    public static UVcreate newInstance(String param1, String param2) {
//        UVcreate fragment = new UVcreate();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.uvcreate, container, false);

        Button button = view.findViewById(R.id.UVbutton);
        button.setOnClickListener(this);
        myViewModel = new ViewModelProvider(requireActivity()).get(ViewModel1.class);
        return view;
    }
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            try {
                this.listener = (UVSensorListener) activity;
            } catch (final ClassCastException e) {
                throw new ClassCastException(activity.toString() + " createUV");
            }
        }
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.UVbutton) {
            rangeExtrema();
            listener.onCreateUVSensor();
        }
    }
    private void rangeExtrema() {
        RangeSlider slider = requireView().findViewById(R.id.UVRangeSensorSlider1);
        myViewModel.setUVMinimum(slider.getValues().get(0));
        myViewModel.setUVMaximum(slider.getValues().get(1));
    }
}