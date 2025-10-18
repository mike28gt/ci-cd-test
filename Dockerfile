
# runtime (el jar lo construye Maven en CI)
FROM eclipse-temurin:22-jre-alpine

# directorio dentro del contenedor que será utilizado
WORKDIR /app

# se copia el jar desde mi equipo al contenedor /app/app.jar
COPY target/ci-cd-test-1.0.jar app.jar

# puerto del servicio que escucha la app
# no publica el puerto hacia el host; al ejecutar se debe mapear con 
# -p 8080:8080
EXPOSE 8080

# ejecuta el proceso como usuario no root
USER 10001

# inicia la aplicación ejecutando el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]