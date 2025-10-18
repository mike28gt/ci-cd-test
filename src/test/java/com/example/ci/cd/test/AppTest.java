package com.example.ci.cd.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    // AAA: Arrange-Act-Assert
    @Test
    void sum_adds_numbers() {
        // Arrange
        int a = 2, b = 3;

        // Act
        int result = App.sum(a, b);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void sum_with_zero() {
        assertEquals(7, App.sum(7, 0));
    }
}
