package id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir.adapter.MyPopular;
import id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir.model.ResultRespons;
import id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    ArrayList<Results> mlist = new ArrayList<>();
    MyPopular myPopular;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view_popular);
        rv.setHasFixedSize(true);
        myPopular = new MyPopular(this, mlist, getContext());
        rv.setAdapter(myPopular);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        downloadDataResource();

        return rootView;
    }

    private void downloadDataResource() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=60c3cf5f4f8c17119972535c1fb25ecd&language=en-US&page=1";
        GsonGetRequest<ResultRespons> myRequest = new GsonGetRequest<ResultRespons>
                (url, ResultRespons.class, null, new Response.Listener<ResultRespons>() {

                    @Override
                    public void onResponse(ResultRespons response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        //fillColor(response.results);
                        mlist.addAll(response.results);
                        myPopular.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(myRequest);
    }

    //private void fillColor(List<Results> results) {
    //    for (int i = 0; i < results.size(); i++)
    //        results.get(i).color = ColorUtil.getRandomColor();
    //}
}

