package com.kaiser.testsever

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private var mDatabase: DatabaseReference
    private var txtMonHoc: TextView? = null
    private var txtDanhSach: TextView? = null
    init {
        mDatabase = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtMonHoc = findViewById(R.id.monhoc)
        txtDanhSach = findViewById(R.id.dsmonhoc)
//        mDatabase.child("MonHoc").setValue("Lập Trình Android")
//        mDatabase.child("DanhSachMonHoc").push().setValue("Lập Trình IOS")
        mDatabase.child("MonHoc").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var tenMonHoc: String = snapshot.getValue().toString()
                txtMonHoc?.text = tenMonHoc
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        mDatabase.child("DanhSachMonHoc").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                var ten: String = snapshot.getValue().toString()
                txtDanhSach?.text = ""
                txtDanhSach?.append(ten + "\n")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}