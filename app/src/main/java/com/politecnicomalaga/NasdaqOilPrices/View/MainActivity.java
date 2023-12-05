package com.politecnicomalaga.NasdaqOilPrices.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.politecnicomalaga.NasdaqOilPrices.Control.JornadaAdapter;
import com.politecnicomalaga.NasdaqOilPrices.Control.MainController;
import com.politecnicomalaga.NasdaqOilPrices.Control.Respuesta;
import com.politecnicomalaga.NasdaqOilPrices.Model.Partido;
import com.politecnicomalaga.NasdaqOilPrices.R;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinkedList<Partido> mList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private JornadaAdapter mAdapter;

    private static MainActivity myActiveActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.rv_prices);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new JornadaAdapter(this, mList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        MainController.setURL();
        MainController.getSingleton().requestDataFromNasdaq();



        MainActivity.myActiveActivity = this;
        MainController.setActivity(this);;
    }

    public void accessData() {
        //Get data from servers throgh controller-model classes
        List<Partido> nuevaLista = MainController.getSingleton().getDataFromNasdaq();

        //Put data in adapter
        mList.clear();
        for (Partido item:nuevaLista) {
            mList.add(item);
        }
        mAdapter.notifyDataSetChanged();
        TextView tv = (TextView) findViewById(R.id.tv_oilDesc);

    }

    public void errorData(String error) {
        TextView tv = (TextView) findViewById(R.id.tv_oilDesc);
        tv.setText(error);

    }


}
