package eugene.com.quadraticequationmobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
//import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var SolBtn=findViewById(R.id.SolBtn) as Button
        var answerText=findViewById(R.id.answer_text) as TextView
       // var h=k.toInt()
        SolBtn.setOnClickListener {
            var A=findViewById(R.id.edit_a) as EditText
            var B=findViewById(R.id.edit_b) as EditText
            var C=findViewById(R.id.edit_c) as EditText

            var a=A.text.toString().toDouble()
            var b=B.text.toString().toDouble()
            var c=C.text.toString().toDouble()
            var D = b * b - a * c * 4
            if (D<0) answerText.text="Нет решения"
            else {
                var x1 = (-b.toDouble() + sqrt(D.toDouble())) / (2 * a).toDouble()
                var x2 = (-b.toDouble() - sqrt(D.toDouble())) / (2 * a).toDouble()
                answerText.text = "Ответ: " + "x1= " + x1.toString() + "  x2= " + x2.toString()
            }
        }

    }
}
