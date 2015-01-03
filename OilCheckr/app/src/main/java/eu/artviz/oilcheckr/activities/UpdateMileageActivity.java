package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.models.Vehicle;

public class UpdateMileageActivity extends Activity implements View.OnClickListener{

    private EditText mEtMileage;
    private Button mBtnSave;
    private Vehicle mVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mileage);

        this.initViews();
        this.init();
    }

    private void init(){
        mVehicle = (Vehicle)getIntent().getExtras().getParcelable(Constants.VEHICLE);
    }

    private void initViews(){
        mEtMileage = (EditText)findViewById(R.id.etMileage);
        mBtnSave = (Button)findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBtnSave.getId()){
            String mileageStr = mEtMileage.getText().toString().trim();
            String message;

            if (mileageStr != null && !mileageStr.isEmpty()){
                int mileage = Integer.valueOf(mileageStr);
                mVehicle.setCurrentMileage(mileage);

                //clear input field
                mEtMileage.setText("");

                message = getResources().getString(R.string.mileage_updated);
                Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            }
            else{
                message = getResources().getString(R.string.enter_mileage);
                Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            }
        }
    }
}
