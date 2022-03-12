package ma.enset.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import ma.enset.news.Models.Articles;
import ma.enset.news.Models.NewsApi;
import ma.enset.news.Service.OnFetchDataListener;
import ma.enset.news.Service.RestServiceAPI;


public class MainActivity extends AppCompatActivity implements SelectListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.search_view);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("wait ...");
                dialog.show();
                RestServiceAPI manager = new RestServiceAPI(MainActivity.this);
                manager.getArticles(listener, "business", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog=new ProgressDialog(this);
        dialog.setTitle("Wait ...");
        dialog.show();

        RestServiceAPI manager = new RestServiceAPI(this);
        manager.getArticles(listener, "business", null);
    }

    public final OnFetchDataListener<NewsApi> listener = new OnFetchDataListener<NewsApi>() {
        @Override
        public void onFetchData(List<Articles> list, String message) {
                if(list.isEmpty()){
                    Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                else{
                    ShowNews(list);
                    dialog.dismiss();
                }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Error !!", Toast.LENGTH_SHORT).show();
        }
    };

    private void ShowNews(List<Articles> list) {
        recyclerView=findViewById(R.id.recyclemain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnnewsClick(Articles article) {
        startActivity(new Intent(MainActivity.this,NewsDetailsActivity.class)
        .putExtra("data", article));
    }
}

