package eugene.com.listproductviewproj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Select

//import com.orm.SugarRecord

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ActiveAndroid.initialize(this);


        var position=intent.getIntExtra("position",1).toLong()

        var product= Product.queryById(position)

        //var product:Product = SugarRecord.findById(Product::class.java,1)

        var nameText=findViewById(R.id.name_text) as EditText
        var descriptionText=findViewById(R.id.desription_text) as EditText
        var img=findViewById(R.id.img_edit) as ImageView
        var priceText=findViewById(R.id.price_text) as EditText
        var saveBtn=findViewById(R.id.save_btn) as ImageView

        nameText.hint=product.name



        descriptionText.hint=product.description
        img.setImageResource(product.img)
        priceText.hint=product.price.toString() + "$"

        saveBtn.setOnClickListener{
            var name=nameText.text
            var desc=descriptionText.text
            var price=priceText.text

            if (name.length!=0){
                Product.queryUpdateName(name.toString(),position)
            }


           // if ((name.length!=0)&&(desc.length!=0)&&(price.length!=0)){



                //product.description=desc.toString()
                //product.price=price.toString().toInt()

            //}
        }

    }
}
