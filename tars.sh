#!/usr/bin/env bash

PROTOCOLS=(epidemic prophet sprayandwait dlife)
SCENERYS=(esparso denso)
NRO_BATCH=1

for protocol in ${PROTOCOLS[@]}; do
    for scenery in ${SCENERYS[@]}; do
        ./one.sh -b ${NRO_BATCH} basic_settings.txt ${protocol}.txt ${scenery}.txt > /tmp/${protocol}_${scenery} 2>&1 &
        echo "Running ${protocol} ${scenery}"
    done
done

wait
echo "All simulations ended"