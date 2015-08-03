/* Iterator Pattern
	provides a way to access the elements of an aggregate object 
	sequentially without exposing its underlying representation.


*/

/*The Composite Pattern 

	allows you to compose objects into tree structures to represent part-whole 
	hierarchies. Composite lets clients treat individual objects and compositions 
	of objects uniformly.
	
*/




// One problem : two different class hold different Collection of one kind of class element
// If you want to iterate through two collection, you have to implement 2 loops

public class MenuItem{
	String name;
	String description;

	boolean vegetarian;
	double price;

	public MenuItem(String name, String description, boolean vegetarian, double price){
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() { return name;}
	public String getDescription() { return description;}
	public double getPrice() { return price;}
	public boolean isVegetarian() { return vegetarian;}

}

public class PancakeHouseMenu{

	// Use ArrayList to save element
	ArrayList menuItems;
	public PancakeHouseMenu(){
		menuItems = new ArrayList();
	}

	public void addItem(String name, String description,boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price); 
		menuItems.add(menuItem);
	}

	public ArrayList getMenuItem(){
		return menuItem;
	}
}


public class DinerMenu{
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;

	// Use array to save elements
	MenuItem[] menuItem;

	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
	}

	public void addItem(String name, String description, boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price); 
		if (numberOfItems >= MAX_ITEMS) {
			System.err.println(“Sorry, menu is full! Can’t add item to menu”); 
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1; 
		}
	}

	public MenuItem[] getMenuItems() {
		return menuItems;
	}
}


public class Waitress { 

	PancakeHouseMenu pancakeHouseMenu; 
	DinerMenu dinerMenu;

	public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) { 
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	// Now print menu get much easier, you do not have to know what you are iterate
	// you only have to get an iterator and put the iterator in an overload method
	public void printMenu() {
		ArrayList breakfastItems = pancakeHouseMenu.getMenuItems();
		MenuItem[] lunchItems = dinerMenu.getMenuItems();	

		for (int i = 0; i < breakfastItems.size(); i++) { 
			MenuItem menuItem = (MenuItem)breakfastItems.get(i); 
			System.out.print(menuItem.getName() + “ “); 
			System.out.println(menuItem.getPrice() + “ “); 
			System.out.println(menuItem.getDescription());
		}
		for (int i = 0; i < lunchItems.length; i++) { 
			MenuItem menuItem = lunchItems[i]; 
			System.out.print(menuItem.getName() + “ “); 
			System.out.println(menuItem.getPrice() + “ “); 
			System.out.println(menuItem.getDescription());
		}
	}	
	
	// other methods here 
}




// build a iterator to handle the multiple collection type issue
// by encapsulate the iteration loop 

public interface Iterator{
	hasNext();
	next();
}


public class DinerMenuIterator implements Iterator{
	MenuItem[] items;

	int position = 0;

	public DinerMenuIterator(MenuItem[] items){
		this.items = items;
	}

	public Object next(){
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}

	public boolean hasNext(){
		if (position >= items.length || items[position] == null) {
			return false;
		} else {
			return true;
		}
	}
}


// we could remove getMenuItems method, and instead return a iterator for 
public class DinerMenu{
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;

	// Use array to save elements
	MenuItem[] menuItem;

	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
	}

	public void addItem(String name, String description, boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price); 
		if (numberOfItems >= MAX_ITEMS) {
			System.err.println(“Sorry, menu is full! Can’t add item to menu”); 
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1; 
		}
	}

	public Iterator createIterator() {
		return new DinerMenuIterator(menuItems);
	}
}


// Now if you want to iterate through the collection, you do not have to know its type
// Just get a iterator from its class and manipulate it as a iterator 

public class Waitress { 

	PancakeHouseMenu pancakeHouseMenu; 
	DinerMenu dinerMenu;

	public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) { 
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	// Now print menu get much easier, you do not have to know what you are iterate
	// you only have to get an iterator and put the iterator in an overload method
	public void printMenu() {
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerIterator = dinerMenu.createIterator(); 
		System.out.println(“MENU\n----\nBREAKFAST”); printMenu(pancakeIterator);
		System.out.println(“\nLUNCH”);
		printMenu(dinerIterator);
	}	
	private void printMenu(Iterator iterator) { 
		while (iterator.hasNext()) {
			MenuItem menuItem = (MenuItem)iterator.next();
			System.out.print(menuItem.getName() + “, “);
			System.out.print(menuItem.getPrice() + “ -- “);
			System.out.println(menuItem.getDescription());
		}
	}
	// other methods here 
}


// Improve what we have done to clean up the waitress more
// Add remove method for iterator 




public class PancakeHouseMenu implements Menu{
	
	// Use array to save elements
	ArrayList menuItems;

