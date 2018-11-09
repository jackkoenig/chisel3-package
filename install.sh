#!/usr/bin/env bash
set -x

if [ ! -d chisel3 ]; then
  git clone --depth 1 git@github.com:freechipsproject/chisel3.git
fi

if [ -d ../node_modules/firrtl-package ]; then
  ln -s ../node_modules/firrtl-package
elif [ -d node_modules/firrtl-package ]; then
  ln -s node_modules/firrtl-package
else
  echo "ERROR: Couldn't find path to firrtl-package!"
  exit 1
fi

