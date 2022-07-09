# 自我生长-Java后端服务
***

## 应用镜像构建
```shell
cd auth
docker build -t lw1243925457/auth-java:v1 .
docker push lw1243925457/auth-java:v1

cd record
docker build -t lw1243925457/record-server-java:v1 .
docker push lw1243925457/record-server-java:v1

cd task
docker build -t lw1243925457/task-server-java:v1 .
docker push lw1243925457/task-server-java:v1
```