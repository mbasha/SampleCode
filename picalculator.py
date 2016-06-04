from __future__ import print_function
import sys, decimal
from decimal import *

# Uses the Bailey-Borwein-Plouffe formula
# https://en.wikipedia.org/wiki/Bailey-Borwein-Plouffe_formula
def calculatePi(i):
    D = decimal.Decimal
    with decimal.localcontext() as ctx:
        ctx.prec = i + 1
        #ctx.rounding = ROUND_FLOOR
        pi = sum(1/Decimal(16)**k *
		(Decimal(4)/(8*k+1) - Decimal(2)/(8*k+4) - Decimal(1)/(8*k+5) - Decimal(1)/(8*k+6))
		# k is the amount of iterations of the summation. I selected 100
		for k in range (100))
    return pi


def shell():
	entry = int(input("Welcome to Abrar Basha's Pi Calculator. Enter the number of digits of Pi you want to see or enter 0 to exit\n"))

	while True:
		if entry == 0:
			break
		else:
			print (calculatePi(entry))
			entry = int(input("Enter the number of digits of Pi you want to see or enter 0 to exit\n"))

if __name__=='__main__':
	shell()
