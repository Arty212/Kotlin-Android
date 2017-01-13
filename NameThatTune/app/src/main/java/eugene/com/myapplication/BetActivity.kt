package eugene.com.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class BetActivity : AppCompatActivity() {

    companion object {
        public val SHARED_NAME_PLAYER1 = "shared_name_player1"
        public val SHARED_NAME_PLAYER2 = "shared_name_player2"
        public val SHARED_BET_PLAYER1 = "shared_bet_player1"
        public val SHARED_POINT_PLAYER1 = "shared_point_player1"
        public val SHARED_POINT_PLAYER2 = "shared_point_player2"
        public val SHARED_BET_PLAYER2 = "shared_bet_player2"
    }

    var k=0;

    var NamePlayer1=""
    var NamePlayer2=""

    var currentPlayer=""
    var current_bet=10

    var readyPl1=0
    var readyPl2=0

    private var BetCurrentPlayer:TextView? =null
    private var CurrentBet:TextView? =null

    private var ReadyPlayer1:TextView? = null
    private var ReadyPlayer2:TextView? = null
    private var ReadyImgPlayer1:ImageView? = null
    private var ReadyImgPlayer2:ImageView? = null

    private var TextPoint:TextView?=null

    private var Sec3Btn:Button?=null
    private var Sec5Btn:Button?=null
    private var Sec7Btn:Button?=null
    private var Sec9Btn:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bet)

        var round=getIntPrefereces(ResultActivity.SHARED_ROUND)
        var actionBar = supportActionBar
        actionBar!!.setSubtitle("Раунд " + round+"/5")

        NamePlayer1=getStringPrefereces(SHARED_NAME_PLAYER1)
        NamePlayer2=getStringPrefereces(SHARED_NAME_PLAYER2)

        ReadyPlayer1=findViewById(R.id.ready_player1) as TextView
        ReadyPlayer2=findViewById(R.id.ready_player2) as TextView
        ReadyImgPlayer1=findViewById(R.id.ready_img_player1) as ImageView
        ReadyImgPlayer2=findViewById(R.id.ready_img_player2) as ImageView

        TextPoint=findViewById(R.id.text_point) as TextView
        var PointPlayer1=getIntPrefereces(SHARED_POINT_PLAYER1)
        var PointPlayer2=getIntPrefereces(SHARED_POINT_PLAYER2)
        TextPoint!!.text=PointPlayer1.toString()+":"+PointPlayer2.toString()

        ReadyPlayer1!!.text=NamePlayer1
        ReadyPlayer2!!.text=NamePlayer2
        ReadyImgPlayer1!!.setBackgroundResource(R.drawable.not_ready)
        ReadyImgPlayer2!!.setBackgroundResource(R.drawable.not_ready)

        var DoBetBtn=findViewById(R.id.do_bet) as Button
        var StartGame=findViewById(R.id.go_game) as Button

        Sec3Btn=findViewById(R.id.sec3_btn) as Button
        Sec5Btn=findViewById(R.id.sec5_btn) as Button
        Sec7Btn=findViewById(R.id.sec7_btn) as Button
        Sec9Btn=findViewById(R.id.sec9_btn) as Button

        BetCurrentPlayer=findViewById(R.id.bet_name_player) as TextView
        CurrentBet=findViewById(R.id.current_bet) as TextView

        if (k==0)
            Player1()
        else
            Player2()

        Sec3Btn!!.setOnClickListener {
                current_bet = 3
                CurrentBet!!.text = current_bet.toString()
        }

        Sec5Btn!!.setOnClickListener {
                current_bet = 5
                CurrentBet!!.text = current_bet.toString()
        }

        Sec7Btn!!.setOnClickListener {
                current_bet=7
                CurrentBet!!.text=current_bet.toString()
        }

        Sec9Btn!!.setOnClickListener {
            if (current_bet==9){
                current_bet=9
                CurrentBet!!.text=current_bet.toString()
            }
        }

        DoBetBtn.setOnClickListener {
            if (current_bet!=0){
                if (current_bet<=9) Sec9Btn!!.isEnabled=false
                if (current_bet<=7) Sec7Btn!!.isEnabled=false
                if (current_bet<=5) Sec5Btn!!.isEnabled=false
                if (current_bet<=3) Sec3Btn!!.isEnabled=false
                setIntPreferences(currentPlayer, current_bet)
                if (k == 1){
                    if (current_bet==3) {
                        readyPl2=1
                        ReadyImgPlayer2!!.setBackgroundResource(R.drawable.ready)
                        if (getIntPrefereces(SHARED_BET_PLAYER1)==9) Sec9Btn!!.isEnabled=true;
                    }
                    if (readyPl1 != 1) Player1();
                }
                else {
                    if (current_bet==3) {
                        if (getIntPrefereces(SHARED_BET_PLAYER2)==9) Sec9Btn!!.isEnabled=true;
                        readyPl1=1
                        ReadyImgPlayer1!!.setBackgroundResource(R.drawable.ready)
                    }
                    if (readyPl2 != 1) Player2()
                }
            }
        }

        StartGame.setOnClickListener {
            if (k==0) {
                if (current_bet != 0) {
                    if (current_bet<=9) Sec9Btn!!.isEnabled=false
                    if (current_bet<=7) Sec7Btn!!.isEnabled=false
                    if (current_bet<=5) Sec5Btn!!.isEnabled=false
                    if (current_bet<=3) Sec3Btn!!.isEnabled=false
                    readyPl1 = 1
                    ReadyImgPlayer1!!.setBackgroundResource(R.drawable.ready)
                    setIntPreferences(currentPlayer, current_bet)
                    if (readyPl2 == 0) Player2()
                    else {
                        var intent = Intent(applicationContext, GameActivity::class.java)
                        MainActivity.mpBackground.stop()
                        startActivity(intent)
                    }
                }
            }
            else if (k==1){
                    if (current_bet !=0) {
                        if (current_bet<=9) Sec9Btn!!.isEnabled=false
                        if (current_bet<=7) Sec7Btn!!.isEnabled=false
                        if (current_bet<=5) Sec5Btn!!.isEnabled=false
                        if (current_bet<=3) Sec3Btn!!.isEnabled=false
                        readyPl2 = 1
                        ReadyImgPlayer2!!.setBackgroundResource(R.drawable.ready)
                        setIntPreferences(currentPlayer, current_bet)
                        if (readyPl1 == 0) Player1()
                        else {
                            var intent = Intent(applicationContext, GameActivity::class.java)
                            MainActivity.mpBackground.stop()
                            startActivity(intent)
                        }
                    }
                }
        }
    }

    public fun setIntPreferences(key:String,bet:Int) {
        val sh = getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putInt(key, bet)
        editor.apply()
    }

    public  fun getIntPrefereces(key:String):Int{
        var def=0;
        if ((key==SHARED_BET_PLAYER1)||(key== SHARED_BET_PLAYER2))
            def=0
        val sh=getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        return sh.getInt(key,def)
    }

    public fun getStringPrefereces(key:String):String{
        val sh=getSharedPreferences(DuetStartActivity.SHARED_KEY, Context.MODE_PRIVATE)
        return sh.getString(key,"0")
    }

    private fun Player1() {
        k=0
        BetCurrentPlayer!!.text = "Игрок " + NamePlayer1 + " делает ставку"
        currentPlayer = "shared_bet_player1"
        current_bet=getIntPrefereces(SHARED_BET_PLAYER1)
        CurrentBet!!.text=current_bet.toString()
    }

    private fun Player2(){
        k=1
            BetCurrentPlayer!!.text="Игрок "+NamePlayer2 + " делает ставку"
            currentPlayer="shared_bet_player2"
            current_bet=getIntPrefereces(SHARED_BET_PLAYER2)
        CurrentBet!!.text=current_bet.toString()
    }
}
