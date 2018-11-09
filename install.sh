#!/usr/bin/env bash
set -x

if [ ! -d chisel3 ]; then
  git clone --depth 1 git@github.com:freechipsproject/chisel3.git
fi

echo `pwd`
ls
ls ..
ls ../..
P=`./find.js firrtl-package`
echo $P
# Create symbolic link to firrtl
ln -fs $P

