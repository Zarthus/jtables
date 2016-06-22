# Building a table

A table consists of two components: Columns and rows.

In your source code, you will define columns before adding any rows.

```java
// Initialize the builder, with the table you desire to use.
builder = SomeTable.build();

// At any point during building the table may throw a MalformedTableException
// The exception message will tell you what exactly went wrong.
//
// When passing static content there is little to fear, but especially
// during dynamic insertions this will be useful.

// Start giving the table a list of columns it has to fill.
builder.columns("First Column", "Second Column");

// Fill it with rows, the builder permits you to chain all methods.
builder.row("A", "B")
    .row("C", "D");

// Print out your table to the good folks of your application
System.out.println(builder.getTable().toString());
```

## Making your own table

Your table needs to meet the following conditions:

* Either extend `AbstractTable` or implement `TableInterface`
    * Have a `render` method that returns a `List` of lines.

And may have the following:

* A static `build` method that returns the right Builder to use.

Caveat: `JTablesBuilder` is made for `AbstractTable`,
`TableBuilder` is made for tables that implement `TableInterface`

## Base Code (AbstractTable)

```java
package your.package;

import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.component.AbstractTable;

public class MyTable extends AbstractTable {
    public static JTablesBuilder<PlainTable> build() {
        return new JTablesBuilder<>(new PlainTable());
    }

    @Override
    public List<String> render() {
        if (isCached()) {
            return cache();
        }

        List<String> render = new ArrayList<>();

        if (isEmpty()) {
            render.add(getClass().getSimpleName() + ": There is nothing to display.");
            return render;
        }

        // TODO: Make something beautiful!

        return cache(render);
    }
}
```

## Base Code (AbstractTable)

```java
package your.pkg;

import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.component.AbstractTable;

public class MyTable extends AbstractTable {
    public static JTablesBuilder<PlainTable> build() {
        return new JTablesBuilder<>(new MyTable());
    }

    @Override
    public List<String> render() {
        if (isCached()) {
            return cache();
        }

        List<String> render = new ArrayList<>();

        if (isEmpty()) {
            render.add(getClass().getSimpleName() + ": There is nothing to display.");
            return render;
        }

        // TODO: Make something beautiful!

        return cache(render);
    }
}
```
