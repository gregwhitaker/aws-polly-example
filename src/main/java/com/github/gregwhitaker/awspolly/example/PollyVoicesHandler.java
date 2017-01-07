package com.github.gregwhitaker.awspolly.example;

import com.amazonaws.services.polly.AmazonPollyAsyncClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.Voice;
import com.amazonaws.util.json.Jackson;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.ArrayList;
import java.util.List;

public class PollyVoicesHandler implements Handler {

    @Inject
    private AmazonPollyAsyncClient polly;

    @Override
    public void handle(Context ctx) throws Exception {
        String token = null;
        List<Voice> voices = new ArrayList<>();

        while (true) {
            DescribeVoicesResult result;
            if (token == null) {
                result = polly.describeVoices(new DescribeVoicesRequest());
            } else {
                result = polly.describeVoices(new DescribeVoicesRequest().withNextToken(token));
            }

            voices.addAll(result.getVoices());

            if (result.getNextToken() != null) {
                token = result.getNextToken();
            } else {
                ctx.render(Jackson.toJsonString(voices));
                break;
            }
        }
    }
}
