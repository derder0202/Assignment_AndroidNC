package com.example.assignment_androidnc.course.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.course.StudentActivity
import com.example.assignment_androidnc.course.corse_model.Student
import com.example.assignment_androidnc.course.course_dao.LopDAO
import com.example.assignment_androidnc.course.course_dao.StudentDAO
import com.example.assignment_androidnc.databinding.ItemLopBinding

class AdapterStudent(var context: Context,var list:ArrayList<Student>,var activity:StudentActivity) : RecyclerView.Adapter<AdapterStudent.ViewHolder>() {
    class ViewHolder(binding: ItemLopBinding) : RecyclerView.ViewHolder(binding.root) {
        val lop = binding.itemStt
        val maSV = binding.itemId
        val tenSV = binding.itemName
        val editBtn = binding.itemLopEditBtn
        val removeBtn = binding.itemLopRemoveBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLopBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = list[position]
        holder.lop.text = student.lop
        holder.maSV.text = student.maSV
        holder.tenSV.text = student.tenSV
        holder.removeBtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Xoa Sinh Vien")
                .setMessage("Ban co chac chan xoa sinh vien nay khong?")
                .setPositiveButton("OK"  ) { p0, p1 ->
                    StudentDAO(context).remove(student)
                    activity.updateRecyclerView()
                    p0.dismiss()
                }
                .setNegativeButton("Cancel"){p0,p1 ->
                    p0.dismiss()
                }
            val dialog = builder.create()
            dialog.show()
        }
        holder.editBtn.setOnClickListener {
            activity.openDialog(student,1)
        }
    }

    override fun getItemCount(): Int = list.size
}