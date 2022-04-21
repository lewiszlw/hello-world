#!/usr/bin/python
import sys

class NullFilter:

    def execute(self):
        #Read data from STDIN...
        data = ""
        for line in sys.stdin.readline():
            data = data + line
            #Write data to STDOUT...
        data += "end"
        sys.stdout.write(data)
        exit(0)
if __name__ == '__main__':
    nf = NullFilter()
    nf.execute()