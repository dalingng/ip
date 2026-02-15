#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean jar

cd text-ui-test

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) --command < input.txt > ACTUAL.TXT

cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix EXPECTED-UNIX.TXT ACTUAL.TXT
diff EXPECTED-UNIX.TXT ACTUAL.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    rm ./data/duke.txt
    exit 0
else
    echo "Test failed!"
    rm ./data/duke.txt
    exit 1
fi