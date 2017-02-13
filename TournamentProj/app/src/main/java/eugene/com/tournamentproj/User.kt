package eugene.com.utilproject

import com.google.gson.annotations.SerializedName

/**
 * Created by USER on 09.02.2017.
 */

class User() {
    //@SerializedName
    public var id:Int =0
    public var point:Int = 0
    public var position = 0
    public var name="noName"

    constructor(id:Int,position:Int,name:String,point:Int):this(){
        this.id=id
        this.position=position
        this.point=point
        this.name=name
    }



}