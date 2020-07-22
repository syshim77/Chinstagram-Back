#!/bin/bash

# RUN APPLICATION
java -cp ./:/chinstagram/operation/  \
    -Xms1024m -Xmx1024m \
    -jar /chinstagram/*.war \
    --server.port=8080 "$@"