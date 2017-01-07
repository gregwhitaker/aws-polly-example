package com.github.gregwhitaker.awspolly.example;

import com.amazonaws.services.polly.AmazonPollyAsyncClient;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.google.inject.Inject;
import io.netty.buffer.Unpooled;
import org.reactivestreams.Subscription;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.io.IOException;

public class PollyReadHandler implements Handler {

    @Inject
    private AmazonPollyAsyncClient polly;

    @Override
    public void handle(Context ctx) throws Exception {
        String voiceId = ctx.getRequest().getQueryParams().get("voiceId");
        String text = ctx.getRequest().getQueryParams().get("text");
        String outputFormat = ctx.getRequest().getQueryParams().get("outputFormat");

        SynthesizeSpeechRequest ssRequest = new SynthesizeSpeechRequest();
        ssRequest.setVoiceId(voiceId);
        ssRequest.setOutputFormat(outputFormat);
        ssRequest.setText(text);

        SynthesizeSpeechResult result = polly.synthesizeSpeech(ssRequest);

        ctx.getResponse().contentType(result.getContentType());
        ctx.getResponse().sendStream(s -> s.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                try {
                    byte[] data      = new byte[1024];
                    int bytesRead = result.getAudioStream().read(data);

                    while(bytesRead != -1) {
                        s.onNext(Unpooled.wrappedBuffer(data));
                        bytesRead = result.getAudioStream().read(data);
                    }
                } catch (IOException e) {
                    ctx.getResponse().status(500);
                    ctx.getResponse().send();
                } finally {
                    s.onComplete();
                }
            }

            @Override
            public void cancel() {

            }
        }));
    }
}
