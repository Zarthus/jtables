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
package org.netirc.library.jtables.human;

import org.junit.Assert;
import org.junit.Test;
import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.exception.MalformedTableException;
import org.netirc.library.jtables.table.CompactTable;
import org.netirc.library.jtables.table.MonospaceTable;
import org.netirc.library.jtables.table.PlainTable;
import org.netirc.library.jtables.table.UnicodeMonospaceTable;
import org.netirc.library.jtables.util.TableHelper;

public class PrintedTableTest {
    @Test
    public void printAllTables() {
        System.out.println("---------- TEST: PRINTED TABLES ----------");
        System.out.println("Below is a list of printed tables, please ");
        System.out.println("verify nothing looks off. We will be ");
        System.out.println("printing one set of KeyValue and one set");
        System.out.println("of normally filled tables for each table.");
        System.out.println("---------- TEST: PRINTED TABLES ----------\n");
        System.out.println("\n- Compact -\n");
        printCompact();
        System.out.println("\n- Monospace -\n");
        printMonospace();
        System.out.println("\n- Plain -\n");
        printPlain();
        System.out.println("\n- Unicode Monospace -\n");
        printUnicodeMonospace();
        System.out.println("\n-------- END TEST: PRINTED TABLES --------");
    }

    private void printCompact() {
       try {
            JTablesBuilder<CompactTable> builder = CompactTable.build();
            TableHelper.fillTableDataKeyValue(builder);
            System.out.println(builder.getTable().toString());
        } catch (MalformedTableException e) {
            fail(e);
        }

        printNewLine();

        try {
            JTablesBuilder<CompactTable> builder = CompactTable.build();
            TableHelper.fillTableData(builder);
            System.out.println(builder.getTable().toString());
        } catch (MalformedTableException e) {
            fail(e);
        }
    }

    private void printMonospace() {
        try {
            JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();
            TableHelper.fillTableDataKeyValue(builder);
            System.out.println(builder.getTable().toStringHorizontal());
            System.out.println(builder.getTable().toStringVertical());
        } catch (MalformedTableException e) {
            fail(e);
        }

        printNewLine();

        try {
            JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();
            TableHelper.fillTableData(builder);
            System.out.println(builder.getTable().toStringHorizontal());
            System.out.println(builder.getTable().toStringVertical());
        } catch (MalformedTableException e) {
            fail(e);
        }
    }

    private void printPlain() {
        try {
            JTablesBuilder<PlainTable> builder = PlainTable.build();
            TableHelper.fillTableDataKeyValue(builder);
            System.out.println(builder.getTable().toString());
        } catch (MalformedTableException e) {
            fail(e);
        }

        printNewLine();

        try {
            JTablesBuilder<PlainTable> builder = PlainTable.build();
            TableHelper.fillTableData(builder);
            System.out.println(builder.getTable().toString());
        } catch (MalformedTableException e) {
            fail(e);
        }
    }

    private void printUnicodeMonospace() {
        try {
            JTablesBuilder<UnicodeMonospaceTable> builder = UnicodeMonospaceTable.build();
            TableHelper.fillTableDataKeyValue(builder);
            System.out.println(builder.getTable().toStringHorizontal());
            System.out.println(builder.getTable().toStringVertical());
        } catch (MalformedTableException e) {
            fail(e);
        }

        printNewLine();

        try {
            JTablesBuilder<UnicodeMonospaceTable> builder = UnicodeMonospaceTable.build();
            TableHelper.fillTableData(builder);
            System.out.println(builder.getTable().toStringHorizontal());
            System.out.println(builder.getTable().toStringVertical());
        } catch (MalformedTableException e) {
            fail(e);
        }
    }

    private void fail(MalformedTableException e) {
        Assert.fail("Error while filling table: " + e.getMessage());
    }

    private void printNewLine() {
        System.out.println("\n");
    }

}
