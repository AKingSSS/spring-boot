package com.aking.learn.customer;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * <p>
 *  自定义banner
 * </p>
 *
 * @author yk
 * @date 2021-01-02
 */
public class MyBanner implements Banner {
    private static final String[] BANNER = {"                      ,--.                 ,--.\n" +
            "   ,---,          ,--/  /|   ,---,       ,--.'|  ,----..\n" +
            "  '  .' \\      ,---,': / ',`--.' |   ,--,:  : | /   /   \\\n" +
            " /  ;    '.    :   : '/ / |   :  :,`--.'`|  ' :|   :     :\n" +
            ":  :       \\   |   '   ,  :   |  '|   :  :  | |.   |  ;. /\n" +
            ":  |   /\\   \\  '   |  /   |   :  |:   |   \\ | :.   ; /--`\n" +
            "|  :  ' ;.   : |   ;  ;   '   '  ;|   : '  '; |;   | ;  __\n" +
            "|  |  ;/  \\   \\:   '   \\  |   |  |'   ' ;.    ;|   : |.' .'\n" +
            "'  :  | \\  \\ ,'|   |    ' '   :  ;|   | | \\   |.   | '_.' :\n" +
            "|  |  '  '--'  '   : |.  \\|   |  ''   : |  ; .''   ; : \\  |\n" +
            "|  :  :        |   | '_\\.''   :  ||   | '`--'  '   | '/  .'\n" +
            "|  | ,'        '   : |    ;   |.' '   : |      |   :    /\n" +
            "`--''          ;   |,'    '---'   ;   |.'       \\   \\ .'\n" +
            "               '---'              '---'          `---`"};

    private static final String SPRING_BOOT = " :: Spring Boot :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println();
    }

}
