package com.revature.p0.utils;


//arrange
//act
//assert

//@BeforeClass //before all tests in class
//@AfterClass //after all tests in class
//@Before //before each test
//@After    //after each test

import com.revature.p0.utils.datastructures.ArrayList;
import org.junit.After;
import org.junit.Before;

public class ArrayListTest {

    private ArrayList<Integer> sut;

    @Before
    public void testSetUp() {
        sut = new ArrayList<Integer>();
    }

    @After
    public void testTearDown() {
        sut = null;
    }

//      Non JUnit testing copied from main while building Arraylist, all this seems to be working.
//    ArrayList<String> testArray = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//        testArray.add("Test " + i);
//    }
//
//        testArray.add(5, "This is index 5.");
//
//        for (int i = 0; i < testArray.size(); i++) {
//        System.out.println(testArray.get(i));
//    }
//
//        for (int i = 6; i < testArray.size(); i++) {
//        testArray.remove(i);
//    }
//
//        System.out.println("Current size: " + testArray.size());
//
//        for (int i = 6; i < testArray.size(); i++) {
//        testArray.add(i, "Index: " + i);
//    }
//
//        for (int i = 0; i < testArray.size(); i++) {
//        System.out.println(testArray.get(i));
//    }
//        System.out.println("Substring 'Index: 8' found at: " + testArray.contains("Index: 8"));
//        System.out.println("Substring 'notthere' found at: " + testArray.contains("notthere"));
}