	public DinerMenu() {
		menuItems = new ArrayList
	}

	public void addItem(String name, String description, boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price); 
		menuItems.add();
	}

	public Iterator createIterator() {
		return menuItems.iterator();
	}
}



public class DinerMenu implements Menu{
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;

	// Use array to save elements
	MenuItem[] menuItem;

	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
	}

	public void addItem(String name, String description, boolean vegetarian, double price){
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price); 
		if (numberOfItems >= MAX_ITEMS) {
			System.err.println(“Sorry, menu is full! Can’t add item to menu”); 
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1; 
		}
	}

	public Iterator createIterator() {
		return new DinerMenuIterator(menuItems);
	}
}


import java.util.Iterator;

public class DinerMenuIterator implements Iterator{
	MenuItem[] items;

	int position = 0;

	public DinerMenuIterator(MenuItem[] list){
		this.list = list;
	}

	public Object next(){
		MenuItem menuItem = items[position];
		position = position + 1;
		return menuItem;
	}

	public boolean hasNext(){
		if (position >= items.length || items[position] == null) {
			return false;
		} else {
			return true;
		}
	}

	// remove the current position element of iterator
	public void remove(){
		if (position <= 0) {
			throw new IllegalStateException("You can’t remove an item until you’ve done at least one next()");
		}

		if (list[position - 1] != null) {
			for (int i = position - 1; i < (list.length - 1) ; i++ ) {
				list[i] = list[i+1];
			}
			list[list.length -1] = null;
		}
	}
}

public interface Menu{
	public Iterator createIterator();
}

// Waitress class changed because both DinerMenu and PancakeHouseMenu implements Menu interface,
// Waitress do not have to know the concrete class of a menu

public class Waitress { 

	Menu pancakeHouseMenu; 
	Menu dinerMenu;

	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) { 
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

	// Now print menu get much easier, you do not have to know what you are iterate
	// you only have to get an iterator and put the iterator in an overload method
	public void printMenu() {
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerIterator = dinerMenu.createIterator(); 
		System.out.println(“MENU\n----\nBREAKFAST”); printMenu(pancakeIterator);
		System.out.println(“\nLUNCH”);
		printMenu(dinerIterator);
	}	
	private void printMenu(Iterator iterator) { 
		while (iterator.hasNext()) {
			MenuItem menuItem = (MenuItem)iterator.next();
			System.out.print(menuItem.getName() + “, “);
			System.out.print(menuItem.getPrice() + “ -- “);
			System.out.println(menuItem.getDescription());
		}
	}
	// other methods here 
}
/*

	Final demonstration of iterator pattern on project

*/

// Demonstration model for waitress-menu project


public class Waitress{
	Menu pancakeHouseMenu;
	Menu dinerMenu;
	Iterator menuIterator;
}

public class PancakeHouseMenu implements Menu{
	ArrayList menuItems;
	Iterator createIterator(){

		// As ArrayList could generate iterator 
		// from java.util, so we do not have to 
		// build a iterator for Pancake House Menu
		return menuItems.iterator();
	}
}

public class DinerMenu implements Menu{
	MenuItem menuItems;

	Iterator createIterator(){
		return new DinerMenuIterator(menuItems);
	}
}


public class DinerMenuIterator implements Iterator{
	hasNext();
	next();
	remove();
}


// Iterator Pattern
/*
	provides a way to access the elements of an aggregate object 
	sequentially without exposing its underlying representation.


*/

// Iterator pattern not only simplify the aggregate class (abstract Menu 
	//and concrete menu for this project) implementation, but seperate the function of it
	// aggregate could focus on the manipulation of collection, while iterator take responsibility
	// for traversal

// Model of Iterator pattern


public class Client{
	Aggregate list;
	Iterator iterator;
}

public interface Aggregate{
	Iterator createIterator();
}

public class ConcreteAggregate implements Aggregate{
	Iterator createIterator();
}

public interface Iterator{
	//java.util.Iterator
}
public class ConcreteIterator{
	hasNext();
	next();
	remove();
}

// Single Responsibility Principle : A class should have only one reason to change. 
// 		Every responsibility of a class is an area of potential change. 
// 		More than one responsibility means more than one area of change.

/*
 	Cohesion :  is a term you’ll hear used as a measure of
 	how closely a class or a module supports a single purpose or responsibility

	We say that a module or class has high cohesion when it is designed around a set of
	related functions, and we say it has low cohesion when it is designed around a set of unrelated functions.
	
	Cohesion is a more general concept than the Single Responsibility Principle, 
	but the two are closely related. Classes that adhere to the principle tend to 
	have high cohesion and are more maintainable than classes that take on multiple 
	responsibilities and have low cohesion

*/


