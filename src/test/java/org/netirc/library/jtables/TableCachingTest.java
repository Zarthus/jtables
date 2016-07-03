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
package org.netirc.library.jtables;

import org.junit.Assert;
import org.junit.Test;
import org.netirc.library.jtables.exception.MalformedTableException;
import org.netirc.library.jtables.table.PlainTable;
import org.netirc.library.jtables.util.TableHelper;

public class TableCachingTest {
    @Test
    public void cacheResult() {
        JTablesBuilder<PlainTable> builder = PlainTable.build();
        PlainTable table;

        try {
            builder.cacheResult();
            TableHelper.fillTableDataKeyValue(builder);
            table = builder.getTable();
        } catch (MalformedTableException e) {
            table = null;
        }

        Assert.assertNotNull(table);
        Assert.assertFalse(table.isCached());

        table.render();
        Assert.assertTrue(table.isRenderCache());
        Assert.assertTrue(table.isCached());
    }
}
