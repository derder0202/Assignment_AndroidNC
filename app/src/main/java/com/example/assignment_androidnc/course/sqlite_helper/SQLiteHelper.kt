package com.example.assignment_androidnc.course.sqlite_helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context?) : SQLiteOpenHelper(context, name, null, version) {
    companion object{
        const val name = "QLSV"
        const val version = 1
    }
    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL("create table SinhVien(maSV text primary key, tenSV text)")
        p0.execSQL("create table Lop(maLop text primary key, tenLop text)")
        p0.execSQL("create table SV_Lop(maSV text references SinhVien(maSV),maLop text references Lop(maLop),primary key(maSV,maLop))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }


}