# Plain Table

## Code

```java
JTablesBuilder<PlainTable> builder = PlainTable.build();
PlainTable table;

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

The format is a dash-separated (`----`) block of `Column: RowItem`.

```
Name: Jon
Gender: Male
Location: United Kingdom
----
Name: John
Gender: Male
Location: United Kingdom
----
Name: kashike
Gender: Male
Location: Canada
----
Name: Zarthus
Gender: Male
Location: The Netherlands
----
```

## Positives

* Displays the same everywhere.
