package eugene.com.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

/**
 * Created by USER on 15.12.2016.
 */
class ResultActivity : AppCompatActivity() {

    public val INTENT_KEY = "intent_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var RepeatBtn=findViewById(R.id.restart_btn) as Button

        RepeatBtn.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}