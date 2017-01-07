package com.github.gregwhitaker.awspolly.example;

import ratpack.guice.Guice;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class Main {

    public static void main(String... args) throws Exception {
        RatpackServer.start(s -> s
                .serverConfig(c -> c.baseDir(BaseDir.find()))
                .registry(Guice.registry(b -> b.module(PollyModule.class)))
                .handlers(chain -> chain
                        .get("voices", PollyVoicesHandler.class)
                        .get("read", PollyReadHandler.class)
                        .files(f -> f
                                .dir("public").indexFiles("index.html"))));
    }
}
