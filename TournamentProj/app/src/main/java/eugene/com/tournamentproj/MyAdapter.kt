package eugene.com.utilproject

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import eugene.com.tournamentproj.R
import java.util.*

/**
 * Created by USER on 09.02.2017.
 */
class MyAdapter (): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    var data:ArrayList<User>? =null

    public fun add(arr:ArrayList<User>){
        data=arr
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder,position:Int)
    {
        var pos = data!![position].position
        when(pos)
        {
            1-> {
                holder.img.setImageResource(R.drawable.image1)
            }
            2-> {
                holder.img.setImageResource(R.drawable.image2)
            }
            3-> {
                holder.img.setImageResource(R.drawable.image3)
            }
            else -> {holder.img.setImageResource(R.drawable.base)}
        }
        holder.nameText.text = data!![position].name
        holder.pointText.text = data!![position].point.toString()
        holder.positionText.text = data!![position].position.toString()

    }


    class MyViewHolder(var v: View):RecyclerView.ViewHolder(v) {
        var view: View =v
        var positionText: TextView
        var pointText: TextView
        var nameText: TextView
        var img:ImageView

        init {
            positionText=view.findViewById(R.id.position_text) as TextView
            pointText=view.findViewById(R.id.point_text) as TextView
            nameText=view.findViewById(R.id.name_text) as TextView
            img=view.findViewById(R.id.img_item) as ImageView
        }

    }

}