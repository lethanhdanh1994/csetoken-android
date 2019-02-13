package sg.cse.wallet.api;


import android.content.Intent;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import sg.cse.wallet.CSEApplication;
import sg.cse.wallet.mvp.signin.SignInActivity;
import sg.cse.wallet.prefs.PrefKey;
import sg.cse.wallet.prefs.Prefs;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class APIClient {

    private static final String BASE_URL = "http://api.csewallet.io/v1/";
  //  private static final String BASE_URL_SUB = "http://35.198.251.214:3000/v1/";
    private static final int TIMEOUT = 5; // 30s

    public static class Builder {

        private Retrofit retrofit = null;
        private HttpLoggingInterceptor interceptor;
        private OkHttpClient.Builder httpClient;

        public void init() {
            httpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS);

            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());

                    ResponseBody responseBody = response.body();

//                  if(responseBody!=null){
//                      String content = responseBody.string();
//                      TransactionsHistoryRes transactionsHistoryRes =     new Gson().fromJson(content, TransactionsHistoryRes.class);
//                  if(  transactionsHistoryRes.getMessage().equals("invalid token")){
//                      Intent i = new Intent(CSEApplication.getAppContext(), SignInActivity.class);
//                      CSEApplication.getAppContext().startActivity(i);
//                  }
//




                  if(response.code()==403){
                      Intent i = new Intent(CSEApplication.getAppContext(), SignInActivity.class);
                      CSEApplication.getAppContext().startActivity(i);
                  }

                    return response;
                }
            });

        }

        /**
         * Get client
         *
         * @return
         */
        public Retrofit getClient() {
            interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            return retrofit;
        }

        /**
         * Get client with header
         *
         * @return
         */
        public Retrofit getClientWithHeader() {


//            if(Prefs.getInstance().getStringValue(PrefKey.APP_TOKEN)==null){
//                httpClient.addInterceptor(new Interceptor() {
//                    @Override
//                    public okhttp3.Response intercept(Chain chain) throws IOException {
//                        Request original = chain.request();
//                        Request request = chain.request().newBuilder()
//                                .addHeader("Content-Type", "application/json")
//                                .method(original.method(), original.body())
//                                .build();
//                        return chain.proceed(request);
//                    }
//                });
//            }else {



                httpClient.addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("access-token", Prefs.getInstance().getStringValue(PrefKey.APP_TOKEN))
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                });

         //   }



            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            return retrofit;
        }
    }
}
