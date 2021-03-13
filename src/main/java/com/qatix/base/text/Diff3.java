package com.qatix.base.text;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Diff3 {
    public static void main(String[] args) throws IOException {
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "**")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(
                Arrays.asList("This is a test senctence.", "This is the second line.", "And here is the finish."),
                Arrays.asList("This is a test for diffutils.", "This is the second line."));

        System.out.println("|original|new|");
        System.out.println("|--------|---|");
        for (DiffRow row : rows) {
            System.out.println("|" + row.getOldLine() + "|" + row.getNewLine() + "|");
        }

        AtomicInteger idx = new AtomicInteger(1);
        List<String> file1 = Files.readAllLines(new File("tmp/file1.txt").toPath()).stream().map(e-> (idx.getAndIncrement()) + ": "+ e).collect(Collectors.toList());

        idx.set(1);
        List<String> file2 = Files.readAllLines(new File("tmp/file2.txt").toPath()).stream().map(e-> (idx.getAndIncrement()) + ": "+ e).collect(Collectors.toList());;

//compute the differences for two test texts.
        List<DiffRow> rows2 = generator.generateDiffRows(
                file1,
                file2);

        for (DiffRow row : rows2) {
            System.out.println("|" + row.getOldLine() + "|" + row.getNewLine() + "|");
        }
    }
}
