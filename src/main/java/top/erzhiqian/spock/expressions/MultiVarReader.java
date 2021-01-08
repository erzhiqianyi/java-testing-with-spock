package top.erzhiqian.spock.expressions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiVarReader implements Iterator<Object[]> {

    private static final String COMMENT_PREFIX = "#";

    private List<String> fileNames;

    private List<Boolean> validFlag;

    private int counter = 0;

    public MultiVarReader(String filePath) {
        this.fileNames = new ArrayList<>();
        this.validFlag = new ArrayList<>();
        parse(filePath);
    }


    private void parse(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.lines(path)
                    .filter(line -> !invalidLine(line))
                    .forEach(line -> {
                        String[] tokens = line.trim().split(" ");
                        fileNames.add(tokens[0]);
                        validFlag.add(tokens[1].toLowerCase().startsWith("pass"));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return counter < fileNames.size();
    }

    @Override
    public Object[] next() {
        Object[] result = new Object[2];
        result[0] = fileNames.get(counter);
        result[1] = validFlag.get(counter);
        counter++;
        return result;
    }

    @Override
    public void remove() {
        // not needed for this test

    }


    private boolean invalidLine(String line) {
        return null == line || line.isEmpty() || line.startsWith(COMMENT_PREFIX);
    }

}
