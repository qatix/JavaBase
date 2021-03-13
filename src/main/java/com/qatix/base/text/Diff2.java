package com.qatix.base.text;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Diff2 {
    public static void main(String[] args) throws IOException {
        //create a configured DiffRowGenerator
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .mergeOriginalRevised(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")      //introduce markdown style for strikethrough
                .newTag(f -> "**")     //introduce markdown style for bold
                .build();


        AtomicInteger idx = new AtomicInteger(1);
        List<String> file1 = Files.readAllLines(new File("tmp/file1.txt").toPath()).stream().map(e-> (idx.getAndIncrement()) + ": "+ e).collect(Collectors.toList());

        idx.set(1);
        List<String> file2 = Files.readAllLines(new File("tmp/file2.txt").toPath()).stream().map(e-> (idx.getAndIncrement()) + ": "+ e).collect(Collectors.toList());;

//compute the differences for two test texts.
        List<DiffRow> rows = generator.generateDiffRows(
                file1,
                file2);

        rows.forEach(System.out::println);
    }
}
