# Adv OOP Project - Ultan Kearns G00343745

## About Project
The aim of this project is to build an asynchronous language detection system using Java and Apache Tomcat

## Features
+ Detects language user enters via query which it compares to a subject database from file
+ parses the query and file and compares the kmers
+ Utilizes a queue for a multi-threaded application
+ Queue is polled periodically by checking for new job numbers then updating the relevant information
+ Allows for multiple queries to be submitted at a time

## Design Patterns
+ Did not use any design patterns due to personal issues and time constraints
+ I did however try to make the application as cohesive as possible as well as loosely coupled although there are a few coupled classes
## Extras
+ Allowed for parsing of both subject database and query text
+ Allowed for multiple options to parse the database into select size of kmers (1,2, or 3)
