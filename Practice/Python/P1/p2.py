class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        s = s.strip()
        return len(s[s.rfind(" "):]) - 1


s = Solution()
s.lengthOfLastWord("  Hello World  ")
