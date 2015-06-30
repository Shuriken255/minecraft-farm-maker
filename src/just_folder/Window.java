package just_folder;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Window extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int markerPosX, markerPosY;
	int scaleX, scaleY, scaleZ;
	int intPartX, intPartY, intSectorX, intSectorY;
	byte choose = 5, pos = 1;
	int size = 16;
	boolean glassed = true;
	String fileName;
	
	JButton Melon = new JButton("Melon");
	JButton Pumpkin = new JButton("Pumpkin");
	JButton Wheat = new JButton("Wheat");
	JButton Carrot = new JButton("Carrot");
	JButton Potato = new JButton("Potato");
	JButton Export = new JButton("               Export JPG file               ");
	JButton onGrass = new JButton("On grass");
	JButton notOnGrass = new JButton("Not on grass");
	JButton inAir = new JButton("In air");
	JLabel glassedL = new JLabel();
	JButton Glassed = new JButton("Glassed");
	JButton NotGlassed = new JButton("Not glassed");
	JLabel position = new JLabel();
	JLabel type = new JLabel();
	JLabel PartX = new JLabel();
	JLabel PartY = new JLabel();
	JLabel SectorX = new JLabel();
	JLabel SectorY = new JLabel();
	JLabel Floor = new JLabel();
	JTextField PartXtext = new JTextField(3);
	JTextField PartYtext = new JTextField(3);
	JTextField FloorText = new JTextField(3);
	JTextField SectorXtext = new JTextField(3);
	JTextField SectorYtext = new JTextField(3);
	BufferedImage image;
	public Window(String s) throws IOException{
		super(s);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(2 + 2 == 5);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBounds(100, 100, 500, 280);
		this.setVisible(true);
		this.add(type);
		type.setText("Type of farm:");
		this.add(Melon);
		Melon.addActionListener(new listener());
		this.add(Pumpkin);
		this.add(Wheat);
		Wheat.addActionListener(new listener());
		this.add(Carrot);
		Carrot.addActionListener(new listener());
		this.add(Potato);
		Potato.addActionListener(new listener());
		this.add(position);
		position.setText("Where does it places?");
		this.add(onGrass);
		onGrass.addActionListener(new listener());
		this.add(notOnGrass);
		notOnGrass.addActionListener(new listener());
		this.add(inAir);
		inAir.addActionListener(new listener());
		this.add(glassedL);
		glassedL.setText("Does your farm glassed?");
		this.add(Glassed);
		Glassed.addActionListener(new listener());
		this.add(NotGlassed);
		NotGlassed.addActionListener(new listener());
		this.add(SectorX);
		SectorX.setText("How many sectors is in your part by X?             ");
		this.add(SectorXtext);
		this.add(SectorY);
		SectorY.setText("How many sectors is in your part by Y?              ");
		this.add(SectorYtext);
		this.add(PartX);
		PartX.setText("How many parts is in your farm by X?                 ");
		this.add(PartXtext);
		this.add(PartY);
		PartY.setText("How many parts is in your farm by Y?                 ");
		this.add(PartYtext);
		this.add(Floor);
		Floor.setText("How many floors is in your farm?                        ");
		this.add(FloorText);
		this.add(Export);
		Export.addActionListener(new listener());
	}
	
	public void ChangeToMelon(){
		choose = 1;
	}
	
	public void ChangeToPumpkin(){
		choose = 2;
	}
	
	public void ChangeToWheat(){
		choose = 3;
	}
	
	public void ChangeToCarrot(){
		choose = 4;
	}
	
	public void ChangeToPotato(){
		choose = 5;
	}
	
	byte[][][] farm3d;
	
	public void build3dfarm(){
		farm3d = new byte[scaleX+1][scaleY+1][scaleZ+1];
		int fl = Integer.parseInt(FloorText.getText());
		for(int f = 1; f<=fl; f++){
			build3dfloor(f);
		}
	}
	// 1 = wet
	// 2 = block
	// 3 = melon
	// 4 = pumpkin
	// 5 = water
	// 6 = carrot
	// 7 = potato
	// 8 = wheat
	// 9 = 
	// 10 = stem down
	// 11 = stem up
	// 12 = torch
	// 16 = glass
	public void build3dwheat(int floor, int x1, int y1){
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				farm3d[x1-1+x][y1-1+y][2+(floor-1)*3] = 1;
			}
		}
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				farm3d[x1-1+x][y1-1+y][3+(floor-1)*3] = 8;
			}
		}
		farm3d[x1-1+3][y1-1+3][2+(floor-1)*3] = 5;
		farm3d[x1-1+3][y1-1+3][3+(floor-1)*3] = 2;
		farm3d[x1-1+3][y1-1+3][4+(floor-1)*3] = 2;
		farm3d[x1+2][y1+3][4+(floor-1)*3] = 12;
		farm3d[x1+2][y1+1][4+(floor-1)*3] = 12;
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 12;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 12;
	}
	
	public void build3dpotato(int floor, int x1, int y1){
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				farm3d[x1-1+x][y1-1+y][2+(floor-1)*3] = 1;
			}
		}
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				farm3d[x1-1+x][y1-1+y][3+(floor-1)*3] = 7;
			}
		}
		farm3d[x1-1+3][y1-1+3][2+(floor-1)*3] = 5;
		farm3d[x1-1+3][y1-1+3][3+(floor-1)*3] = 2;
		farm3d[x1-1+3][y1-1+3][4+(floor-1)*3] = 2;
		farm3d[x1+2][y1+3][4+(floor-1)*3] = 12;
		farm3d[x1+2][y1+1][4+(floor-1)*3] = 12;
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 12;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 12;
	}
	
	public void build3dcarrot(int floor, int x1, int y1){
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				farm3d[x1-1+x][y1-1+y][2+(floor-1)*3] = 1;
			}
		}
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				farm3d[x1-1+x][y1-1+y][3+(floor-1)*3] = 6;
			}
		}
		farm3d[x1-1+3][y1-1+3][2+(floor-1)*3] = 5;
		farm3d[x1-1+3][y1-1+3][3+(floor-1)*3] = 2;
		farm3d[x1-1+3][y1-1+3][4+(floor-1)*3] = 2;
		farm3d[x1+2][y1+3][4+(floor-1)*3] = 12;
		farm3d[x1+2][y1+1][4+(floor-1)*3] = 12;
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 12;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 12;
	}
	
	public void build3dmelon(int floor, int x1, int y1){
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				System.out.println(x1-1+x);
				System.out.println(y1-1+y);
				System.out.println(2+(floor-1)*3);
				System.out.println();
				farm3d[x1-1+x][y1-1+y][2+(floor-1)*3] = 1;
			}
		}
		for(int x = 1; x<=5;x++){
			farm3d[x1-1+x][y1+3][3+(floor-1)*3] = 10;
			farm3d[x1-1+x][y1+1][3+(floor-1)*3] = 11;
			farm3d[x1-1+x][y1][3+(floor-1)*3] = 3;
			farm3d[x1-1+x][y1+2][3+(floor-1)*3] = 2;
			farm3d[x1-1+x][y1+4][3+(floor-1)*3] = 3;
			farm3d[x1-1+x][y1+2][4+(floor-1)*3] = 2;
			farm3d[x1-1+x][y1+2][2+(floor-1)*3] = 2;
		}
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 13;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 15;
		farm3d[x1-1+3][y1-1+3][2+(floor-1)*3] = 5;
		farm3d[x1][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+4][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+2][y1+3][4+(floor-1)*3] = 12;
		farm3d[x1+2][y1+1][4+(floor-1)*3] = 12;
	}
	
	public void build3dpumpkin(int floor, int x1, int y1){
		for(int y = 1; y<=5;y++){
			for(int x = 1; x<=5;x++){
				System.out.println(x1-1+x);
				System.out.println(y1-1+y);
				System.out.println(2+(floor-1)*3);
				System.out.println();
				farm3d[x1-1+x][y1-1+y][2+(floor-1)*3] = 1;
			}
		}
		for(int x = 1; x<=5;x++){
			farm3d[x1-1+x][y1+3][3+(floor-1)*3] = 10;
			farm3d[x1-1+x][y1+1][3+(floor-1)*3] = 11;
			farm3d[x1-1+x][y1][3+(floor-1)*3] = 4;
			farm3d[x1-1+x][y1+2][3+(floor-1)*3] = 2;
			farm3d[x1-1+x][y1+4][3+(floor-1)*3] = 4;
			farm3d[x1-1+x][y1+2][4+(floor-1)*3] = 2;
		}
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 13;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 15;
		farm3d[x1-1+3][y1-1+3][2+(floor-1)*3] = 5;
		farm3d[x1][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+4][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+1][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+3][y1+2][4+(floor-1)*3] = 2;
		farm3d[x1+2][y1+3][4+(floor-1)*3] = 12;
		farm3d[x1+2][y1+1][4+(floor-1)*3] = 12;
	}
	
	public void build3dpart(int floor, int x1, int y1){
		for(int y = 1; y<=intSectorY; y++){
			for(int x = 1; x<=intSectorX; x++){
				switch(choose){
				case 1: build3dmelon(floor,x1+(x-1)*5,y1+(y-1)*5); break;
				case 2: build3dpumpkin(floor,x1+(x-1)*5,y1+(y-1)*5); break;
				case 3: build3dwheat(floor,x1+(x-1)*5,y1+(y-1)*5); break;
				case 4: build3dcarrot(floor,x1+(x-1)*5,y1+(y-1)*5); break;
				case 5: build3dpotato(floor,x1+(x-1)*5,y1+(y-1)*5); break;
				}
			}
		}
	}
	
	public void build3dfloor(int floor){
		int kX = 2, kY = 2;
		for(int x = 1; x<=intPartX; x++){
			kX++;
			for(int y = 1; y<=intPartY; y++){
				kY++;
				build3dpart(floor, (x-1)*intSectorX*5+kX, (y-1)*intSectorY*5+kY);
			}
			kY = 2;
		}
		for(int x = 2; x<scaleX; x++){
			for(int y = 2; y<scaleY; y++){
				if(farm3d[x][y][(floor-1)*3+2] == 0){
					farm3d[x][y][(floor-1)*3+2] = 2;
				}
			}
		}
		for(int x = 2; x<scaleX; x++){
			for(int y = 2; y<scaleY; y++){
				for(int z = 2; z<=3; z++){
					if(x == 2 | x == scaleX-1 | y == 2 | y == scaleY-1){
						farm3d[x][y][(floor-1)*3+z+1] = 2;
					}
				}
			}
		}
		for(int x = 2; x<scaleX; x++){
			for(int y = 2; y<scaleY; y++){
				farm3d[x][y][scaleZ-2] = 2;
			}
		}
		if(pos == 3){
			for(int x = 2; x<scaleX; x++){
				for(int y = 2; y<scaleY; y++){
					farm3d[x][y][1] = 2;
				}
			}
		}
		if(glassed){
			for(int x = 2; x<scaleX; x++){
				for(int y = 2; y<scaleY; y++){
					if(x == 2 || x == scaleX-1 || y == 2 || y == scaleY-1){
						farm3d[x][y][(floor-1)*3+3] = 16;
						farm3d[x][y][(floor-1)*3+4] = 16;
					}
				}
			}
			farm3d[2][2][(floor-1)*3+3] = 2;
			farm3d[2][scaleY-1][(floor-1)*3+3] = 2;
			farm3d[scaleX-1][2][(floor-1)*3+3] = 2;
			farm3d[scaleX-1][scaleY-1][(floor-1)*3+3] = 2;
			farm3d[2][2][(floor-1)*3+4] = 2;
			farm3d[2][scaleY-1][(floor-1)*3+4] = 2;
			farm3d[scaleX-1][2][(floor-1)*3+4] = 2;
			farm3d[scaleX-1][scaleY-1][(floor-1)*3+4] = 2;
			for(int x = 3; x<scaleX-1; x++){
				for(int y = 3; y<scaleY-1; y++){
					farm3d[x][y][scaleZ-2] = 16;
				}
			}
		}
	}
	
	Graphics2D im;
	
	
	
	
	
	
	
	public void exportJPG() throws IOException{
		boolean ready = true;
		try{
			intPartX = Integer.parseInt(PartXtext.getText());
			intPartY = Integer.parseInt(PartYtext.getText());
			intSectorX = Integer.parseInt(SectorXtext.getText());
			intSectorY = Integer.parseInt(SectorYtext.getText());
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Write number in all text fields!");
			ready = false;
		}
		if(ready){
			scaleX = 3+(intPartX*intSectorX)*5+intPartX;
			scaleY = 3+(intPartY*intSectorY)*5+intPartY;
			scaleZ = 4+Integer.parseInt(FloorText.getText())*3;
			System.out.println(String.valueOf(scaleX) + " " 
					+ String.valueOf(scaleY) + " "
					+ String.valueOf(scaleZ) + " ");
			System.out.println();
			build3dfarm();
			int dimX = (scaleX)*size+(scaleX+scaleY+10)*16;
			int dimY = (scaleY+scaleX)*8+(scaleZ)*16+400;
			image = new BufferedImage(dimX,dimY,BufferedImage.TYPE_INT_BGR);
			im = image.createGraphics();
			drawingImage(im, dimX, dimY);
			exportImage(image, fileName + ".jpg");
			JOptionPane.showMessageDialog(null, "Picture saved as '"
					+ fileName + ".jpg'!");
		}
	}
	
		public void drawingImage(Graphics2D pm, int x1, int y1) throws IOException{
		BufferedImage wet = ImageIO.read(new File("textures/top/wet.png"));
		BufferedImage water = ImageIO.read(new File("textures/top/water.png"));
		BufferedImage wheat = ImageIO.read(new File("textures/top/wheat.png"));
		BufferedImage stemup = ImageIO.read(new File("textures/top/stemup.png"));
		BufferedImage stemdown = ImageIO.read(new File("textures/top/stemdown.png"));
		BufferedImage pumpkin = ImageIO.read(new File("textures/top/pumpkin.png"));
		BufferedImage potato = ImageIO.read(new File("textures/top/potato.png"));
		BufferedImage melon = ImageIO.read(new File("textures/top/melon.png"));
		BufferedImage cobblestone = ImageIO.read(new File("textures/top/cobblestone.png"));
		BufferedImage carrot = ImageIO.read(new File("textures/top/carrot.png"));
		BufferedImage glass = ImageIO.read(new File("textures/top/glass.png"));
		BufferedImage dark = ImageIO.read(new File("textures/top/dark.png"));
		
		BufferedImage wet3d = ImageIO.read(new File("textures/isometric/wet.png"));
		BufferedImage glass3d = ImageIO.read(new File("textures/isometric/glass.png"));
		BufferedImage wheat3d = ImageIO.read(new File("textures/isometric/wheat.png"));
		BufferedImage stemup3d = ImageIO.read(new File("textures/isometric/stemup.png"));
		BufferedImage stemdown3d = ImageIO.read(new File("textures/isometric/stemdown.png"));
		BufferedImage pumpkin3d = ImageIO.read(new File("textures/isometric/pumpkin.png"));
		BufferedImage potato3d = ImageIO.read(new File("textures/isometric/potato.png"));
		BufferedImage melon3d = ImageIO.read(new File("textures/isometric/melon.png"));
		BufferedImage cobblestone3d = ImageIO.read(new File("textures/isometric/cobblestone.png"));
		BufferedImage carrot3d = ImageIO.read(new File("textures/isometric/carrot.png"));
		BufferedImage water3d = ImageIO.read(new File("textures/isometric/water.png"));
		
		int x2, y2;
		String text;
		im.setColor(Color.white);
		im.fillRect(0, 0, x1, y1);
		im.setColor(Color.white);
		for(int x = 1; x<=scaleX; x++){
			for(int y = 1; y<=scaleY; y++){
				x2 = (x-1)*size;
				y2 = (y-1)*size+(scaleY+scaleX*2)-(scaleZ*4);
				switch(farm3d[x][y][3]){
					case 1: im.drawImage(wet, null, x2, y2); break;
					case 2: im.drawImage(cobblestone, null, x2, y2); break;
					case 3: im.drawImage(melon, null, x2, y2); break;
					case 4: im.drawImage(pumpkin, null, x2, y2); break;
					case 5: im.drawImage(water, null, x2, y2); break;
					case 6: im.drawImage(carrot, null, x2, y2); break;
					case 7: im.drawImage(potato, null, x2, y2); break;
					case 8: im.drawImage(wheat, null, x2, y2); break;
					case 10: im.drawImage(stemdown, null, x2, y2); break;
					case 11: im.drawImage(stemup, null, x2, y2); break;
					case 16: im.drawImage(glass, null, x2, y2); break;
					default: if(x != 1 & x != scaleX & y != 1 & y != scaleY){
						im.drawImage(dark, null, x2, y2); break;
					}
				}
			}
		}
		for(int x = 1; x<=scaleX; x++){
			for(int y = 1; y<=scaleY; y++){
				for(int z = 1; z<=scaleZ; z++){
					System.out.println(String.valueOf(x)
							+ " " + String.valueOf(y)
							+ " " + String.valueOf(z));
					x2 = ((scaleX+5)*size+scaleX*16)-(x*16)+(y*16);
					y2 = (scaleZ*16)+(x*8)+(y*8)-(z*16)-40;
					switch(farm3d[x][y][z]){
						case 1: im.drawImage(wet3d, null, x2, y2); break;
						case 2: im.drawImage(cobblestone3d, null, x2, y2); break;
						case 3: im.drawImage(melon3d, null, x2, y2); break;
						case 4: im.drawImage(pumpkin3d, null, x2, y2); break;
						case 16: im.drawImage(glass3d, null, x2, y2); break;
						case 6: im.drawImage(carrot3d, null, x2, y2); break;
						case 7: im.drawImage(potato3d, null, x2, y2); break;
						case 8: im.drawImage(wheat3d, null, x2, y2); break;
						case 10: im.drawImage(stemdown3d, null, x2, y2); break;
						case 11: im.drawImage(stemup3d, null, x2, y2); break;
					}					
				}
			}
		}
		for(int x = 3; x<=7; x++){
			for(int y = 3; y<=7; y++){
				for(int z = 2; z<=4; z++){
					x2 = x1-(x*16)+(y*16)+y-120;
					y2 = (scaleZ*16)+(scaleX*8)+(scaleY*8)+(x*8)+(y*8)-(z*16)+10;
					switch(farm3d[x][y][z]){
						case 1: im.drawImage(wet3d, null, x2, y2); break;
						case 2: im.drawImage(cobblestone3d, null, x2, y2); break;
						case 3: im.drawImage(melon3d, null, x2, y2); break;
						case 4: im.drawImage(pumpkin3d, null, x2, y2); break;
						case 16: im.drawImage(glass3d, null, x2, y2); break;
						case 6: im.drawImage(carrot3d, null, x2, y2); break;
						case 7: im.drawImage(potato3d, null, x2, y2); break;
						case 8: im.drawImage(wheat3d, null, x2, y2); break;
						case 10: im.drawImage(stemdown3d, null, x2, y2); break;
						case 11: im.drawImage(stemup3d, null, x2, y2); break;
					}					
				}
			}
		}
		for(int x = 3; x<=7; x++){
			for(int y = 3; y<=7; y++){
				for(int z = 2; z<=3; z++){
					x2 = x1-(x*16)+(y*16)+y-120;
					y2 = (scaleZ*16)+(scaleX*8)+(scaleY*8)+(x*8)+(y*8)-(z*16)+140;
					switch(farm3d[x][y][z]){
						case 1: im.drawImage(wet3d, null, x2, y2); break;
						case 2: im.drawImage(cobblestone3d, null, x2, y2); break;
						case 3: im.drawImage(melon3d, null, x2, y2); break;
						case 4: im.drawImage(pumpkin3d, null, x2, y2); break;
						case 16: im.drawImage(glass3d, null, x2, y2); break;
						case 6: im.drawImage(carrot3d, null, x2, y2); break;
						case 7: im.drawImage(potato3d, null, x2, y2); break;
						case 8: im.drawImage(wheat3d, null, x2, y2); break;
						case 10: im.drawImage(stemdown3d, null, x2, y2); break;
						case 11: im.drawImage(stemup3d, null, x2, y2); break;
					}					
				}
			}
		}
		for(int x = 3; x<=7; x++){
			for(int y = 3; y<=7; y++){
				x2 = x1-(x*16)+(y*16)+y-120;
				y2 = (scaleZ*16)+(scaleX*8)+(scaleY*8)+(x*8)+(y*8)-(2*16)+270;
				switch(farm3d[x][y][2]){
					case 1: im.drawImage(wet3d, null, x2, y2); break;
					case 2: im.drawImage(cobblestone3d, null, x2, y2); break;
					case 3: im.drawImage(melon3d, null, x2, y2); break;
					case 4: im.drawImage(pumpkin3d, null, x2, y2); break;
					case 16: im.drawImage(glass3d, null, x2, y2); break;
					case 6: im.drawImage(carrot3d, null, x2, y2); break;
					case 7: im.drawImage(potato3d, null, x2, y2); break;
					case 8: im.drawImage(wheat3d, null, x2, y2); break;
					case 10: im.drawImage(stemdown3d, null, x2, y2); break;
					case 11: im.drawImage(stemup3d, null, x2, y2); break;
					case 5: im.drawImage(water3d, null, x2, y2); break;
				}
			}
		}
		switch(choose){
		case 1:
			fileName = "Melon farm "; break;
		case 2:
			fileName = "Pumpkin farm "; break;
		case 3:
			fileName = "Wheat farm "; break;
		case 4:
			fileName = "Carrot farm "; break;
		case 5:
			fileName = "Potato farm "; break;
		}
		im.setFont(new Font("TimesRoman", Font.BOLD, 18));
		im.setColor(Color.black);
		x2 = 50;
		y2 = (scaleZ*16)+(scaleX*8)+(scaleY*8)+20;
		text = "Information:";
		im.drawString(text, x2, y2);
		y2 = y2+20;
		text = "Scale:" + String.valueOf(scaleX-2) + "x"
				+ String.valueOf(scaleZ-2) + "x"
				+ String.valueOf(scaleY-2);
		im.drawString(text, x2, y2);
		fileName = fileName + " " + String.valueOf(scaleX-2) + "x"
				+ String.valueOf(scaleZ-2) + "x"
				+ String.valueOf(scaleY-2);
		y2 = y2+20;
		int gather = intSectorX*intPartX*intSectorY*intSectorX;
		gather = gather*Integer.parseInt(FloorText.getText());
		int gather1;
		int gather2;
		switch(choose){
		case 1:
			gather = gather*10;
			gather1 = gather/64;
			gather2 = gather - gather1*64;
			text = "Melons after gather: ";
			text = text + String.valueOf(gather1) 
					+ " stacks and " + String.valueOf(gather2);
			break;
		case 2: 
			gather = gather*10;
			gather1 = gather/64;
			gather2 = gather - gather1*64;
			text = "Pumpkins after gather: ";
			text = text + String.valueOf(gather1) 
					+ " stacks and " + String.valueOf(gather2);
			break;
		case 3: 
			gather = gather*24;
			gather1 = gather/64;
			gather2 = gather - gather1*64;
			text = "Wheat after gather: ";
			text = text + String.valueOf(gather1) 
					+ " stacks and " + String.valueOf(gather2);
			break;
		case 4: 
			gather = gather*24;
			gather1 = gather/64;
			gather2 = gather - gather1*64;
			text = "Carrot after gather: ";
			text = text + String.valueOf(gather1) 
					+ " stacks and " + String.valueOf(gather2);
			break;
		case 5: 
			gather = gather*24;
			gather1 = gather/64;
			gather2 = gather - gather1*64;
			text = "Potato after gather: ";
			text = text + String.valueOf(gather1) 
					+ " stacks and " + String.valueOf(gather2);
			break;
		}
		y2 = y2+40;
		text = "Resources to build:";
		im.drawString(text, x2, y2);
		y2 = y2+20;
		int blocks = 0;
		int block1;
		int block2;
		for(int x = 1; x<=scaleX; x++){
			for(int y = 1; y<=scaleY; y++){
				for(int z = 1; z<=scaleZ; z++){
					if(farm3d[x][y][z] == 2){
						blocks++;
					}
				}
			}
		}
		block1 = blocks/64;
		block2 = blocks - block1*64;
		text = "Blocks: " + String.valueOf(block1) + " stacks and "
				+ String.valueOf(block2);
		im.drawString(text, x2, y2);
		y2 = y2+20;
		blocks = 0;
		for(int x = 1; x<=scaleX; x++){
			for(int y = 1; y<=scaleY; y++){
				for(int z = 1; z<=scaleZ; z++){
					if(farm3d[x][y][z] == 12){
						blocks++;
					}
				}
			}
		}
		block1 = blocks/64;
		block2 = blocks - block1*64;
		text = "Torches: " + String.valueOf(block1) + " stacks and "
				+ String.valueOf(block2);
		im.drawString(text, x2, y2);
		if(glassed){
			y2 = y2+20;
			blocks = 0;
			for(int x = 1; x<=scaleX; x++){
				for(int y = 1; y<=scaleY; y++){
					for(int z = 1; z<=scaleZ; z++){
						if(farm3d[x][y][z] == 16){
							blocks++;
						}
					}
				}
			}
			block1 = blocks/64;
			block2 = blocks - block1*64;
			text = "Glass: " + String.valueOf(block1) + " stacks and "
					+ String.valueOf(block2);
			im.drawString(text, x2, y2);
		}
		
		
		y2 = y2+20;
		blocks = 0;
		int zDirt;
		if(pos == 1){
			zDirt = 3;
		}
		else {
			zDirt = 2;
		}
		for(int x = 1; x<=scaleX; x++){
			for(int y = 1; y<=scaleY; y++){
				for(int z = zDirt; z<=scaleZ; z++){
					if(farm3d[x][y][z] == 1){
						blocks++;
					}
				}
			}
		}
		
		block1 = blocks/64;
		block2 = blocks - block1*64;
		text = "Dirt: " + String.valueOf(block1) + " stacks and "
				+ String.valueOf(block2);
		im.drawString(text, x2, y2);
		y2 = y2+20;
		int seeds = 0;
		int seeds2;
		int seeds1;
		switch(choose){
		case 1: 
			for(int x = 1; x<=scaleX; x++){
				for(int y = 1; y<=scaleY; y++){
					for(int z = 1; z<=scaleZ; z++){
						if(farm3d[x][y][z] == 10 | farm3d[x][y][z] == 11){
							seeds++;
						}
					}
				}
			}
			seeds1 = seeds/64;
			seeds2 = seeds-seeds1*64;
			text = "Melon seeds: " + String.valueOf(seeds1)
					+ " stacks and " + String.valueOf(seeds2);
			break;
		case 2: 
			for(int x = 1; x<=scaleX; x++){
				for(int y = 1; y<=scaleY; y++){
					for(int z = 1; z<=scaleZ; z++){
						if(farm3d[x][y][z] == 10 | farm3d[x][y][z] == 11){
							seeds++;
						}
					}
				}
			}
			seeds1 = seeds/64;
			seeds2 = seeds-seeds1*64;
			text = "Pumpkin seeds: " + String.valueOf(seeds1)
					+ " stacks and " + String.valueOf(seeds2);
			break;	
		case 3: 
			for(int x = 1; x<=scaleX; x++){
				for(int y = 1; y<=scaleY; y++){
					for(int z = 1; z<=scaleZ; z++){
						if(farm3d[x][y][z] == 8){
							seeds++;
						}
					}
				}
			}
			seeds1 = seeds/64;
			seeds2 = seeds-seeds1*64;
			text = "Wheat seeds: " + String.valueOf(seeds1)
					+ " stacks and " + String.valueOf(seeds2);
			break;
		case 4: 
			for(int x = 1; x<=scaleX; x++){
				for(int y = 1; y<=scaleY; y++){
					for(int z = 1; z<=scaleZ; z++){
						if(farm3d[x][y][z] == 6){
							seeds++;
						}
					}
				}
			}
			seeds1 = seeds/64;
			seeds2 = seeds-seeds1*64;
			text = "Carrots: " + String.valueOf(seeds1)
					+ " stacks and " + String.valueOf(seeds2);
			break;
		case 5: 
			for(int x = 1; x<=scaleX; x++){
				for(int y = 1; y<=scaleY; y++){
					for(int z = 1; z<=scaleZ; z++){
						if(farm3d[x][y][z] == 7){
							seeds++;
						}
					}
				}
			}
			seeds1 = seeds/64;
			seeds2 = seeds-seeds1*64;
			text = "Potatos: " + String.valueOf(seeds1)
					+ " stacks and " + String.valueOf(seeds2);
			break;
		}
		im.drawString(text, x2, y2);
	}
	
	public void exportImage(BufferedImage b, String s) throws IOException{
		File imageFile = new File(s);
		ImageIO.write(b, "JPG", imageFile);
	}
	
	
	public class listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Melon){
				ChangeToMelon();
			}
			if(e.getSource()==Pumpkin){
				ChangeToPumpkin();
			}
			if(e.getSource()==Wheat){
				ChangeToWheat();
			}
			if(e.getSource()==Carrot){
				ChangeToCarrot();
			}
			if(e.getSource()==Potato){
				ChangeToPotato();
			}
			if(e.getSource()==onGrass){
				pos = 1;
			}
			if(e.getSource()==notOnGrass){
				pos = 2;
			}
			if(e.getSource()==inAir){
				pos = 3;
			}
			if(e.getSource()==Export){
				try {
					exportJPG();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource()==Glassed){
				glassed = true;
			}
			if(e.getSource()==NotGlassed){
				glassed = false;
			}
		}
	}
}