package com.example.soundloneteamcomp.searcharticle.listSearchArticle.model;

import com.example.soundloneteamcomp.searcharticle.SearchArticle.Doc;

import java.util.List;

public interface DataListener {
    void onResponse(List<Doc> docs);

    void onError();
}
