package com.hds.core.http.easy;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestServer;

/**
 * Release 环境
 */
public class ReleaseServer implements IRequestServer {
    private String server;

    public ReleaseServer(String server) {
        this.server = server;
    }

    @NonNull
    @Override
    public String getHost() {
        return server;
    }
}
