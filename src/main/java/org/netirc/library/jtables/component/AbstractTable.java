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

package org.netirc.library.jtables.component;

import org.netirc.library.jtables.exception.MalformedTableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class AbstractTable implements TableInterface {
    protected List<String> columns = new ArrayList<>();
    protected List<List<String>> rows = new ArrayList<>();
    protected boolean renderCache;
    protected List<String> renderCacheResult;
    protected boolean permitEmptyRowItems = false;

    public AbstractTable() {
        this.renderCache = false;
    }

    public AbstractTable(boolean cache) {
        renderCache = cache;
    }

    abstract public List<String> render();

    protected List<String> cache() {
        if (renderCache && renderCacheResult != null) {
            return renderCacheResult;
        }

        return null;
    }

    protected List<String> cache(List<String> result) {
        if (!renderCache) {
            return result;
        }

        if (renderCacheResult == null) {
            renderCacheResult = result;
        }

        return renderCacheResult;
    }

    public void addColumn(String name) throws MalformedTableException {
        if (!isPermitEmptyRowItems() && rows.size() != 0) {
            throw new MalformedTableException(
                "Permittance of empty columns is false, yet tried to add new column while rows are present"
            );
        }

        columns.add(name);
    }

    public void addRow(List<String> rowItems) throws MalformedTableException {
        if (!isPermitEmptyRowItems()) {
            if (rowItems.size() != columnCount()) {
                throw new MalformedTableException(
                    "Permittance of empty columns is false, yet tried to add non equal row item count versus column count, " +
                        "rowItems.size() = " + rowItems.size() + ", columns.size() = " + columnCount()
                );
            }

            for (String item : rowItems) {
                if (item == null || item.isEmpty()) {
                    throw new MalformedTableException("Table Row Items may not be null or empty.");
                }
            }
        }

        rows.add(rowItems);
    }

    public void addRows(String... rows) throws MalformedTableException {
        List<String> rs = new ArrayList<>();
        Collections.addAll(rs, rows);

        addRow(rs);
    }

    public int rowCount() {
        return rows.size();
    }

    public int columnCount() {
        return columns.size();
    }

    public String toString() {
        return String.join("\n", render());
    }

    public boolean isRenderCache() {
        return renderCache;
    }

    public boolean isCached() {
        return renderCacheResult != null;
    }

    public boolean isPermitEmptyRowItems() {
        return permitEmptyRowItems;
    }

    public boolean isEmpty() {
        return rowCount() == 0 || columnCount() == 0;
    }

    public void enableCacheResult() {
        renderCache = true;
    }

    public void setPermitEmptyRows(boolean permitEmptyRowItems) {
        this.permitEmptyRowItems = permitEmptyRowItems;
    }
}
