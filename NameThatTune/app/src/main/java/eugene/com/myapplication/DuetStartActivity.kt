package eugene.com.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class DuetStartActivity : AppCompatActivity() {

    companion object{
        public val SHARED_NAME_PLAYER1= "shared_name_player1"
        public val SHARED_NAME_PLAYER2= "shared_name_player2"
        public val SHARED_KEY="shared_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duet_start)

        var EditName1=findViewById(R.id.edit_name_player1) as EditText
        var EditName2=findViewById(R.id.edit_name_player2) as EditText
        var StartBtn=findViewById(R.id.start_duet_btn) as ImageView

        StartBtn.setOnClickListener {
            var NamePlayer1=EditName1.text.toString()
            var NamePlayer2=EditName2.text.toString()
            if ((NamePlayer1.length!=0)&&(NamePlayer2.length!=0))
            {
                setStringPreferences(SHARED_NAME_PLAYER1,NamePlayer1)
                setStringPreferences(SHARED_NAME_PLAYER2,NamePlayer2)
                var intent= Intent(applicationContext,BetActivity::class.java)
                startActivity(intent)
            }

        }

    }

    private fun setStringPreferences(key:String,name:String) {
        val sh = getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE)
        val editor = sh.edit()
        editor.putString(key, name)
        editor.apply()
    }

}
