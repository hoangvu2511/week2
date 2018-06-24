package com.example.soundloneteamcomp.searcharticle.listSearchArticle.model;

import android.support.annotation.Nullable;

public interface SearchArticleModel {
    void getArticle(DataListener listener);

    void searchByBody(DataListener listener,String search);

    void searchFull(DataListener listener,
                    @Nullable String q,
                    @Nullable String beginDate,
                    @Nullable String sort,
                    @Nullable String fp,
                    int page);

    void searchByFilter(DataListener listener,
                        @Nullable String begin,
                        @Nullable String sort,
                        @Nullable String newVal,
                        int page);
}
