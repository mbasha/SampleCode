from __future__ import print_function
import sys, decimal, math
from decimal import *

# Uses the Bailey-Borwein-Plouffe formula
# https://en.wikipedia.org/wiki/Bailey-Borwein-Plouffe_formula
def calculatePi(i):
    D = decimal.Decimal
    with decimal.localcontext() as ctx:
        ctx.prec = i + 1
        pi = sum(1/Decimal(16)**k *
		(Decimal(4)/(8*k+1) - Decimal(2)/(8*k+4) - Decimal(1)/(8*k+5) - Decimal(1)/(8*k+6))
		# k is the amount of iterations of the summation. I selected 100
		for k in range (100)) 
    return pi

def truncate(f, n):
    return math.floor(f * 10 ** n) / 10 ** n

def shell():
	entry = int(input("Welcome to Abrar Basha's Pi Calculator. Enter the number of digits of Pi you want to see or enter 0 to exit\n"))
    
	while True:
		if entry == 0:
			break
		elif isinstance(entry, str):
			entry = int(input("This is not a number. Enter the number of digits of Pi you want to see or enter 0 to exit\n"))
		else:
			finalValue = truncate(calculatePi(entry+1), entry)
			print (finalValue)
			#print(caculatePi(entry)) #use this instead of the previous two lines if you want more than 11 digits. not really sure why the truncate is confining me to 11 digits. 
			entry = int(input("Enter the number of digits of Pi you want to see or enter 0 to exit\n"))

if __name__=='__main__':
	shell()
