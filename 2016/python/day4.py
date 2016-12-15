from functools import reduce
with open("d:/work/aoc2016/problems/day4_input.txt") as f:
    content = f.readlines()

cntH = 0
for st in content:
    a, b, c = map(int, st.rstrip().split())
    if (a + b > c) and (a + c > b) and (b + c > a):
        cntH += 1
print(cntH)

cnt = 0
x = [list(map(int, l.split())) for l in content]
for x, y, z in zip(x[::3], x[1::3], x[2::3]):
    for a, b, c in zip(x, y, z):
        if not (a + b <= c or b + c <= a or a + c <= b):
            cnt += 1
print(cnt)
print("sdf", end="", flush=True)

def divisors(n):
    return list(filter(lambda a: n%a == 0, list(range(2,int(n/2)+1))))

# import sys
#
# from itertools import izip_longest
#
# args = [iter([int(x) for x in line.split()] for line in sys.stdin)] * 3
#
# print sum(sum(y[0] + y[1] > y[2]
#               for y in [sorted(tri) for tri in zip(*x)])
#           for x in izip_longest(*args))