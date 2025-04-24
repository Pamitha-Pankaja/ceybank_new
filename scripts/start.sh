#!/bin/bash
echo "Starting Spring Boot App..."
systemctl enable ceybankrest.service
systemctl start ceybankrest.service
