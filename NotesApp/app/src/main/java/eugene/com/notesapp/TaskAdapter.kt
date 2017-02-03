package eugene.com.notesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

/**
 * Created by USER on 02.02.2017.
 */
class TaskAdapter(data: ArrayList<Task>): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    public var mData = data

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameText.text = mData[position].nameText
        holder.descriptionText.text=mData[position].descriptionText
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.task_item,null,false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var view:View;
        var deleteBtn:Button
        var nameText:TextView
        var descriptionText:TextView

        init{
            view=itemView
            deleteBtn=view.findViewById(R.id.delete_btn) as Button
            nameText=view.findViewById(R.id.name_text) as TextView
            descriptionText=view.findViewById(R.id.description_text) as TextView

            deleteBtn.setOnClickListener{
                Toast.makeText(view.context,"Delete",Toast.LENGTH_SHORT).show()
            }
        }
    }



}