package com.example.pro.analysebrand;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText searchText;
    private ListView listView;
    private List<String> resultArray = new ArrayList<String>();
    private List<BrandAnalysisResult> brandsArr = new ArrayList<BrandAnalysisResult>();
    private BrandAnalysisResult bar = new BrandAnalysisResult();
    private Brand brand = new Brand();
    final static int ADD_ITEM = 0;
    private List<Brand> brandList = new ArrayList<Brand>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);
        BrandAnalysisResult bar1 = new BrandAnalysisResult("","");
        brandsArr.add(bar1);
        BrandAdapter adp = new BrandAdapter(this,brandsArr);
        listView.setAdapter(adp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        ((BrandAnalysisResult)listView.getAdapter()
                                .getItem(position)).getBrandName(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), GraphActivity.class);
                for(int i=0;i<brandList.size();i++){
                    if(((BrandAnalysisResult)listView.getAdapter()
                            .getItem(position)).getBrandName().equals(brandList.get(i).getBar().getBrandName())){
                        intent.putExtra("brandName",((BrandAnalysisResult)listView.getAdapter()
                                .getItem(position)).getBrandName());
                        intent.putExtra("brandPosCount",brand.getPositiveCount());
                        intent.putExtra("brandNegCount",brand.getNegativeCount());
                    }
                }

                startActivityForResult(intent,ADD_ITEM);
            }
        });
    }
    public void btnClicked(View v){

        String urlString ="http://10.0.2.2:8080";

        bar.setBrandName(searchText.getText().toString());
        brand.setBar(bar);

        HttpGetRequest tsk = new HttpGetRequest();
        tsk.execute(urlString,searchText.getText().toString());


    }
    ProgressDialog prgDialog;
    class HttpGetRequest extends AsyncTask<String, Void, String> {

        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;


        @Override
        protected void onPreExecute() {
            prgDialog = new ProgressDialog(MainActivity.this);
            prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prgDialog.setMessage("Processing");
            prgDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            String name = params[1];
            String urlStr = params[0];

            StringBuffer sb = new StringBuffer("");

            urlStr = urlStr + "/" + name;

            String result =    "";

            try {
                URL url = new URL(urlStr);

                conn =
                        (HttpURLConnection)url.openConnection();


                BufferedReader reader
                        = new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                String nl = System.getProperty("line.separator");
                int index = 0;
                while((result =  reader.readLine()) != null){
                    sb.append(result + nl);
                    resultArray.add(index,result);
                    index++;
                }





            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                conn.disconnect();
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String obj) {
            prgDialog.dismiss();
            try {
                Toast.makeText(MainActivity.this, obj, Toast.LENGTH_SHORT).show();
                int totalTweetCount = 0;
                for(int i=0;i<resultArray.size();i++){
                    if(resultArray.get(i).contains("Positive count:")){
                        brand.setPositiveCount(((int) resultArray.get(i).charAt(16)));
                    }else if(resultArray.get(i).contains("Negative count:")){
                        brand.setNegativeCount(((int) resultArray.get(i).charAt(16)));
                    }
                }
                //bar.setTweetCount(String.valueOf(brand.getNegativeCount() + " " + brand.getPositiveCount()));
                brandsArr.add(bar);
                BrandAdapter adp = new BrandAdapter(MainActivity.this,brandsArr);
                listView.setAdapter(adp);
                brandList.add((brandsArr.size()),brand);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
