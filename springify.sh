#!/bin/bash

cd springify
mvn clean install
cd ..
java -jar springify/target/springify-1.0.jar
