package com.blinxapp.customeradpter;

import java.util.ArrayList;

import com.blinxapp.R;
import com.blinxapp.dto.Province;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/***** Adapter class extends with ArrayAdapter ******/
public class CustomAdapter extends ArrayAdapter{
     
    //private Activity activity;
   public  ArrayList<?> data;
    Province tempValues=null;
    LayoutInflater inflater;
     
    /*************  CustomAdapter Constructor *****************/
    public CustomAdapter(Context activitySpinner,int textViewResourceId, ArrayList<?> objects)
     {
    	super(activitySpinner, textViewResourceId, objects);
    	this.data=objects;
    	inflater = (LayoutInflater)activitySpinner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
     }
 
    /*@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Province getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}
*/
	/*@Override
	public int getPosition(Province item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}*/

	@Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
 
    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
 
        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
    	
        View row = inflater.inflate(R.layout.spinner_rows, parent, false);
         
        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (Province) data.get(position);
         
        TextView label        = (TextView)row.findViewById(R.id.customprovince);
        //TextView sub          = (TextView)row.findViewById(R.id.sub);
       /* if(position==0){
            // Default selected Spinner item
            label.setText("Please select company");
        }
        else
        {   // Set values for spinner each row
            label.setText(tempValues.getProvince_name());
        } */ 
        label.setText(tempValues.getProvince_name());
        return row;
      }
 }