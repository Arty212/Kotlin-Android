package eugene.com.myapplication

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

class GameActivity : AppCompatActivity() {

    val MIN=1
    val MAX=9
    var CurrentMusic=0

    var PointPlayer1=0
    var PointPlayer2=0

    var BetPlayer1=0
    var BetPlayer2=0
    var MinBet=0;
    var CurrentPlayer=0;
    var status=0

    private var time=-2

    companion object{
        public var mpGame = MediaPlayer()
        public var SHARED_SUCCES_MUSIC="shared_succes_music"
        public var SHARED_CURRENT_MUSIC="shared_current_music"
        public var SHARED_STATUS="shared_status"
        public var SHARED_CURRENT_PLAYER="shared_current_player"
    }


    private var TextPoint:TextView?=null
    private var TextTime:TextView?=null
    private var TextName:TextView?=null
    private var ListBtn:Button?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        TextPoint=findViewById(R.id.text_point) as TextView
        TextTime=findViewById(R.id.text_time) as TextView
        TextName=findViewById(R.id.current_player_name) as TextView
        ListBtn=findViewById(R.id.start_list_music) as Button

        PointPlayer1=getIntPrefereces(BetActivity.SHARED_POINT_PLAYER1)
        PointPlayer2=getIntPrefereces(BetActivity.SHARED_POINT_PLAYER2)

        BetPlayer1=getIntPrefereces(BetActivity.SHARED_BET_PLAYER1)
        BetPlayer2=getIntPrefereces(BetActivity.SHARED_BET_PLAYER2)

        var PlayerName1=getStringPrefereces(BetActivity.SHARED_NAME_PLAYER1)
        var PlayerName2=getStringPrefereces(BetActivity.SHARED_NAME_PLAYER2)

        TextPoint!!.text=PointPlayer1.toString()+":"+PointPlayer2.toString()

        status=getIntPrefereces(SHARED_STATUS)
        if(status==0) {
            if (BetPlayer1 < BetPlayer2) {
                MinBet = BetPlayer1
                CurrentPlayer = 0
            } else {
                MinBet = BetPlayer2
                CurrentPlayer = 1
            }
            setIntPreferences(SHARED_CURRENT_PLAYER,CurrentPlayer)
            RandomCurrentMusic()
            setIntPreferences(SHARED_CURRENT_MUSIC,CurrentMusic)
        }
        else {
            CurrentPlayer=getIntPrefereces(SHARED_CURRENT_PLAYER)
            if (CurrentPlayer==1) {
                CurrentPlayer=0
                MinBet=getIntPrefereces(BetActivity.SHARED_BET_PLAYER1)
            }
            else {
                CurrentPlayer=1
                MinBet=getIntPrefereces(BetActivity.SHARED_BET_PLAYER1)
            }
            setIntPreferences(SHARED_CURRENT_PLAYER,CurrentPlayer)
            CurrentMusic=getIntPrefereces(SHARED_CURRENT_MUSIC)
            MakeMPplayer(CurrentMusic)
        }

        if (CurrentPlayer==0) TextName!!.text="Игрок "+PlayerName1.toString()+" слушает"
        else  TextName!!.text="Игрок "+PlayerName2.toString()+" слушает"

        ListBtn!!.setOnClickListener {
            ListBtn!!.isEnabled=false
            var timer=Timer()
            timer.schedule(timerTask{
                if (time==-2) mpGame.start()
                time++
                if (time>-1){
                    TextTime!!.post {
                        TextTime!!.text = time.toString()
                    }
                }
                if (time==MinBet) {
                    timer.cancel()
                    mpGame.stop()
                    var intent= Intent(applicationContext,AnswerActivity::class.java)
                    startActivity(intent)
                }

            },1000,1000)

        }








    }



    private fun RandomCurrentMusic()
    {
        var Str=getStringPrefereces(SHARED_SUCCES_MUSIC)
        if (Str.length>1)
        {
            var parts=Str.split(",")
            var ok=0;
            var flag=0;
            while(flag==0){
                CurrentMusic=getRandomInt(MIN,MAX)
                for( part in parts) {
                    if (CurrentMusic==part.toInt())
                        ok++
                }
                if (ok==0) flag++;
            }
        }
        else CurrentMusic=getRandomInt(MIN,MAX)
        Str=Str+","+CurrentMusic.toString()
        setStringPreferences(SHARED_SUCCES_MUSIC,Str)
        MakeMPplayer(CurrentMusic)

    }

    fun MakeMPplayer(CurrentMus:Int){
        when(CurrentMus){
            1 -> mpGame=MediaPlayer.create(applicationContext,R.raw.elka_okolo_tebya)
            2 -> mpGame=MediaPlayer.create(applicationContext,R.raw.eminem_lose_yourself)
            3 -> mpGame=MediaPlayer.create(applicationContext,R.raw.leningrad_eksponat)
            4 -> mpGame=MediaPlayer.create(applicationContext,R.raw.iowa_ulibaysya)
            5 -> mpGame=MediaPlayer.create(applicationContext,R.raw.zveri_rayoni_kvartali)
            6 -> mpGame=MediaPlayer.create(applicationContext,R.raw.iuriy_nikulin_esli_b_ya_bil_sultan)
            7 -> mpGame=MediaPlayer.create(applicationContext,R.raw.abba_happy_new_year)
            8 -> mpGame=MediaPlayer.create(applicationContext,R.raw.beatles_yesterday)
        }
    }


    fun getRandomInt(min:Int, max:Int):Int {
        return ((Math.random() * (max - min)) + min).toInt();
    }

    private fun setStringPreferences(key:String,name:String) {
        val sh = getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putString(key, name)
        editor.apply()
    }

    private  fun getStringPrefereces(key:String):String{
        val sh=getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        return sh.getString(key,"0")
    }

    public fun setIntPreferences(key:String,bet:Int) {
        val sh = getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putInt(key, bet)
        editor.apply()
    }

    public  fun getIntPrefereces(key:String):Int{
        val sh=getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        return sh.getInt(key,0)
    }
}
