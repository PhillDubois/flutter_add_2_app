#!/bin/sh

pushd `dirname $0`

flutter pub run pigeon \
    --input pigeon/schema.dart \
    --dart_out lib/src/pigeon/api.dart \
    --objc_header_out ../../ios/Cashbee/Flutter/Pigeon/Api.h \
    --objc_source_out ../../ios/Cashbee/Flutter/Pigeon/Api.m \
    --objc_prefix CB
popd