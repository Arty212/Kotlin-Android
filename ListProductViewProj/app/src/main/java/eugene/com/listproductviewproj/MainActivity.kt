package eugene.com.listproductviewproj

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

//import com.orm.SugarDb




class MainActivity : AppCompatActivity() {


    var products:Array<Product> = Array(10,{i-> Product() })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Delete().from(Product::class.java).execute();

        var countText=findViewById(R.id.count_text) as TextView
        var afterCountText=findViewById(R.id.count_text2) as TextView
        var LoadBtn=findViewById(R.id.load_btn) as Button

        var names:Array<String> = arrayOf("Bosh","Gorenje","Ardo","Whirpool","Siemens")
        var descriptions:Array<String> = arrayOf("Холодильник","Плита газовая", "Посудомоечная машина","Другой холодильник","Микроволновка")
        var imgs:Array<Int> = arrayOf(R.drawable.fridge1,R.drawable.plita,R.drawable.dishwash,R.drawable.fridge2,R.drawable.fridge1)
        if (Product.queryCount()!=0){
            for (i in 0..4)
            {
                products[i]=Product.queryById((i+1).toLong())
            }
        }
        else {
            var names:Array<String> = arrayOf("Bosh","Gorenje","Ardo","Whirpool","Siemens")
            var descriptions:Array<String> = arrayOf("Холодильник","Плита газовая", "Посудомоечная машина","Другой холодильник","Микроволновка")
            var imgs:Array<Int> = arrayOf(R.drawable.fridge1,R.drawable.plita,R.drawable.dishwash,R.drawable.fridge2,R.drawable.fridge1)

            for (i in 0..4)
            {
                var prod=Product(names[i],descriptions[i],imgs[i],10)
                prod.save()
                products[i]=prod
            }


        }




        var list=findViewById(R.id.list) as RecyclerView
        list.layoutManager=LinearLayoutManager(this)
        var adapter=MyAdapter()
        adapter.setOnProductClickAction {position->
            var intent= Intent(applicationContext,EditActivity::class.java)

            intent.putExtra("position",position)
            startActivity(intent)
        }



        adapter.data=products
        var count=adapter.itemCount
        countText.text=Product.queryCount().toString()
        //Product.allDelete()
        afterCountText.text=Product.queryCount().toString()
        list.adapter=adapter

        LoadBtn.setOnClickListener{
            var names:Array<String> = arrayOf("Bosh","Gorenje","Ardo","Whirpool","Siemens")
            var descriptions:Array<String> = arrayOf("Холодильник","Плита газовая", "Посудомоечная машина","Другой холодильник","Микроволновка")
            var imgs:Array<Int> = arrayOf(R.drawable.fridge1,R.drawable.plita,R.drawable.dishwash,R.drawable.fridge2,R.drawable.fridge1)

            for (i in 0..4){
                var prod=Product(names[i],descriptions[i],imgs[i],10)
                prod.save()
            }

            var intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }


    }
}
