package com.example.assignment_androidnc.course.course_dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.assignment_androidnc.course.corse_model.Lop
import com.example.assignment_androidnc.course.sqlite_helper.SQLiteHelper

class LopDAO(var context: Context) {
    private var sqLiteHelper = SQLiteHelper(context)
    private lateinit var db: SQLiteDatabase

    //create table Lop(maLop text primary key, tenLop text)
    fun insert(lop: Lop){
        db = sqLiteHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("maLop",lop.maLop)
        contentValues.put("tenLop",lop.tenLop)
        val result = if(db.insert("Lop",null,contentValues)<0) "them lop that bai" else "them lop thanh cong"
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun remove(lop: Lop){
        db = sqLiteHelper.writableDatabase
        val result = if (db.delete("Lop","maLop='${lop.maLop}' ",null)<=0) "xoa that bai" else "xoa thanh cong"
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun update(lop: Lop){
        db = sqLiteHelper.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("maLop",lop.maLop)
        contentValues.put("tenLop",lop.tenLop)
        val result = if(db.update("Lop",contentValues,"maLop='${lop.maLop}'",null)<=0) "cap nhat that bai" else "cap nhat thanh cong"
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun getData(sql:String):ArrayList<Lop>{
        db = sqLiteHelper.writableDatabase
        val lopList = ArrayList<Lop>()
        val cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast){
            lopList.add(Lop(cursor.getString(0),cursor.getString(1)))
            cursor.moveToNext()
        }
        cursor.close()
        db.close()
        return lopList
    }
    fun getAll() = getData("select *from Lop")
}