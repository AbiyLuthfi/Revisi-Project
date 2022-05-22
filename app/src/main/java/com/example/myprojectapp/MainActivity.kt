package com.example.myprojectapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var etPlate : EditText
    private lateinit var etName : EditText
    private lateinit var btnSave : Button
    private lateinit var ref : DatabaseReference
    private lateinit var carList: MutableList<CarIdn>
    private lateinit var listCar : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("Car Identifier")

        etPlate = findViewById(R.id.et_plate)
        etName = findViewById(R.id.et_name)
        listCar = findViewById(R.id.lv_car)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)

        carList = mutableListOf()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    carList.clear()
                    for (h in snapshot.children){
                        val car = h.getValue(CarIdn::class.java)
                        if (car != null) {
                            carList.add(car)
                        }
                    }

                    val adapter = CarIdnAdapter(this@MainActivity,R.layout.item_car,carList)
                    listCar.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val plate : String = etPlate.text.toString().trim()
        val name : String = etName.text.toString().trim()
        if (plate.isEmpty()){
            etPlate.error = "Fill the Car Number of Plate!"
            return
        }
        if (name.isEmpty()) {
            etName.error = "Fill the Car Name or Type!"
            return
        }

        val carIdn = ref.push().key

        val idn = CarIdn(carIdn,plate, name)

        if (carIdn != null) {
            ref.child(carIdn).setValue(idn).addOnCompleteListener{
                Toast.makeText(applicationContext, "Data is successfully added!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
