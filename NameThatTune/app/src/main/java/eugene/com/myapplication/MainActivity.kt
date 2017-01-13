package eugene.com.myapplication

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    companion object{
        public var mpBackground = MediaPlayer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var actionBar = supportActionBar
        actionBar!!.setSubtitle("Угадай мелодию")

        mpBackground=MediaPlayer.create(applicationContext,R.raw.backgroud_music)
        mpBackground.start()
        mpBackground.setOnCompletionListener{ mpBackground.start()}

        setIntPreferences(GameActivity.SHARED_CURRENT_PLAYER,0)
        setIntPreferences(GameActivity.SHARED_STATUS,0)
        setIntPreferences(GameActivity.SHARED_CURRENT_MUSIC,0)
        setIntPreferences(AnswerActivity.SHARED_ANSWER_STATUS,0)
        setIntPreferences(AnswerActivity.SHARED_VAR1,0)
        setIntPreferences(AnswerActivity.SHARED_VAR2,0)
        setIntPreferences(AnswerActivity.SHARED_VAR2,0)
        setIntPreferences(BetActivity.SHARED_BET_PLAYER1,9)
        setIntPreferences(BetActivity.SHARED_BET_PLAYER2,9)
        setIntPreferences(ResultActivity.SHARED_ROUND,1)
        setStringPreferences(DuetStartActivity.SHARED_NAME_PLAYER1,"")
        setStringPreferences(DuetStartActivity.SHARED_NAME_PLAYER2,"")
        setIntPreferences(BetActivity.SHARED_POINT_PLAYER1,0)
        setIntPreferences(BetActivity.SHARED_POINT_PLAYER2,0)
        setStringPreferences(GameActivity.SHARED_SUCCES_MUSIC,"0")

        var BattleBtn=findViewById(R.id.duet_btn) as ImageView

        BattleBtn.setOnClickListener {
            var intent= Intent(applicationContext,DuetStartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setStringPreferences(key:String,name:String) {
        val sh = getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putString(key, name)
        editor.apply()
    }

    public fun setIntPreferences(key:String,bet:Int) {
        val sh = getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putInt(key, bet)
        editor.apply()
    }

    override fun onStop() {
        super.onStop()
        mpBackground.stop();
    }
}
