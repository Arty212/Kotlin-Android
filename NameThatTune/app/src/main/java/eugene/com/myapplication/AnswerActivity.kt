package eugene.com.myapplication

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class AnswerActivity : AppCompatActivity() {

    companion object{
        public var SHARED_VAR1="shared_var3"
        public var SHARED_VAR2="shared_var2"
        public var SHARED_VAR3="shared_var3"
        public var SHARED_ANSWER_STATUS="shared_answer_status"
        public var Var1_Key="key_var1"
        public var Var2_Key="key_var2"
        public var Var3_Key="key_var3"
        public var Flag_Key="key_flag"

    }

    private var Btn1Player: Button? = null
    private var Btn2Player: Button? = null
    private var Btn3Player: Button? = null
    private var Btn4Player: Button? = null

    private var TextNamePlayer:TextView? = null

    val MIN=0
    val MAX=9

    var randBtn1=0
    var randBtn2=0
    var randBtn3=0
    var randBtn4=0

    var VarMusic1=0;
    var VarMusic2=0;
    var VarMusic3=0;

    //var Flag=0;

    var status=0

    var CurrentMusic=0

    var CurrentPlayer=0
    var StatusAnswer=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        Btn1Player=findViewById(R.id.answer_player1_btn1) as Button
        Btn2Player=findViewById(R.id.answer_player1_btn2) as Button
        Btn3Player=findViewById(R.id.answer_player1_btn3) as Button
        Btn4Player=findViewById(R.id.answer_player1_btn4) as Button
        TextNamePlayer=findViewById(R.id.text_current_player) as TextView

        CurrentPlayer=getIntPrefereces(GameActivity.SHARED_CURRENT_PLAYER)
        if (CurrentPlayer==0) TextNamePlayer!!.text="Игрок "+getStringPrefereces(DuetStartActivity.SHARED_NAME_PLAYER1)+" отвечает"
        else  TextNamePlayer!!.text="Игрок "+getStringPrefereces(DuetStartActivity.SHARED_NAME_PLAYER2)+" отвечает"

        status=getIntPrefereces(GameActivity.SHARED_STATUS)

        if (status==0)
        {
            //if (savedInstanceState != null) {
               // Flag=savedInstanceState.getInt(Flag_Key)
              //  if (Flag==0)
                    RandomVersusMusic()
               // else{
                //    VarMusic1=savedInstanceState.getInt(Var1_Key)
               //     VarMusic2=savedInstanceState.getInt(Var2_Key)
               //     VarMusic3=savedInstanceState.getInt(Var3_Key)
               /// }
           // }
        }
        else if (status==1){
            VarMusic1=getIntPrefereces(SHARED_VAR1)
            VarMusic2=getIntPrefereces(SHARED_VAR2)
            VarMusic3=getIntPrefereces(SHARED_VAR3)
        }
        InitButton()

        Btn1Player!!.setOnClickListener {
            if (randBtn1==1) StatusAnswer=1
            setIntPreferences(SHARED_ANSWER_STATUS,StatusAnswer)
            var intent= Intent(applicationContext,ResultActivity::class.java)
            startActivity(intent)
        }

        Btn2Player!!.setOnClickListener {
            if (randBtn1==2) StatusAnswer=1
            setIntPreferences(SHARED_ANSWER_STATUS,StatusAnswer)
            var intent= Intent(applicationContext,ResultActivity::class.java)
            startActivity(intent)
        }

        Btn3Player!!.setOnClickListener {
            if (randBtn1==3) StatusAnswer=1
            setIntPreferences(SHARED_ANSWER_STATUS,StatusAnswer)
            var intent= Intent(applicationContext,ResultActivity::class.java)
            startActivity(intent)}

        Btn4Player!!.setOnClickListener {
            if (randBtn1==4) StatusAnswer=1
            setIntPreferences(SHARED_ANSWER_STATUS,StatusAnswer)
            var intent= Intent(applicationContext,ResultActivity::class.java)
            startActivity(intent)}

    }

    private fun InitButton(){
        var flag2=0
        var flag3=0
        var flag4=0

        CurrentMusic=getIntPrefereces(GameActivity.SHARED_CURRENT_MUSIC)

        randBtn1=getRandomInt(1,5)
        ChooseBtn(randBtn1,CurrentMusic)

        while(flag2==0){
            randBtn2=getRandomInt(1,5)
            if (randBtn2!=randBtn1)
                flag2=1
        }
        ChooseBtn(randBtn2,VarMusic1)

        while(flag3==0){
            randBtn3=getRandomInt(1,5)
            if ((randBtn3!=randBtn1)&&(randBtn3!=randBtn2))
                    flag3=1
        }
        ChooseBtn(randBtn3,VarMusic2)

        while(flag4==0){
            randBtn4=getRandomInt(1,5)
            if ((randBtn4!=randBtn1)&&(randBtn4!=randBtn2)&&(randBtn4!=randBtn3))
                        flag4=1
        }
        ChooseBtn(randBtn4,VarMusic3)
    }

    private fun ChooseBtn(a:Int,variant:Int)
    {
        when(a){
            1-> Btn1Player!!.text=getNameMusic(variant)
            2-> Btn2Player!!.text=getNameMusic(variant)
            3-> Btn3Player!!.text=getNameMusic(variant)
            4-> Btn4Player!!.text=getNameMusic(variant)
        }
    }

    private fun getNameMusic(variant:Int):String
    {
        when (variant) {
            1 -> {return "Ёлка - Около тебя"}
            2 -> {return "Eminem - Lose Yourself"}
            3 -> {return "Ленинград - Экспонат"}
            4 -> {return "IOWA - Улыбайся"}
            5 -> {return "Звери - Районы"}
            6 -> {return "Никулин - Если б я был султан.."}
            7 -> {return "ABBA - Happy New Year"}
            8 -> {return "Beatles - Yesterday"}
            9 -> {return "Katy Perry - Birthday"}
            10 -> {return "Adele - Hello"}
            11 -> {return "Led Zeppelin - Kashmir"}
            12 -> {return "Queen - We Are the Champions"}
            13 -> {return "The Rolling Stones - Angie"}
            14 -> {return "Scorpions - Still Loving You"}
            15 -> {return "The Zombies - She's Not There"}
            16 -> {return "ДДТ - Что такое осень"}
            17 -> {return "Кипелов - Я свободен"}
            18 -> {return "Кино - Хочу перемен"}
            19 -> {return "Эдуард Хиль - Песня без слов"}
        }
        return "0"
    }

    private fun RandomVersusMusic()
    {
        var flag1=0
        var flag2=0
        var flag3=0
        while(flag1==0){
            VarMusic1=getRandomInt(1,20)
            if (VarMusic1!=CurrentMusic)
                flag1=1;
        }

        while(flag2==0){
            VarMusic2=getRandomInt(1,20)
            if (VarMusic2!=CurrentMusic)
                if (VarMusic2!=VarMusic1)
                    flag2=1;
        }

        while(flag3==0){
            VarMusic3=getRandomInt(1,20)
            if (VarMusic3!=CurrentMusic)
                if (VarMusic3!=VarMusic1)
                    if (VarMusic3!=VarMusic2)
                        flag3=1;
        }
        setIntPreferences(SHARED_VAR1,VarMusic1)
        setIntPreferences(SHARED_VAR2,VarMusic2)
        setIntPreferences(SHARED_VAR3,VarMusic3)
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

   /* override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if (outState != null) {
            outState.putInt(Var1_Key,VarMusic1)
            outState.putInt(Var2_Key,VarMusic2)
            outState.putInt(Var3_Key,VarMusic3)
            outState.putInt(Flag_Key,Flag+1)
        }
    }*/

}
