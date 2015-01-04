package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.data.DataManager;
import eu.artviz.oilcheckr.models.History;
import eu.artviz.oilcheckr.models.Vehicle;

public class UpdateMileageActivity extends Activity implements View.OnClickListener{

    private DataManager mDataManager;
    private Vehicle mVehicle;
    private History mHistory;
    private Bundle mBundle;

    private EditText mEtMileage;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mileage);

        this.init();
        this.initViews();
    }

    private void init(){
        mDataManager = DataManager.getInstance(this);
        mBundle = getIntent().getExtras();

        mVehicle = (Vehicle) mBundle.getParcelable(Constants.VEHICLE);
        mHistory = (History) mBundle.getParcelable(Constants.HISTORY);
    }

    private void initViews(){
        mEtMileage = (EditText)findViewById(R.id.etMileage);
        mBtnSave = (Button)findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(this);

        if (mVehicle.getCurrentMileage() != 0) {
            mEtMileage.setText(String.valueOf(mVehicle.getCurrentMileage()));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBtnSave.getId()){
            String mileageStr = mEtMileage.getText().toString().trim();
            String message;
            int mileage = 0;

            if (mileageStr != null && !mileageStr.isEmpty()){

                try {
                    mileage = Integer.valueOf(mileageStr);

                    mVehicle.setCurrentMileage(mileage);

                    mDataManager.vehicles().update(mVehicle);

                    Toast.makeText(this, R.string.mileage_updated,Toast.LENGTH_LONG).show();

                    if (mHistory != null) {
                        mHistory.setMileageChanged(mVehicle.getCurrentMileage());
                        mDataManager.histories().create(mHistory);
                    }

                    navigateBack();
                }
                catch (NumberFormatException e) {
                    Toast.makeText(this, R.string.invalid_mileage_update, Toast.LENGTH_SHORT).show();
                }
            }
            else{
                message = getString(R.string.enter_mileage);
                Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            }
        }
    }

    private void navigateBack() {
        boolean isFromHome = mBundle.getBoolean(Constants.FROM_HOME, false);
        boolean isFromDetail = mBundle.getBoolean(Constants.FROM_DETAIL, false);

        if (isFromHome) {
            goToHome();
        }
        else if (isFromDetail) {
            goToDetail();
        }
    }

    private void goToDetail() {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(Constants.VEHICLE, mVehicle);
        startActivity(detailIntent);
    }

    private void goToHome() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }
}
