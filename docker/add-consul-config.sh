#!/bin/bash

# path, serviceName, servicePort
function addEntry() {
	result=$(curl -s -X PUT -d $3 http://localhost:8500/v1/kv/$1/$2)
	output=$(curl -s http://localhost:8500/v1/kv/$1/$2?raw)
	echo "$2 -> $output: $result"
}

addEntry 'service/backend/services' 'echoservice' '9000'

echo "Results:"
curl http://localhost:8500/v1/kv/service?recurse
