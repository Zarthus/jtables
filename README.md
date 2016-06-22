# Java Tabular Output Library
[![Build Status](https://travis-ci.org/netirc/jtables.svg?branch=master)](https://travis-ci.org/netirc/jtables)
[![codecov](https://codecov.io/gh/netirc/jtables/branch/master/graph/badge.svg?token=5lx0UPnByX)](https://codecov.io/gh/netirc/jtables)
[![license](https://img.shields.io/badge/license-MIT-brightgreen.svg)](LICENSE)

JTables (Java Tables) is a small and lightweight library that helps you generate tablular output in various predefined formats.

## Example Code

```java
import org.netirc.library.jtables.JTablesBuilder;
import org.netirc.library.jtables.exception.MalformedTableException;
import org.netirc.library.jtables.table.MonospaceTable;

public class MyCoolTable {
    public static void main(String[] args) {
        JTablesBuilder<MonospaceTable> builder = MonospaceTable.build();
        MonospaceTable table;

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

Produces:

```
+-----------------------------------------------------------------------+
| Service               | Offers API            | Website               |
+-----------------------------------------------------------------------+
| GitHub                | Yes                   | https://github.com    |
| BitBucket             | Yes                   | https://bitbucket.com |
| GitLab                | Yes                   | https://gitlab.com    |
+-----------------------------------------------------------------------+
```

Refer to the [documentation](docs) for example outputs and available tables.

## Picking the right table format

Tables are nice, but each table has their positive and negative things.

The [documentation](docs/tables) explains the cons and pros of each table.

## Including in your project

Maven:

```xml
<dependency>
    <groupId>org.netirc.library</groupId>
    <artifactId>jtables</artifactId>
    <version>1.0</version>
</dependency>
```

Gradle:

```groovy
compile 'org.netirc.library:jtables:1.0'
```

## Support

There are two primary means of support.

- Leave an [issue](https://github.com/netirc/jtables/issues/new) on the [GitHub Repository](https://github.com/netirc/jtables)
- Alternatively, you may ask your question through IRC on [EsperNet in #netirc](https://webchat.esper.net?channels=netirc&nick=jtables_...)

To allow us to help you, we suggest you seek out [the documentation](docs) and [contributing](CONTRIBUTING.md) files first.

If you believe you have found a security error, please email `security <at> netirc <dot> org`

## License

JTables is licensed under the [MIT license](LICENSE)
