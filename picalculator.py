# using this guy as a base: https://github.com/geekpradd/PythonPi/blob/master/PythonPi.py
# TODO: Change calculatePi to Chudnovsky Algorithm for accuracy.
 	# http://www.craig-wood.com/nick/articles/pi-chudnovsky/

from __future__ import print_function
import math, sys
from math import factorial
from decimal import *
getcontext().rounding = ROUND_FLOOR
sys.setrecursionlimit(100000)

python2 = sys.version_info[0] == 2
if python2:
	input = raw_input

def calculatePi(i):
	int(i)
	print (sum(1/Decimal(16)**k *
          (Decimal(4)/(8*k+1) -
           Decimal(2)/(8*k+4) -
           Decimal(1)/(8*k+5) -
           Decimal(1)/(8*k+6)) for k in range(i)))

def shell():
	print ("Welcome to Pi Calculator. In the shell below Enter the number of digits upto which the value of Pi should be calculated or enter quit to exit")

	while True:
		print (">>> ", end='')
		entry = input()
		if entry == "quit":
			break
		if not entry.isdigit():
			print ("You did not enter a number. Try again")
		else:
			print (calculatePi(entry))
			#getcontext().prec=entry

if __name__=='__main__':
	shell()
