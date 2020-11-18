package com.qatix.net.http.openfeign;

import feign.AsyncFeign;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyAppAsync {

    public static void main(String... args) throws InterruptedException, ExecutionException, TimeoutException {
        GitHub github = AsyncFeign.asyncBuilder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        // Fetch and print a list of the contributors to this library.
        CompletableFuture<List<GitHub.Contributor>> contributorsFuture = github.contributorsAsync("OpenFeign", "feign");
        List<GitHub.Contributor> contributorList = contributorsFuture.get(5, TimeUnit.SECONDS);

        for (GitHub.Contributor contributor : contributorList) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }
}
