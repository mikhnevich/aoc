with open("day2_input.txt") as f:
    content = f.readlines()

delta = {'U': (-1, 0), 'D': (1, 0), 'R': (0, 1), 'L': (0, -1)}

keypad1 = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
valid1 = {0: {0, 1, 2}, 1: {0, 1, 2}, 2: {0, 1, 2}}

keypad2 = [[0, 0, 1, 0, 0], [0, 2, 3, 4, 0], [5, 6, 7, 8, 9], [0, 'A', 'B', 'C', 0], [0, 0, 'D', 0, 0]]
valid2 = {0: {2}, 1: {1, 2, 3}, 2: set(range(5)), 3: {1, 2, 3}, 4: {2}}


def move(pos, dir, valid):
    y, x = pos
    dy, dx = delta[dir]
    ret = (y + dy, x + dx)
    if ret[1] in valid.get(ret[0], {}):
        return ret
    return pos


def solve(pos, keypad, valid):
    for st in content:
        for ch in st.rstrip():
            pos = move(pos, ch, valid)
        print(keypad[pos[0]][pos[1]], end="", flush=True)


solve((1, 1), keypad1, valid1)
print()
solve((2, 0), keypad2, valid2)
