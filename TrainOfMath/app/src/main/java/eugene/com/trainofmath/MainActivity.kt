package eugene.com.trainofmath

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private val LEVEL="level"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Button1=findViewById(R.id.level1_btn) as Button
        var Button2=findViewById(R.id.level2_btn) as Button
        var Button3=findViewById(R.id.level3_btn) as Button

        Button1.setOnClickListener {
            var intent = Intent(applicationContext,TaskActivity::class.java)
            intent.putExtra(LEVEL,1);
            startActivity(intent)
        }

        Button2.setOnClickListener {
            var intent = Intent(applicationContext,TaskActivity::class.java)
            intent.putExtra(LEVEL,2);
            startActivity(intent)
        }

        Button3.setOnClickListener {
            var intent = Intent(applicationContext,TaskActivity::class.java)
            intent.putExtra(LEVEL,3);
            startActivity(intent)
        }
    }
}
