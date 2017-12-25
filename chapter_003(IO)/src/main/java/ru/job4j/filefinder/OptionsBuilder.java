package ru.job4j.filefinder;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

/**
 * class OptionsBuilder - предоставляет строковые опции для FileFinder.
 */
public class OptionsBuilder {
    /**
     * Возвращает построенные опции.
     * @return
     */
    public Options getArguments() {
        Options result = new Options();

        Option directory = new Option("d", "directory", true,
                "source directory to execute search");
        directory.setRequired(true);

        Option filename = new Option("n", "filename", true,
                "filename or regex for search");
        filename.setRequired(true);

        OptionGroup searchType = new OptionGroup();

        searchType.addOption(new Option("m", "mask", false,
                "find file by mask"));
        searchType.addOption(new Option("f", "fullname", false,
                "find file by full filename"));
        searchType.addOption(new Option("r", "regex", false,
                "find file by regex"));
        searchType.setRequired(true);

        Option output = new Option("o", "output", true,
                "output log filename");

        result.addOption(directory);
        result.addOption(filename);
        result.addOptionGroup(searchType);
        result.addOption(output);

        return result;
    }
}
