package com.example.adaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView m_oListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] strDate = {"2017-01-03","1965-02-23","2016-04-13","2010-01-01","2017-06-20",
        "2017-07-08","1980-04-14","2016-09-26","2014-10-11","2010-12-24"};

        int nDatCnt = 0;
        ArrayList<ItemData> oData = new ArrayList<>();
        for(int i = 0; i < 100; ++i)
        {
            ItemData oItem = new ItemData();
            oItem.strTitle = "데이터" + (i+1);
            oItem.strDate = strDate[nDatCnt++];
            oData.add(oItem);
            if(nDatCnt>=strDate.length)nDatCnt = 0;
        }

        m_oListView = (ListView)findViewById(R.id.listView);
        ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }
}
