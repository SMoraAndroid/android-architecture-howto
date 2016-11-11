package com.smora.arch.webserver;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class WebServer {

    public static final int SERVER_PORT = 8080;

    private static final String LOG_TAG = WebServer.class.getSimpleName();

    static volatile WebServer singleton = null;

    public static WebServer with(final Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null.");
        }
        if (singleton == null) {
            synchronized (WebServer.class) {
                if (singleton == null) {
                    singleton = new WebServer(context);
                }
            }
        }
        return singleton;
    }

    private final Context mContext;
    private final Server mServer;

    public WebServer(final Context context) {
        mContext = context;
        mServer = new Server();
    }

    public void start() {
        Log.v(LOG_TAG, "start");

        if (mServer.isAlive()) {
            Log.v(LOG_TAG, "server is already alive");
            return;
        }

        try {
            mServer.start();
            Log.v(LOG_TAG, "server started");
        } catch (IOException e) {
            Log.w(LOG_TAG, "error when starting server", e);
        }
    }

    public void stop() {
        Log.v(LOG_TAG, "stop");

        if (!mServer.isAlive()) {
            Log.v(LOG_TAG, "server is already stopped");
            return;
        }

        mServer.stop();
        Log.v(LOG_TAG, "server stopped");
    }

    private static class Server extends NanoHTTPD {

        public Server() {
            super(SERVER_PORT);
        }

        @Override
        public Response serve(IHTTPSession session) {
            return newFixedLengthResponse("Hello WebServer");
        }
    }

}
