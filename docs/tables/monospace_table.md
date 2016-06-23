# Monospace Table

## Code

```java
JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();
MonospaceTable table;

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

## Output (horizontal)

The format is a ...

```
+-----------------------------------------------------+
| Name            | Gender          | Location        |
+-----------------------------------------------------+
| Jon             | Male            | United Kingdom  |
| John            | Male            | United Kingdom  |
| kashike         | Male            | Canada          |
| Zarthus         | Male            | The Netherlands |
+-----------------------------------------------------+
```

## Output (vertical)

The format is a ...

```
+----------+-----------------+
| Name     | Jon             |
| Gender   | Male            |
| Location | United Kingdom  |
+----------+-----------------+
| Name     | John            |
| Gender   | Male            |
| Location | United Kingdom  |
+----------+-----------------+
| Name     | kashike         |
| Gender   | Male            |
| Location | Canada          |
+----------+-----------------+
| Name     | Zarthus         |
| Gender   | Male            |
| Location | The Netherlands |
+----------+-----------------+
```

## Positives

* Easy to read.
* Perfect for key => value display.

## Negatives

* Requires a Monospace font to display properly.
