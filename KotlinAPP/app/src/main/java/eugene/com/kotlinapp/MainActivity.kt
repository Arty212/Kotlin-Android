package eugene.com.kotlinapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        private val POINT_KEY= "point_key"
        private val TIME_KEY="time_key"
        private val SHARED_KEY="clicker_shared"
        private  val SHARED_KEY_POINT="clicker_shared_point"
        private var mpBackground =MediaPlayer()
        private var mp=MediaPlayer()
    }



    private var time=25
    private var point=0

    private var flag=false

    private var PointText:TextView? =null
    private var TimeText:TextView? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //if (savedInstanceState==null)
         //   PointText!!.text=getIntPrefereces(SHARED_KEY_POINT).toString()

        mpBackground=MediaPlayer.create(applicationContext,R.raw.background_trak)
        mpBackground.start()
        mpBackground.setOnCompletionListener{ mpBackground.start()}

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
                var random=(Math.random()*10).toInt()
                if (!mp.isPlaying) {
                    when(random){
                        1 -> { mp = MediaPlayer.create(applicationContext, R.raw.trak_2);mp.start()}
                        3-> {mp = MediaPlayer.create(applicationContext, R.raw.trak_1);mp.start()}
                        7-> {mp = MediaPlayer.create(applicationContext, R.raw.trak_3);mp.start()}
                    }
                }

                if (time<=0) {
                    ClickBtn.post{
                        ClickBtn.isEnabled=false;
                    }

                    if (getIntPrefereces(SHARED_KEY_POINT)< point) {
                        setIntPreferences(SHARED_KEY_POINT, point)
                        flag=true;
                    }
                    var intent = Intent(applicationContext,ResultActivity::class.java)
                    intent.putExtra(ResultActivity.INTENT_KEY,point)
                    intent.putExtra(ResultActivity.INTENT_POINT,getIntPrefereces(SHARED_KEY_POINT))
                    intent.putExtra(ResultActivity.INTENT_RESULT,flag)

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
            TimeText!!.text=(time/60).toString()+":"+(time % 60).toString()
        }
    }

    private fun setIntPreferences(key:String,point:Int) {
        val sh = getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putInt(SHARED_KEY_POINT, point)
        editor.apply()
    }

    private  fun getIntPrefereces(key:String):Int{
        val sh=getSharedPreferences(SHARED_KEY,Context.MODE_PRIVATE)
        return sh.getInt(key,0)
    }

    override fun onStop() {
        super.onStop()
        mp.stop()
        mpBackground.stop()
    }

    override fun onResume() {
        super.onResume()
        //mp.stop()
        //mpBackground.stop()
    }
}
