/**
 * Copyright (c) 2016 netirc.org development team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.netirc.library.jtables;

import org.junit.Assert;
import org.junit.Test;
import org.netirc.library.jtables.table.CompactTable;
import org.netirc.library.jtables.table.MonospaceTable;
import org.netirc.library.jtables.table.PlainTable;
import org.netirc.library.jtables.table.UnicodeMonospaceTable;

public class TableIsEmptyTest {
    private final String shouldEndWith = ": There is nothing to display.";

    @Test
    public void testCompactTable() {
        JTablesBuilder<CompactTable> builder = CompactTable.build();

        Assert.assertEquals("CompactTable" + shouldEndWith, builder.getTable().toString());
    }

    @Test
    public void testMonospaceTable() {
        JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();

        Assert.assertEquals("MonospaceTable" + shouldEndWith, builder.getTable().toString());
        Assert.assertEquals("MonospaceTable" + shouldEndWith, builder.getTable().toStringHorizontal());
        Assert.assertEquals("MonospaceTable" + shouldEndWith, builder.getTable().toStringVertical());
    }

    @Test
    public void testPlainTable() {
        JTablesBuilder<PlainTable> builder = PlainTable.build();

        Assert.assertEquals("PlainTable" + shouldEndWith, builder.getTable().toString());
    }

    @Test
    public void testUnicodeMonospaceTable() {
        JTablesBuilder<UnicodeMonospaceTable> builder = UnicodeMonospaceTable.build();

        Assert.assertEquals("UnicodeMonospaceTable" + shouldEndWith, builder.getTable().toString());
        Assert.assertEquals("UnicodeMonospaceTable" + shouldEndWith, builder.getTable().toStringHorizontal());
        Assert.assertEquals("UnicodeMonospaceTable" + shouldEndWith, builder.getTable().toStringVertical());
    }
}
