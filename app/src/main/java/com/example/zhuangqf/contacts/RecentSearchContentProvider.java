package com.example.zhuangqf.contacts;


import android.content.SearchRecentSuggestionsProvider;

public class RecentSearchContentProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.zhuangqf.contacts.search";

    public RecentSearchContentProvider() {
        setupSuggestions(AUTHORITY, DATABASE_MODE_QUERIES);
    }
}
