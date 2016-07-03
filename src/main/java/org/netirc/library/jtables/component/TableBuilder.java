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
package org.netirc.library.jtables.component;

import org.netirc.library.jtables.exception.MalformedTableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableBuilder<Table extends TableInterface> {
    protected Table table;

    public TableBuilder(Table table) {
        this.table = table;
    }

    protected TableBuilder() {}

    public TableBuilder<Table> column(String name) throws MalformedTableException {
        table.addColumn(name);

        return this;
    }

    public TableBuilder<Table> columns(String... names) throws MalformedTableException {
        for (String name : names) {
            table.addColumn(name);
        }

        return this;
    }

    public TableBuilder<Table> row(List<String> rowItems) throws MalformedTableException {
        table.addRow(rowItems);

        return this;
    }

    public TableBuilder<Table> row(String... rowItems) throws MalformedTableException {
        List<String> rowList = new ArrayList<>();
        Collections.addAll(rowList, rowItems);

        row(rowList);
        return this;
    }

    public TableBuilder<Table> row(TableRow tableRow) throws MalformedTableException {
        table.addRow(tableRow.getRowItems());

        return this;
    }

    public TableRow newTableRow() {
        return new TableRow();
    }

    public Table getTable() {
        return table;
    }
}
