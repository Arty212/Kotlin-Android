package eugene.com.listproductviewproj

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.annotation.Table
import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import com.activeandroid.query.Update




/**
 * Created by USER on 22.01.2017.
 */

@Table(name = "Products")
class Product():Model(){
//:SugarRecord(){
    //@Column(name="id",unique = true)
    //var id=0
    @Column(name="Name")
    var name="noName"
    @Column(name="Description")
    var description="noDescription"
    @Column(name="ImgID")
    var img=R.drawable.product
    @Column (name="Price")
    var price=0


    constructor(name:String,description:String,img:Int,price:Int):this(){
        this.name=name
        this.description=description
        this.img=img
        this.price=price
    }


    companion object {
        public fun queryNoteList(): List<Product> {
            val modelList:List<Product> = Select().from(Product::class.java).execute()
            return modelList
        }

        public fun queryById(id: Long): Product {
            val model:Product = Select().from(Product::class.java).where("Id = ?", id).executeSingle()
            return model;
        }

        public fun deleteById(id: Long): Product {
            val model:Product = Delete().from(Product::class.java).where("Id = ?", id).executeSingle()
            return model
        }

        public fun queryCount():Int{
                val modelList:List<Product> = Select().from(Product::class.java).execute()
                return modelList.size
        }

        public fun allDelete(){
            val modelList:List<Product> = Select().from(Product::class.java).execute()
            for (i in  1..modelList.size)
            {
                queryById(i.toLong())
            }
        }

        public fun queryUpdateName(value:String,id:Long)
        {
            Update(Product::class.java)
                    .set("Name = "+value)
                    .where("Id = ?", id)
                    .execute()
        }
    }



    override fun equals(other: Any?): Boolean {
        if (other !is Product) {
            return false
        }

        val id = other.getId()
        return id == this.getId()
    }
}