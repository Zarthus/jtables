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
import org.netirc.library.jtables.component.AbstractTable;
import org.netirc.library.jtables.exception.MalformedTableException;
import org.netirc.library.jtables.table.PlainTable;
import org.netirc.library.jtables.util.TableHelper;

/**
 * Note: Tables handle empty values at their own leisure in the render method.
 *
 * In PlainTable's case, which we are piggybacking off of, the format will be "columnName: value"
 * This may yield results where we test against .equals("Value: null") because it is concatinated as a string.
 *
 * In the future this test can be made more robust by implementing a table specifically for testing that permits
 * consistent parsing and is not dependent on PlainTable or other API changes, or directly get the result from the
 * rows variable in the table.
 */
public class TableEmptyRowTest {
    @Test(expected = MalformedTableException.class)
    public void rowWithEmptyString() throws MalformedTableException {
        JTablesBuilder<PlainTable> builder = PlainTable.build();

        builder.columns("Key", "Value");
        builder.row("KeyWithoutValue", "");
    }

    @Test(expected = MalformedTableException.class)
    public void rowWithNull() throws MalformedTableException {
        JTablesBuilder<PlainTable> builder = PlainTable.build();

        builder.columns("Key", "Value");
        builder.row("KeyWithoutValue", null);
    }

    @Test
    public void rowWithEmptyStringAndEmptyRowItems() {
        JTablesBuilder<PlainTable> builder = PlainTable.build();
        PlainTable table;

        try {
            builder.permitEmptyRowItems();
            builder.columns("Key", "Value");
            builder.row("KeyWithoutValue", "");
            table = builder.getTable();
        } catch (MalformedTableException e) {
            table = null;
        }

        Assert.assertNotNull(table);
        Assert.assertTrue(table.isPermitEmptyRowItems());
        Assert.assertEquals("Value: ", table.render().get(1));
    }

    @Test
    public void rowWithNullAndEmptyRowItems() {
        JTablesBuilder<PlainTable> builder = PlainTable.build();
        PlainTable table;

        try {
            builder.permitEmptyRowItems();
            builder.columns("Key", "Value");
            builder.row("KeyWithoutValue", null);
            table = builder.getTable();
        } catch (MalformedTableException e) {
            table = null;
        }

        Assert.assertNotNull(table);
        Assert.assertTrue(table.isPermitEmptyRowItems());
        Assert.assertEquals("Value: null", table.render().get(1));
    }

    @Test
    public void rowEmptyRowsFalseByDefault() {
        JTablesBuilder<PlainTable> builder = PlainTable.build();
        PlainTable table;

        table = builder.getTable();
        Assert.assertFalse(table.isPermitEmptyRowItems());

        table.setPermitEmptyRows(true);
        Assert.assertTrue(table.isPermitEmptyRowItems());
    }
}
