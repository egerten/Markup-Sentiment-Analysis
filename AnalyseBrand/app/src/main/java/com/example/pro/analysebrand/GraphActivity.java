package com.example.pro.analysebrand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        Intent intent = getIntent();
        String name = "Brand ";
        int posCount = 0, negCount = 0;
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            Intent i = getIntent();
            if(extras != null){
                name = (String)savedInstanceState.getSerializable("brandName");
                posCount =  i.getIntExtra("brandPosCount",0);
                negCount =  i.getIntExtra("brandNegCount",1);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                        new DataPoint(0, posCount),
                        new DataPoint(1, negCount),
                });
                graph.addSeries(series);

                TextView txtTitle = (TextView) findViewById(R.id.title);
                txtTitle.setText(name + " Positive " + posCount + " Negative "+negCount);
            }
        }

    }
}
