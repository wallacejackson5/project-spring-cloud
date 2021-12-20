#!/bin/bash
set -e

rm -fr /var/log/*.log

tail -f /dev/null
exec echo "Successfully started!"