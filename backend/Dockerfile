# Étape 1 : Construire l'application
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /workspace/app

# Copier uniquement les fichiers nécessaires pour la phase de build
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests && rm -rf ~/.m2/repository

# Étape 2 : Créer une image mince
FROM eclipse-temurin:17-jre-alpine
WORKDIR /workspace/app

# Installer les dépendances et nettoyer les fichiers inutiles en une seule couche
RUN apk update && \
    apk add --no-cache curl && \
    addgroup -S appgroup && \
    adduser -S amor -G appgroup && \
    rm -rf /var/cache/apk/* /tmp/* /var/tmp/* /usr/share/man /usr/share/doc

# Passer à l'utilisateur non root
USER amor

# Copier le fichier JAR généré depuis l'étape de construction
COPY --from=build /workspace/app/target/*.jar ./app.jar

# Exposer le port et définir l'entrée
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]