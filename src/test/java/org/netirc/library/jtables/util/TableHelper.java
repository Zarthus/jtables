/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 netirc.org development team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.netirc.library.jtables.util;

import org.netirc.library.jtables.component.TableBuilder;
import org.netirc.library.jtables.component.TableRow;
import org.netirc.library.jtables.exception.MalformedTableException;

import java.util.Random;

public class TableHelper {
    private static String symbols;

    public static void fillTableDataKeyValue(TableBuilder builder) throws MalformedTableException {
        builder
            .columns("Key", "Value")
            .row("name", "Zarthus")
            .row("gender", "Male")
            .row("location", "The Netherlands");
    }

    public static void fillTableData(TableBuilder builder) throws MalformedTableException {
        builder
            .columns("Name", "Gender", "Location")
            .row("Jon", "Male", "United Kingdom")
            .row("John", "Male", "United Kingdom")
            .row("kashike", "Male", "Canada")
            .row("Zarthus", "Male", "The Netherlands");
    }

    public static void fillTableDataManyColumns(TableBuilder builder) throws MalformedTableException {
        builder
            .columns("Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6")
            .row("A", "B", "C", "D", "E", "F")
            .row("G", "H", "I", "J", "K", "L")
            .row("M", "N", "O", "P", "Q", "R");
    }

    public static void fillTableDataManyRows(TableBuilder builder) throws MalformedTableException {
        builder
            .columns("Column 1", "Column 2", "Column 3")
            .row("A", "B", "C")
            .row("D", "E", "F")
            .row("G", "H", "I");
    }

    /**
     * Fill the table with large strings of content, useful for testing string truncation and exceeding boundries.
     */
    public static void fillTableBigData(TableBuilder builder, final int columnCount, final int rowCount,
                                        final int minCharLen, final int maxCharLen) throws MalformedTableException {
        int colCountRemaining = columnCount;
        int rowCountRemaining = rowCount;

        while (colCountRemaining-- > 0) {
            builder.column("Column " + colCountRemaining);
        }

        while (rowCountRemaining-- > 0) {
            TableRow tableRow = builder.newTableRow();

            for (int i = 0; i < columnCount; i++) {
                tableRow.append(generateRandomString(minCharLen, maxCharLen));
            }

            builder.row(tableRow);
        }
    }

    public static void fillTableDataDynamically(TableBuilder builder, final int columns, final int rows) throws MalformedTableException {
        int colCountRemaining = columns;
        int rowCountRemaining = rows;

        while (colCountRemaining-- > 0) {
            builder.column("Column " + colCountRemaining);
        }

        while (rowCountRemaining-- > 0) {
            TableRow tableRow = builder.newTableRow();

            for (int i = 0; i < columns; i++) {
                tableRow.append("Row " + rowCountRemaining + ":  RowItem " + i);
            }

            builder.row(tableRow);
        }
    }

    public static String generateRandomString(final int minCharLen, final int maxCharLen) {
        if (minCharLen > maxCharLen) {
            throw new IllegalArgumentException("Maximum Character Length must be larger than the Minimum Character Length");
        }

        StringBuilder builder = new StringBuilder();
        generateCharacterList();
        int len = (int) (Math.random() * (minCharLen - maxCharLen)) + minCharLen;

        Random r = new Random();
        final int randomMax = symbols.length();

        for (int i = 0; i < len; i++) {
            builder.append(symbols.charAt(r.nextInt(randomMax)));
        }

        return builder.toString();
    }

    protected static void generateCharacterList() {
        if (symbols != null) {
            return;
        }

        StringBuilder tmp = new StringBuilder();

        for (char ch = '0'; ch <= '9'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            tmp.append(ch);
        }

        symbols = tmp.toString();
    }
}
