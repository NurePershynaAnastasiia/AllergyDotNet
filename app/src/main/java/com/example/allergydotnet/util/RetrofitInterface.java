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

    @POST("/loadDoctors")
    Call<ArrayList<DoctorInfo>> executeDoctorsInfo();

    @POST("/loadDoctorMobile")
    Call<DoctorInfo> executeDoctorInfo(@Body HashMap<String, String> map);

    @POST("/loadAllAllergens")
    Call<ArrayList<AllergensNamesInfo>> executeAllergenNamesInfo(@Body HashMap<String, String> map);

    @POST("/addNewAllergen")
    Call<AllergensNamesInfo> executeAddAllergen(@Body HashMap<String, String> map);

    @POST("/loadUserConsultations")
    Call<ArrayList<UserConsultsInfo>> executeLoadUserConsultationsn(@Body HashMap<String, String> map);

    @POST("/cancelCons")
    Call<UserConsultsInfo> executeCancelCons(@Body HashMap<String, String> map);

    @POST("/addCons")
    Call<NewConsultInfo> executeAddCons(@Body HashMap<String, String> map);
}