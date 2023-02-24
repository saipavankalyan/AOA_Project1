import sys
import random

def generate_testcases(n, m):
    print(n, m)
    times = []
    for i in range(1, m+1):
        start = random.randrange(1, n+1)
        end = random.randrange(start, n+1)
        times.append((start, end))
    times = sorted(times)
    for time in times:
        print(time[0], time[1])

n = len(sys.argv)
if n != 3:
    print("Enter n and m value")
else:
    generate_testcases(int(sys.argv[1]), int(sys.argv[2]))