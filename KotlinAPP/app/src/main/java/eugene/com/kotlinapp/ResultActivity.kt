package eugene.com.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

/**
 * Created by USER on 15.12.2016.
 */
class ResultActivity : AppCompatActivity() {


    companion object {
        val INTENT_KEY = "intent_key"
        val INTENT_POINT="intent_point"
        val INTENT_RESULT="intent_result"
    }

    private var recText:TextView? =null
    private var TextPoint:TextView? =null
    //private var result_img:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        recText = findViewById(R.id.record_text) as TextView
        TextPoint=findViewById(R.id.result_text) as TextView
        var result_img=findViewById(R.id.result_img) as ImageView

        var point=intent.getIntExtra(INTENT_KEY,0)
        var flag=intent.getBooleanExtra(INTENT_RESULT,false)
        var point_record=intent.getIntExtra(INTENT_POINT,0)

        Log.d("my_log",point.toString())

        when{
            point<0.7*point_record-> result_img.setImageDrawable(applicationContext.resources.getDrawable(R.drawable.frightened))
            point>=0.7*point_record&&point<point_record->result_img.setImageDrawable(applicationContext.resources.getDrawable(R.drawable.sad))
            point>=point_record->result_img.setImageDrawable(applicationContext.resources.getDrawable(R.drawable.smile))
        }



        TextPoint!!.text=point.toString()
        if (flag==true) recText!!.text="Вы сделали новый рекорд"
        else recText!!.text="Текущий рекорд = "+point_record.toString()

        var RepeatBtn=findViewById(R.id.restart_btn) as Button

        RepeatBtn.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}