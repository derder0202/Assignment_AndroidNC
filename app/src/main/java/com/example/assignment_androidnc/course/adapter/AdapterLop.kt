package com.example.assignment_androidnc.course.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.course.LopActivity
import com.example.assignment_androidnc.course.corse_model.Lop
import com.example.assignment_androidnc.course.course_dao.LopDAO
import com.example.assignment_androidnc.course.course_dao.StudentDAO
import com.example.assignment_androidnc.databinding.DialogLopBinding
import com.example.assignment_androidnc.databinding.ItemLopBinding

class AdapterLop (var context: Context,var lopList:ArrayList<Lop>,var activity: LopActivity) : RecyclerView.Adapter<AdapterLop.ViewHolder>() {
    class ViewHolder(binding:ItemLopBinding) : RecyclerView.ViewHolder(binding.root) {
        val maLop = binding.itemId
        val tenLop = binding.itemName
        val stt = binding.itemStt
        val editBtn = binding.itemLopEditBtn
        val removeBtn = binding.itemLopRemoveBtn
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

        holder.editBtn.setOnClickListener {
            activity.openDialog(lop,1)
        }

        holder.removeBtn.setOnClickListener {
            if (StudentDAO(context).getAll().size!=0){
                Toast.makeText(context,"khong the xoa lop khi dang co sinh vien hoc",Toast.LENGTH_SHORT).show()
            } else{
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Xoa Lop")
                    .setMessage("Ban co chac chan xoa lop nay khong?")
                    .setPositiveButton("OK"  ) { p0, p1 ->
                        LopDAO(context).remove(lop)
                        activity.updateRecyclerView()
                        p0.dismiss()
                    }
                    .setNegativeButton("Cancel"){p0,p1 ->
                        p0.dismiss()
                    }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun getItemCount(): Int = lopList.size
}