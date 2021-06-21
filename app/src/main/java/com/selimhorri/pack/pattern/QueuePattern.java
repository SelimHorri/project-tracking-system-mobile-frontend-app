package com.selimhorri.pack.pattern;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class QueuePattern {

    private static QueuePattern instance;
    private static Context context;
    private RequestQueue requestQueue;

    public QueuePattern(final Context cntxt) {
        context = cntxt;
        this.requestQueue = this.getRequestQueue();
    }

    public static synchronized QueuePattern getInstance(final Context context) {
        if (instance == null)
            instance = new QueuePattern(context);
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





