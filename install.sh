#!/usr/bin/env bash
set -x

if [ ! -d chisel3 ]; then
  git clone --depth 1 git@github.com:freechipsproject/chisel3.git
fi

echo `pwd`
ls
ls ..
ls ../..
# Create symbolic link to firrtl
ln -fs `./find.js firrtl-package`

