package ma.enset.news.Service;

import java.util.List;

import ma.enset.news.Models.Articles;
import ma.enset.news.Models.NewsApi;

public interface OnFetchDataListener<NewsApi> {
    public void onFetchData(List<Articles> list, String message);
    public void onError(String message);
}
