package com.smora.arch.webserver;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

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

    private final Server mServer;

    public WebServer(final Context context) {
        mServer = new Server(context);
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

        private AssetManager mAssetManager;

        public Server(final Context context) {
            super(SERVER_PORT);
            mAssetManager = context.getAssets();
        }

        @Override
        public Response serve(IHTTPSession session) {
            try {
                final InputStream is = mAssetManager.open("ws" + session.getUri() + "/resp.json");
                int size = is.available();
                byte buffer[] = new byte[size];
                is.read(buffer);
                is.close();
                return newFixedLengthResponse(new String(buffer));

            } catch (IOException e) {
                Log.w(LOG_TAG, "Error server: ", e);
                return newFixedLengthResponse("Error server : "  + e.getLocalizedMessage());
            }

        }
    }

}
