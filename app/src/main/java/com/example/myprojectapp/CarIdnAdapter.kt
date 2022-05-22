package com.example.myprojectapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class CarIdnAdapter(val cCtx : Context, val layoutResId : Int, val idnList :List<CarIdn>) :ArrayAdapter<CarIdn> (cCtx,layoutResId,idnList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(cCtx)
        val view : View = layoutInflater.inflate(layoutResId,null)

        val dpPlate : TextView = view.findViewById(R.id.dp_plate)
        val dpName : TextView = view.findViewById(R.id.dp_name)



        val carIdn = idnList[position]

        dpPlate.text = carIdn.plate
        dpName.text = carIdn.name
        return view
    }


}