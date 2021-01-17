spring-camel-etl

A spring boot camel route that processes csv files from file directory(can be used also with sftp server as source), extracts, validates and insert records in the db and move the files to success or error directories and uploads the csv files to an azure storage account. 
For azure storage azurite is used.