package com.example.soundloneteamcomp.searcharticle.listSearchArticle.presenter;

import android.support.annotation.Nullable;

public interface SearchArticleInterface {
    void getArticle();

    void searchByBody(String search);

    void searchFull(String begindate,
                    String sort,
                    boolean art,
                    boolean fashion,
                    boolean sport,
                    String query,
                    int page);

    void searchByFilter(@Nullable String begin,
                        @Nullable String sort,
                        @Nullable String newVal,
                        int page);
}
