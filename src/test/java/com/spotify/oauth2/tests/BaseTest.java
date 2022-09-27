package com.spotify.oauth2.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;



public class BaseTest {

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("Starting test :"+m.getName());
		System.out.println("Thread id :"+Thread.currentThread().getId());
	}
}
