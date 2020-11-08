package com.esamson;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.esamson.entity.BoardTest;
import com.esamson.entity.CardTest;
import com.esamson.entity.DeckTest;
import com.esamson.entity.GameTest;
import com.esamson.entity.PlayerTest;

/**
 * TestSuite.
 * 
 * @author esamson74@gmail.com
 */
@RunWith(Suite.class)
@SuiteClasses({ 
	BoardTest.class, 
	PlayerTest.class, 
	DeckTest.class, 
	CardTest.class, 
	GameTest.class
})
public class AllTests {}
