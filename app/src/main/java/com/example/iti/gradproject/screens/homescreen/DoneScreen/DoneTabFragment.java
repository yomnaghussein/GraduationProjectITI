package com.example.iti.gradproject.screens.homescreen.DoneScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.App;
import com.example.iti.gradproject.models.Utilities;
import com.example.iti.gradproject.models.entities.Order;
import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.example.iti.gradproject.models.entities.UserProfile;
import com.example.iti.gradproject.screens.homescreen.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DoneTabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DoneTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoneTabFragment extends Fragment implements DoneContract.DoneFragment{
    @BindView(R.id.doneRecycleView)
    RecyclerView doneRecycleView;
    @BindView(R.id.swipeDone)
    SwipeRefreshLayout swipeRefreshLayout;
    UserProfile userProfile = Utilities.getUserFromPref(App.getApplication());
    String accesstoken = Utilities.getTokenFromPref(App.getApplication());
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DoneContract.DonePresenter donePresenter = new DonePresenterImpl(App.getApplication(),this);
    private OnFragmentInteractionListener mListener;

    public DoneTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoneTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoneTabFragment newInstance(String param1, String param2) {
        DoneTabFragment fragment = new DoneTabFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_done_tab, container, false);
        ButterKnife.bind(this, view);


        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHistoryOrders();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    @Override
    public void setHistoryOrdersAdapter(List<OrderResponseObject> orderResponseObjectList, List<String> orderStatusList) {
        doneRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        OrderListAdapter adapter=new OrderListAdapter(getContext(),orderResponseObjectList,orderStatusList);
        doneRecycleView.setAdapter(adapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            Log.e("asmaa","done");
            getHistoryOrders();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        getHistoryOrders();
    }

    public void getHistoryOrders(){
        if (!Utilities.isConnectedToInternet(getActivity())) {
            Utilities.showInternetErrorDialog(getActivity());
        }
        else if(userProfile.getId()!=null&&accesstoken!=null)
            donePresenter.getHistoryOrders(userProfile.getId().toString(),accesstoken);

    }
}
