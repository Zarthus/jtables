# Making your own Table

Tables are very easy to make by yourself. The first and foremost question
you need to ask yourself is whether you want to extend on the utility tools
JTables provides, or if you want to write something from the ground up.

For the purposes of this tutorial, we will assume we are extending the tools
JTables provides for us. When considering compatibility, there are two key differences:

* A base table (without JTables utilities) implements `TableInterface` rather than extending `AbstractTable`
* A base table should work with a `TableBuilder` rather than a `JTablesBuilder`.

## In short

Tables have access to a few internal variables, namely `columns` and `rows`.
These are `List`s that contain the columns and rows from the builder.

The idea is to supply a `render` method that renders the supplied data in to a
table format that you desire.

## The first step

A minimal table:
```java
class MyTable extends AbstractTable {
    // Optional method, but the library always provides one.
    public static JTablesBuilder<MyTable> build() {
        return new JTablesBuilder<>(new MyTable());
    }

    // The primary method: Here we render our table in to a
    // List of lines. Which will be joined in #toString with
    // a newline separator.
    public List<String> render() {
        List<String> render = new ArrayList<>();

        /* Do something with `columns` and `rows` here */

        return render;
    }
}
```

And a minimal example on how to use your table:
```java
public class Abc {
    public static void main(String[] args) {
        JtablesBuilder<MyTable> builder = MyTable.build();
        MyTable table;

        try {
            builder.columns("Service", "Offers API", "Website")
                .row("GitHub", "Yes", "https://github.com")
                .row("BitBucket", "Yes", "https://bitbucket.com")
                .row("GitLab", "Yes", "https://gitlab.com");
            table = builder.getTable();
        } catch (MalformedTableException e) {
            table = null;
        }

        if (table != null) {
            System.out.println(table.toString());
        } else {
            System.out.println("Something went wrong.");
        }
    }
}
```


### Implementing `OrientationRenderable`

Tables which can be rendered in two directions (vertically and horizontally) may
implement `OrientationRenderable`, [documentation for orientation can be found here](orientation.md).

### Testing your Table

The hardest part of writing a table is to keep it functional through means of tests.
We can't give good advice for this, but you can reference our tests to acquire inspiration.

Ultimately, printing out the table and having a human eye look at it is the most error free
test.

### Your table in JTables

If your table is good for commonplace use, feel free to submit a Pull Request to
get your table added to the library. Keep in mind the project's goal is to remain
rather minimal and small.

Although if there is demand we can create a `jtables-contrib`
repository where other people's tables can be pushed to.
