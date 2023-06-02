package com.example.allergydotnet.util;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/userLogin")
    Call<LoginInfo> executeLogin(@Body HashMap<String, String> map);

    @POST("/userRegistration")
    Call<SignupInfo> executeSignup (@Body HashMap<String, String> map);

    @POST("/createNote")
    Call<NoteInfo> executeNote (@Body HashMap<String, String> map);

    @POST("/loadUserNameSub")
    Call<UserNameSubInfo> executeUserNameSub(@Body HashMap<String, String> map);

    @POST("/loadUserAllergens")
    Call<ArrayList<UserAllergensInfo>> executeUserAllergens (@Body HashMap<String, String> map);

    @POST("/loadNotesName")
    Call<UserNotationsInfo> executeUserNotationsNames (@Body ArrayList<UserNameSubInfo> arr);

    @POST("/loadFullNotes")
    Call<ArrayList<UserNotationsInfo>> executeUserNotations(@Body HashMap<String, String> map);
}