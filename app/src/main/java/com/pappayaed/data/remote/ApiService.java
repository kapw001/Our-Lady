package com.pappayaed.data.remote;

import com.pappayaed.data.model.ResultResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by yasar on 6/3/18.
 */

public interface ApiService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/login")
    Observable<String> loginValidate(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/profile")
    Observable<ResultResponse> getStudentProfile(@Body String json);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/daily_class_attendance")
    Observable<ResultResponse> getDailyAttendanceList(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/update_daily_class_attendance")
    Observable<ResultResponse> updateAttendanceList(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/get_daily_homework_list")
    Observable<ResultResponse> getDailyHomeWorkList(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/update_my_subject_homework")
    Observable<String> updateHomeWorkBySubject(@Body String json);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/student_fee_terms_list")
    Observable<ResultResponse> getStudentFeeList(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/student_attendance_list")
    Observable<ResultResponse> getStudentAtttendanceList(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/parent_circular")
    Observable<ResultResponse> getCircularAndHomeWork(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/attachment_id_data")
    Observable<ResultResponse> getAttachment_id_data(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/attachment_id_data")
    <T> Observable<Response<ResultResponse>> getAttachment_id_data1(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/attachment_id_data")
    <T> Observable<Response<ResponseBody>> getAttachment_id_data2(@Body String json);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/standard_fee_collection_list")
    Observable<ResultResponse> getStandard_fee_collection(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/get_student_heartrate_list")
    Observable<ResultResponse> get_Standard_heartrate_list(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/update_parent_toten")
    Observable<String> put_Device_Token(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("mobile/rest/where_is_my_student")
    Observable<String> whereisstudent(@Body String json);

}
