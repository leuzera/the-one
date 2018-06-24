#! /usr/bin/env python
# coding=utf-8

import sys
import os

if len(sys.argv) < 3:
    sys.exit('Uso: %s input_file output_file' % sys.argv[0])

if not os.path.exists(sys.argv[1]):
    sys.exit('Erro: arquivo %s não exisite' % sys.argv[1])

out_file = open(sys.argv[2], 'w')

with open(sys.argv[1], 'r') as in_file:
    tempo = 0
    for line in in_file:
        line = line.strip()
        if line[0] == '[':
            tempo = line[1:-1]
        else:
            out_file.write('{0} {1}\n'.format(tempo, line))

out_file.close()



