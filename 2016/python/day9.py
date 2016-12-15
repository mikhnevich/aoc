# (c) blockingthesky https://www.reddit.com/r/adventofcode/comments/5hbygy/2016_day_9_solutions/daz279z/
inp = open('../../problems/day9.txt').read().strip()

def decompress(s, recursive):
    if '(' not in s:
        return len(s)
    ret = 0
    while '(' in s:
        ret += s.find('(')
        s = s[s.find('('):]
        marker = s[1:s.find(')')].split('x')
        s = s[s.find(')') + 1:]
        if recursive:
            ret += decompress(s[:int(marker[0])], recursive) * int(marker[1])
        else:
            ret += len(s[:int(marker[0])]) * int(marker[1])
        s = s[int(marker[0]):]
    ret += len(s)
    return ret

print(decompress(inp, False))
print(decompress(inp, True))
