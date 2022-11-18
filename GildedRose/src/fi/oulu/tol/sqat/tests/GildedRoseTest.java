package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	@Test
	public void dayPassesTest_regular_for_basic_items() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", 20, 6));
	
		List<Item> items = inn.getItems();
		
		int initialSellIn = items.get(0).getSellIn();
		
		int initialQuality = items.get(0).getQuality();
		
		inn.oneDay();
		
		int currentSellIn = items.get(0).getSellIn();
		
		assertEquals("Sell-in days should decrease as a day passes!", initialSellIn-1, currentSellIn);
		
		int currentQuality = items.get(0).getQuality();
		
		assertEquals("The item quality should decrease as the sell date approaches closer!", initialQuality - 1, currentQuality);
	}
	
	@Test
	public void dayPassesTest_regular_for_backstage_passes() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 20, 5));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20));
		List<Item> items = inn.getItems();
		
		int initialSellIn_aged_brie = items.get(0).getSellIn();
		int initialSellIn_backstagePass = items.get(1).getSellIn();
		
		int initialQuality_aged_brie = items.get(0).getQuality();
		int initialQuality_backstagePass = items.get(1).getQuality();
		
		inn.oneDay();
		
		int currentSellIn_aged_brie = items.get(0).getSellIn();
		int currentSellIn_backstagePass = items.get(1).getSellIn();
		
		assertEquals("Sell-in days should decrease as a day passes!", initialSellIn_aged_brie-1, currentSellIn_aged_brie);
		assertEquals("Sell-in days should decrease as a day passes!", initialSellIn_backstagePass-1, currentSellIn_backstagePass);
		
		int currentQuality_aged_brie = items.get(0).getQuality();
		int currentQuality_backstagePass = items.get(1).getQuality();
		
		
		assertEquals("The backstage pass quality should increase as the sell date approaches closer!", initialQuality_aged_brie + 1, currentQuality_aged_brie);
		assertEquals("The backstage pass quality should increase as the sell date approaches closer!", initialQuality_backstagePass + 1, currentQuality_backstagePass);
	}
	
	@Test
	public void dayPassesTest_regular_for_legendary_items() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	
		List<Item> items = inn.getItems();
		
		int initialSellIn = items.get(0).getSellIn();
		
		int initialQuality = items.get(0).getQuality();
		
		inn.oneDay();
		
		int currentSellIn = items.get(0).getSellIn();
		
		assertEquals("The legendary item should be never sold! Thus no changes to sellIn days should occur!", initialSellIn, currentSellIn);
		
		int currentQuality = items.get(0).getQuality();
		
		assertEquals("The legendary item quality should not change", initialQuality, currentQuality);
	}
	
	@Test
	public void dayPassesTest_sellIn_less_than_0_for_basic_items() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", -1, 6));
	
		List<Item> items = inn.getItems();
		int initialQuality = items.get(0).getQuality();
		inn.oneDay();
		
		int currentQuality = items.get(0).getQuality();
		
		assertEquals("The item quality should decrease after the sell date is passed!", initialQuality - 1, currentQuality);
	}
	
	@Test
	public void dayPasses_sellIn_less_than_11_for_backstage_pass() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 5));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		List<Item> items = inn.getItems();
		//int initialQuality_aged_brie = items.get(0).getQuality();
		int initialQuality_backstagePass = items.get(1).getQuality();
		
		inn.oneDay();
		
		int currentQuality_backstagePass = items.get(1).getQuality();
		
		assertEquals("The backstage pass quality should increase by two as the sell date is closer than 11 days!", initialQuality_backstagePass+2, currentQuality_backstagePass);
	}
	
	@Test
	public void dayPasses_sellIn_less_than_6_for_backstage_pass() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
		List<Item> items = inn.getItems();
		int initialQuality_backstagePass = items.get(0).getQuality();
		
		inn.oneDay();
		
		int currentQuality_backstagePass = items.get(0).getQuality();
		
		assertEquals("The backstage pass quality should increase by three as the sell date is closer than 6 days!", initialQuality_backstagePass+3, currentQuality_backstagePass);
	}
	
	@Test
	public void dayPasses_sellIn_less_than_0_for_backstage_pass() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20));
		List<Item> items = inn.getItems();
		int initialQuality_backstagePass = items.get(0).getQuality();
		
		inn.oneDay();
		
		int currentQuality_backstagePass = items.get(0).getQuality();
		
		assertEquals("The backstage pass quality should decrease to zero after the sell date passes!", 0, currentQuality_backstagePass);
	}
	
	@Test
	public void skipLoopTest() {
		GildedRose inn = new GildedRose();
		List<Item> items = inn.getItems();
		inn.oneDay();
	}
	/*
	@Test
	public void onePassThroughLoopTest() {
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Conjured Mana Cake", -1, 6));
		List<Item> items = inn.getItems();
		inn.oneDay();
	} */	
}
