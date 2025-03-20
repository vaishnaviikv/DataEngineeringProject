DataEngineeringProject

A collection of Hadoop MapReduce examples for common big data processing tasks, including Word Count, PageRank, and Maximum Temperature. This project demonstrates basic to intermediate MapReduce patterns, as well as jar packaging and execution on a Hadoop cluster.

Key Folders and Files
MaxTemp: Demonstrates finding the maximum temperature from a dataset of temperature readings (often used as a classic introductory Hadoop example).
PageRanker: Illustrates a simplified PageRank algorithm that iterates over a graph dataset and computes rank scores for each node.
WordCount: The standard “Hello World” of MapReduce, which counts the frequency of words in a given input dataset.
HiveTrino.pdf: Contains documentation or notes on using Hive and Trino for SQL-based analytics in a big data environment.

Advanced Analytics (Hive and Trino)

For SQL-based queries on large datasets:
Refer to HiveTrino.pdf for setting up Hive or Trino on your Hadoop environment.
Create tables or external tables pointing to HDFS directories.
Run SQL queries (e.g., SELECT ... FROM table;) to validate or further analyze outputs from these MapReduce jobs.
