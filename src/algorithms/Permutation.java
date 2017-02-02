package algorithms;

import utils.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by connected on 11/14/16.
 */
public class Permutation {

    private int size;
    private Integer[] lastPermutation;

    public Permutation(Integer size) {
        this.size = size;
        this.lastPermutation = new Integer[size];
    }

    public Integer[] generateNextPermutation() {
        List<Integer> permutations = new ArrayList<>(Arrays.asList(lastPermutation));
        if (permutations.get(0) == null) {
            permutations = new ArrayList<>();
            generatePermutation(0, permutations);
        } else {
            Integer permutationOn, index = size;

            do {
                index--;
                permutationOn = lastPermutation[index];
                generatePermutation(index, permutations);
            } while (permutationOn.equals(lastPermutation[index]));
        }

        return lastPermutation;
    }

    private List<Integer> generateCopyOfListWithoutUnnecessaryItems(List<Integer> permutations, Integer index) {
        List<Integer> copy = new ArrayList<>(permutations);

        for (int i = copy.size() - 1; i > index; i--) {
            copy.remove(i);
        }

        return copy;
    }

    private void generatePermutation(Integer initialPosition, List<Integer> permutations) {
        for (int i = initialPosition; i < this.size; i++) {
            this.lastPermutation[i] = generateNextValidNumber(generateCopyOfListWithoutUnnecessaryItems(permutations, i), i);
            removePermutationsStartingOf(i + 1);
            permutations = new ArrayList<>(Arrays.asList(lastPermutation));
        }
    }

    private Integer generateNextValidNumber(List<Integer> numbers, int index) {
        Integer number = this.lastPermutation[index];
        Integer aux;

        if(number == null) {
            number = 0;
        }

        aux = number;

        while(numbers.contains(number)) {
            if(number < this.size - 1) {
                number++;
            } else {
                return aux;
            }
        }
        return number;
    }

    private void removePermutationsStartingOf(Integer index) {
        for (int i = index; i < this.size; i++) {
            this.lastPermutation[i] = -1;
        }
    }
}