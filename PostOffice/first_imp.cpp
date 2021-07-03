class Solution {
public:
    int maximumUnits(vector<vector<int>>& boxTypes, int truckSize) {
        sort(begin(boxTypes), end(boxTypes), [](auto &a, auto &b) {
            return a[1] < b[1];
        });
        int ans = 0;
        int a = boxTypes.size() - 1;
        while (truckSize > 0) {            
            ans += boxTypes[a][1];
            truckSize --;
            if (--boxTypes[a][0] == 0) {
                a --;
                if (a < 0) break;
            }
        }
        return ans;
    }
};
