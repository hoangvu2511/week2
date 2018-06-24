package com.example.soundloneteamcomp.searcharticle.listSearchArticle.view;

import com.example.soundloneteamcomp.searcharticle.SearchArticle.Doc;

import java.util.List;

public interface ArticleViewInreface {
    void showListArticle(List<Doc> docs);

    void showLoading();

    void hideLoading();
}

