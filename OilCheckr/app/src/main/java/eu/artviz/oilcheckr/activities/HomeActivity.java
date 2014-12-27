package eu.artviz.oilcheckr.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.adapters.VehicleAdapter;
import eu.artviz.oilcheckr.data.interfaces.IDao;
import eu.artviz.oilcheckr.data.mockers.VehicleDaoMocker;
import eu.artviz.oilcheckr.models.Vehicle;

public class HomeActivity extends ListActivity implements View.OnClickListener{

    private List<Vehicle> mVehicles;
    private IDao<Vehicle> mVehicleDao;

    private ListView mLvVerhicles;
    private Button mBtnAddVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        mVehicleDao = new VehicleDaoMocker();
        mVehicles = mVehicleDao.getAll();

        mLvVerhicles = getListView();
        VehicleAdapter vehicleAdapter = new VehicleAdapter(this, mVehicles);
        mLvVerhicles.setAdapter(vehicleAdapter);

        mBtnAddVehicle = (Button) findViewById(R.id.btnAddVehicle);

        mBtnAddVehicle.setOnClickListener(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        goToDetail(position);
    }

    private void goToDetail(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("VEHICLE", mVehicles.get(position));
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
            case R.id.btnAddVehicle:
                goToAddVehicle();
                break;
        }
    }

    private void goToAddVehicle() {
        Intent addVehicleIntent = new Intent(this, AddVehicleActivity.class);
        startActivity(addVehicleIntent);
    }
}
