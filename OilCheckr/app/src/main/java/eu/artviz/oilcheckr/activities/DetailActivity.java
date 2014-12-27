package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.data.dao.HistoryDao;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Vehicle;

public class DetailActivity extends Activity implements View.OnClickListener{

    private TextView mTvVehicleName, mTvMileage, mTvOilChangeTarget, mTvCurrentOil, mTvAverageMileage;
    private Button mBtnToUpdate, mBtnToOilChange;
    private Vehicle mVehicle;
    private History mHistory;
    private HistoryDao mHistoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    private void initViews(){
        mTvVehicleName = (TextView)findViewById(R.id.tvVehicleName);
        mTvMileage = (TextView)findViewById(R.id.tvMileage);
        mTvOilChangeTarget = (TextView)findViewById(R.id.tvOilChangeTarget);
        mTvCurrentOil = (TextView)findViewById(R.id.tvCurrentOil);
        mTvAverageMileage = (TextView)findViewById(R.id.tvAverageMileage);

        mBtnToUpdate = (Button)findViewById(R.id.btnToUpdate);
        mBtnToOilChange = (Button)findViewById(R.id.btnToOilChange);

        mBtnToUpdate.setOnClickListener(this);
        mBtnToOilChange.setOnClickListener(this);
    }

    private void init(){
        mHistoryDao = new HistoryDao(this);
        mVehicle = (Vehicle)getIntent().getSerializableExtra(Constants.VEHICLE);
        mTvVehicleName.setText(mVehicle.getName());
        mTvMileage.setText(String.valueOf(mVehicle.getCurrentMileage()));

        mTvCurrentOil.setText(mVehicle.getOil().getName());
        mTvAverageMileage.setText(String.valueOf(mVehicle.getAverageDayMileage()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBtnToUpdate.getId()){
            Intent intent = new Intent(this,UpdateMileageActivity.class);
        }
        else if(v.getId() == mBtnToOilChange.getId()){

        }
    }
}
