package ipvc.estg.pmtrab4.Login

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginEndPoints {

    @FormUrlEncoded
    @POST("/myslim/api/login/post")
    fun postTest(@Field("username") username: String, @Field("password") password: String): Call<LoginOutputPost>
    @FormUrlEncoded
    @POST("myslim/api/login/create")
    fun postcreate(@Field("username") username: String, @Field("password") password: String): Call<LoginOutputPost>
}