package eugene.com.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var point=0
        var ClickBtn = findViewById(R.id.mybutton) as Button
        var PointText=findViewById(R.id.point_text) as TextView
        var TimeText=findViewById(R.id.time_text) as TextView

        var time=30

        ClickBtn.setOnClickListener { point++; PointText.text=point.toString() }

        var timer=Timer()
        timer.schedule(timerTask {
            time--
            TimeText.post{
                TimeText.text=(time/60).toString() + " : "+ (time%60).toString()
            }
            if (time<=0) {
                ClickBtn.post{
                    ClickBtn.isEnabled=false;
                }

                timer.cancel()
            }
        }, 1000,500)












    }
}
