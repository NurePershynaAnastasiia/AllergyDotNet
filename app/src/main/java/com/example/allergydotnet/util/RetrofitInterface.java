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

    @POST("/noteInfo")
    Call<NoteInfo> executeNote (@Body HashMap<String, String> map);

    @POST("/loadUserNameSub")
    Call<UserNameSubInfo> executeUserNameSub(@Body HashMap<String, String> map);

    @POST("/userAlergens")
    Call<UserAllegensInfo> executeUserAlergens (@Body HashMap<String, String> map);

    @POST("/userNotations")
    Call<UserNotationsInfo> executeUserNotations (@Body HashMap<String, String> map);
}