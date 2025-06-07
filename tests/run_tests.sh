#!/usr/bin/env bash
set -e
DIR="$(cd "$(dirname "$0")" && pwd)"
OUTPUT=$("$DIR/../nicnac16-asm" "$DIR/../testSymbols.asm")
echo "$OUTPUT" > /tmp/out.txt
grep -q "Label: START, value: 7" /tmp/out.txt
grep -q "writing: 1007" /tmp/out.txt
grep -q "ENDLESS: 9" /tmp/out.txt
