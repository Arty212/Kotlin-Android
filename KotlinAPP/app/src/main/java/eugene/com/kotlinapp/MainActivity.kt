package eugene.com.kotlinapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val POINT_KEY= "point_key"
    private val TIME_KEY="time_key"

    private var time=25
    private var point=0

    private var PointText:TextView? =null
    private var TimeText:TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ClickBtn = findViewById(R.id.mybutton) as ImageButton
        PointText=findViewById(R.id.point_text) as TextView
        TimeText=findViewById(R.id.time_text) as TextView



        ClickBtn.setOnClickListener {
            point++;
            PointText!!.text=point.toString() }

        if (point==0)
        {
            var timer=Timer()
            timer.schedule(timerTask {
                time--
                TimeText!!.post{
                    TimeText!!.text=(time/60).toString() + " : "+ (time%60).toString()
                }
                if (time<=0) {
                    ClickBtn.post{
                        ClickBtn.isEnabled=false;
                    }

                    var intent = Intent(applicationContext,ResultActivity::class.java)
                    startActivity(intent)
                    timer.cancel()

                }
            }, 1000,500)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if (outState != null) {
            outState.putInt(POINT_KEY,point)
            outState.putInt(TIME_KEY,time)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            point = savedInstanceState.getInt(POINT_KEY)
            time=savedInstanceState.getInt(TIME_KEY)
            PointText!!.text=point.toString()
            TimeText!!.text=time.toString()
        }
    }
}
