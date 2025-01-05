# Use the official MySQL 8.0 image as the base image
FROM mysql:8.0.35

# Forcefully set environment variables to avoid auto-generation
ENV MYSQL_RANDOM_ROOT_PASSWORD=no
ENV MYSQL_ROOT_PASSWORD=root

# Copy the init.sql script to the directory where MySQL expects initialization scripts
COPY ./initdb/init.sql /docker-entrypoint-initdb.d/

# Debugging step: Log environment variables during build
RUN printenv > /env.log

# Expose the MySQL port (default is 3306)
EXPOSE 3306

# The default CMD provided by the MySQL image starts the MySQL server, so no need to modify it