// Java Collection Framework
// For collction implements Collection interface, we could
// have iterator from one method 

public interface Collection{
	add();
	addAll();
	clear();
	contains();
	containsAll();
	equals(); 
	hashCode(); 
	isEmpty(); 
	iterator(); 
	remove(); 
	removeAll(); 
	retainAll(); 
	size(); 
	toArray()
}


// Use for/in sentence, iterator sometimes is not necessary
/*
	for (Object obj: collection) {

	}
*/


// More improvement : Simplify the way to printMenu, you do not have to call 
// createIterator and printMenu(Iterator) multiple times


public class Waitress { 

	// Each menu now is an element in this 
	// ArrayList
	ArrayList menus;
	public Waitress(ArrayList menus) { 
		this.menus = menus;
	}
	public void printMenu() {
		// Iterate through all menu
		Iterator menuIterator = menus.iterator(); 
		while(menuIterator.hasNext()) {

			Menu menu = (Menu)menuIterator.next();

			// Iterate through all menu item in one menu
			printMenu(menu.createIterator()); 
		}
	}
	void printMenu(Iterator iterator) { 
		while (iterator.hasNext()) {
			MenuItem menuItem = (MenuItem)iterator.next(); 
			System.out.print(menuItem.getName() + “, “); 
			System.out.print(menuItem.getPrice() + “ -- “); 
			System.out.println(menuItem.getDescription());
		} 
	}
}


// Problem : There is a desert menu added in Diner Menu

// Solution : build a tree like pattern, we could traverse tree node, and each elements in the tree node
// Submenus could be treated as the sub-node of a root menu

/*The Composite Pattern 

	allows you to compose objects into tree structures to represent part-whole 
	hierarchies. Composite lets clients treat individual objects and compositions 
	of objects uniformly.
	
*/


// Model : 


public class Client{
	Component component;
}
// Component could be leaf or composite
// Composite contains a set of components
public abstract class Component{
	operation();
	add(Component);
	remove(Component);
	getChild(int);
}

/*
	Composite and leaf now may have same methods,
	but these methods implemented in different ways
	
	Some method only meaningful for leaf or composite will
	be UnsupportedMethodException in others

*/


// Define the behavior for the elements in the composition
public class Leaf extends Component{
	operation();
}
// Define Behavior of the components having children
// and to store child components
public class Composite extends Component{

}



// implement a Composite Pattern on the extensible Menu project

public interface MenuComponent {

	/*For method only make sense for either leaf or
		composite, the default method will be throw exception
		then if you do not override it in concrete class, it will 
		throw exception when you call it
	*/
	public void add(MenuComponent menuCompoent){
		throw new UnsupportedOperationException();
	}
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException(); 
	}
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		throw new UnsupportedOperationException(); 
	}

	public double getPrice() {
		throw new UnsupportedOperationException();
	}

	public boolean isVegetarian() {
		throw new UnsupportedOperationException(); 
	}
	
	public void print() {
		throw new UnsupportedOperationException();
	}
}

// Menu Item is a leaf component in the tree

public class MenuItem extends MenuComponent { 
	String name;
	String description; 
	boolean vegetarian; 
	double price;

	public MenuItem(String name, String description,boolean vegetarian, double price){
		this.name = name; 
		this.description = description; 
		this.vegetarian = vegetarian; 
		this.price = price;
	}

	public String getName() { 
		return name;
	}

	public String getDescription() { 
		return description;
	}

	public double getPrice() { 
		return price;
	}


	public boolean isVegetarian() { 
		return vegetarian;
	}
	public void print() { 
		System.out.print(“ “ + getName()); 
		if (isVegetarian()) {
			System.out.print(“(v)”); 
		}
		System.out.println(“, “ + getPrice());
		System.out.println(“ -- “ + getDescription()); }
}


public class Menu extends MenuComponent { 

	ArrayList menuComponents = new ArrayList(); 
	String name;
	String description;

	public Menu(String name, String description) { 
		this.name = name;
		this.description = description;
	}

	public void add(MenuComponent menuComponent) { 
		menuComponents.add(menuComponent);
	}

	public void remove(MenuComponent menuComponent) { 
		menuComponents.remove(menuComponent);
	}

	public MenuComponent getChild(int i) {
		return (MenuComponent)menuComponents.get(i);
	}

	public String getName() { 
		return name;
	}

	public String getDescription() { 
		return description;
	}

	public void print() {
		System.out.print(“\n” + getName()); 
		System.out.println(“, “ + getDescription()); 
		System.out.println(“---------------------”);

		Iterator iterator = menuComponents.iterator(); 
		while (iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent)iterator.next();

			// If current component is a MenuItem, it will print the detail
			// If current component is a Menu, it will print the whole menu and then 
			// go to the next component 
			menuComponent.print(); 
		}
	} 
}

