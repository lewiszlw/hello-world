# coding: utf-8

import pysnooper
import sys

@pysnooper.snoop()
def number_to_bits(number):
    if number:
        bits = []
        while number:
            number, remainder = divmod(number, 2)
            bits.insert(0, remainder)
        return bits
    else:
        return [0]

def divmod(number, dividend=2):
    remainder = number % dividend
    number = number // dividend
    return number, remainder

print(number_to_bits(6))