import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyBigInteger {
    private final String val;
    private int[] store;

    public MyBigInteger(String val) {
        this.val = val;
        store = new int[val.length()];
        for (int i = 0;i < store.length;i++) {
            char currChar = val.charAt(i);
            if (Character.isDigit(currChar)) {
                store[i] = Character.getNumericValue(currChar);
            } else {
                throw new NumberFormatException("Incorrect format. Not a Number.");
            }
        }
    }

    public MyBigInteger multiply(MyBigInteger x) {

        int[] xStore;
        int[] yStore;
        if (this.store.length >= x.store.length) {
            xStore = this.store;
            yStore = x.store;
        } else {
            xStore = x.store;
            yStore = this.store;
        }

        int m = yStore.length;
        int n = xStore.length;

        List<List<Integer>> prodLists = new ArrayList<>();

        int xIndex = xStore.length-1;
        int yIndex = yStore.length-1;

        for (int yi = yIndex;yi >= 0;yi--) {
            prodLists.add(new ArrayList<>());
            int levels = 0;
            int currLevel = prodLists.size()-1;
            while (levels < currLevel) {
                prodLists.get(currLevel).add(0);
                levels += 1;
            }
            int remainder = 0;
            for (int xi = xIndex;xi >= 0;xi--) {
                int prod = xStore[xi] * yStore[yi] + remainder;
                int currProd = prod % 10;
                remainder = prod / 10;
                prodLists.get(currLevel).add(currProd);
            }
            if (remainder > 0) {
                String carry = String.valueOf(remainder);
                if (carry.length() > 1) {
                    for (int i = carry.length()-1;i >=0;i--) {
                        char digit = carry.charAt(i);
                        prodLists.get(currLevel).add(Character.getNumericValue(digit));
                    }
                } else {
                    prodLists.get(currLevel).add(remainder);
                }
            }
        }

        int maxSize = 0;
        for (List<Integer> list: prodLists) {
            maxSize = Math.max(maxSize,list.size());
        }

        int[] result = new int[m + n];
        int resIndex = result.length-1;

        int remainder = 0;

        for (int i = 0;i < maxSize;i++) {
            int sum = 0;
            for (List<Integer> list : prodLists) {
                if (i < list.size()) {
                    sum += list.get(i);
                }
            }
            sum += remainder;
            result[resIndex--] = sum % 10;
            remainder = sum / 10;
        }
        if (remainder > 0) {
            result[resIndex] = remainder;
        }

        int index = 0;
        while (index < result.length && result[index] == 0) {
            index++;
        }

        if (index > 0) {
            result = Arrays.copyOfRange(result,index,result.length);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num);
        }

        MyBigInteger sol = new MyBigInteger(sb.toString());
        sol.store = result;

        return sol;
    }

    public MyBigInteger add(MyBigInteger x) {

        int[] xStore = x.store;
        int maxLength = Math.max(xStore.length,this.store.length);
        int[] result = new int[maxLength+1];

        int xIndex = xStore.length-1;
        int thisIndex = this.store.length-1;
        int remainder = 0;

        for (int i = result.length-1;i >= 0;i--) {
            int sum = 0;
            if (xIndex >= 0 && thisIndex >= 0) {
                sum = xStore[xIndex--] + this.store[thisIndex--];
            }
            else if (xIndex >= 0) {
                sum = xStore[xIndex--];
            }
            else if (thisIndex >= 0) {
                sum = this.store[thisIndex--];
            }
            sum = sum + remainder;
            result[i] = sum % 10;
            remainder = sum / 10;
        }

        int index = 0;

        while (index < result.length) {
            if (result[index] == 0) {
                index += 1;
            } else {
                break;
            }
        }

        if (index > 0) {
            result = Arrays.copyOfRange(result,index,result.length);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num);
        }

        MyBigInteger res = new MyBigInteger(sb.toString());
        res.store = result;
        return res;
    }

    public String toString() {
        return val;
    }
}