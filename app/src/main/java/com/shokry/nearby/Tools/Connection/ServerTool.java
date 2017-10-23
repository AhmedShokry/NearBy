package com.shokry.nearby.Tools.Connection;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;


import com.shokry.nearby.Models.ForsquareResponseModel;
import com.shokry.nearby.Tools.loadingDialog;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class ServerTool {


    public interface ApiInterface {


        @GET(URLS.URL_GET_DATA)
        Call<ForsquareResponseModel> getJsonData(@Query("client_id") String clientID, @Query("client_secret") String clientSecret, @Query("v") String date, @Query("ll") String latAndLong);

    }

    public static void getJsonData(final Context context, String clientID, String clientSecret, String date, String latAndLong, final APICallBack apiCallBack) {
        final Dialog dialogsLoading = new loadingDialog().showDialog(context);
        final RetrofitTool retrofitTool = new RetrofitTool();
        retrofitTool.getAPIBuilder(URLS.URL_BASE).getJsonData(clientID, clientSecret, date, latAndLong).enqueue(new RetrofitTool.APICallBack<ForsquareResponseModel>() {


            @Override
            public void onSuccess(ForsquareResponseModel response) {
                apiCallBack.onSuccess(response);
                if (dialogsLoading != null) {
                    dialogsLoading.dismiss();
                }


            }

            @Override
            public void onFailed(int statusCode, ResponseBody responseBody) {
                apiCallBack.onFailed(statusCode, responseBody);
                if (dialogsLoading != null) {
                    dialogsLoading.dismiss();
                }


            }
        });
    }


    private static <M> void makeRequestToken(final Context context, Call call, final APICallBack apiCallBack, final RetrofitTool retrofitTool) {
        call.enqueue(new RetrofitTool.APICallBack<M>() {
            @Override
            public void onSuccess(M response) {
                apiCallBack.onSuccess(response);


            }

            @Override
            public void onFailed(int statusCode, ResponseBody responseBody) {
                //Hide loading
                handleGeneralFailure(context, statusCode, responseBody, retrofitTool);
                apiCallBack.onFailed(statusCode, responseBody);

            }
        });
    }

    private static void handleGeneralFailure(Context context, int statusCode, ResponseBody responseBody, RetrofitTool retrofitTool) {
        Retrofit retrofit = retrofitTool.getRetrofit(URLS.URL_BASE);
        Log.d("statusCode", statusCode + "");

    }

    public static interface APICallBack<T> {
        void onSuccess(T response);

        void onFailed(int statusCode, ResponseBody responseBody);

    }

}
