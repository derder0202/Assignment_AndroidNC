package com.example.assignment_androidnc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment_androidnc.Social.SocialActivity
import com.example.assignment_androidnc.course.CourseActivity
import com.example.assignment_androidnc.databinding.ActivityMainBinding
import com.example.assignment_androidnc.maps.MapsActivity
import com.example.assignment_androidnc.news.NewsActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainCourse.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
        }
        binding.mainMaps.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
        binding.mainNews.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
        binding.mainSocial.setOnClickListener {
            startActivity(Intent(this, SocialActivity::class.java))
        }

    }
}