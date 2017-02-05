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


class Product():Model(){

    var name="noName"

    var description="noDescription"

    var textCategory = "Бытовая техника"

    var decriptionCategory = "Техника для дома"

    var img=R.drawable.product

    var price=0


    constructor(name:String,description:String,img:Int,price:Int):this(){
        this.name=name
        this.description=description
        this.img=img
        this.price=price
    }

    constructor(name:String,description:String,price:Int):this(){
        this.name=name
        this.description=description
        this.price=price
    }

    constructor(name:String,description:String,category:String,categoryDesc:String,img:Int,price:Int):this(){
        this.name=name
        this.description=description
        this.textCategory=category
        this.decriptionCategory=categoryDesc
        this.img=img
        this.price=price
    }

}