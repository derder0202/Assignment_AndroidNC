package com.example.assignment_androidnc.course

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.R
import com.example.assignment_androidnc.course.adapter.AdapterLop
import com.example.assignment_androidnc.course.corse_model.Lop
import com.example.assignment_androidnc.course.course_dao.LopDAO
import com.example.assignment_androidnc.databinding.ActivityLopBinding
import com.example.assignment_androidnc.databinding.DialogLopBinding
import com.google.android.material.textfield.TextInputLayout

class LopActivity : AppCompatActivity() {
    lateinit var binding: ActivityLopBinding
    lateinit var adapterLop: AdapterLop
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.lopRecylerView

        binding.lopFab.setOnClickListener {
            openDialog()
        }
        updateRecyclerView()

    }
    fun updateRecyclerView(){
        val list = LopDAO(this@LopActivity).getAll()
        adapterLop = AdapterLop(this@LopActivity,list)
        recyclerView.layoutManager = LinearLayoutManager(this@LopActivity)
        recyclerView.adapter = adapterLop
    }

    fun openDialog(){
        val builder = AlertDialog.Builder(this@LopActivity)
        builder.setTitle("Thêm lớp")
        val binding = DialogLopBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        dialog.show()

        val maLop = binding.maLop
        val tenLop = binding.tenLop
        val maLopEdt = maLop.editText!!
        val tenLopEdt = tenLop.editText!!

        binding.dialogLopCloseBtn.setOnClickListener {
            dialog.dismiss()
        }
        binding.dialogLopThemBtn.setOnClickListener {
            if(checkField(maLop,tenLop)){
                LopDAO(this).insert(Lop(maLopEdt.text.toString(),tenLopEdt.text.toString()))
                updateRecyclerView()
            }
        }

    }
    fun checkField(vararg textInputLayout: TextInputLayout):Boolean{
        textInputLayout.forEach {
            if(it.editText!!.text.isEmpty()){
                it.error = "Không bỏ trống trường này"
                return false
            } else it.error = null
        }
        return true
    }
}