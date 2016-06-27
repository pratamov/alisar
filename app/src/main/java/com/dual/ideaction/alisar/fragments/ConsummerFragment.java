package com.dual.ideaction.alisar.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.dual.ideaction.alisar.R;
import com.dual.ideaction.alisar.exception.ConnectionException;
import com.dual.ideaction.alisar.service.ServiceSync;
import com.dual.ideaction.alisar.service.ServiceSyncImpl;

public class ConsummerFragment extends Fragment {

    private static final String ARG_PARAM1 = "switch1";
    private static final String ARG_PARAM2 = "switch2";
    private static final String ARG_PARAM3 = "switch3";

    private boolean switch1;
    private boolean switch2;
    private boolean switch3;

    String consumerId1, consumerId2, consumerId3;
    String consumerName2, consumerName3, consumerName1;
    boolean consumerSwitch1, consumerSwitch2, consumerSwitch3;

    private OnFragmentInteractionListener mListener;

    public ConsummerFragment() {
    }

    public static ConsummerFragment newInstance(String consumerId1, String consumerId2, String consumerId3,
                                                String consumerName1, String consumerName2, String consumerName3,
                                                boolean consumerSwitch1, boolean consumerSwitch2, boolean consumerSwitch3) {
        ConsummerFragment fragment = new ConsummerFragment();
        Bundle args = new Bundle();
        args.putString("consumerId1", consumerId1);
        args.putString("consumerId2", consumerId2);
        args.putString("consumerId3", consumerId3);
        args.putString("consumerName1", consumerName1);
        args.putString("consumerName2", consumerName2);
        args.putString("consumerName3", consumerName3);
        args.putBoolean("consumerSwitch1", consumerSwitch1);
        args.putBoolean("consumerSwitch2", consumerSwitch2);
        args.putBoolean("consumerSwitch3", consumerSwitch3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            consumerId1 = getArguments().getString("consumerId1");
            consumerId2 = getArguments().getString("consumerId2");
            consumerId3 = getArguments().getString("consumerId3");
            consumerName1 = getArguments().getString("consumerName1");
            consumerName2 = getArguments().getString("consumerName2");
            consumerName3 = getArguments().getString("consumerName3");
            consumerSwitch1 = getArguments().getBoolean("consumerSwitch1");
            consumerSwitch2 = getArguments().getBoolean("consumerSwitch2");
            consumerSwitch3 = getArguments().getBoolean("consumerSwitch3");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_consummer, container, false);
        final Switch sw1 = (Switch) v.findViewById(R.id.switch1);
        final Switch sw2 = (Switch) v.findViewById(R.id.switch2);
        final Switch sw3 = (Switch) v.findViewById(R.id.switch3);
        sw1.setChecked(consumerSwitch1);
        sw2.setChecked(consumerSwitch2);
        sw3.setChecked(consumerSwitch3);
        sw1.setText(consumerName1);
        sw2.setText(consumerName2);
        sw3.setText(consumerName3);
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    ServiceSyncImpl service = ServiceSyncImpl.getInstance();
                    service.setConsumerSwitch(isChecked, consumerId1);
                } catch (ConnectionException e) {
                    sw1.setChecked(consumerSwitch1);
                }

            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    ServiceSyncImpl service = ServiceSyncImpl.getInstance();
                    service.setConsumerSwitch(isChecked, consumerId2);
                } catch (ConnectionException e) {
                    sw2.setChecked(consumerSwitch2);
                }

            }
        });
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    ServiceSyncImpl service = ServiceSyncImpl.getInstance();
                    service.setConsumerSwitch(isChecked, consumerId3);
                } catch (ConnectionException e) {
                    sw3.setChecked(consumerSwitch3);
                }

            }
        });
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public interface OnSwitchChangeListener {
        void onSwitch1Change(boolean state);

        void onSwitch2Change(boolean state);

        void onSwitch3Change(boolean state);
    }
}
