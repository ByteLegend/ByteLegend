## NGINX

```
docker run -d -p 443:443 -v `pwd`/../bytelegend-secret/bytelegend.com:/cert -v `pwd`/deployment/nginx/nginx.conf:/etc/nginx/nginx.conf nginx
```