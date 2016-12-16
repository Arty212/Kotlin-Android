package eugene.com.trainofmath

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class TaskActivity : AppCompatActivity() {

    private val LEVEL="level"
    private val MAX=100
    private val MIN=0
    private val TRUE_ANSER="true_anser"
    private val CURRENT_ANSER="current_anser"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        var TaskText=findViewById(R.id.text_task) as TextView
        var Button=findViewById(R.id.check_btn) as Button
        var EditText=findViewById(R.id.edit_answer) as EditText

        var TrueAnswer=0
        val intent = intent

        var level = intent.getIntExtra(LEVEL,1)
        TaskText.text=level.toString()
        var a=getRandomInt(MAX,MIN)
        var b=getRandomInt(MAX,MIN)
        when (level){
                1->{
                    TaskText.text=a.toString() + " + " + b.toString() + " =  ?"
                    TrueAnswer=a+b
                }

                2->{
                    TaskText.text=
                            a.toString() + " * " + b.toString() + " - "+" ("+a.toString() + " - "+b.toString()+")"+ " =  ?"
                    TrueAnswer=a*b-(a-b)
                }

                3->{
                    TaskText.text= a.toString() + " * " + b.toString() + " - "+(a-5).toString() + "*"+"("+a.toString() + " - "+b.toString()+")"+ " =  ?"
                    TrueAnswer=a*b-(a-5)*(a-b)
                }
        }

        Button.setOnClickListener {
            var anser=EditText.text.toString().toInt()
            var intent = Intent(applicationContext,ResultActivity::class.java)
            intent.putExtra(TRUE_ANSER,TrueAnswer)
            intent.putExtra(CURRENT_ANSER,anser)
            intent.putExtra(LEVEL,level)
            startActivity(intent)
        }
    }

    fun getRandomInt(min:Int, max:Int):Int {
        return ((Math.random() * (max - min)) + min).toInt();
    }
}


