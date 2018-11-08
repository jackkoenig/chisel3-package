#!/usr/bin/env bash

if [ ! -d chisel3 ]; then
  git clone git@github.com:freechipsproject/chisel3.git
fi

# Create symbolic link to firrtl
ln -fs `./find.js firrtl-package`

