package com.example.assignment_androidnc.course

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.R
import com.example.assignment_androidnc.course.adapter.AdapterLop
import com.example.assignment_androidnc.course.adapter.AdapterStudent
import com.example.assignment_androidnc.course.corse_model.Student
import com.example.assignment_androidnc.course.course_dao.LopDAO
import com.example.assignment_androidnc.course.course_dao.StudentDAO
import com.example.assignment_androidnc.databinding.ActivityStudentBinding
import com.example.assignment_androidnc.databinding.DialogLopBinding
import com.example.assignment_androidnc.databinding.DialogStudentBinding

class StudentActivity : AppCompatActivity() {
    lateinit var binding: ActivityStudentBinding
    lateinit var adapterStudent: AdapterStudent
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.stuRecylerView
        binding.stuFab.setOnClickListener {
            openDialog(Student(),0)
        }
        updateRecyclerView()
    }
    fun updateRecyclerView(){
        val list = StudentDAO(this).getAll()
        adapterStudent = AdapterStudent(this,list,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterStudent
    }
    fun openDialog(student: Student,type:Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if(type==0)"Thêm Sinh viên" else "cap nhat sinh vien")
        val binding = DialogStudentBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dialog = builder.create()
        dialog.show()

        val spinner = binding.spinner
        val maSV = binding.maSV
        val tenSV = binding.tenSV

        if(type==1){
            maSV.editText!!.setText(student.maSV)
            tenSV.editText!!.setText(student.tenSV)
            spinner.setSelection(LopDAO(this).getAll().indexOfFirst { lop -> lop.maLop == student.lop })
            maSV.editText!!.isEnabled = false
        }

        val listLop = LopDAO(this).getAll()
        val listLopToString = ArrayList<String>()
        listLop.forEach {
            listLopToString.add(it.toString())
        }
        val adapterSpinner = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listLopToString)
        spinner.adapter = adapterSpinner

        binding.dialogStudentAddBtn.setOnClickListener {
            if(listLop.isNotEmpty()&&LopActivity().checkField(maSV,tenSV)){
                val student = Student(maSV.editText!!.text.toString(),tenSV.editText!!.text.toString(),listLop[spinner.selectedItemPosition].maLop)
                if(type==0){
                    StudentDAO(this).insert(student)
                } else{
                    StudentDAO(this).update(student)
                }
                updateRecyclerView()
                dialog.dismiss()
            }
        }
        binding.dialogStudentCloseBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

}