import java.util.*;

public class Solution {
    public static int getPriceRecurse(int[] items_to_buy, ComboItem[] price_list, int index, ArrayList<Integer> overflow) {
        if (items_to_buy.length == index) {
            return 0;
        }

        int item = items_to_buy[index];
        int min_price = Integer.MAX_VALUE;

        int ret = overflow.indexOf(item);
        if (ret >= 0) {
            overflow.remove(ret);
            min_price = getPriceRecurse(items_to_buy, price_list, index + 1, overflow);
            overflow.add(item);
            return min_price;
        }

        for (int i = 0; i < price_list.length; i++) {
            int item_price = price_list[i].price;
            int[] items = price_list[i].items;
            if (Arrays.binarySearch(items, item) < 0) {
                continue;
            }
            boolean skipped = false;
            for (int j = 0; j < items.length; j++) {
                if (!skipped && items[j] == item) {
                    skipped = true;
                    continue;
                }
                overflow.add(items[j]);
            }
            int total_price = item_price + getPriceRecurse(items_to_buy, price_list, index + 1, overflow);
            if (total_price < min_price) {
                min_price = total_price;
            }
            System.out.println("Array: " + Arrays.toString(items));
            System.out.print("ArrayList: ");
            for (int j = 0; j < overflow.size(); j++) {
                System.out.print((overflow.get(j)) + " ");
            }
            System.out.print("\n");
            if (items.length > 1) {
                overflow.subList(overflow.size() - items.length + 1, overflow.size()).clear();
            }
        }

        return min_price;
    }

    public static int getPrice(int[] items_to_buy, ComboItem[] price_list) {
        return getPriceRecurse(items_to_buy, price_list, 0, new ArrayList<Integer>());
    }

    private static class ComboItem {
        int price;
        int[] items;
        public ComboItem(int price, int[] items) {
            this.price = price;
            this.items = items;
        }
    }

    public static void main(String[] args) {
        ComboItem[] price_list = {new ComboItem(5, new int[]{0}), 
                                    new ComboItem(4, new int[]{1}), 
                                    new ComboItem(8, new int[]{2}), 
                                    new ComboItem(12, new int[]{0,1,2}), 
                                    new ComboItem(14, new int[]{0,2}), 
                                    new ComboItem(9, new int[]{0,0})};
        int[] items_to_buy = {0,0};
        System.out.println(getPrice(items_to_buy, price_list));
    }
}

