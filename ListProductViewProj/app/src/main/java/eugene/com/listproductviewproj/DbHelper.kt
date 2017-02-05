package eugene.com.listproductviewproj

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.activeandroid.query.Select
import java.util.*

/**
 * Created by USER on 03.02.2017.
 */
class DbHelper private constructor(context: Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DB_CREATE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

    }

    companion object{
        private var dbHelper:DbHelper? =null
        private var db:SQLiteDatabase?=null

        private val DB_NAME="ListProduct_db"
        private val DB_TABLE_PRODUCT="product"
        private val DB_VERSION=1
        public val DB_COLUMN_NAME="name"
        public val DB_COLUMN_DESCRIPTION="description"
        public val DB_COLUMN_CATEGORY="category"
        public val DB_COLUMN_CATEGORY_DESC="category_desc"
        public val DB_COLUMN_PRICE="price"
        private val DB_COLUMN_IMG="img"
        private val DB_PRIMARY_KEY="_id"

        private val DB_CREATE_QUERY = "CREATE TABLE "+
                DB_TABLE_PRODUCT +" ( "+ DB_PRIMARY_KEY+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+ DB_COLUMN_NAME+" TEXT NOT NULL," +
                " "+ DB_COLUMN_DESCRIPTION+" TEXT," +
                " "+ DB_COLUMN_CATEGORY+" TEXT," +
                " "+ DB_COLUMN_CATEGORY_DESC+" TEXT,"+
                " "+ DB_COLUMN_IMG+" INTEGER," +
                " "+ DB_COLUMN_PRICE+" INTEGER )"

        public fun initDbHelper(context: Context)
        {
            if (dbHelper==null)
            {
                dbHelper= DbHelper(context)
                db= dbHelper!!.writableDatabase
            }
        }

        public fun deleteAll(){
            db!!.execSQL("DELETE FROM "+ DB_TABLE_PRODUCT)
        }

        public fun updateDate(id:Int,cv:ContentValues)
        {
            var selection = DB_PRIMARY_KEY+" = "+id
            db!!.update(DB_TABLE_PRODUCT,cv,selection,null)
        }

        public fun addProducts(arr:ArrayList<Product>){
            for (product in arr){
                var cv=ContentValues()
                cv.put(DB_COLUMN_NAME,product.name)
                cv.put(DB_COLUMN_DESCRIPTION,product.description)
                cv.put(DB_COLUMN_CATEGORY,product.textCategory)
                cv.put(DB_COLUMN_CATEGORY_DESC,product.decriptionCategory)
                cv.put(DB_COLUMN_IMG,product.img)
                cv.put(DB_COLUMN_PRICE,product.price)
                db!!.insert(DB_TABLE_PRODUCT,null,cv)
            }
        }

        public fun addProduct(product:Product){
            var cv=ContentValues()
            cv.put(DB_COLUMN_NAME,product.name)
            cv.put(DB_COLUMN_DESCRIPTION,product.description)
            cv.put(DB_COLUMN_CATEGORY,product.textCategory)
            cv.put(DB_COLUMN_CATEGORY_DESC,product.decriptionCategory)
            cv.put(DB_COLUMN_IMG,product.img)
            cv.put(DB_COLUMN_PRICE,product.price)
            db!!.insert(DB_TABLE_PRODUCT,null,cv)
        }

        public  fun getProduct(id:Int):Product{
            var product:Product
            var selection = DB_PRIMARY_KEY+" = "+id

            var resultQuery=db!!.query(DB_TABLE_PRODUCT,null,selection,null,null,null,null)

            resultQuery.moveToNext()
                product= Product(
                            resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                            resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_DESCRIPTION)),
                            resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_CATEGORY)),
                            resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_CATEGORY_DESC)),
                            resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_IMG)),
                            resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_PRICE))
                )
                return  product
        }

        public fun getProducts():ArrayList<Product> {
            var result=ArrayList<Product>()

            var resultQuery=db!!.query(DB_TABLE_PRODUCT,null,null,null,null,null,null)

            while(resultQuery.moveToNext())
                result.add(Product(
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_NAME)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_DESCRIPTION)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_CATEGORY)),
                        resultQuery.getString(resultQuery.getColumnIndex(DB_COLUMN_CATEGORY_DESC)),
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_IMG)),
                        resultQuery.getInt(resultQuery.getColumnIndex(DB_COLUMN_PRICE))
                ))
            return result

        }

        public fun closeDbHelper(){
            db!!.close()
            dbHelper!!.close()
        }

    }


}