# JDBC
* JDBC stands for Java Database Connectivity access data rows and columns
* A relational database organises data into tables consisting of rows and columns.
* We use SQL (Structured Query Language) to interact with a relational database.
* Examples use a Derby database which is from Apache.

## CRUD Operations

| Operation | SQL keyword | Description                                   |
|-----------|-------------|-----------------------------------------------|
| Create    | INSERT      | Inserts a new row into the table              |
| Read      | SELECT      | Retrieves data from the table                 |
| Update    | UPDATE      | Changes data in 0 or more rows (in the table) |
| Delete    | DELETE      | Deletes 0 or more rows (from the table)       |

### Connecting to a database
* JDBC URL
  * protocol : subprotocol : subname
  * protocol is always jdbc
  * subprotocol is the vendor or product name
    * derby, mysql, oracle, postgress
  * subname is the database connection details:
    * `"jdbc:derby://localhost:1527/BANK_DB"`  // including the port
    * `"jdbc:derby://localhost/BANK_DB"`       // port is optional when using localhost
    * `"jdbc:derby://127.0.0.1/BANK_DB"`       // ip address for localhost

### PreparedStatement
* Both *PreparedStatement* and *CallableStatement* are asub-interfaces of *Statement*. We will talk about *CallableStatement* later; for the moment we will focus on *PreparedStatement*
* A *PreparedStatement* enables us to execute SQL statements.
* Use the *Connection* object to get a *PreparedStatement*.
  * *DriverManager* -> *Connection*
  * *Connection* -> *PreparedStatement*
  * *PreparedStatement* -> execute the SQL
* Once we have the *PreparedStatement* we can now run the SQL.
* **The type of SQL statement determines the way you run it.**
* SELECT
  * ResultSet rs = ps.**executeQuery()**;
* INSERT, UPDATE, DELETE
  * int rowsAffected = ps.**executeQuery();**
* SELECT, INSERT, UPDATE, DELETE
  * boolean isResultSet = ps.**execute();**
    * true - ResultSet rs = ps.**getResultSet();** // we ran a query
    * false - int rowsAffected = ps.**getUpdatedCount();** // we ran un 'update' 

### CallableStatement
* Rather than have your SQL as a *PreparedStatement* in your Java code which must be sent accross the network; we can have our SQL stored on the database in a "stored procedure"
* SQL can be stored in the database as *stored procedures*. This reduces network traffic.
* We use *CallableStatement* when we are working with stores procedures.
* We do not need to be able to read/write stored procedures for Java certification but we must know how to call/execute them.
* In addition to having no parameters, a stored procedure can specify the following parameters:
  * IN - an input parameter
  * OUT - an output parameter
  * INOUT - a parameter that serves for both input and output

### Resource Leaks
* Database resources are expensive to create so make sure you close them when finished.
* *try-with-resources* is very helpful here as it automatically closes resources for us.
* The order is important:
1. ResultSet
2. PreparedStatement or CallableStatement
3. Connection
* try-with-resources closes resources "automatically, in the reverse order from which they were initialized" [JLS]