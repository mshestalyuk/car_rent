FROM postgres:latest
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin
ENV POSTGRES_DB=carrent
COPY ./carrent.sql /docker-entrypoint-initdb.d/
