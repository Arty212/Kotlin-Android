package eugene.com.utilproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

/**
 * Created by USER on 13.02.2017.
 */
class DbHelper private constructor(context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DB_CREATE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        private var dbHelper: DbHelper? = null
        private var db: SQLiteDatabase? = null

        private val DB_NAME = "TurnirDB"
        private val DB_TABLE_TURNIR = "product"
        private val DB_VERSION = 1
        public val DB_COLUMN_POSITION = "position"
        public val DB_COLUMN_ID = "id"
        public val DB_COLUMN_NAME = "name"
        public val DB_COLUMN_POINT = "point"
        private val DB_PRIMARY_KEY="_id"

        private val DB_CREATE_QUERY = "CREATE TABLE " +
                DB_TABLE_TURNIR + " ( " + DB_PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+ DB_COLUMN_ID + " INTEGER," +
                " " + DB_COLUMN_NAME + " TEXT NOT NULL," +
                " " + DB_COLUMN_POSITION + " INTEGER," +
                " " + DB_COLUMN_POINT + " INTEGER)"

        public fun initDbHelper(context: Context) {
            if (dbHelper == null) {
                dbHelper = DbHelper(context)
                db = dbHelper!!.writableDatabase
            }
        }

        public fun addUsers(arr: ArrayList<User>) {
            for (user in arr) {
                var cv = ContentValues()
                cv.put(DB_COLUMN_NAME, user.name)
                cv.put(DB_COLUMN_ID, user.id)
                cv.put(DB_COLUMN_POINT, user.point)
                cv.put(DB_COLUMN_POSITION, user.position)
                db!!.insert(DB_TABLE_TURNIR, null, cv)
            }
        }

        public fun getUsers():ArrayList<User> {
            var result=ArrayList<User>()

            var resultQuery=db!!.query(DB_TABLE_TURNIR,null,null,null,null,null,null)

            while(resultQuery.moveToNext())
                result.add(User(
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_ID)),
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_POSITION)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_POINT))
                ))
            return result
        }

        public fun DeleteAll(){

            db!!.delete(DB_TABLE_TURNIR,null,null)
        }
        public fun Delete(){

            db!!.delete(DB_TABLE_TURNIR,"_id=7",null)
        }

    }

}