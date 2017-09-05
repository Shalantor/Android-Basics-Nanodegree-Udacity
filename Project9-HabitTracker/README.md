# Habit Tracker

## Specifications

### Layout
* __Overall Layout:__ No UI is required for this project. 

### Functionality
* __Table Definition:__ There exists a contract class that defines name of table and constants.Inside the contract class, there is an inner class for each table created. 
* __Table Creation:__ There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade().
* __Data Insertion:__ There is a single insert method that adds at least two values of two different datatypes (e.g. INTEGER, STRING) into the database using a ContentValues object and the insert() method.
* __Data Reading:__ There is a single read method that returns a Cursor object. It should get the data repository in read and use the query() method to retrieve at least one column of data. 
* __External Libraries and Packages:__ No external libraries (e.g. Realm) are used for the database code, and no Content Providers is used. All data insertion and reading should be done using direct method calls to the SQLite database in the SQLiteOpenHelper class. 
