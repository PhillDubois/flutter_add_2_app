#!/bin/sh

pushd `dirname $0`

flutter pub run pigeon \
    --input pigeon/schema.dart \
    --dart_out lib/src/pigeon/api.dart \
    --java_out ../../android/app/src/main/java/fr/cashbee/cashbee/flutter/pigeon/PigeonApi.java \
    --java_package "fr.cashbee.cashbee.flutter.pigeon"
popd