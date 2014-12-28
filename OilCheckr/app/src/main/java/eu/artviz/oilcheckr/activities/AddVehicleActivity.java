package eu.artviz.oilcheckr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import eu.artviz.oilcheckr.R;
import eu.artviz.oilcheckr.common.Constants;
import eu.artviz.oilcheckr.models.MileageUnit;
import eu.artviz.oilcheckr.models.Vehicle;

public class AddVehicleActivity extends Activity implements View.OnClickListener{

    private EditText mEtVehicleName;
    private RadioGroup mRgMileageUnit;
    private RadioButton mRbKm;
    private RadioButton mRbMiles;
    private Button mBtnContinue;
    private Vehicle mVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        initViews();
        init();
    }

    private void init(){
        mVehicle = new Vehicle();
    }

    private void initViews(){
        mEtVehicleName = (EditText)findViewById(R.id.etVehicleName);
        mRgMileageUnit = (RadioGroup)findViewById(R.id.rgMileageUnit);
        mRbKm = (RadioButton)findViewById(R.id.rbKm);
        mRbMiles = (RadioButton)findViewById(R.id.rbMile);
        mBtnContinue = (Button)findViewById(R.id.btnContinue);
        mBtnContinue.setOnClickListener(this);
    }

    private RadioButton getCheckedRadio(){
        int radioButtonID = mRgMileageUnit.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)mRgMileageUnit.findViewById(radioButtonID);

        return radioButton;
    }

    private boolean setVehicle(){
        String vehicleName = mEtVehicleName.getText().toString().trim();
        RadioButton checkedRdBtn = getCheckedRadio();
        if (vehicleName != null && !vehicleName.isEmpty() && vehicleName.length() > 0){
            mVehicle.setName(vehicleName);
        }
        else{
            String message = getResources().getString(R.string.enter_vehicle_name);
            Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            return false;
        }

        if (checkedRdBtn.getId() == mRbKm.getId()){
            mVehicle.setMileageUnit(MileageUnit.Kilometres);
        }
        else{
            mVehicle.setMileageUnit(MileageUnit.Miles);
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mBtnContinue.getId()){

            if (setVehicle()){
                Intent intent = new Intent(this,UpdateMileageActivity.class);
                intent.putExtra(Constants.VEHICLE,mVehicle);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"error",Toast.LENGTH_LONG);
            }
        }
    }
}
