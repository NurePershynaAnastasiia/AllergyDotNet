package com.example.allergydotnet.util;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/userLogin")
    Call<LoginInfo> executeLogin(@Body HashMap<String, String> map);

    @POST("/userRegistration")
    Call<SignupInfo> executeSignup (@Body HashMap<String, String> map);

    @POST("/userProfile")
    Call<UserProfileInfo> executeProfile (@Body HashMap<String, String> map);
}