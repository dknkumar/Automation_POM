#!/bin/bash
GIT_HASH=$(git rev-parse HEAD)
a=($(git diff --name-only ${GIT_HASH} origin/USSC | grep '\.feature' | sed 's/.feature$//' | xargs -n1 basename))
len=${#a[@]}
b=[]
## Use bash for loop
for (( i=0; i<${len}; i++ ))
do
 echo "${a[i]}"
done