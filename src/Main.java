import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;

@SuppressWarnings("serial")
public class Main extends Frame {
	
	static Drawing d;
	static String current = "";
	static boolean fcolorset = false;
	static boolean deleter = false;
	static Color fill = null;
	static Point p1, p2, p3;
	public static String datafile = "";
	static ArrayList<DrawableObject> objects = new ArrayList<DrawableObject>();
	public static DrawableObject temp = null;
	public Button Square;
	public Button Rectangle;
	public Button Circle;
	public Button Line;
	public Button setFill;
	public Button setColor;
	public Button ClearColors;
	public Button ClearScreen;
	public static Button Deleter;
	
	public Main(){
		super("I draw shapes");
		d = new Drawing();
		final JColorChooser colorc = new JColorChooser();
		addWindowListener(new closer());
		
		//Sets up the layout for the program
		BoxLayout horizontal = new BoxLayout (this, BoxLayout.X_AXIS);
		this.setLayout(horizontal);
		Container buttons = new Container();
		buttons.setLayout(new BoxLayout (buttons, BoxLayout.Y_AXIS));
		Container canv = new Container();
		canv.setLayout(new BoxLayout (canv, BoxLayout.Y_AXIS));
		
		this.add(buttons);
		this.add(canv);
		canv.add(d);
		
		//Sets up all our buttons to the screen
		this.Square = new Button();
		this.Square.setLabel("Square");
		Square.addActionListener(new Action("square"));
		buttons.add(Square);
		this.Rectangle = new Button();
		this.Rectangle.setLabel("Rectangle");
		Rectangle.addActionListener(new Action("rectangle"));
		buttons.add(Rectangle);
		this.Circle = new Button();
		this.Circle.setLabel("Circle");
		Circle.addActionListener(new Action("circle"));
		buttons.add(Circle);
		this.Line = new Button();
		this.Line.setLabel("Line");
		Line.addActionListener(new Action("line"));
		buttons.add(Line);
		this.setFill = new Button();
		this.setFill.setLabel("Set Fill");
		setFill.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(colorc,
			            "Choose a color...", null);
				if (c != null){
					fill = c;
					fcolorset = true;
					objects.add(new DrawableObject("color", fill.getRed(), fill.getGreen(), fill.getBlue(), 0));
					if(!current.equals("line")){
						current = "filled" + current;
					}
		        } 
			}
		});
		buttons.add(setFill);
		this.setColor = new Button();
		this.setColor.setLabel("Set Outline Color");
		setColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(colorc,
			            "Choose a color...", null);
				if (c != null){
					objects.add(new DrawableObject("color", c.getRed(), c.getGreen(), c.getBlue(), 0));
			    } 
			}
		});
		buttons.add(setColor);
		this.ClearColors = new Button();
		this.ClearColors.setLabel("Reset Colors");
		ClearColors.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				fill = null;
				fcolorset = false;
				objects.add(new DrawableObject("color", 0, 0, 0, 0));
				if(current.length() > 5 && current.substring(0, 6).equals("filled")){
		        	  current = current.substring(6);
		        }
				
			}
		});
		buttons.add(ClearColors);
		this.Deleter = new Button();
		this.Deleter.setLabel("Deleter : OFF");
		Deleter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (deleter == false){
					deleter = true;
					Main.Deleter.setLabel("Deleter : ON");
				}
				else{
					deleter = false;
					Main.Deleter.setLabel("Deleter : OFF");
				}
			}
		});
		buttons.add(Deleter);
		this.ClearScreen = new Button();
		this.ClearScreen.setLabel("Clear Screen");
		ClearScreen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				objects.clear();
				d.repaint();
			}
		});
		buttons.add(ClearScreen);
		
	}

	public static void main(String[] args) {
		Main f = new Main();
		
		MenuBar bar = new MenuBar(); //Creates the bar
		Menu fileMenu = new Menu("File"); //Creates a menu for the bar
		Menu ExportMenu = new Menu("Export"); //Creates a menu for the bar
		MenuItem open = new MenuItem("Open"); //Creates an item for the menu
		MenuItem save = new MenuItem("Save");
		MenuItem png = new MenuItem("Export as PNG");
		png.addActionListener(new Exporter(f, d));
		open.addActionListener(new Opener(f, d));
		save.addActionListener(new Saver(f, d));
		
		//Add items to menu
		fileMenu.add(open);
		fileMenu.add(save);
		ExportMenu.add(png);
		bar.add(fileMenu); //Add menu to bar
		bar.add(ExportMenu);
		
		f.setMenuBar(bar); //Add bar to frame
		f.d.repaint();
		f.pack();
		f.setVisible(true);

	}
	
	//Reads the datafile
	public static void reader(){
		try{
			FileReader in = new FileReader(datafile);
			BufferedReader br = new BufferedReader(in);
			String line = br.readLine();
			while(line != null){
				processLine(line);
				line = br.readLine();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//Manages the reading of each line of a datafile
	private static void processLine(String line) {
		String temp[] = line.split(" ");
		if(temp.length == 4){
			objects.add(new DrawableObject(temp[0], temp[1], temp[2], temp[3], null));
		}else if(temp.length == 5){
			objects.add(new DrawableObject(temp[0], temp[1], temp[2], temp[3], temp[4]));
		}
		else{
			System.out.println("Bad input");
		}		
	}
	
	//Takes a point and removes the uppermost shape on that coordinate
	public static void deleteobjat(Point point) {
		if(deleter == true){
			boolean del = false;
			for(int i = objects.size()-1; i >= 0 && del == false; i--){
				DrawableObject drawableObject = objects.get(i);
				if (drawableObject.collision(point) == true){
					objects.remove(i);
					del = true;
				}	
			}
			d.repaint();
		}
	}

	public static void resetPoints() {
		p1 = null;
		p2 = null;
		p3 = null;
		
	}
	
	//Updates the temporary DrawableObject.
	public static void updateObject() {
		int w, h;
		if(current.equals("line")){
			w = p2.x;
			h = p2.y;
		}
		else{
			w = p2.x - p1.x;
			h = p2.y - p1.y;
		}
		
		temp.Update(p1.x, p1.y, w, h);
		d.repaint();
	}
	
	//Adds an object to the arraylist of drawableObjects.
	public static void addObject() {
		int w, h;
		if(current.equals("line")){
			w = p3.x;
			h = p3.y;
		}
		else{
			w = p3.x - p1.x;
			h = p3.y - p1.y;
		}
		objects.add(new DrawableObject(current, p1.x, p1.y, w, h));
		temp = null;
		resetPoints();
		
	}

}
