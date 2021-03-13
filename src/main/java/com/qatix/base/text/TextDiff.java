package com.qatix.base.text;

import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import com.github.difflib.patch.PatchFailedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TextDiff {
    public static void main(String[] args) throws IOException, PatchFailedException {

        List<String> original = Files.readAllLines(new File("tmp/file1.txt").toPath());
        List<String> patched = Files.readAllLines(new File("tmp/file2.txt").toPath());

// At first, parse the unified diff file and get the patch
        Patch<String> patch = UnifiedDiffUtils.parseUnifiedDiff(patched);

// Then apply the computed patch to the given text
        List<String> result = DiffUtils.patch(original, patch);

//simple output to console
        System.out.println(result);
    }
}
