FROM openjdk:16-slim
RUN groupadd runner && useradd -g runner runner
RUN mkdir /app
COPY build/install/api /app/
WORKDIR /app/bin
USER runner
CMD ["./api"]