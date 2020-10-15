package com.bsuir.its.service.password;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PassGeneratorImpl implements PassGenerator {
    @Override
    public String generate(int length) {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for(int j = 0; j < length-3; j++)
        {
           randomString.append((char) ('a' + random.nextInt(26)));
        }

        randomString.append("0?!");

        return randomString.toString();
    }
}
