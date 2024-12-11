import com.example.learn.data.models.NewWord
import com.example.learn.data.models.Results
import com.example.learn.data.models.WordList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("/wordlists")
    suspend fun getWordLists(): List<WordList>

    @GET("/wordlists/{id}")
    suspend fun getWordList(@Path("id") id: Int): List<NewWord>

    @GET("/user/{id}/wordlists")
    suspend fun getUserWordLists(@Path("id") id: Int): List<WordList>

    @GET("/user/{id}/wordlists/{wlid}")
    suspend fun getUserWordList(@Path("id") id: Int, @Path("wlid") wlId: Int): Boolean

    @GET("/user/{id}/wordlists/add/{wlid}")
    suspend fun addUserWordList(@Path("id") id: Int, @Path("wlid") wlId: Int): Boolean

    @GET("/user/{id}/wordlists/remove/{wlid}")
    suspend fun removeUserWordList(@Path("id") id: Int, @Path("wlid") wlId: Int): Boolean

    @GET("/user/{id}/learn")
    suspend fun getUserLearnList(@Path("id") id: Int): List<NewWord>

    @POST("/user/{id}/upload_results")
    suspend fun uploadResults(@Path("id") id: Int, @Body reults: Results): Boolean

    @GET("/user/new")
    suspend fun getUserId(): Int
}

val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://150.241.64.198:8000").addConverterFactory(
    GsonConverterFactory.create()).client(client).build()

val api: Api = retrofit.create(Api::class.java)

