#!/usr/bin/env node

const { getInstalledPath } = require('get-installed-path')
const path = require('path')

var args = process.argv.slice(2);

var dep = args[0]

getInstalledPath(dep, {
  local: true
}).then((p) => {
  //console.log(p)
  console.log(path.relative(process.cwd(), p))
})
.catch(function(reason) {
   // rejection
  console.log(`Installed package '${dep}' not found`)
  process.exit(1)
});
