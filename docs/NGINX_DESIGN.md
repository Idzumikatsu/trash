# NGINX Design

## Target Architecture

Traffic reaches the application through an NGINX reverse proxy.
The Spring Boot backend communicates with PostgreSQL while NGINX exposes metrics through a standalone Prometheus exporter process.

```
Client ---> NGINX ---> App ---> PostgreSQL
                |
                +--> Prometheus Exporter
```

NGINX is deployed as a standalone service in front of the application.
The application runs as a single instance, so no load balancing is required.
Clients today support HTTP/1.1 and HTTP/2, so we plan to enable HTTP/2 on the public interface. HTTP/3 is not required yet.

NGINX configuration is rolled out using the standard update strategy to minimise risk.
The approved architecture and these decisions are now recorded in this design document.
