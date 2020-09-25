package com.paysera.sdk.wallet.handlers;

import bolts.Task;
import okhttp3.internal.http2.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Executor;

public class AlwaysAsyncJsonHttpResponseHandler {
    private Executor executor = Task.UI_THREAD_EXECUTOR;

    public AlwaysAsyncJsonHttpResponseHandler() { }

    public AlwaysAsyncJsonHttpResponseHandler(Executor executor) {
        this.executor = executor;
    }

    public void onSuccess(int statusCode, Header[] headers, JSONArray response) { }

    public void onSuccess(int statusCode, Header[] headers, JSONObject response) { }

    public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject response) { }

    public Executor getExecutor() {
        return executor;
    }
}