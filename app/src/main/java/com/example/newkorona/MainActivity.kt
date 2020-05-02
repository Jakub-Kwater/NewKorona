package com.example.newkorona

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

import android.util.Log
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import com.google.gson.Gson



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson() {


        doAsync {
            Request().run()
            uiThread {
                longToast("Request performed")
            }
        }

    }
}

//val persons:ArrayList<Data>  = Gson().fromJson(response, new TypeToken<List<PersonModel>>(){}.getType())

class HomeFeed:ArrayList<Data>()

class Data(val country: String, val cases: Int, val todayCases: Int, val deaths: Int, val todayDeaths: Int,
           val recovered: Int, val active: Int, val critical: Int, val casesPerOneMillion: Int,
           val deathsPerOneMillion: Int, val totalTests: Int, val testsPerOneMillion: Int)

class Request {

    companion object{
        val url = "http://coronavirus-19-api.herokuapp.com/countries/?fbclid=IwAR1Dka65-zF7Gx6SenmP7gocy34ykgDANM4aM_tAnL66iaxYnHFjF0Mev8g"
    }
    fun run():ArrayList<Data>{
        val repoListJsonStr = URL(url).readText()

        val prasedData = Gson().fromJson(repoListJsonStr, ArrayList<Data>::class.java) // próbowałem tu wstawić formę z array list ale nie działa i nie jestem pewien czemu
        Log.d(javaClass.simpleName, prasedData.toString())
        return prasedData
    }

}

val data:ArrayList<Data> = Request().run()