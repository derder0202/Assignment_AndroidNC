package com.example.assignment_androidnc.course.course_dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.assignment_androidnc.course.corse_model.Student
import com.example.assignment_androidnc.course.sqlite_helper.SQLiteHelper

class StudentDAO(var context: Context) {
    private var sqLiteHelper = SQLiteHelper(context)
    private lateinit var db: SQLiteDatabase
    //"create table SinhVien(maSV text primary key, tenSV text)"
    //create table SV_Lop(maSV text references SinhVien(maSV),maLop text references Lop(maLop),primary key(maSV,maLop))
    fun insert(student: Student){
        db = sqLiteHelper.writableDatabase
        val values = ContentValues()
        val value2 = ContentValues()
        values.put("maSV",student.maSV)
        values.put("tenSV",student.tenSV)
        value2.put("maSV",student.maSV)
        value2.put("maLop",student.lop)
        val result1 = db.insert("SinhVien",null,values)<0
        val result2 = db.insert("SV_Lop",null,value2)<0
        val result = if(result1&&result2) "them that bai" else "them thanh cong"
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show()
    }

    fun getData(sql:String):ArrayList<Student>{
        db = sqLiteHelper.writableDatabase
        val list = ArrayList<Student>()
        val cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast){
            list.add(Student(cursor.getString(0),cursor.getString(1),cursor.getString(2)))
            cursor.moveToNext()
        }
        cursor.close()
        db.close()
        return list
    }
    fun getAll() = getData("select SinhVien.maSV,tenSV, Lop.maLop from SinhVien \n" +
            "join SV_Lop on SinhVien.maSV = SV_Lop.maSV\n" +
            "JOIN Lop on SV_Lop.maLop = Lop.maLop")
}