public class Waitress { 
	// Now you could pass in only one menu object and 
	// iterate through it
	MenuComponent allMenus;
	public Waitress(MenuComponent allMenus) { 
		this.allMenus = allMenus;
	}
	public void printMenu() { 
		allMenus.print();
	} 
}

// Test Drive
public class MenuTestDrive {
	public static void main(String args[]) {

		MenuComponent pancakeHouseMenu = new Menu(“PANCAKE HOUSE MENU”, “Breakfast”);
		MenuComponent dinerMenu = new Menu(“DINER MENU”, “Lunch”);
		MenuComponent cafeMenu = new Menu(“CAFE MENU”, “Dinner”);
		MenuComponent dessertMenu = new Menu(“DESSERT MENU”, “Dessert of course!”);
		MenuComponent allMenus = new Menu(“ALL MENUS”, “All menus combined”);

		// add menu and menuItem in a total menu
		allMenus.add(pancakeHouseMenu); 
		allMenus.add(dinerMenu); 
		allMenus.add(cafeMenu);

		// add menu items here
		dinerMenu.add(new MenuItem( "Pasta", "Spaghetti with Marinara Sauce", "and a slice of sourdough bread", true, 3.89));
		dinerMenu.add(dessertMenu);
		dessertMenu.add(new MenuItem( "Apple Pie","Apple pie with a flakey crust, topped with vanilla icecream", true, 1.59));
		
		// add more menu items here
		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu(); 

	}
}

// With composite pattern, we use transparency to trade Single Responsibility Principle




// Combine Composite Pattern with Externla Iterator
// instead of the previous internal Iterator

public class Menu extends MenuComponent { 
	// other code here doesn’t change
	Iterator iterator = null;
	public Iterator createIterator() { 
		if (iterator == null) {
			iterator = new CompositeIterator(menuComponents.iterator()); 
		}
		return iterator; 
	}
}

public class MenuItem extends MenuComponent {
	// other code here doesn’t change
	// Menu Item do not have collection object in them
	// so it will return a NullIterator
	public Iterator createIterator() { 
		return new NullIterator();
	} 
}


// NullIterator design
// 1 return null : simple, but you have to 
// judge null pointer in its super class

// 2 return an interator that always return false when hasNext is called


import java.util.Iterator;
public class NullIterator implements Iterator {
	public Object next() { 
		return null;
	}
	public boolean hasNext() { 
		return false;
	}
	public void remove() {
		throw new UnsupportedOperationException();
	} 
}



// New composite iterator 

import java.util.*;

public class CompositeIterator implements Iterator{
	Stack stack = new Stack();

	public CompositeIterator(Iterator iterator) { 
		stack.push(iterator);
	}

	public Object Next(){
		if (hasNext()) {
			Iterator iterator = (Iterator)stack.peek();
			MenuComponent component = (MenuComponent) iterator.next();

			if (component instanceof Menu) {

				// If this component is a menu, we have another composite
				// to be included for iteration, so we have to throw it back
				// to stack


				stack.push(component.createIterator());
			}

			// for both leaf and component, we throw it back

			return component;
		} else {
			return null;
		}
	}

	public hasNext(){
		if (stack.isEmpty()) {
			return false;
		} else {
			Iterator iterator = (Iterator) stack.peek();

			if (!iterator.hasNext()) {
				stack.pop();
				return hasNext();
			}

			else {
				return true;
			}
		}
	}
	// only support traversal
	public void remove(){
		throw new UnsupportedOperationException();
	}


}



// Iterate through menu for a specific requirement, like Vegetarian


public class Waitress { 
	MenuComponent allMenus;
	public Waitress(MenuComponent allMenus) { 
		this.allMenus = allMenus;
	}
	public void printMenu() { 
		allMenus.print();
	}

	￼public void printVegetarianMenu() {
		Iterator iterator = allMenus.createIterator(); 
		System.out.println("\nVEGETARIAN MENU\n----"); 
		while (iterator.hasNext()) {
			MenuComponent menuComponent = (MenuComponent)iterator.next();

			// if you do not want to use a try/catch block, you could try
			// to override isVagetarian method in composite class always return false

			// in this case, the text book want to communicate the detail that 
			// isVegetarian is an unsupported method in composite class, and others could override it in the future
			try {

				// because we do not override isVegetarian method
				// in all composite composite class, so if you call it
				// in a menu it will throw exception
				if (menuComponent.isVegetarian()) {
					menuComponent.print(); 
				}
			} catch (UnsupportedOperationException e) {
				// if the component is a menu, it will jump right here
				// becaseu its isVegetatian method is not overrided
			} 

		}
	}
}








