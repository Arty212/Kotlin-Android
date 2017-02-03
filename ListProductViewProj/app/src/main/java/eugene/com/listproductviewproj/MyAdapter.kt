package eugene.com.listproductviewproj

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ViewUtils
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * Created by USER on 22.01.2017.
 */
class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var data:Array<Product> = arrayOf()

    private var onProductClickAction: ((Int) -> Unit)? = null

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(position: Int): Product {
        return data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder,position:Int)
    {
        holder.nameText.text = data[position].name
        holder.descriptionText.text = data[position].description.toString()
        holder.img.setImageResource(data[position].img)
        holder.priceText.text=data[position].price.toString()+"$"
    }


    inner class MyViewHolder(var v:View):RecyclerView.ViewHolder(v)
    {
        var view: View =v
        var nameText: TextView
        var descriptionText: TextView
        var img: ImageView
        var editBtn: ImageButton
        var priceText: TextView

        init {
            nameText = view.findViewById(R.id.name_text) as TextView
            descriptionText = view.findViewById(R.id.desription_text) as TextView
            img = view.findViewById(R.id.img) as ImageView
            priceText=view.findViewById(R.id.price_text) as TextView
            editBtn=view.findViewById(R.id.edit_btn) as ImageButton

            if (onProductClickAction!=null)
                editBtn.setOnClickListener {
                    onProductClickAction?.invoke(adapterPosition+1)
                    }
        }
    }

    fun setOnProductClickAction(action:((Int)->Unit)){
        this.onProductClickAction = action
    }
}