data = "R4, R4, L1, R3, L5, R2, R5, R1, L4, R3, L5, R2, L3, L4, L3, R1, R5, R1, L3, L1, R3, L1, R2, R2, L2, R5, L3, L4, R4, R4, R2, L4, L1, R5, L1, L4, R4, L1, R1, L2, R5, L2, L3, R2, R1, L194, R2, L4, R49, R1, R3, L5, L4, L1, R4, R2, R1, L5, R3, L5, L4, R4, R4, L2, L3, R78, L5, R4, R191, R4, R3, R1, L2, R1, R3, L1, R3, R4, R2, L2, R1, R4, L5, R2, L2, L4, L2, R1, R2, L3, R5, R2, L3, L3, R3, L1, L1, R5, L4, L4, L2, R5, R1, R4, L3, L5, L4, R5, L4, R5, R4, L3, L2, L5, R4, R3, L3, R1, L5, R5, R1, L3, R2, L5, R5, L3, R1, R4, L5, R4, R2, R3, L4, L5, R3, R4, L5, L5, R4, L4, L4, R1, R5, R3, L1, L4, L3, L4, R1, L5, L1, R2, R2, R4, R4, L5, R4, R1, L1, L1, L3, L5, L2, R4, L3, L5, L4, L1, R3"
data = data.replace(" ", "").split(",")


def solve1(data):
    v = (0, 1)
    x = 0
    y = 0
    for item in data:
        l = int(item[1:])
        v = (v[1], -v[0]) if item[0] == "R" else (-v[1], v[0])
        x += v[0] * l
        y += v[1] * l
    return abs(x) + abs(y)


def solve2(data):
    v = (0, 1)
    x = 0
    y = 0
    seen = set()
    seen.add((0, 0))
    for item in data:
        l = int(item[1:])
        v = (v[1], -v[0]) if item[0] == "R" else (-v[1], v[0])
        for ad in range(l):
            x += v[0]
            y += v[1]
            if (x, y) in seen: return abs(x) + abs(y)
            seen.add((x, y))

print(solve1(data))
print(solve2(data))