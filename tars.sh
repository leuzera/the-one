#!/usr/bin/env bash

PROTOCOLS=(epidemic.txt prophet.txt sprayandwait.txt dlife.txt)
SCENERYS=(esparso.txt denso.txt)
NRO_BATCH=1

for protocol in ${PROTOCOLS[@]}; do
    for scenery in ${SCENERYS[@]}; do
        ./one.sh -b ${NRO_BATCH} basic_settings.txt ${protocol} ${scenery} > /dev/null 2>&1 &
    done
done

wait
echo "\nAll simulations ended\n"