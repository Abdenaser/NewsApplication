package ma.enset.news.Service;


import android.content.Context;
import android.widget.Toast;

import java.util.List;

import ma.enset.news.Models.Articles;
import ma.enset.news.Models.NewsApi;
import ma.enset.news.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RestServiceAPI {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   public void getArticles(OnFetchDataListener listener,String category,String query){
       //start api calling
       CallNewsAPI callNewsAPI=retrofit.create(CallNewsAPI.class);
       Call<NewsApi> call=callNewsAPI.callHeadline("ma",category,query,context.getString(R.string.keys_api));
        try{

            call.enqueue(new Callback<NewsApi>() {
                @Override
                public void onResponse(Call<NewsApi> call, Response<NewsApi> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error !!", Toast.LENGTH_SHORT).show();
                    }else{
                        listener.onFetchData(response.body().getArticles(), response.message());
                    }
                }

                @Override
                public void onFailure(Call<NewsApi> call, Throwable t) {
                    listener.onError("Request Failed !!");
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
   }

    public interface CallNewsAPI{
        @GET("top-headlines")
        Call<NewsApi> callHeadline(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String query,
            @Query("apikey") String api_key
        );
    }

    public RestServiceAPI(Context context) {
        this.context = context;
    }
}
