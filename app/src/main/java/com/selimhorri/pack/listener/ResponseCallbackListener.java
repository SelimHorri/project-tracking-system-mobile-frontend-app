package com.selimhorri.pack.listener;

public interface ResponseCallbackListener {

    @FunctionalInterface
    public interface ResponseCallbackSuccessListener<T> {
        void onResponse(final T t);
    }

    @FunctionalInterface
    public interface ResponseCallbackErrorListener {
        void onError(final String msg);
    }

}
