


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TextEditor extends JFrame implements ActionListener {
	
	JTextArea textarea;
	JScrollPane scrollpane;
	JSpinner fontsizespinner;
	JLabel fontlabel;
	JButton fontcolorbutton;
	JComboBox fontbox;
	JMenuBar menubar;
	JMenu filemenu;
	JMenuItem openitem;
	JMenuItem saveitem;
	JMenuItem exititem;
	
	TextEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Text Editor");
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		
		textarea=new JTextArea();

		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setFont(new Font("Arial",Font.PLAIN,50));
		
		scrollpane=new JScrollPane(textarea);
		scrollpane.setPreferredSize(new Dimension(450,450));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		fontlabel=new JLabel("Font: ");
		fontsizespinner=new JSpinner();
		fontsizespinner.setPreferredSize(new Dimension(50,25));
		fontsizespinner.setValue(20);
		fontsizespinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
			textarea.setFont(new Font(textarea.getFont().getFamily(),Font.PLAIN,(int) fontsizespinner.getValue()));
				
			}
		});
		
		fontcolorbutton=new JButton("color");
		fontcolorbutton.addActionListener(this);
		
		String[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontbox=new JComboBox(fonts);
		fontbox.addActionListener(this);
		fontbox.setSelectedItem("Arial");
		
		menubar=new JMenuBar();
		filemenu=new JMenu("File");
		openitem=new JMenuItem("open");
		saveitem=new JMenuItem("save");
		exititem=new JMenuItem("exit");
		
		filemenu.setMnemonic(KeyEvent.VK_F);
		openitem.setMnemonic(KeyEvent.VK_L);
		saveitem.setMnemonic(KeyEvent.VK_S);
		exititem.setMnemonic(KeyEvent.VK_E);
		openitem.addActionListener(this);
		saveitem.addActionListener(this);
		exititem.addActionListener(this);
		
		filemenu.add(openitem);
		filemenu.add(saveitem);
		filemenu.add(exititem);
		
		menubar.add(filemenu);
		
		
		
		
		
			
		this.setJMenuBar(menubar);
		this.add(fontlabel);
		this.add(fontsizespinner);
		this.add(fontcolorbutton);
		this.add(fontbox);
		this.add(scrollpane);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==fontcolorbutton) {
		JColorChooser colorchooser=new JColorChooser();
	
		Color color=colorchooser.showDialog(null,"choose a color",Color.black);
		textarea.setForeground(color);
		
       }
		
		if(e.getSource()==fontbox) {
			textarea.setFont(new Font((String)fontbox.getSelectedItem(),Font.PLAIN,textarea.getFont().getSize()));
		}
		if(e.getSource()==openitem) {

}
		if(e.getSource()==saveitem) {
			JFileChooser filechooser=new JFileChooser(); 
			filechooser.setCurrentDirectory(new File("C:\\Users\\V Bharathiraja\\Downloads"));
			
			int response=filechooser.showSaveDialog(null);
			
			if(response==JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileout=null;
				
			file=new File(filechooser.getSelectedFile().getAbsolutePath());
			try {
				fileout=new PrintWriter(file);
				fileout.println(textarea.getText());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			finally {
				fileout.close();
			}
				
				
				
			}

}

		if(e.getSource()==exititem) {
			System.exit(0);
		}

}


}
