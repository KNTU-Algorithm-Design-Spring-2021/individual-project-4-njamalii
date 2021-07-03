class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(begin(boxTypes), end(boxTypes), [](auto &a, auto &b) {
            return a[1] > b[1];
        });
        int ans = 0;
        int a = 0;
        while ((a < boxTypes.size()) && (truckSize > 0)) {
            int m = min(truckSize, boxTypes[a][0]);
            ans += boxTypes[a][1] * m;
            truckSize -= m;
            a ++;
        }
        return ans;
    }
};
