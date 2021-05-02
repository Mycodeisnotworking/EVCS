#!/bin/bash
docker run -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=evc_dev -d mysql:8.0.24