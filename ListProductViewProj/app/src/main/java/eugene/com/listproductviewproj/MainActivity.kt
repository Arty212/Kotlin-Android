package eugene.com.listproductviewproj

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Delete
import java.util.*

class MainActivity : AppCompatActivity() {

    var data=ArrayList<Product>()

    var names:Array<String> = arrayOf("Bosh","Gorenje","Ardo","Whirpool","Siemens")
    var descriptions:Array<String> = arrayOf("Холодильник","Плита газовая", "Посудомоечная машина","Другой холодильник","Микроволновка")
    var imgs:Array<Int> = arrayOf(R.drawable.fridge1,R.drawable.plita,R.drawable.dishwash,R.drawable.fridge2,R.drawable.fridge1)
    var categors:Array<String> = arrayOf("Бытовая техника","Бытовая техника","Бытовая техника","Бытовая техника","Бытовая техника")
    var categorsDesc:Array<String> = arrayOf("Техника для дома и всей семьи","Техника для дома и всей семьи","Техника для дома и всей семьи","Техника для дома и всей семьи","Техника для дома и всей семьи")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DbHelper.initDbHelper(applicationContext)
        if(DbHelper.getProducts().size==0)
        {
            DbHelper.deleteAll()
            for (i in 0..4) {
                data.add(Product(names[i], descriptions[i], categors[i],categorsDesc[i],imgs[i], 100))
            }
            DbHelper.addProducts(data)
        }
        data=DbHelper.getProducts()

        var list=findViewById(R.id.list) as RecyclerView
        list.layoutManager=LinearLayoutManager(applicationContext)

        var adapter=MyAdapter(data)

        adapter.setOnProductClickAction {position->
            var intent= Intent(applicationContext,EditActivity::class.java)

            intent.putExtra("position",position)
            startActivity(intent)
        }

        list.adapter=adapter

    }




}
