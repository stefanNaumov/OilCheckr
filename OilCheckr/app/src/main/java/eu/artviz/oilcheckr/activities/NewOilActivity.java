package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.data.DataManager;
import eu.artviz.oilcheckr.models.Oil;
import eu.artviz.oilcheckr.models.Vehicle;

public class NewOilActivity extends Activity implements View.OnClickListener{

    private Vehicle mVehicle;
    private Oil mNewOil;
    private DataManager mDataManager;
    private EditText mEtOilName, mEtMileageRange;
    private Button mBtnAddNewOil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_oil);

        initViews();
        init();
    }

    private void init(){
        mVehicle = (Vehicle)getIntent().getExtras().getParcelable(Constants.VEHICLE);
        mDataManager = DataManager.getInstance(this);
    }

    private void initViews(){
        mEtOilName = (EditText)findViewById(R.id.etOilName);
        mEtMileageRange = (EditText)findViewById(R.id.etMileageRange);
        mBtnAddNewOil = (Button)findViewById(R.id.btnAddNewOil);
        mBtnAddNewOil.setOnClickListener(this);
    }

    private boolean setOil(){
        String oilName = mEtOilName.getText().toString().trim();
        String mileageRangeStr = mEtMileageRange.getText().toString().trim();

        if (oilName == null || oilName.isEmpty()){
            String message = getResources().getString(R.string.enter_oil_name);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            return false;
        }

        if (mileageRangeStr == null || mileageRangeStr.isEmpty()){
            String message = getResources().getString(R.string.enter_mileage_range);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            return false;
        }

        int mileageRange = Integer.valueOf(mileageRangeStr);

        mNewOil = new Oil(oilName, mVehicle, mileageRange);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBtnAddNewOil.getId()){
            setOil();
            mDataManager.oils().create(mNewOil);
        }
    }
}
