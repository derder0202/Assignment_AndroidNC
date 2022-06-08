package com.example.assignment_androidnc.course.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.course.corse_model.Student
import com.example.assignment_androidnc.databinding.ItemLopBinding

class AdapterStudent(var context: Context,var list:ArrayList<Student>) : RecyclerView.Adapter<AdapterStudent.ViewHolder>() {
    class ViewHolder(binding: ItemLopBinding) : RecyclerView.ViewHolder(binding.root) {
        val lop = binding.itemStt
        val maSV = binding.itemId
        val tenSV = binding.itemName
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
    }

    override fun getItemCount(): Int = list.size
}