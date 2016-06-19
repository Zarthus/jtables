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

package org.netirc.library.jtables.table;

import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.component.AbstractTable;
import org.netirc.library.jtables.component.OrientationRenderable;

import java.util.ArrayList;
import java.util.List;

import org.netirc.library.jtables.util.TableStringUtil;

public class MonospaceTable extends AbstractTable implements OrientationRenderable {
    private final String CORNER = "+";
    private final String LINE_HORIZONTAL = "-";
    private final String LINE_VERTICAL = "|";

    public static JTablesBuilder<MonospaceTable> build() {
        return new JTablesBuilder<>(new MonospaceTable());
    }

    @Override
    public List<String> render() {
        if (columnCount() <= 4) {
            return renderHorizontal();
        }

        return renderVertical();
    }

    @Override
    public List<String> renderHorizontal() {
        if (isCached()) {
            return cache();
        }

        int columnSize;
        if (columns.size() > 4) {
            columnSize = 160 / columns.size();
        } else {
            int mrl = maxRowLength();
            int mcl = maxColumnLength();
            int maxLength = mrl > mcl ? mrl : mcl;
            if (maxLength > 40) {
                maxLength = 40;
            }

            columnSize = maxLength + 4;
        }

        final String rowSeparatorItemTpl = CORNER +
            TableStringUtil.repeat(
                TableStringUtil.repeat(LINE_HORIZONTAL, columnSize - 2) + CORNER,
                columns.size()
            );
        final String rowSeparatorTpl = CORNER
            + TableStringUtil.repeat(LINE_HORIZONTAL, rowSeparatorItemTpl.length() - 2)
            + CORNER;
        final String itemTpl = LINE_VERTICAL
            + " REPL ";

        List<String> render = new ArrayList<>();
        render.add(rowSeparatorTpl);

        String item = "";
        for (String column : columns) {
            item += TableStringUtil.pad(
                itemTpl.replace("REPL", TableStringUtil.truncuate(column, columnSize - 2)), columnSize - 1
            );
        }
        item += LINE_VERTICAL;
        render.add(item);
        render.add(rowSeparatorTpl);

        for (List<String> row : rows) {
            item = "";

            for (String rowItem : row) {
                item += TableStringUtil.pad(
                    itemTpl.replace("REPL", TableStringUtil.truncuate(rowItem, columnSize - 2)), columnSize - 1
                );
            }

            item += LINE_VERTICAL;
            render.add(item);
        }

        render.add(rowSeparatorTpl);

        return cache(render);
    }

    @Override
    public List<String> renderVertical() {
        if (isCached()) {
            return cache();
        }

        int headerLength = maxColumnLength();
        int rowLength = maxRowLength();

        final String rowSeparator = CORNER
            + TableStringUtil.repeat(LINE_HORIZONTAL, headerLength + 2)
            + CORNER
            + TableStringUtil.repeat(LINE_HORIZONTAL, rowLength + 2)
            + CORNER;
        final String lineFormat = LINE_VERTICAL
            + " COLUMN "
            + LINE_VERTICAL
            + " ROWITEM "
            + LINE_VERTICAL;

        List<String> render = new ArrayList<>();
        int columnIndex = 0;

        render.add(rowSeparator);

        for (List<String> row : rows) {
            for (String rowItem : row) {
                render.add(
                    lineFormat
                        .replace("COLUMN", TableStringUtil.pad(TableStringUtil.truncuate(columns.get(columnIndex), 38), headerLength))
                        .replace("ROWITEM", TableStringUtil.pad(TableStringUtil.truncuate(rowItem, 118), rowLength))
                );

                columnIndex++;
            }

            render.add(rowSeparator);
            columnIndex = 0;
        }

        return cache(render);
    }

    @Override
    public String toStringVertical() {
        return String.join("\n", renderVertical());
    }

    @Override
    public String toStringHorizontal() {
        return String.join("\n", renderHorizontal());
    }

    protected int maxColumnLength() {
        int maxLength = 0;

        for (String column : columns) {
            if (maxLength > column.length()) {
                continue;
            }

            maxLength = column.length();
        }

        return maxLength;
    }

    protected int maxRowLength() {
        int maxLength = 0;

        for (List<String> row : rows) {
            for (String rowItem : row) {
                if (maxLength > rowItem.length()) {
                    continue;
                }

                maxLength = rowItem.length();
            }
        }

        return maxLength;
    }
}
