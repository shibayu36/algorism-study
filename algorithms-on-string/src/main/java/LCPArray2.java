import java.util.Arrays;
import java.util.List;

public class LCPArray2 {
  public static List<Integer> make(String string, List<Integer> suffixArray) {
    string += "\0"; // 番兵を入れておく

    int size = suffixArray.size();
    Integer[] rank = new Integer[size];
    for (int i = 0; i < size; i++) {
      rank[suffixArray.get(i)] = i;
    }

    Integer[] lcpArray = new Integer[size];
    Arrays.fill(lcpArray, 0);
    int lcp = 0;
    for (int i = 0; i < size; i++) {
      int index = rank[i];
      int pos1 = suffixArray.get(index);
      if (index == size - 1) {
        lcpArray[index] = lcp = 0;
        continue;
      }

      int pos2 = suffixArray.get(index + 1);
      lcpArray[index] = lcp = calcLCP(string, pos1, pos2, lcp);
      lcp--;
      if (lcp <= 0) lcp = 0;
    }
    return Arrays.asList(lcpArray);
  }

  private static int calcLCP(String string, int pos1, int pos2, int lcp) {
    while (string.charAt(pos1 + lcp) == string.charAt(pos2 + lcp)) {
      lcp++;
    }
    return lcp;
  }
}
