package eugene.com.listproductviewproj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.activeandroid.ActiveAndroid
import com.activeandroid.query.Select
import android.content.ContentValues
import android.content.Intent


class EditActivity : AppCompatActivity() {
    var product:Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        var position=intent.getIntExtra("position",1)

        product=DbHelper.getProduct(position)

        var nameText=findViewById(R.id.name_text) as EditText
        var descriptionText=findViewById(R.id.desription_text) as EditText
        var img=findViewById(R.id.img_edit) as ImageView
        var priceText=findViewById(R.id.price_text) as EditText
        var saveBtn=findViewById(R.id.save_btn) as ImageView
        var categoryText=findViewById(R.id.category_text_edit) as TextView
        var categoryDesc=findViewById(R.id.category_desc_edit) as TextView

        nameText.hint=product!!.name
        descriptionText.hint=product!!.description
        categoryText.hint=product!!.textCategory
        categoryDesc.hint=product!!.decriptionCategory
        img.setImageResource(product!!.img)
        priceText.hint=product!!.price.toString() + "$"

        saveBtn.setOnClickListener{
            var name=nameText.text.toString()
            var desc=descriptionText.text.toString()
            var price=priceText.text.toString().toInt()
            var categor=categoryText.text.toString()
            var catDesc = categoryDesc.text.toString()

            var cv=ContentValues()
            if (name.length!=0){
                cv.put(DbHelper.DB_COLUMN_NAME,name)
            }

            if (desc.length!=0){
                cv.put(DbHelper.DB_COLUMN_DESCRIPTION,desc)
            }

            if (price!=0){
                cv.put(DbHelper.DB_COLUMN_PRICE,price)
            }

            if (categor.length!=0){
                cv.put(DbHelper.DB_COLUMN_CATEGORY,categor)
            }

            if (catDesc.length!=0){
                cv.put(DbHelper.DB_COLUMN_CATEGORY_DESC,catDesc)
            }

            DbHelper.updateDate(position,cv)

            var intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
