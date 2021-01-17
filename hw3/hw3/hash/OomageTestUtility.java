package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        HashMap<Integer, Integer> bucketNumToCount = new HashMap<>();
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            if (bucketNumToCount.containsKey(bucketNum)) {
                bucketNumToCount.put(bucketNum, bucketNumToCount.get(bucketNum) + 1);
            }
            else {
                bucketNumToCount.put(bucketNum, 1);
            }
        }
        for (Integer num : bucketNumToCount.keySet()) {
            if (bucketNumToCount.get(num) < N /50 || bucketNumToCount.get(num) > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
