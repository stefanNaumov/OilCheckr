package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.data.DataManager;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Vehicle;

public class DetailActivity extends Activity implements View.OnClickListener{

    private TextView mTvVehicleName, mTvMileage, mTvOilChangeTarget, mTvCurrentOil, mTvAverageMileage;
    private Button mBtnToUpdate, mBtnToOilChange;
    private Vehicle mVehicle;
    private History mHistory;
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        init();
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
        mVehicle = (Vehicle)getIntent().getExtras().getParcelable(Constants.VEHICLE);
        if (mVehicle != null) {
            mTvVehicleName.setText(mVehicle.getName());
            mTvMileage.setText(String.valueOf(mVehicle.getCurrentMileage()));

            mTvCurrentOil.setText(mVehicle.getOil().getName());
            mTvAverageMileage.setText(String.valueOf(mVehicle.getAverageDayMileage()));

            mDataManager = DataManager.getInstance();

            mHistory = getHistory();

            if (mHistory != null) {
                int range = mHistory.getMileageChanged() + mVehicle.getOil().getRange();
                mTvOilChangeTarget.setText(String.valueOf(mHistory.getMileageChanged() + mVehicle.getOil().getRange()));
            }
        }
    }

    private History getHistory(){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(Constants.HISTORY_VEHICLE_FIELD, mVehicle);
        map.put(Constants.HISTORY_OIL_FIELD, mVehicle.getOil());

        List<History> histories = mDataManager.histories().search(map);

        if (histories.size() > 0){
            return histories.get(0);
        }

        return null;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == mBtnToUpdate.getId()){
            intent = new Intent(this,UpdateMileageActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == mBtnToOilChange.getId()){
            intent = new Intent(this,OilChangeActivity.class);
            startActivity(intent);
        }

    }
}
