#!/bin/bash
GIT_HASH=$(git rev-parse HEAD)
fileschanged=($(git diff --name-only ${GIT_HASH} origin/USSC | grep '\.feature' | sed 's/.feature$//' | xargs -n1 basename))
numberofFiles=${#fileschanged[@]}
echo $numberofFiles