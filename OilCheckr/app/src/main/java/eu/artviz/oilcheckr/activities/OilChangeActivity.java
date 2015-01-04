package eu.artviz.oilcheckr.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.data.DataManager;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;

public class OilChangeActivity extends ListActivity implements View.OnClickListener {

    private Vehicle mVehicle;
    private List<Oil> mOils;
    private DataManager mDataManager;

    private Button mBtnAddNewOil;
    private ListView mLvOils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_change);

        init();
        initViews();
    }

    private void init() {
        mDataManager = DataManager.getInstance(this);
        mOils = new ArrayList<Oil>();

        Bundle bundle = getIntent().getExtras();

        mVehicle = (Vehicle) bundle.getParcelable(Constants.VEHICLE);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put(Constants.HISTORY_VEHICLE_FIELD, mVehicle);

        List<History> histories = mDataManager.histories().search(map);

        for (History history: histories) {
            mOils.add(history.getOil());
        }
    }

    private void initViews() {
        mBtnAddNewOil = (Button) findViewById(R.id.btnAddNewOil);
        mLvOils = getListView();

        ArrayAdapter<Oil> adapter = new ArrayAdapter<Oil>(this, android.R.layout.simple_list_item_2, android.R.id.text1, mOils) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Oil oil = mOils.get(position);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(oil.getName());
                text2.setText(oil.getRange() + " " + mVehicle.getMileageUnit());

                return view;
            }
        };

        mLvOils.setAdapter(adapter);

        mBtnAddNewOil.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oil_change, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnAddNewOil:
                goToAddNewOil();
                break;
        }
    }

    private void goToAddNewOil() {
        Intent newOildIntent = new Intent(this, NewOilActivity.class);
        newOildIntent.putExtra(Constants.VEHICLE, mVehicle);
        startActivity(newOildIntent);
    }
}
