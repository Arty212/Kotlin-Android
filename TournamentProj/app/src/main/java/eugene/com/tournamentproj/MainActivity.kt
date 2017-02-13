package eugene.com.tournamentproj

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.ImageView
import android.widget.Toast
import eugene.com.utilproject.API
import eugene.com.utilproject.DbHelper
import eugene.com.utilproject.MyAdapter
import eugene.com.utilproject.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    var img: ImageView? = null
    //var progressDialog: ProgressDialog? = null

    var adapter:MyAdapter?=null

    var arr = arrayOf("http://savepic.ru/12908746.png",
            "http://savepic.ru/12877002.png",
            "http://savepic.ru/12864714.png",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Robot_icon_blueeye.svg/600px-Robot_icon_blueeye.svg.png")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        DbHelper.initDbHelper(applicationContext)
        adapter= MyAdapter()

        if (DbHelper.getUsers().size==0){
            DownloadData()
        }
        else {
            var data= DbHelper.getUsers()
            var list = findViewById(R.id.list) as RecyclerView
            list.layoutManager = LinearLayoutManager(applicationContext)
            adapter!!.add(data)
            adapter!!.notifyDataSetChanged()
            list.adapter = adapter
            Toast.makeText(applicationContext, "Загрузка завершена", Toast.LENGTH_SHORT).show()
        }


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
              //      .setAction("Action", null).show()

            UpdateData()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_update) {
            UpdateData()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    public fun DownloadData(){
        var retrofit = Retrofit.Builder()
                .baseUrl("http://arty221.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var network = retrofit.create<API>(API::class.java)
        var call = network.getRecords()
        call.enqueue(object : Callback<ArrayList<User>> {
            override fun onFailure(call: Call<ArrayList<User>>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ArrayList<User>>?, response: Response<ArrayList<User>>?)
            {
                var data = response!!.body()
                DbHelper.addUsers(data)
                //Log.v("JSON",data.toString())
                var list = findViewById(R.id.list) as RecyclerView
                list.layoutManager = LinearLayoutManager(applicationContext)
                adapter!!.add(data)
                adapter!!.notifyDataSetChanged()
                list.adapter = adapter
                Toast.makeText(applicationContext, "Загрузка завершена", Toast.LENGTH_SHORT).show()
            }
        });
    }

    public fun UpdateData()
    {
        DbHelper.DeleteAll()
        DownloadData()
        var data=DbHelper.getUsers()
        adapter!!.add(data)
        adapter!!.notifyDataSetChanged()
    }
}
