# 使用 openjdk 8 作为基础镜像
FROM openjdk:8-jdk-slim
# 将项目 jar 包添加到镜像中
ADD target/customer-system.jar app.jar
# 设置环境变量
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]
