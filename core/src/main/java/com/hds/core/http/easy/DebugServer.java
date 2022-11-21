package com.hds.core.http.easy;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestServer;

/**
 * Debug 环境
 */
public class DebugServer implements IRequestServer {

    private String server;

    public DebugServer(String server) {
        this.server = server;
    }

    @NonNull
    @Override
    public String getHost() {
        return server;
    }
}
