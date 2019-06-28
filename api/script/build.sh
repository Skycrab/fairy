#!/usr/bin/env bash
{
    CUR="$(cd $(dirname $0); pwd)"
    BD_DIR="$(cd $(dirname $0)/..; pwd)"
    MODULE_DIR="$BD_DIR"
    OUT_DIR="$MODULE_DIR/output"

    cd $MODULE_DIR
    rm -rf $OUT_DIR/*

    mkdir -p $OUT_DIR/lib
    mkdir -p $OUT_DIR/bin

    source $CUR/setenv.sh

    profile="prod"
    if [ $# -ge 1 ]; then
        if [ "$1" != "online" -a "$1" != "test" -a "$1" != "dev" -a "$1" != "pre" ]; then
            profile="prod"
        else
            profile="$1"
        fi
    fi

    export profile=$profile

    controller="control.sh"
    if [ $profile = "online" ]; then
        controller="control.sh"
    fi

    #start build
    echo "Start building ......"
    cd $BD_DIR
    $BD_DIR/../gradlew clean
    $BD_DIR/../gradlew build -x test -Pprofile=$profile
    mv $MODULE_DIR/build/libs/*.jar $OUT_DIR/lib/cloud-api.jar &&
    cp $CUR/$controller $OUT_DIR/control.sh &&
    cp $CUR/setenv.sh $OUT_DIR/bin/ &&
    ls $OUT_DIR/ &&
    exit 0
} || {
    echo "build failed!"
    exit 1
}