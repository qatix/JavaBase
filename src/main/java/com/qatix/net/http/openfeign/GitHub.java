package com.qatix.net.http.openfeign;

import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GitHub {

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    CompletableFuture<List<Contributor>> contributorsAsync(@Param("owner") String owner, @Param("repo") String repo);

    public static class Contributor {
        String login;
        int contributions;
    }

    public static class Issue {
        String title;
        String body;
        List<String> assignees;
        int milestone;
        List<String> labels;
    }
}
