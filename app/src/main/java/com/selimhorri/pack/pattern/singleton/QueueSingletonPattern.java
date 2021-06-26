package com.selimhorri.pack.pattern.singleton;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class QueueSingletonPattern {

    private static QueueSingletonPattern instance;
    private static Context context;
    private RequestQueue requestQueue;

    public QueueSingletonPattern(final Context cntxt) {
        context = cntxt;
        this.requestQueue = this.getRequestQueue();
    }

    public static synchronized QueueSingletonPattern getInstance(final Context context) {
        if (instance == null)
            instance = new QueueSingletonPattern(context);
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (this.requestQueue == null)
            this.requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(final Request<T> request) {
        this.requestQueue.add(request);
    }



}





