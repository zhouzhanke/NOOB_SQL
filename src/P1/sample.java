package P1;

public class sample {
    String  select = "SELECT * FROM table_1\n" +
            "Returns all the data in table_1 (all rows, all columns)\n\n" +

            "SELECT * FROM table_1 WHERE condition\n" +
            "Returns all rows in table_1 that match the condition. All columns returned (the * means all columns)\n\n" +

            "SELECT col_1, col_2 FROM table_1\n" +
            "Returns columns col_1 and col_2 from table_1 (all rows)\n\n" +


            "SELECT  DISTINCT col_1 , col_2 \n" +
            "FROM table_1\n" +
            "WHERE condition\n" +
            "Return col_1 , col_2 from table_1 (only rows with distinct value)\n\n" +


            "SELECT col_1, COUNT(col_1), SUM(col_2), AVG(col_3), MIN(col_4), MAX(col_5)\n" +
            "FROM...\n" +
            "return the numbers of rows in col_1, total value in col_2, average value in col_3, minimum value in col_4, maximum value " +
            "in col_5 from the table and group by col_1";

    String join = "SELECT...\n" +
            "FROM\n" +
            "A INNER JOIN B ON conditionX\n" +
            "WHERE..." +
            "Return the data from new table where " +
            "row in A combine with row in B when the condition is true\n\n" +

            "SELECT...\n" +
            "FROM\n" +
            "A LEFT OUTER JOIN B ON conditionX\n" +
            "WHERE..." +
            "Return the data from new table where " +
            "row in A combine with row in B when the condition is true " +
            "row in A combine with NULL when the condition is false\n\n" +

            "SELECT...\n" +
            "FROM\n" +
            "A RIGHT OUTER JOIN B ON conditionX\n" +
            "WHERE..." +
            "Return the data from new table where " +
            "row in A combine with row in B when the condition is true " +
            "NULL combine with row in B when the condition is false\n\n" +

            "SELECT...\n" +
            "FROM\n" +
            "A FULL OUTER JOIN B ON conditionX\n" +
            "WHERE..." +
            "Return the data from new table where " +
            "row in A combine with row in B when the condition is true " +
            "row in A combine with NULL or NULL combine with row in B when the condition is false\n\n";

    String select_case = "SELECT\n" +
            "CASE\n" +
            "WHEN condition THEN 'yes'\n" +
            "ELSE 'NO'\n" +
            "END\n" +
            "FROM...\n" +
            "WHERE...\n" +
            "if statement in SQL, if condition is true return 'yes' on that row, else return 'no'\n\n";


    String as = "SELECT col_1 As a, col_2 As b\n" +
            "FROM...\n" +
            "WHERE...\n" +
            "Rename col_1 As a, col_2 As b when returns columns col_1 and col_2 from the table (all rows)\n\n" +

            "SELECT...FROM (SELECT...FROM...WHERE...) AS table_1 WHERE...\n" +
            "create a temporary table and use it";

    String select_others = "SELECT...FROM...WHERE...UNION\n" +
            "SELECT...FROM...WHERE...\n\n" +
            "SELECT...FROM...WHERE...UNION ALL\n" +
            "SELECT...FROM...WHERE...\n\n" +
            "SELECT...FROM...WHERE...INTERSECT\n" +
            "SELECT...FROM...WHERE...\n\n" +
            "SELECT...FROM...WHERE...INTERSECT ALL\n" +
            "SELECT...FROM...WHERE...\n\n" +
            "SELECT...FROM...WHERE...EXCEPT\n" +
            "SELECT...FROM...WHERE...\n\n" +
            "SELECT...FROM...WHERE...EXCEPT ALL\n" +
            "SELECT...FROM...WHERE...\n\n" +
            "SELECT...FROM A CROSS JOIN B WHERE...\n\n" +
            "SELECT...FROM...HAVING MIN(year) >= 2000\n" +
            "HAVING similar to WHERE but can use COUNT, SUM, AVG, MIN, MAX...\n\n" +
            "SELECT...FROM...WHERE...ORDER BY a\n" +
            "sort the result by column a";

    String with_select = "WITH\n" +
            "table_1 AS(SELECT...FROM...WHERE...),\n" +
            "table_2 AS(SELECT...FROM...WHERE...)\n" +
            "SELECT...FROM table_1 INNER JOIN table_2 WHERE...\n" +
            "save SQL quarry code as temporary table and use it.";

    String insert = "INSERT INTO table_1 (col1, col2, ..., colN)\n" +
            "VALUES (val1, val2, ..., valN)\n" +
            "add a row to the table with specific column\n\n" +
            "INSERT INTO table\n" +
            "VALUES (val1, val2, ..., valN)\n" +
            "add a row to the table_1";

    String delete = "DELETE FROM table_1\n" +
            "WHERE conditions\n" +
            "delete rows from table_1 when condition is true\n" +
            "if no condition all the row will be deleted";

    String update = "UPDATE table_1\n" +
            "SET\n" +
            "column = value,\n" +
            "other_column = other_value\n" +
            "WHERE conditions\n" +
            "update a table_1 when condition is true\n" +
            "if no condition all the row will be updated";

    String create = "CREATE TABLE table_1 (\n" +
            "col1 DATA_TYPE,\n" +
            "col2 DATA_TYPE,\n" +
            "...,\n" +
            "colN DATA_TYPE,\n" +
            "PRIMARY KEY(column_names)\n" +
            "create a new table with col1...colN" +
            ")\n\n" +
            "common DATA_TYPE:" +
            "CHAR(length) – fixed-length strings of length chars\n" +
            "VARCHAR(length) – strings of up to length chars\n" +
            "INTEGER – integers\n" +
            "REAL – floats\n" +
            "DOUBLE PRECISION – doubles\n" +
            "NUMERIC(n,p) – number of n digits, p after decimal\n" +
            "DATE – date value\n" +
            "TIMESTAMP – a date and time\n" +
            "TIME – time of day\n" +
            "INTERVAL – date/time interval or duration\n";

    String drop = "DROP TABLE table_1\n" +
            "delete the table_1 from database";
}
