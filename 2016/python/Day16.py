# by blockingthesky
def check(d):
    ret = ''
    for i in range(0, len(d), 2):
        ret += '1' if d[i] == d[i + 1] else '0'
    return ret

def csum(data, l):
    data = data[:l]
    while len(data) % 2 == 0:
        data = check(data)
    return data

def nt(a):
    b = a[::-1]
    b = ''.join('0' if i == '1' else '1' for i in b)
    return a + '0' + b

inp = "00111101111101000"
while len(inp) < 272:
    inp = nt(inp)
print "Part 1:", csum(inp, 272)

inp = "00111101111101000"
while len(inp) < 35651584:
    inp = nt(inp)
print "Part 2:", csum(inp, 35651584)
