package com.dual.ideaction.alisar.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dual.ideaction.alisar.R;


public class BudgetFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BudgetFragment() {
    }

    public static BudgetFragment newInstance(String param1, String param2) {
        BudgetFragment fragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_budget, container, false);

        Button b = (Button) v.findViewById(R.id.setBudget);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText electricityBudgetTxt = (EditText) getView().findViewById(R.id.electricityBudget);
                EditText electricityCostTxt = (EditText) getView().findViewById(R.id.electricityCost);
                double electricityBudget = Double.parseDouble(electricityBudgetTxt.getText().toString());
                double electricityCost = Double.parseDouble(electricityCostTxt.getText().toString());

                double monthlyAllocation = ((int) (100 * electricityBudget / electricityCost)) / 100;
                double dailyAllocation = ((int) (100 * monthlyAllocation / 30)) / 100;

                TextView budgetMonthly = (TextView) getView().findViewById(R.id.budgetMonthly);
                TextView budgetDaily = (TextView) getView().findViewById(R.id.budgetDaily);

                budgetMonthly.setText(monthlyAllocation + " kWh");
                budgetDaily.setText(dailyAllocation + " kWh");

                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
        return v;
    }

    public void reAttach() {
        FragmentManager f = getFragmentManager();
        if (f != null) {
            f.beginTransaction()
                    .detach(this)
                    .attach(this)
                    .commit();
        }
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
}
