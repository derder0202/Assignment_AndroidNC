package com.example.assignment_androidnc.course.corse_model

data class Lop(var maLop: String="",var tenLop:String=""){
    override fun toString(): String {
        return "$maLop\t\t\t$tenLop"
    }
}