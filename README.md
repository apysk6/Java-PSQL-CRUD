Linux: Ubuntu 17.10
IDE: Intellij IDEA
Used database: PSQL (PostgreSQL)

PostgreSQL Configuration:
1. sudo apt-get update
2. sudo apt-get install postgresql postgresql-contrib
3. sudo -u postgres psql
4. CREATE USER 'username' WITH PASSWORD 'password';
5. ALTER USER 'username' WITH SUPERUSER;
6. CREATE DATABASE 'name';
7. \c database
8. run person.sql script to create Person table and records (\i */person.sql)
9. add "postgresql-version.jdbc.jar" to IntelliJ (File -> Project Structure -> Add)

Files in project:
1. Add Panel - panel that allows user to add records into database.
2. Connector - class that is responsible for the first connection with database (in LoggingPanel).
3. EditMenuPanel - panel that allows user to choose record that user want to edit from database.
4. EditsPanel - panel allows user to edit chosen record in previous panel.
5. Export - class that is responsible for exporting database into HTML file.
6. LoggingPanel - panel that allows user to connect with created database.
7. MenuPanel - menu panel with buttons where user can decide what user want to do.
