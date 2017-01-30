package ru.job4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

/**
 * Find class.
 *
 * @author Denis
 * @since 26.01.2017
 */
public class Find {
    /**
     * Starting directory for search.
     */
    private File dir;

    /**
     * Name of searching file.
     */
    private String name = "";

    /**
     * Setting key of searching.
     * 'm' - mask.
     * 'f' - full name.
     */
    private char mask;

    /**
     * Name of log file.
     */
    private File log;

    /**
     * String of keys.
     */
    private String keys = "";

    /**
     * Running search with keys.
     *
     * @param in keys.
     * @return finding files or 'Not valid keys'.
     */
    String runSearching(String in) {
        StringBuilder result = new StringBuilder();
        this.keys = in;

        if (!validate()) {
            result.append("Not valid keys");
        } else {
            IOFileFilter fileFilter = new WildcardFileFilter(name);
            Collection<File> listOfFiles = FileUtils.listFilesAndDirs(dir, fileFilter, TrueFileFilter.TRUE);

            try {
                if (this.log.exists()) {
                    FileUtils.forceDelete(this.log);
                }
                for (File f : listOfFiles) {
                    if (f.isDirectory()) {
                        continue;
                    }
                    if (result.length() != 0) {
                        result.append(System.getProperty("line.separator"));
                    }
                    result.append(f);
                }
                FileUtils.writeStringToFile(this.log, result.toString(), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    /**
     * Validation of keys.
     *
     * @return true if keys are valid.
     */
    private boolean validate() {
        boolean result = true;
        for (int i = 0; i < this.keys.length(); i++) {
            if (this.keys.charAt(i) == '-' && this.keys.charAt(i + 1) == 'd') {
                i = readInDoubleQuotes(i + 2, "dir");
                if (i == -1 || i == this.keys.length()) {
                    break;
                }
            }
            if (this.keys.charAt(i) == '-' && this.keys.charAt(i + 1) == 'n') {
                i = readInDoubleQuotes(i + 2, "name");
                if (i == -1 || i == this.keys.length()) {
                    break;
                }
            }
            if (this.keys.charAt(i) == '-' && this.keys.charAt(i + 1) == 'm') {
                this.mask = 'm';
            }
            if (this.keys.charAt(i) == '-' && this.keys.charAt(i + 1) == 'f') {
                this.mask = 'f';
            }
            if (this.keys.charAt(i) == '-' && this.keys.charAt(i + 1) == 'o') {
                i = readInDoubleQuotes(i + 2, "log");
                if (i == -1 || i == this.keys.length()) {
                    break;
                }
            }
        }

        result = this.keys.length() != 0
                && this.dir.exists() & !this.name.isEmpty() & this.mask != '\0' & this.log != null;

//        Matcher m;
//        m = Pattern.compile("^(?=.*-d\\b)(?=.*-n\\b)(?=.*(-m|-f)\\b).*$").matcher(this.keys);
//        m = Pattern.compile("-d\\s\"(.*?)\"").matcher(this.keys);
//        m = Pattern.compile("-n\\s\"(.*?)\"").matcher(this.keys);
//        m = Pattern.compile("-(f|m)($|\\s(?=-[dno]))").matcher(this.keys);
//        m = Pattern.compile("-o\\s\"(.*?)\"").matcher(this.keys);
        return result;
    }

    /**
     * Read string in double quotes.
     *
     * @param startIndex start index.
     * @param fieldName  filled field name.
     * @return index of second quotes. Or -1 if quotes not found.
     */
    private int readInDoubleQuotes(int startIndex, String fieldName) {
        int secondQuotes = -1;
        for (int i = startIndex; i < this.keys.length(); i++) {
            if (this.keys.charAt(i) == '"') {
                i++;
                secondQuotes = this.keys.indexOf('"', i);
                if (secondQuotes == -1) {
                    break;
                }
                String string = this.keys.substring(i, secondQuotes);
                secondQuotes++;
                if ("dir".equals(fieldName)) {
                    this.dir = new File(string);
                } else if ("name".equals(fieldName)) {
                    this.name = string;
                } else if ("log".equals(fieldName)) {
                    if (string.length() > 0) {
                        String dir = getClass().getClassLoader().getResource(".").getPath();
                        this.log = new File(dir.substring(1) + string);
                    }
                }
                break;
            }
        }
        return secondQuotes;
    }

    /**
     * Main method.
     *
     * @param args params.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("String of key example:");
        System.out.println("-d \"c:/1\" -n \"*.txt\" -m -o \"log.txt\"");
        System.out.println(new Find().runSearching(scanner.nextLine()));
    }
}