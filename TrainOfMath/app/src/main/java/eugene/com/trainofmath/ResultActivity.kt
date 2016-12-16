package eugene.com.trainofmath

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    private val TRUE_ANSER="true_anser"
    private val CURRENT_ANSER="current_anser"
    private val LEVEL="level"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var intent=intent

        var answer=intent.getIntExtra(CURRENT_ANSER,0)
        var trueAnswer=intent.getIntExtra(TRUE_ANSER,0)
        var level=intent.getIntExtra(LEVEL,0)

        var TextTotal=findViewById(R.id.total_anser) as TextView
        var Answer=findViewById(R.id.your_anser) as TextView
        var TrueAnswer=findViewById(R.id.true_anser) as TextView

        if (answer==trueAnswer) TextTotal.text=getString(R.string.you_right)
        else TextTotal.text=getString(R.string.your_false)

        Answer.text="Ваш ответ:  " + answer.toString()
        TrueAnswer.text="Правильный ответ:  " + trueAnswer.toString()

        var TryBtn=findViewById(R.id.repeat_btn) as Button
        var ChooseBtn=findViewById(R.id.choose_level) as Button

        TryBtn.setOnClickListener {
            var intent = Intent(applicationContext,TaskActivity::class.java)
            intent.putExtra(LEVEL,level)
            startActivity(intent)
        }

        ChooseBtn.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }



    }
}
