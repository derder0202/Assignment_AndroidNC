package com.example.assignment_androidnc.course.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.course.LopActivity
import com.example.assignment_androidnc.course.corse_model.Lop
import com.example.assignment_androidnc.course.course_dao.LopDAO
import com.example.assignment_androidnc.databinding.ItemLopBinding

class AdapterLop (var context: Context,var lopList:ArrayList<Lop>) : RecyclerView.Adapter<AdapterLop.ViewHolder>() {
    class ViewHolder(binding:ItemLopBinding) : RecyclerView.ViewHolder(binding.root) {
        val maLop = binding.itemId
        val tenLop = binding.itemName
        val stt = binding.itemStt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLopBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lop = lopList[position]
        holder.stt.text = "${position+1}"
        holder.maLop.text = lop.maLop
        holder.tenLop.text = lop.tenLop
    }

    override fun getItemCount(): Int = lopList.size
}