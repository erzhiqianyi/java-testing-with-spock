package top.erzhiqian.spock.expressions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InvalidNamesGen implements Iterator<String> {

    private static final String COMMENT_PREFIX = "#";

    private List<String> invalidNames;

    private int counter = 0;

    public InvalidNamesGen(String filePath) {
        invalidNames = new ArrayList<>();
        parse(filePath);
    }

    private void parse(String filePath) {
        Path path = Paths.get(filePath);
        try {
            invalidNames = Files.lines(path)
                    .filter(line -> !invalidLine(line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean invalidLine(String line) {
        return null == line || line.isEmpty() || line.startsWith(COMMENT_PREFIX);
    }

    @Override
    public boolean hasNext() {
        return counter < invalidNames.size();
    }

    @Override
    public String next() {
        String result = invalidNames.get(counter);
        counter++;
        return result;
    }

    @Override
    public void remove() {
        // not needed for this test

    }
}
