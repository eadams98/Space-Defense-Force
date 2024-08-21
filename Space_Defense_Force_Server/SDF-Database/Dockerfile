FROM mysql:8.0.35

WORKDIR /app

ADD ../initdb/init.sql /docker-entrypoint-initdb.d

EXPOSE 3000

CMD [ "executable" ]
