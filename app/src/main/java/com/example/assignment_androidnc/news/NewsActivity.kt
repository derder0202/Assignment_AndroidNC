package com.example.assignment_androidnc.news

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_androidnc.databinding.ActivityNewsBinding
import com.example.assignment_androidnc.news.XMLParser.Companion.getDocument
import com.example.assignment_androidnc.news.XMLParser.Companion.getNodeValue
import org.w3c.dom.Element
import org.xml.sax.SAXException
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL

class NewsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewsBinding
    private var listNews =ArrayList<NewsModel>()
    lateinit var adapterNews: AdapterNews
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.newsRecyclerView
        ReadNews().execute("https://vnexpress.net/rss/the-gioi.rss")

    }

    inner class ReadNews() : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String?): String {
            var result = ""
            try {
                val url = URL(p0[0])
                val inputStreamReader = InputStreamReader(url.openConnection().getInputStream())
                inputStreamReader.use {
                    result = it.readText()
                }
            } catch (e:MalformedURLException){
                e.printStackTrace()
            } catch (e:IOException){
                e.printStackTrace()
            }
            return result
        }

        override fun onPostExecute(result: String?) {
           super.onPostExecute(result)
           try {
               val document = getDocument(result!!)
               val nodeList = document.getElementsByTagName("item")
               var title = ""
               var des = ""
               var url = ""
               for (i in 0 until nodeList.length){
                   val element = nodeList.item(i) as Element
                   title = getNodeValue(element,"title")
                   des = getNodeValue(element,"description")
                   des = des.substring(des.indexOf("</br>")+5)
                   url = getNodeValue(element,"description")
                   url = url.substring(url.indexOf("src=\"")+5,url.indexOf("\" ></a>"))
                   listNews.add(NewsModel(title,des,url, getNodeValue(element,"link")))
               }
               val adapter = AdapterNews(this@NewsActivity,listNews)
               recyclerView.layoutManager = LinearLayoutManager(this@NewsActivity)
               recyclerView.adapter = adapter
           } catch (e:IOException){
               e.printStackTrace()
           } catch (e:SAXException){
               e.printStackTrace()
           }
        }

    }
}