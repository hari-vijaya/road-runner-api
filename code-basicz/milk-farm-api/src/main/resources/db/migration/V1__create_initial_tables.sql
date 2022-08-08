CREATE TABLE CUSTOMER (
       id VARCHAR(50),
       customer_code VARCHAR(50) NOT NULL unique,
       customer_name VARCHAR(50) NOT NULL,
       mobile_no VARCHAR(10),
       route_name VARCHAR(50) NOT NULL,
       village VARCHAR(50),
       supervisor VARCHAR(50) NOT NULL,
       created_by VARCHAR(50) NOT NULL,
       updated_by VARCHAR(50) NOT NULL,
       created_on DATETIME NOT NULL,
       updated_on DATETIME NOT NULL,
       PRIMARY KEY(id)
);