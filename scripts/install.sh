#!/bin/bash
echo "Installing Spring Boot App systemd service..."

cat <<EOF > /etc/systemd/system/ceybankrest.service
[Unit]
Description=Spring Boot App - CeyBankRest
After=network.target

[Service]
User=ec2-user
ExecStart=/usr/bin/java -jar /opt/ceybankrest/backend/*.jar
SuccessExitStatus=143
Restart=always
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF

systemctl daemon-reexec
systemctl daemon-reload
