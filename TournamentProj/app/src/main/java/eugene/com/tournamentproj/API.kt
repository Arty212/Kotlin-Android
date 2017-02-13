package eugene.com.utilproject

import retrofit2.http.GET
import retrofit2.Call
import java.util.*

/**
 * Created by USER on 09.02.2017.
 */
interface API {
    @GET("get.php")
    fun getRecords(): Call<ArrayList<User>>

}