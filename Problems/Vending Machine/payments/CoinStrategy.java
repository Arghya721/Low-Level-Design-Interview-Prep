package payments;

import java.util.HashMap;
import java.util.Map;

import inventory.Inventory;

public class CoinStrategy implements Payments {
    
    private HashMap<Coin, Integer> coinsCount;

    public CoinStrategy(HashMap<Coin, Integer> coinsCount) {
        this.coinsCount = coinsCount;
    }

    public HashMap<Coin, Integer> getCoinsCount() {
        return coinsCount;
    }

    @Override
    public void pay(Inventory inventory, int totalAmount) {
        // check if enough coins are available
        int totalAmountInserted = 0;

        for(Map.Entry<Coin, Integer> entry : coinsCount.entrySet()) {
            Coin coin = entry.getKey();
            int count = entry.getValue();
            switch (coin) {
                case ONE:
                    totalAmountInserted += Coin.ONE.getValue() * count;
                    break;

                case TWO:
                    totalAmountInserted += Coin.TWO.getValue() * count;
                    break;

                case FIVE:
                    totalAmountInserted += Coin.FIVE.getValue() * count;
                    break;
                
                case TEN:
                    totalAmountInserted += Coin.TEN.getValue() * count;
                    break;

                default:
                    break;
            }
        }

        if(totalAmountInserted < totalAmount) {
            throw new RuntimeException("Not enough coins inserted. Required: " + totalAmount + ", Inserted: " + totalAmountInserted);
        }

        if (totalAmount == totalAmountInserted) {
            // add these coins back to the inventory
            for(Map.Entry<Coin, Integer> entry : inventory.getCoinsCount().entrySet()) {
                Coin coin = entry.getKey();
                int count = entry.getValue();
                inventory.addCoin(coin, count);
            }
        } else {
            int change = totalAmountInserted - totalAmount;
            System.out.println("Change to be given: " + change);

            // check if coins are available for change
            HashMap<Coin, Integer> changeCoins = new HashMap<>();
            HashMap<Coin, Integer> availableCoins = inventory.getCoinsCount();
            
            if(change >= Coin.TEN.getValue() && availableCoins.getOrDefault(Coin.TEN, 0) > 0){
                int numberOfTen = change / Coin.TEN.getValue();
                if (numberOfTen > 0 && numberOfTen <= availableCoins.getOrDefault(Coin.TEN, 0)) {
                    availableCoins.put(Coin.TEN, availableCoins.getOrDefault(Coin.TEN, 0) - numberOfTen);
                    changeCoins.put(Coin.TEN, numberOfTen);
                    change -= numberOfTen * Coin.TEN.getValue();
                } else {
                    changeCoins.put(Coin.TEN, availableCoins.getOrDefault(Coin.TEN, 0));
                    change -= availableCoins.getOrDefault(Coin.TEN, 0) * Coin.TEN.getValue();
                    availableCoins.put(Coin.TEN, 0);
                }
            }  
            
            if(change >= Coin.FIVE.getValue() && availableCoins.getOrDefault(Coin.FIVE, 0) > 0){
                int numberOfFive = change / Coin.FIVE.getValue();
                if (numberOfFive > 0 && numberOfFive <= availableCoins.getOrDefault(Coin.FIVE, 0)) {
                    availableCoins.put(Coin.FIVE, availableCoins.getOrDefault(Coin.FIVE, 0) - numberOfFive);
                    changeCoins.put(Coin.FIVE, numberOfFive);
                    change -= numberOfFive * Coin.FIVE.getValue();
                } else {
                    changeCoins.put(Coin.FIVE, availableCoins.getOrDefault(Coin.FIVE, 0));
                    change -= availableCoins.getOrDefault(Coin.FIVE, 0) * Coin.FIVE.getValue();
                    availableCoins.put(Coin.FIVE, 0);
                }
            }

            if(change >= Coin.TWO.getValue() && availableCoins.getOrDefault(Coin.TWO, 0) > 0){
                int numberOfTwo = change / Coin.TWO.getValue();
                if (numberOfTwo > 0 && numberOfTwo <= availableCoins.getOrDefault(Coin.TWO, 0)) {
                    availableCoins.put(Coin.TWO, availableCoins.getOrDefault(Coin.TWO, 0) - numberOfTwo);
                    changeCoins.put(Coin.TWO, numberOfTwo);
                    change -= numberOfTwo * Coin.TWO.getValue();
                } else {
                    changeCoins.put(Coin.TWO, availableCoins.getOrDefault(Coin.TWO, 0));
                    change -= availableCoins.getOrDefault(Coin.TWO, 0) * Coin.TWO.getValue();
                    availableCoins.put(Coin.TWO, 0);
                }
            }

            if(change >= Coin.ONE.getValue() && availableCoins.getOrDefault(Coin.ONE, 0) > 0){
                int numberOfOne = change;
                if (numberOfOne > 0 && numberOfOne <= availableCoins.getOrDefault(Coin.ONE, 0)) {
                    availableCoins.put(Coin.ONE, availableCoins.getOrDefault(Coin.ONE, 0) - numberOfOne);
                    changeCoins.put(Coin.ONE, numberOfOne);
                    change -= numberOfOne * Coin.ONE.getValue();
                } else {
                    changeCoins.put(Coin.ONE, availableCoins.getOrDefault(Coin.ONE, 0));
                    change -= availableCoins.getOrDefault(Coin.ONE, 0) * Coin.ONE.getValue();
                    availableCoins.put(Coin.ONE, 0);
                }
            }

            if(change > 0) {
                throw new RuntimeException("Not enough coins available for change. Required: " + change);
            }

            System.out.println("Payment successful");

            // print the change given
            System.out.println("Change given: ");
            for(Map.Entry<Coin, Integer> entry : changeCoins.entrySet()) {
                Coin coin = entry.getKey();
                int count = entry.getValue();
                System.out.println(coin + ": " + count);
            }

            // add the currentCoins to available
            for(Map.Entry<Coin, Integer> entry : coinsCount.entrySet()) {
                Coin coin = entry.getKey();
                int count = entry.getValue();
                availableCoins.put(coin, availableCoins.getOrDefault(coin, 0) + count);
            }

            // update the inventory
            inventory.setCoinsCount(availableCoins);
        }
    }
}
