package top.erzhiqian.spock.sample.file;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A sample class that takes a a list of image
 * names and returns only those that are jpeg files
 *
 * @author Kostis
 */
public class FileExtensionFilter {
    private Set<String> validExtensions = new HashSet<>();
    private static final String FILE_EXTENSION_INDEX = ".";

    public void addValidExtension(String extension) {
        validExtensions.add(extension);
    }

    public Collection<String> filterFileNames(List<String> input) {
        if (null == input || input.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = input.stream()
                .filter(item -> item.lastIndexOf(FILE_EXTENSION_INDEX) >= 0)
                .filter(item -> validExtensions.contains(fileExtension(item)))
                .collect(Collectors.toList());
        return result;
    }

    private String fileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(FILE_EXTENSION_INDEX) + 1);
    }


}
