# Compact Table

## Code

```java
JTablesBuilder<CompactTable> builder = CompactTable.build();
CompactTable table;

try {
    // Fill the table.
    table = builder.getTable();
} catch (MalformedTableException e) {
    table = null;
}

if (table != null) {
    // Use table. For example: System.out.println(table.toString());
}
```

## Output

The format is a comma-separated line of `Column: RowItem` for each row.

```
Name: Jon, Gender: Male, Location: United Kingdom
Name: John, Gender: Male, Location: United Kingdom
Name: kashike, Gender: Male, Location: Canada
Name: Zarthus, Gender: Male, Location: The Netherlands
```

## Positives

* Displays the same everywhere.
* Takes up very little space.

## Negatives

* Difficult to read.
* Scales poorly with more columns.
