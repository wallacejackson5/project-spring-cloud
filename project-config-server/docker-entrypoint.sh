#!/bin/bash
set -e

rm -fr /var/log/*.log
cat > /var/log/app.log

#/opt/metricbeat-7.10.2-linux-x86_64/./metricbeat -e -c /opt/metricbeat-7.10.2-linux-x86_64/metricbeat.yml &

java -cp /app/resources:/app/classes:/app/libs/* com.example.demo.Application