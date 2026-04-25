package org.buildwithraghu.javafeatures.exceptions;

import java.util.Scanner;

public class TestExceptions {

    public static void main(String[] args) throws CustomException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n > 10) {
            throw new CustomException();
        }
    }
}
