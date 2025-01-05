CREATE DATABASE IF NOT EXISTS defense_force;

use mysql;
-- Ensure the root user exists and has the correct password
CREATE USER IF NOT EXISTS 'sdf'@'%' IDENTIFIED BY 'root';
CREATE USER IF NOT EXISTS 'sdf'@'localhost' IDENTIFIED BY 'root';
-- CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY 'root';
-- CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY 'root';

-- Reset the root user's password in case it's overwritten
ALTER USER 'sdf'@'%' IDENTIFIED BY 'root';
ALTER USER 'sdf'@'localhost' IDENTIFIED BY 'root';
-- ALTER USER 'root'@'%' IDENTIFIED BY 'root';
-- ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';

-- Grant full privileges to the root user
GRANT ALL PRIVILEGES ON *.* TO 'sdf'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'sdf'@'localhost';
-- GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';

FLUSH PRIVILEGES;
