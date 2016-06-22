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
import org.netirc.library.jtables.util.TableStringUtil;

import java.util.ArrayList;
import java.util.List;

public class UnicodeMonospaceTable extends AbstractTable implements OrientationRenderable {
    public static JTablesBuilder<UnicodeMonospaceTable> build() {
        return new JTablesBuilder<>(new UnicodeMonospaceTable());
    }

    private final String CORNER_POS_LTOP = "┌";
    private final String CORNER_POS_RTOP = "┐";
    private final String CORNER_POS_MTOP = "┬";

    private final String CORNER_POS_LMID = "├";
    private final String CORNER_POS_RMID = "┤";
    private final String CORNER_POS_MMID = "┼";

    private final String CORNER_POS_LBOT = "└";
    private final String CORNER_POS_RBOT = "┘";
    private final String CORNER_POS_MBOT = "┴";

    private final String LINE_HORIZONTAL = "─";
    private final String LINE_VERTICAL = "│";

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

        List<String> render = new ArrayList<>();

        if (isEmpty()) {
            render.add(getClass().getSimpleName() + ": There is nothing to display.");
            return render;
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

        final String rowSeparatorBase = TableStringUtil.repeat(
            TableStringUtil.repeat(LINE_HORIZONTAL, columnSize - 2) + "%tchar",
            columns.size()
        );
        // Remove the last %tchar, so we can lazily append it in the rowSeperatorPOS variables.
        final String rowSeparatorBaseSmall = rowSeparatorBase.substring(0, rowSeparatorBase.length() - 6);

        final String rowSeparatorTop = CORNER_POS_LTOP
            + rowSeparatorBaseSmall.replace("%tchar", CORNER_POS_MTOP)
            + CORNER_POS_RTOP;
        final String rowSeparatorMid = CORNER_POS_LMID
            + rowSeparatorBaseSmall.replace("%tchar", CORNER_POS_MMID)
            + CORNER_POS_RMID;
        final String rowSeparatorBot = CORNER_POS_LBOT
            + rowSeparatorBaseSmall.replace("%tchar", CORNER_POS_MBOT)
            + CORNER_POS_RBOT;

        final String itemTpl = LINE_VERTICAL + " REPL";

        render.add(rowSeparatorTop);

        String item = "";
        for (String column : columns) {
            item += TableStringUtil.pad(
                itemTpl.replace("REPL", TableStringUtil.truncuate(column, columnSize - 2)), columnSize - 1
            );
        }
        item += LINE_VERTICAL;
        render.add(item);
        render.add(rowSeparatorMid);

        for (List<String> row : rows) {
            item = "";

            for (String rowItem : row) {
                item += TableStringUtil.pad(
                    itemTpl.replace("REPL", TableStringUtil.truncuate(rowItem, columnSize - 4)), columnSize - 1
                );
            }

            item += LINE_VERTICAL;
            render.add(item);
        }

        render.add(rowSeparatorBot);

        return cache(render);
    }

    @Override
    public List<String> renderVertical() {
        if (isCached()) {
            return cache();
        }

        List<String> render = new ArrayList<>();

        if (isEmpty()) {
            render.add(getClass().getSimpleName() + ": There is nothing to display.");
            return render;
        }

        int headerLength = maxColumnLength();
        int rowLength = maxRowLength();

        final String rowSeparatorBase = TableStringUtil.repeat(LINE_HORIZONTAL, headerLength + 2)
            + "%tchar"
            + TableStringUtil.repeat(LINE_HORIZONTAL, rowLength + 2);
        final String rowSeparatorTop = CORNER_POS_LTOP
            + rowSeparatorBase.replace("%tchar", CORNER_POS_MTOP)
            + CORNER_POS_RTOP;
        final String rowSeparatorMid = CORNER_POS_LMID
            + rowSeparatorBase.replace("%tchar", CORNER_POS_MMID)
            + CORNER_POS_RMID;
        final String rowSeparatorBot = CORNER_POS_LBOT
            + rowSeparatorBase.replace("%tchar", CORNER_POS_MBOT)
            + CORNER_POS_RBOT;

        final String lineFormat = LINE_VERTICAL
            + " COLUMN "
            + LINE_VERTICAL
            + " ROWITEM "
            + LINE_VERTICAL;

        int columnIndex = 0;

        render.add(rowSeparatorTop);

        for (List<String> row : rows) {
            for (String rowItem : row) {
                render.add(
                    lineFormat
                        .replace("COLUMN", TableStringUtil.pad(TableStringUtil.truncuate(columns.get(columnIndex), 38), headerLength))
                        .replace("ROWITEM", TableStringUtil.pad(TableStringUtil.truncuate(rowItem, 118), rowLength))
                );

                columnIndex++;
            }

            render.add(rowSeparatorMid);
            columnIndex = 0;
        }

        render.remove(render.size() - 1);
        render.add(rowSeparatorBot);

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
