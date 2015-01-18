package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private List<History> mAllHistories;
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        initViews();
    }

    private void init(){
        mDataManager = DataManager.getInstance(this);
        mVehicle = (Vehicle)getIntent().getExtras().getParcelable(Constants.VEHICLE);
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

        if (mVehicle != null) {
            mTvVehicleName.setText(mVehicle.getName());
            mTvMileage.setText(String.valueOf(mVehicle.getCurrentMileage()));

            if (mVehicle.getOil() != null) {
                mTvCurrentOil.setText(mVehicle.getOil().getName());
                mAllHistories = getHistories();
                mHistory = mAllHistories.get(0);
            }

            mTvAverageMileage.setText(String.valueOf(mVehicle.getAverageDayMileage()));

            if (mHistory != null) {
                int range = mHistory.getMileageChanged() + mVehicle.getOil().getRange();
                mTvOilChangeTarget.setText(String.valueOf(mHistory.getMileageChanged() + mVehicle.getOil().getRange()));
                calculateAverageDayConsumption();
            }
        }
    }

    private List<History> getHistories(){
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(Constants.HISTORY_VEHICLE_FIELD, mVehicle);
        map.put(Constants.HISTORY_OIL_FIELD, mVehicle.getOil());

        List<History> histories = mDataManager.histories().search(map);

        if (histories.size() > 0){
            return histories;
        }

        return null;
    }

    private void calculateAverageDayConsumption(){
        List<Integer> oilConsumptions = new ArrayList<Integer>();
        int sum = 0;

        if (mAllHistories != null && mAllHistories.size() > 0){
            if (mAllHistories.size() > 1){
                for (int i = 0; i < mAllHistories.size() - 1; i++){
                    History firstHistory = mAllHistories.get(i);
                    History secHistory = mAllHistories.get(i + 1);

                    String firstHistDateStr = getDateAsString(firstHistory.getDateChanged());
                    String secHistDateStr = getDateAsString(secHistory.getDateChanged());

                    int daysDifference = getDaysDifference(firstHistDateStr, secHistDateStr);

                    int currPeriodOilConsumption = mVehicle.getOilCapacity() / daysDifference;
                    oilConsumptions.add(currPeriodOilConsumption);
                }

                for (int i = 0; i < oilConsumptions.size(); i++){
                    sum += oilConsumptions.get(i);
                }

                //Set the average consumption
                int averageConsumption = sum / oilConsumptions.size();
                mVehicle.setAverageDayMileage(averageConsumption);
            }
            else{
               mVehicle.setAverageDayMileage(0);
            }
        }
    }

    private String getDateAsString(Date date){
        Format dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
        String dateStr = dateFormatter.format(date);

        return dateStr;
    }

    private int getDaysDifference(String fromDate, String toDate){
        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        //split year, month and days from the date using StringBuffer.
        StringBuffer sBuffer = new StringBuffer(fromDate);
        String yearFrom = sBuffer.substring(6,10);
        String monFrom = sBuffer.substring(0,2);
        String ddFrom = sBuffer.substring(3,5);
        int intYearFrom = Integer.parseInt(yearFrom);
        int intMonFrom = Integer.parseInt(monFrom);
        int intDdFrom = Integer.parseInt(ddFrom);

        // set the fromDate in java.util.Calendar
        cal1.set(intYearFrom, intMonFrom, intDdFrom);

        StringBuffer sBuffer1 = new StringBuffer(toDate);
        String yearTo = sBuffer1.substring(6,10);
        String monTo = sBuffer1.substring(0,2);
        String ddTo = sBuffer1.substring(3,5);
        int intYearTo = Integer.parseInt(yearTo);
        int intMonTo = Integer.parseInt(monTo);
        int intDdTo = Integer.parseInt(ddTo);

        // set the toDate in java.util.Calendar
        cal2.set(intYearTo, intMonTo, intDdTo);

        int days = daysBetween(cal1.getTime(),cal2.getTime());

        return days;
    }

    private int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == mBtnToUpdate.getId()){
            intent = new Intent(this,UpdateMileageActivity.class);
            intent.putExtra(Constants.VEHICLE,mVehicle);
            intent.putExtra(Constants.FROM_DETAIL, true);
            startActivity(intent);
        }
        else if(v.getId() == mBtnToOilChange.getId()){
            intent = new Intent(this,OilChangeActivity.class);
            intent.putExtra(Constants.VEHICLE,mVehicle);
            startActivity(intent);
        }

    }
}
