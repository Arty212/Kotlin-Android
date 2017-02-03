package eugene.com.notesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

/**
 * Created by USER on 02.02.2017.
 */
class DbHelper private constructor(context:Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DB_CREATE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

    }

    companion object{
        private var dbHelper:DbHelper? = null
        private var db: SQLiteDatabase? = null


        private val DB_NAME="test"
        private val DB_VERSION = 1
        private val DB_COLUMN_NAME="name"
        private val DB_COLUMN_DESCRIPTION="description"
        private val DB_TABLE_TASK="task"
        private val DB_CREATE_QUERY = "CREATE TABLE "+
                DB_TABLE_TASK +" ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "+ DB_COLUMN_NAME+" TEXT NOT NULL, "+ DB_COLUMN_DESCRIPTION+" TEXT )"

        public fun initDbHelper(context: Context)
        {
            if (dbHelper == null){
                dbHelper =DbHelper(context)
                db= dbHelper!!.writableDatabase
            }
        }

        public fun addTask(arr:ArrayList<Task>){
            for (task in arr){
                var cv=ContentValues()
                cv.put(DB_COLUMN_NAME, task.nameText)
                cv.put(DB_COLUMN_DESCRIPTION,task.descriptionText)
                db!!.insert(DB_TABLE_TASK,null,cv)
            }
        }

        public fun getTasks():ArrayList<Task> {

            var result = ArrayList<Task>()

            var resultQuery = db!!.query(DB_TABLE_TASK, null,null,null,null,null,null)
            while(resultQuery.moveToNext())
                result.add(Task(
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_DESCRIPTION))
                ))
            return result
        }

        public fun closeDbHelper(){
            db!!.close()
            dbHelper!!.close()
        }

    }



}