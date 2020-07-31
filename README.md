# Chinstagram

Chinstagram BE repository from '친해지길바래' team.



### How to run

```bash
# Run Database
docker run --name mysql-db -p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=landvibe \
-e MYSQL_DATABASE=chinstagram \
-e MYSQL_USER=admin \
-e MYSQL_PASSWORD=admin -d mysql

# Run Server
cd Chinstagram/
./gradlew build -x test
cp build/libs/*.war docker/war
cd docker/
docker build -t chinstagram .
docker run -d --name=chinstagram \
-p 8080:8080 chinstagram

# Run Nginx
docker run --name nginx \
-v ~/Chinstagram-Back/Chinstagram/docker/nginx/nginx.conf:/etc/nginx/nginx.conf:ro \
-p 80:80 -d nginx
```



