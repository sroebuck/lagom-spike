#!/bin/sh

TAG=spike/jdk8-busybox

echo "Building..."
docker build -t $TAG -f .

echo "To test:"
docker run --rm $TAG java -version
