#!/bin/bash

mvn clean package

docker build -t registry.adamassoft.it/template_manager . && \
docker push registry.adamassoft.it/template_manager && \
curl -X POST http://goldrake:9000/api/webhooks/89dfe21e-e492-4512-9cdc-63f31f49c706
