package com.github.gregwhitaker.awspolly.example;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.polly.AmazonPollyAsyncClient;
import com.google.inject.AbstractModule;

public class PollyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AmazonPollyAsyncClient.class).toInstance(new AmazonPollyAsyncClient(new DefaultAWSCredentialsProviderChain()));
        bind(PollyReadHandler.class);
        bind(PollyVoicesHandler.class);
    }
}
