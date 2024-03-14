package com.nested;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;

    @BeforeEach
    void setUp() {
        account = new Account("John Doe");
        System.out.println("@BeforeEach: Global setup for all tests");
    }

    @Nested
    class Depositing {

        @BeforeEach
        void setUp() {
            account.deposit(100);
            System.out.println("@BeforeEach for Depositing: Deposited 100 for setup");
        }

        @Test
        void depositIncreasesBalance() {
            System.out.println("Running test: depositIncreasesBalance");
            int initialBalance = account.getBalance();
            account.deposit(200);
            assertEquals(initialBalance + 200, account.getBalance(), "Depositing should increase balance");
        }

        @Test
        void depositingNegativeAmountFails() {
            System.out.println("Running test: depositingNegativeAmountFails");
            assertThrows(IllegalArgumentException.class, () -> account.deposit(-100),
                    "Depositing negative amount should fail");
        }
    }

    @Nested
    class Withdrawing {

        @BeforeEach
        void setUp() {
            account.deposit(200);
            System.out.println("@BeforeEach for Withdrawing: Deposited 200 for setup");
        }

        @Test
        void withdrawDecreasesBalance() {
            System.out.println("Running test: withdrawDecreasesBalance");
            int initialBalance = account.getBalance();
            account.withdraw(100);
            assertEquals(initialBalance - 100, account.getBalance(), "Withdrawing should decrease balance");
        }

        @Nested
        class LargeWithdrawTests {
            @BeforeEach
            void setUp() {
                System.out.println("Setup for LargeWithdrawTests (No additional setup required)");
            }

            @Test
            void largeWithdrawFailsWithoutSufficientBalance() {
                assertThrows(InsufficientFundsException.class, () -> account.withdraw(1000),
                        "Should throw InsufficientFundsException for large withdrawal without sufficient balance");
            }

            @Test
            void withdrawingMoreThanBalanceFails() {
                System.out.println("Running test: withdrawingMoreThanBalanceFails");
                int balance = account.getBalance();
                assertThrows(InsufficientFundsException.class, () -> account.withdraw(balance + 100),
                        "Withdrawing more than balance should fail");
            }
        }
    }
}
