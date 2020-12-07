package ipvc.estg.pmtrab4.Login

import android.text.Editable
import com.google.android.gms.maps.model.LatLng
import ipvc.estg.pmtrab4.dataclasse.Nota
import retrofit2.Call
import retrofit2.http.*

interface LoginEndPoints {

    @FormUrlEncoded
    @POST("/myslim/api/login/post")
    fun postTest(@Field("username") username: String, @Field("password") password: String): Call<LoginOutputPost>
    @FormUrlEncoded
    @POST("myslim/api/login/create")
    fun postcreate(@Field("username") username: String, @Field("password") password: String): Call<LoginOutputPost>
    @FormUrlEncoded
    @POST("myslim/api/ticket/create")
    fun create(@Field("username") username: String, @Field("tipo") tipo: String,@Field("texto") texto: Editable, @Field("lat") lat: String, @Field("lon") lon: String,  @Field("foto") foto: String): Call<TicketOutputPost>
    @GET("/myslim/api/markers")
    fun getNotas(): Call<List<Nota>>
    @GET("/myslim/api/select/{id}")
    fun getNota(@Path("id") id: Int): Call<List<Nota>>
}