# --- GIAI ĐOẠN 1: BUILD ---
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Tối ưu cache: tải dependencies trước để hạn chế lỗi mạng/đứt quãng khi build lại
COPY pom.xml ./
COPY .mvn ./.mvn

RUN --mount=type=cache,target=/root/.m2 \
		mvn -B -ntp -DskipTests \
			-Dmaven.wagon.http.retryHandler.count=3 \
			dependency:go-offline

# Copy source code
COPY src ./src

# Build file jar
RUN --mount=type=cache,target=/root/.m2 \
		mvn -B -ntp -DskipTests \
			-Dmaven.wagon.http.retryHandler.count=3 \
			clean package


# --- GIAI ĐOẠN 2: RUN ---
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy file jar từ stage builder
COPY --from=builder /app/target/*.jar app.jar

# Mở port
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]