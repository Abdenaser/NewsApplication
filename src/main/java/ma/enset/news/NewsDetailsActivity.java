package ma.enset.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

import ma.enset.news.Models.Articles;
import ma.enset.news.Models.NewsApi;
import ma.enset.news.Service.OnFetchDataListener;
import ma.enset.news.Service.RestServiceAPI;

public class NewsDetailsActivity extends AppCompatActivity{
    Articles articles;
    TextView txt_title,txt_author,txt_time,txt_detail,txt_content;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);



        txt_title=findViewById(R.id.txt_detail_title);
        txt_author=findViewById(R.id.txt_detail_author);
        txt_time=findViewById(R.id.txt_detail_time);
        txt_detail=findViewById(R.id.txt_detail_detail);
        txt_content=findViewById(R.id.txt_detail_content);
        imageView=findViewById(R.id.img_detail_news);


        articles= (Articles) getIntent().getSerializableExtra("data");

        txt_author.setText(articles.getAuthor());
        txt_title.setText(articles.getTitle());
        txt_time.setText(articles.getPublishedAt());
        txt_detail.setText(articles.getDescription());
        txt_content.setText(articles.getContent());
        //pour seter une  images en utilise la bibliothéque Picasso
        Picasso.get().load(articles.getUrlToImage()).into(imageView);
    }


}