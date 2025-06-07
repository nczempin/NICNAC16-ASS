#!/usr/bin/env bash
set -e

# verify g++
if ! command -v g++ >/dev/null; then
  echo "g++ not found" >&2
  exit 1
fi

echo "g++ version:" $(g++ --version | head -n1)

# verify make
if ! command -v make >/dev/null; then
  echo "make not found" >&2
  exit 1
fi

echo "make version:" $(make --version | head -n1)

echo "Environment looks good."
