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

import org.netirc.library.jtables.component.AbstractTable;
import org.netirc.library.jtables.component.TableBuilder;
import org.netirc.library.jtables.component.TableRow;
import org.netirc.library.jtables.exception.MalformedTableException;

import java.util.List;

public class JTablesBuilder<Table extends AbstractTable> extends TableBuilder<Table> {
    public JTablesBuilder(Table table) {
        super(table);
    }

    public JTablesBuilder<Table> cacheResult() {
        table.enableCacheResult();

        return this;
    }

    public JTablesBuilder<Table> permitEmptyRowItems() {
        table.setPermitEmptyRows(true);

        return this;
    }

    public JTablesBuilder<Table> columns(String... names) throws MalformedTableException {
        super.columns(names);
        return this;
    }

    public JTablesBuilder<Table> column(String name) throws MalformedTableException {
        super.column(name);
        return this;
    }

    public JTablesBuilder<Table> row(List<String> rowItems) throws MalformedTableException {
        super.row(rowItems);
        return this;
    }

    public JTablesBuilder<Table> row(String... rowItems) throws MalformedTableException {
        super.row(rowItems);
        return this;
    }

    public JTablesBuilder<Table> row(TableRow tableRow) throws MalformedTableException {
        super.row(tableRow);
        return this;
    }

    public Table getTable() {
        return table;
    }
}
