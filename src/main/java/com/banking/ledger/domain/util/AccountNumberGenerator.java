package com.banking.ledger.domain.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random random = new Random();

    public static String generate() {
        int part1 = 100000 + random.nextInt(900000);
        int part2 = random.nextInt(10);
        return part1 + "-" + part2;
    }
}