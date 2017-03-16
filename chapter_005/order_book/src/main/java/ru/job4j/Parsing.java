package ru.job4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.Collections;
import java.util.ArrayDeque;

/**
 * Parsing class.
 *
 * @author Denis
 * @since 14.03.2017
 */
class Parsing {
    /**
     * Data from file.
     */
    private Map<Integer, FullOrder> data;

    /**
     * Numbers of books in file.
     */
    private Set<Integer> bookCount;

    /**
     * All buys from book.
     */
    private Map<Double, Integer> buys;

    /**
     * All sells from books.
     */
    private Map<Double, Integer> sells;

    /**
     * Matched sells.
     */
    private Deque<Map.Entry<Double, Integer>> sellQueue;

    /**
     * Matched buys.
     */
    private Deque<Map.Entry<Double, Integer>> buyQueue;

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        new Parsing().start("C:\\orders.xml");
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * Default constructor.
     */
    Parsing() {
        data = new HashMap<>();
        bookCount = new HashSet<>();
        buys = new TreeMap<>(Collections.reverseOrder());
        sells = new TreeMap<>();
        sellQueue = new ArrayDeque<>();
        buyQueue = new ArrayDeque<>();
    }

    /**
     * Start parsing.
     *
     * @param pathname file name
     */
    void start(String pathname) {
        readFile(pathname);
        for (Integer i : bookCount) {
            parseSellsAndBuys(i);
            matching();
            print(i);
        }
    }

    /**
     * Parse sells and buys from data.
     *
     * @param book number of book
     */
    private void parseSellsAndBuys(int book) {
        buys.clear();
        sells.clear();
        int price;
        for (FullOrder ord : data.values()) {
            if (ord.getBook() == book) {
                if (ord.isOperationSell()) {
                    price = sells.get(ord.getPrice()) == null ? 0 : sells.get(ord.getPrice());
                    sells.put(ord.getPrice(), ord.getVolume() + price);
                } else {
                    price = buys.get(ord.getPrice()) == null ? 0 : buys.get(ord.getPrice());
                    buys.put(ord.getPrice(), ord.getVolume() + price);
                }
            }
        }
    }

    /**
     * Matching sells and buys.
     */
    private void matching() {
        sellQueue.clear();
        buyQueue.clear();
        buyQueue.addAll(buys.entrySet());
        sellQueue.addAll(sells.entrySet());
        Map.Entry<Double, Integer> buy;
        Map.Entry<Double, Integer> sell;
        int difference;
        while (true) {
            buy = buyQueue.poll();
            sell = sellQueue.poll();
            if (buy.getKey() >= sell.getKey()) {
                difference = Math.min(buy.getValue(), sell.getValue());
                buy.setValue(buy.getValue() - difference);
                sell.setValue(sell.getValue() - difference);
                if (buy.getValue() > 0) {
                    buyQueue.push(buy);
                }
                if (sell.getValue() > 0) {
                    sellQueue.push(sell);
                }
            } else {
                buyQueue.push(buy);
                sellQueue.push(sell);
                System.out.println();
                break;
            }
        }
    }

    /**
     * Print result to console.
     *
     * @param book number of book
     */
    private void print(int book) {
        Map.Entry<Double, Integer> buy;
        Map.Entry<Double, Integer> sell;
        System.out.printf("Order book: book-%s%s", book, System.lineSeparator());
        System.out.println("BID                  ASK");
        System.out.println("Volume@Price – Volume@Price");
        while (buyQueue.size() > 0 & sellQueue.size() > 0) {
            buy = buyQueue.poll();
            sell = sellQueue.poll();
            if (buy == null) {
                System.out.printf("-----@-----  - %s@%s%s", sell.getValue(), sell.getKey(), System.lineSeparator());
            } else if (sell == null) {
                System.out.printf("%s@%s - -----@-----%s", buy.getValue(), buy.getKey(), System.lineSeparator());
            } else {
                System.out.printf("%s@%s - %s@%s%s",
                        buy.getValue(), buy.getKey(), sell.getValue(), sell.getKey(), System.lineSeparator());
            }
        }
    }

    /**
     * Read XML-file.
     *
     * @param pathname file name
     */
    private void readFile(String pathname) {
        File file = new File(pathname);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int index;
            int book;
            Double price;
            int volume;
            Integer orderId;
            while ((line = br.readLine()) != null) {
                if (line.contains("<AddOrder") || line.contains("<DeleteOrder")) {
                    price = 0.0;
                    volume = 0;
                    index = line.indexOf("book=") + 11;
                    book = Integer.parseInt(getParameterFromLine(index, line));
                    bookCount.add(book);
                    index = line.indexOf("orderId=") + 9;
                    orderId = Integer.parseInt(getParameterFromLine(index, line));
                    if (line.contains("<AddOrder")) {
                        index = line.indexOf("price=") + 7;
                        price = Double.parseDouble(getParameterFromLine(index, line));
                        index = line.indexOf("volume=") + 8;
                        volume = Integer.parseInt(getParameterFromLine(index, line));
                    }
                    if (line.contains("<AddOrder")) {
                        data.put(orderId, new FullOrder(book, line.contains("SELL"), price, volume));
                    } else {
                        data.remove(orderId);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get parameter from text line.
     *
     * @param index start position
     * @param line  text line
     * @return parameter value
     */
    private String getParameterFromLine(int index, String line) {
        StringBuilder parameter = new StringBuilder();
        for (int i = index; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                break;
            }
            parameter.append(line.charAt(i));
        }
        return parameter.toString();
    }

    /**
     * Order with all fields.
     */
    private class FullOrder {
        /**
         * Book number.
         */
        private int book;

        /**
         * Sell flag.
         */
        private boolean operationSell;

        /**
         * Price.
         */
        private Double price;

        /**
         * Volume.
         */
        private int volume;

        /**
         * Main constructor.
         *
         * @param book          book number
         * @param operationSell sell flag
         * @param price         price
         * @param volume        volume
         */
        FullOrder(int book, boolean operationSell, Double price, int volume) {
            this.book = book;
            this.operationSell = operationSell;
            this.price = price;
            this.volume = volume;
        }

        /**
         * Get book number.
         *
         * @return book number
         */
        int getBook() {
            return book;
        }

        /**
         * Get sell flag.
         *
         * @return true if operation is sell
         */
        boolean isOperationSell() {
            return operationSell;
        }

        /**
         * Get price.
         *
         * @return price
         */
        Double getPrice() {
            return price;
        }

        /**
         * Get volume.
         *
         * @return volume
         */
        int getVolume() {
            return volume;
        }
    }
}
