package payments;

import java.util.HashMap;
import java.util.Map;

import inventory.Inventory;

public class NoteStrategy implements Payments {

    private HashMap<Note, Integer> notesCount;

    public NoteStrategy(HashMap<Note, Integer> notesCount) {
        this.notesCount = notesCount;
    }

    public HashMap<Note, Integer> getNotesCount() {
        return notesCount;
    }

    @Override
    public void pay(Inventory inventory, int totalAmount) {
        // check if enough notes are available
        int totalAmountInserted = 0;

        for(Map.Entry<Note, Integer> entry : notesCount.entrySet()) {
            Note note = entry.getKey();
            int count = entry.getValue();
            switch (note) {
                case HUNDRED:
                    totalAmountInserted += Note.HUNDRED.getValue() * count;
                    break;

                case TEN:
                    totalAmountInserted += Note.TEN.getValue() * count;
                    break;

                case TWENTY:
                    totalAmountInserted += Note.TWENTY.getValue() * count;
                    break;

                case FIFTY:
                    totalAmountInserted += Note.FIFTY.getValue() * count;
                    break;
                
                case TWO_HUNDRED:
                    totalAmountInserted += Note.TWO_HUNDRED.getValue() * count;
                    break;

                case FIVE_HUNDRED:
                    totalAmountInserted += Note.FIVE_HUNDRED.getValue() * count;
                    break;

                default:
                    break;
            }
        }

        if(totalAmountInserted < totalAmount) {
            throw new RuntimeException("Not enough notes inserted. Required: " + totalAmount + ", Inserted: " + totalAmountInserted);
        }

        if (totalAmount == totalAmountInserted) {
            // add these notes back to the inventory
            for(Map.Entry<Note, Integer> entry : inventory.getNotesCount().entrySet()) {
                Note note = entry.getKey();
                int count = entry.getValue();
                inventory.addNote(note, count);
            }
        } else {
            // calculate change and return it
            int change = totalAmountInserted - totalAmount;
            HashMap<Note, Integer> changeNotes = new HashMap<>();
            HashMap<Note, Integer> availableNotes = inventory.getNotesCount();

            if(change >= Note.FIVE_HUNDRED.getValue() && availableNotes.getOrDefault(Note.FIVE_HUNDRED, 0) > 0){
                int numberOfFiveHundred = change / Note.FIVE_HUNDRED.getValue();
                if (numberOfFiveHundred <= availableNotes.getOrDefault(Note.FIVE_HUNDRED, 0)) {
                    availableNotes.put(Note.FIVE_HUNDRED, availableNotes.getOrDefault(Note.FIVE_HUNDRED, 0) - numberOfFiveHundred);
                    changeNotes.put(Note.FIVE_HUNDRED, numberOfFiveHundred);
                    change -= numberOfFiveHundred * Note.FIVE_HUNDRED.getValue();
                } else {
                    changeNotes.put(Note.FIVE_HUNDRED, availableNotes.getOrDefault(Note.FIVE_HUNDRED, 0));
                    change -= availableNotes.getOrDefault(Note.FIVE_HUNDRED, 0) * Note.FIVE_HUNDRED.getValue();
                    availableNotes.put(Note.FIVE_HUNDRED, 0);
                }
            }  

            if(change >= Note.TWO_HUNDRED.getValue() && availableNotes.getOrDefault(Note.TWO_HUNDRED, 0) > 0){
                int numberOfTwoHundred = change / Note.TWO_HUNDRED.getValue();
                if (numberOfTwoHundred > 0 && numberOfTwoHundred <= availableNotes.getOrDefault(Note.TWO_HUNDRED, 0)) {
                    availableNotes.put(Note.TWO_HUNDRED, availableNotes.getOrDefault(Note.TWO_HUNDRED, 0) - numberOfTwoHundred);
                    changeNotes.put(Note.TWO_HUNDRED, numberOfTwoHundred);
                    change -= numberOfTwoHundred * Note.TWO_HUNDRED.getValue();
                } else {
                    changeNotes.put(Note.TWO_HUNDRED, availableNotes.getOrDefault(Note.TWO_HUNDRED, 0));
                    change -= availableNotes.getOrDefault(Note.TWO_HUNDRED, 0) * Note.TWO_HUNDRED.getValue();
                    availableNotes.put(Note.TWO_HUNDRED, 0);
                }
            }

            if(change >= Note.HUNDRED.getValue() && availableNotes.getOrDefault(Note.HUNDRED, 0) > 0){
                int numberOfHundred = change / Note.HUNDRED.getValue();
                if (numberOfHundred > 0 && numberOfHundred <= availableNotes.getOrDefault(Note.HUNDRED, 0)) {
                    availableNotes.put(Note.HUNDRED, availableNotes.getOrDefault(Note.HUNDRED, 0) - numberOfHundred);
                    changeNotes.put(Note.HUNDRED, numberOfHundred);
                        change -= numberOfHundred * Note.HUNDRED.getValue();
                } else {
                    changeNotes.put(Note.HUNDRED, availableNotes.getOrDefault(Note.HUNDRED, 0));
                    change -= availableNotes.getOrDefault(Note.HUNDRED, 0) * Note.HUNDRED.getValue();
                    availableNotes.put(Note.HUNDRED, 0);
                }
            }

            if(change >= Note.FIFTY.getValue() && availableNotes.getOrDefault(Note.FIFTY, 0) > 0){
                int numberOfFifty = change / Note.FIFTY.getValue();
                if (numberOfFifty > 0 && numberOfFifty <= availableNotes.getOrDefault(Note.FIFTY, 0)) {
                    availableNotes.put(Note.FIFTY, availableNotes.getOrDefault(Note.FIFTY, 0) - numberOfFifty);
                    changeNotes.put(Note.FIFTY, numberOfFifty);
                    change -= numberOfFifty * Note.FIFTY.getValue();
                } else {
                    changeNotes.put(Note.FIFTY, availableNotes.getOrDefault(Note.FIFTY, 0));
                    change -= availableNotes.getOrDefault(Note.FIFTY, 0) * Note.FIFTY.getValue();
                    availableNotes.put(Note.FIFTY, 0);
                }
            }

            if(change >= Note.TWENTY.getValue() && availableNotes.getOrDefault(Note.TWENTY, 0) > 0){
                int numberOfTwenty = change / Note.TWENTY.getValue();
                if (numberOfTwenty > 0 && numberOfTwenty <= availableNotes.getOrDefault(Note.TWENTY, 0)) {
                    availableNotes.put(Note.TWENTY, availableNotes.getOrDefault(Note.TWENTY, 0) - numberOfTwenty);
                    changeNotes.put(Note.TWENTY, numberOfTwenty);
                    change -= numberOfTwenty * Note.TWENTY.getValue();
                } else {
                    changeNotes.put(Note.TWENTY, availableNotes.getOrDefault(Note.TWENTY, 0));
                    change -= availableNotes.getOrDefault(Note.TWENTY, 0) * Note.TWENTY.getValue();
                    availableNotes.put(Note.TWENTY, 0);
                }
            }

            if(change >= Note.TEN.getValue() && availableNotes.getOrDefault(Note.TEN, 0) > 0){
                int numberOfTen = change / Note.TEN.getValue();
                if (numberOfTen > 0 && numberOfTen <= availableNotes.getOrDefault(Note.TEN, 0)) {
                    availableNotes.put(Note.TEN, availableNotes.getOrDefault(Note.TEN, 0) - numberOfTen);
                    changeNotes.put(Note.TEN, numberOfTen);
                    change -= numberOfTen * Note.TEN.getValue();
                } else {
                    changeNotes.put(Note.TEN, availableNotes.getOrDefault(Note.TEN, 0));
                    change -= availableNotes.getOrDefault(Note.TEN, 0) * Note.TEN.getValue();
                    availableNotes.put(Note.TEN, 0);
                }
            }

            if(change > 0)
                throw new RuntimeException("Not enough notes available for change. Required: " + change);

            // print the change given
            System.out.println("Change given: ");
            for(Map.Entry<Note, Integer> entry : changeNotes.entrySet()) {
                Note note = entry.getKey();
                int count = entry.getValue();
                System.out.println(note + ": " + count);
            }

            // add the currentNotes to available
            for(Map.Entry<Note, Integer> entry : notesCount.entrySet()) {
                Note note = entry.getKey();
                int count = entry.getValue();
                availableNotes.put(note, availableNotes.getOrDefault(note, 0) + count);
            }

            // update the inventory
            inventory.setNotesCount(availableNotes);
        }
    }
}
