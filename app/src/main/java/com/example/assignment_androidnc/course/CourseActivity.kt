package com.example.assignment_androidnc.course

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment_androidnc.R
import com.example.assignment_androidnc.databinding.ActivityCourseBinding

class CourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.courseLopBtn.setOnClickListener {
            startActivity(Intent(this,LopActivity::class.java))
        }
        binding.courseStuBtn.setOnClickListener {
            startActivity(Intent(this,StudentActivity::class.java))
        }
    }
}