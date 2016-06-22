# Orientation

Tables that implement the `OrientationRenderable` interface permit rendering with
columns horizontally or vertically. When you call `table.renderHorizontal()` you are
asking the class for a table rendered with horizontal columns.

## Default `render` call
All JTables provided tables, by default, will go for a horizontal table layout if the column
count is equal or less than 4. If there are more than 4 columns it will render vertically.

## Orientation and Caching
The default JTables tables do not account for orientation when caching. If you are working on a
table with orientation, the options available to you are to create more than one table instance,
call the `clearCache()` method, or to never enable caching in the first place (and optionally
do the caching yourself).

If you have enabled caching and are requesting a horizontalRender while a vertical render is cached,
you will get a vertical render returned.

### The interface

You can view the [full interface here](../src/main/java/org.netirc.library.jtables/component/OrientationRenderable.java)
