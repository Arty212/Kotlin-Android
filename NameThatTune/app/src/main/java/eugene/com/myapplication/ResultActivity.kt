package eugene.com.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    companion object{
        public var SHARED_ROUND="shared_round"
    }

    var round=1
    var status=0
    var CurrentPlayer=0
    var StatusAnswer=0

    var TextRound:TextView?=null
    var TextPoint:TextView?=null
    var ImgResult:ImageView?=null
    var TextResult:TextView?=null
    var TextName:TextView?=null
    var BtnNext:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        TextRound=findViewById(R.id.text_number_round) as TextView
        TextPoint=findViewById(R.id.text_point) as TextView
        TextResult=findViewById(R.id.text_result) as TextView
        TextName=findViewById(R.id.player_name) as TextView
        ImgResult=findViewById(R.id.img_result) as ImageView
        BtnNext=findViewById(R.id.next_btn) as Button

        var intent:Intent?=null

        round=getIntPrefereces(SHARED_ROUND)

        TextRound!!.text="Раунд "+round.toString()+"/"+"5"

        TextPoint!!.text=getIntPrefereces(BetActivity.SHARED_POINT_PLAYER1).toString()+":"+getIntPrefereces(BetActivity.SHARED_POINT_PLAYER2).toString()

        CurrentPlayer=getIntPrefereces(GameActivity.SHARED_CURRENT_PLAYER)
        StatusAnswer=getIntPrefereces(AnswerActivity.SHARED_ANSWER_STATUS)
        status=getIntPrefereces(GameActivity.SHARED_STATUS)

        if (CurrentPlayer==0) TextName!!.text=getStringPrefereces(DuetStartActivity.SHARED_NAME_PLAYER1)
        else  TextName!!.text=getStringPrefereces(DuetStartActivity.SHARED_NAME_PLAYER2)


        if (StatusAnswer==1) {
            ImgResult!!.setImageDrawable((applicationContext.resources.getDrawable(R.drawable.smile)))
            TextResult!!.text="Вы выиграли раунд"
            if (CurrentPlayer==0) setIntPreferences(BetActivity.SHARED_POINT_PLAYER1,getIntPrefereces(BetActivity.SHARED_POINT_PLAYER1)+1)
            else  setIntPreferences(BetActivity.SHARED_POINT_PLAYER2,getIntPrefereces(BetActivity.SHARED_POINT_PLAYER2)+1)
            setIntPreferences(SHARED_ROUND,round+1)
            UpdateAll()
            intent= Intent(applicationContext,BetActivity::class.java)
        }
        else
            if (status==0){
                ImgResult!!.setImageDrawable((applicationContext.resources.getDrawable(R.drawable.sad)))
                TextResult!!.text="Вы не угадали. Ход переходит сопернику!"
                status=1;
                setIntPreferences(GameActivity.SHARED_STATUS,status)
                intent= Intent(applicationContext,GameActivity::class.java)
            }
            else {
                ImgResult!!.setImageDrawable((applicationContext.resources.getDrawable(R.drawable.frightened)))
                TextResult!!.text="Никто не угадал"
                UpdateAll()
                setIntPreferences(SHARED_ROUND,round+1)
                intent= Intent(applicationContext,BetActivity::class.java)

            }
        BtnNext!!.setOnClickListener {
            if (round==5) {
                intent = Intent(applicationContext,FinishActivity::class.java)
                startActivity(intent)
            }
            startActivity(intent)
        }


    }

    private fun UpdateAll(){
        setIntPreferences(GameActivity.SHARED_CURRENT_PLAYER,0)
        setIntPreferences(GameActivity.SHARED_STATUS,0)
        setIntPreferences(GameActivity.SHARED_CURRENT_MUSIC,0)
        setIntPreferences(AnswerActivity.SHARED_ANSWER_STATUS,0)
        setIntPreferences(AnswerActivity.SHARED_VAR1,0)
        setIntPreferences(AnswerActivity.SHARED_VAR2,0)
        setIntPreferences(AnswerActivity.SHARED_VAR2,0)
        setIntPreferences(BetActivity.SHARED_BET_PLAYER1,9)
        setIntPreferences(BetActivity.SHARED_BET_PLAYER2,9)
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
        var def=0
        if (key== SHARED_ROUND) def=1
        val sh=getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        return sh.getInt(key,def)
    }
}
