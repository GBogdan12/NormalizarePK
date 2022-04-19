package sgbd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.*;
import java.awt.event.ActionListener;
import  java.sql.Statement ;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;    

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		graficaAlegereBazaDate();
	}
	
	private static void graficaAlegereBazaDate() {
		JFrame frame = new JFrame("SGBD Proiect");  
        JPanel panel = new JPanel(); 
        
        JButton button = new JButton();  
        button.setText("Alege o baza de date existenta"); 
        
        button.addActionListener( new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		grafica();
        	}
        });
        
          // Adauga in label's  
        panel.add(button);  
        frame.add(panel); 

        // Propietati frame
        
        frame.setSize(500, 720);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
        frame.pack();
        //connectiondb();
        
	}
	
	
	private static void grafica(){
		
		JFrame frame = new JFrame("SGBD Proiect");  
        JPanel panel = new JPanel(); 
        JButton[] button = new JButton[100];
        String numeBaza = null;
        int i=0;
        String numebaza = null;
		try
        {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
			String database = "test";
            String url = "jdbc:sqlserver://DESKTOP-TOLELQM\\SQLEXPRESS:1430;user=sa;password=bogdan123";
            Connection con = DriverManager.getConnection(url);
            Statement sta = con.createStatement();
            ResultSet listaBaze;
            listaBaze=sta.executeQuery("select name from sys.databases");
            
            while(listaBaze.next()) {
            	numebaza = listaBaze.getString(1);
                button[i] = new JButton(numebaza);  
                panel.add(button[i]);
                i++;
            }
            con.close();
            JOptionPane.showMessageDialog(null,
                    "Am gasit "+i+" baze de date!",
                    "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e)
        {
        	JOptionPane.showMessageDialog(null,
                    "Nu s-a putut connecta....",
                    "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
        }
		
		//Aleg la ce baza ma conectez
		button[6].addActionListener( new ActionListener() {
        	String numeBaza = "Cursuri_backup";
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,
                        "Ai selectat baza de date: " +numeBaza +"",
                        "Informatie",
                        JOptionPane.INFORMATION_MESSAGE);
        		connectiondb(numeBaza);
        	}
        });
		
		button[7].addActionListener( new ActionListener() {
        	String numeBaza = "Cursuri";
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,
                        "Ai selectat baza de date: " +numeBaza +"",
                        "Informatie",
                        JOptionPane.INFORMATION_MESSAGE);
        		connectiondb(numeBaza);
        	}
        });

		
          // Adauga in label's  
          
        frame.add(panel); 

        // Propietati frame
        
        frame.setSize(500, 720);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
        frame.pack();
        //connectiondb();
        
	}
	
	
	public static void connectiondb(String numeBaza){
		String numeBaza1 = numeBaza;
		
		JOptionPane.showMessageDialog(null,
                "Se incearca connexiunea cu baza de date: " +numeBaza +"....",
                "Informatie",
                JOptionPane.INFORMATION_MESSAGE);
		try
        {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");				
            String url = "jdbc:sqlserver://DESKTOP-TOLELQM\\SQLEXPRESS:1430;databaseName="+numeBaza1+";user=sa;password=bogdan123";
            Connection con = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null,
                    "S-a connectat cu succes la baza de date " +numeBaza +"....",
                   "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
            Statement sta = con.createStatement();
            String Sql="select Nume from Users";      
        } catch (Exception e)
        {
        	JOptionPane.showMessageDialog(null,
                    "Nu s-a putut connecta la " +numeBaza +"....",
                    "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
        }
		
		JFrame frame = new JFrame("SGBD Proiect");  
        JPanel panel = new JPanel(); 
        Label label1 = new Label("Normalizare Baza De Date");
        JButton button = new JButton("Tabele fara key primare");
        JButton button1 = new JButton("Verifica tipul cheii primare");
        frame.setSize(500, 720);  
        frame.setLayout(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
         
        
        button.setBounds(120, 150, 250, 30);
        button1.setBounds(120, 200, 250, 30);
        label1.setBounds(120,100,250,30);
        label1.setFont(new Font("Verdana",18, 0));
        frame.add(button);
        frame.add(button1);
        frame.add(label1);
        frame.setLayout(null);
        frame.setVisible(true); 
        
		//Butoane actiune
        
        button.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,
                        "Se verifica cheile primare... ",
                        "Informatie",
                        JOptionPane.INFORMATION_MESSAGE);
        		verificaExistentaCheilorPrimare(numeBaza1);
        		
        	}
        });
        
        button1.addActionListener( new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null,
                        "Se Cauta cheile primare stricate... ",
                        "Informatie",
                        JOptionPane.INFORMATION_MESSAGE);
        		verificaChei(numeBaza1);
        		
        	}
        });
        
		
        
	}
	
	public static void verificaExistentaCheilorPrimare(String numeBaza1) {
		String numeBaza = numeBaza1;
		String PrimaryKey = null;
		int i =0;
		int i1 =0;
		try
        {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");			
            String url = "jdbc:sqlserver://DESKTOP-TOLELQM\\SQLEXPRESS:1430;databaseName="+numeBaza+";user=sa;password=bogdan123";
            Connection con = DriverManager.getConnection(url);     
            String tabelePk = "Use " + numeBaza1+"; select TABLE_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE CONSTRAINT_NAME LIKE '%pk%'";
            String tabele = "Use " + numeBaza1+"; select TABLE_NAME from INFORMATION_SCHEMA.TABLES";
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            ResultSet rs = stmt.executeQuery(tabelePk);
            ResultSet rs2 = stmt2.executeQuery(tabele);
            
            List<String> tabelePk1 = new ArrayList<String>();
            List<String> tabele1 = new ArrayList<String>();
            List<String> tabeleFaraPk1 = new ArrayList<String>();
            
            while(rs2.next()) {
            	 tabele1.add(rs2.getString(1));
            	 tabeleFaraPk1.add(rs2.getString(1));
            	 
            } 
         
            while(rs.next()) {
            	tabelePk1.add(rs.getString(1));
            	
           } 
            
            if(tabele1.size() >= tabelePk1.size() ) {      	
            for(int i5=0;i5<tabele1.size();i5++) {     
            	for(int i6=0;i6<tabelePk1.size();i6++) {   
            		if(tabele1.get(i5).equals(tabelePk1.get(i6))) {
            			tabeleFaraPk1.remove(tabele1.get(i5));
            		}
            	}
            }
            }
            if(tabelePk1.size()>0) {
            	PrimaryKey ="tabelePk1.size()";
            }
            if(tabeleFaraPk1.size()==1) {
       		//grafica pentru tabelele fara chei primare
            	JFrame frame = new JFrame("SGBD Proiect");  
                JPanel panel = new JPanel(); 
                JButton button = new JButton("Alege");
                Label label1 = new Label("Tabele Fara Chei primare: " + tabeleFaraPk1.get(0));  
                Label label2 = new Label("In ce tabel doriti o cheie primara?");  
                Object[][] data = new Object [tabeleFaraPk1.size()][tabeleFaraPk1.size()];
                JTextField textCheiePrimara = new JTextField(); 
                
                button.setBounds(480,560,150,30);
                label1.setBounds(120,100,350,30);
                label1.setFont(new Font("Verdana",18, 0)); 
                label2.setBounds(120,500,310,30);
                label2.setFont(new Font("Verdana",11, 0)); 
                textCheiePrimara.setBounds(450,500,310,30);
                textCheiePrimara.setBounds(450,500,310,30);
                JButton button1 = new JButton("Inapoi");
                button1.setBounds(480,600,150,30);
              
                frame.add(button1);
            	frame.setSize(820, 720);  
                frame.setLayout(null); 
                frame.add(button);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                frame.add(label1);
                frame.add(label2);
                frame.add(textCheiePrimara);
                frame.setLayout(null);
                frame.setVisible(true);
        		
                
                button.addActionListener( new ActionListener() {
                	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		boolean exista = false;
                	
                		for(int i5=0;i5<tabeleFaraPk1.size();i5++) {        
                		if(tabeleFaraPk1.get(i5).toLowerCase().equals(textCheiePrimara.getText().toLowerCase()) && textCheiePrimara.getText().length()>0 ) {                			
                			exista=true;
                			
                		}}
                		 if (exista == true) {
                			 String numeCheiePrimara = (String)JOptionPane.showInputDialog(
                                     frame,
                                     "Introduceti numele cheii primare pentru tabela "+textCheiePrimara.getText()+" :\n", JOptionPane.PLAIN_MESSAGE);
                			 
                			 if (((numeCheiePrimara == null) || (numeCheiePrimara.length() <= 0))) {
                				 JOptionPane.showMessageDialog(null,
                                         "Nume eronat....",
                                         "Informatie",
                                         JOptionPane.INFORMATION_MESSAGE);
                				    return;
                			 }
                			 else
                			 {
                				 String sql = "ALTER TABLE " + textCheiePrimara.getText() + " ADD " + numeCheiePrimara +" NUMERIC NOT NULL IDENTITY(1,1) PRIMARY KEY;";
                				 try {
                					 
									ResultSet rs3 = stmt3.executeQuery(sql);
									verificaExistentaCheilorPrimare(numeBaza);
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null,
	                                         "Cheia primara " + numeCheiePrimara + " a fost adaugata in tabela " + textCheiePrimara.getText(),
	                                         "Informatie",
	                                         JOptionPane.INFORMATION_MESSAGE);
									verificaExistentaCheilorPrimare(numeBaza);
								}
                			 }
                		 }

                	}
                });
                
                button1.addActionListener( new ActionListener() {
                	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		graficaAlegereBazaDate();
                	}});
            
        	}  
            else
            if(PrimaryKey == null) { 
    	
            	if(tabeleFaraPk1.size()>1) {
            	
            	 
            		//grafica pentru tabelele fara chei primare
                	JFrame frame = new JFrame("SGBD Proiect");  
                    JPanel panel = new JPanel(); 
                    JButton button = new JButton("Alege");
                    
                    Label label1 = new Label("Tabele Fara Chei primare");  
                    Label label2 = new Label("In ce tabel doriti o cheie primara?");  
                    Object[][] data = new Object [tabeleFaraPk1.size()][tabeleFaraPk1.size()];
                    JTextField textCheiePrimara = new JTextField(); 
                    JButton button1 = new JButton("Inapoi");
                    button1.setBounds(480,600,150,30);
                    frame.add(button1);
                    button.setBounds(480,560,150,30);
                   
                    label1.setBounds(120,100,250,30);
                    label1.setFont(new Font("Verdana",18, 0)); 
                    label2.setBounds(120,500,310,30);
                    label2.setFont(new Font("Verdana",11, 0)); 
                    textCheiePrimara.setBounds(450,500,310,30);
                    textCheiePrimara.setBounds(450,500,310,30);
                	for(int i5=0;i5<tabeleFaraPk1.size();i5++) {
                		data[i5][1] = tabeleFaraPk1.get(i5);
                		data[i5][0] = "Nume Tabel";
                	}
                	 String[] columns = new String[] {"Nr", "Nume_Tabel"};
                    
                    JTable table = new JTable(data, columns);
                    
                    table.setBounds(120,160,300,200);
                    table.disable();
                    JScrollPane sp=new JScrollPane(table);  
                	frame.add(sp);
                	frame.add(table);
                	frame.setSize(820, 720);  
                    frame.setLayout(null); 
                    frame.add(button);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                    frame.add(label1);
                    frame.add(label2);
                    frame.add(textCheiePrimara);
                    frame.setLayout(null);
                    frame.setVisible(true);
                    
                    button.addActionListener( new ActionListener() {
                    	
                    	@Override
                    	public void actionPerformed(ActionEvent e) {
                    		boolean exista = false;
                    	
                    		for(int i5=0;i5<tabeleFaraPk1.size();i5++) {        
                    		if(tabeleFaraPk1.get(i5).toLowerCase().equals(textCheiePrimara.getText().toLowerCase()) && textCheiePrimara.getText().length()>0 ) {                			
                    			exista=true;
                    			
                    		}}
                    		 if (exista == true) {
                    			 String numeCheiePrimara = (String)JOptionPane.showInputDialog(
                                         frame,
                                         "Introduceti numele cheii primare pentru tabela "+textCheiePrimara.getText()+" :\n", JOptionPane.PLAIN_MESSAGE);
                    			 
                    			 if (((numeCheiePrimara == null) || (numeCheiePrimara.length() <= 0))) {
                    				 JOptionPane.showMessageDialog(null,
                                             "Nume eronat....",
                                             "Informatie",
                                             JOptionPane.INFORMATION_MESSAGE);
                    				    return;
                    			 }
                    			 else
                    			 {
                    				 String sql = "ALTER TABLE " + textCheiePrimara.getText() + " ADD " + numeCheiePrimara +" NUMERIC NOT NULL IDENTITY(1,1) PRIMARY KEY;";
                    				 try {
                    					 
    									ResultSet rs3 = stmt3.executeQuery(sql);
    									verificaExistentaCheilorPrimare(numeBaza);
    								} catch (SQLException e1) {
    									JOptionPane.showMessageDialog(null,
    	                                         "Cheia primara " + numeCheiePrimara + " a fost adaugata in tabela " + textCheiePrimara.getText(),
    	                                         "Informatie",
    	                                         JOptionPane.INFORMATION_MESSAGE);
    									verificaExistentaCheilorPrimare(numeBaza);
    								}
                    			 }
                    		 }

                    	}
                    });
                
                    button1.addActionListener( new ActionListener() {
                    	
                    	@Override
                    	public void actionPerformed(ActionEvent e) {
                    		connectiondb(numeBaza);
                    	}});
           
            	
            	}
            	  else {
            		  JOptionPane.showMessageDialog(null,
                              "Nu exista tabele in baza de date:  " +numeBaza +"....",
                              "Informatie",
                              JOptionPane.INFORMATION_MESSAGE);
                  	graficaAlegereBazaDate();
                  }
            }
            else
            {
            	//grafica pentru tabelele fara chei primare
            	JFrame frame = new JFrame("SGBD Proiect");  
                JPanel panel = new JPanel(); 
                JButton button = new JButton("Alege");
                Label label1 = new Label("Tabele Fara Chei primare");  
                Label label2 = new Label("In ce tabel doriti o cheie primara?");  
                Object[][] data = new Object [tabeleFaraPk1.size()][tabeleFaraPk1.size()];
                JTextField textCheiePrimara = new JTextField(); 
                JButton button1 = new JButton("Inapoi");
                button1.setBounds(480,600,150,30);
                frame.add(button1);
                
                
                button.setBounds(480,560,150,30);
                label1.setBounds(120,100,250,30);
                label1.setFont(new Font("Verdana",18, 0)); 
                label2.setBounds(120,500,310,30);
                label2.setFont(new Font("Verdana",11, 0)); 
                textCheiePrimara.setBounds(450,500,310,30);
                textCheiePrimara.setBounds(450,500,310,30);
            	for(int i5=0;i5<tabeleFaraPk1.size();i5++) {
            		data[i5][1] = tabeleFaraPk1.get(i5);
            		data[i5][0] = "Nume Tabel";
            	}
            	 String[] columns = new String[] {"Nr", "Nume_Tabel"};
                
                JTable table = new JTable(data, columns);
                
                table.setBounds(120,160,300,200);
                table.disable();
                JScrollPane sp=new JScrollPane(table);  
            	frame.add(sp);
            	frame.add(table);
            	frame.setSize(820, 720);  
                frame.setLayout(null); 
                frame.add(button);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                frame.add(label1);
                frame.add(label2);
                frame.add(textCheiePrimara);
                frame.setLayout(null);
                frame.setVisible(true);
                
                button.addActionListener( new ActionListener() {
                	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		boolean exista = false;
                	
                		for(int i5=0;i5<tabeleFaraPk1.size();i5++) {        
                		if(tabeleFaraPk1.get(i5).toLowerCase().equals(textCheiePrimara.getText().toLowerCase()) && textCheiePrimara.getText().length()>0 ) {                			
                			exista=true;
                			
                		}}
                		 if (exista == true) {
                			 String numeCheiePrimara = (String)JOptionPane.showInputDialog(
                                     frame,
                                     "Introduceti numele cheii primare pentru tabela "+textCheiePrimara.getText()+" :\n", JOptionPane.PLAIN_MESSAGE);
                			 
                			 if (((numeCheiePrimara == null) || (numeCheiePrimara.length() <= 0))) {
                				 JOptionPane.showMessageDialog(null,
                                         "Nume eronat....",
                                         "Informatie",
                                         JOptionPane.INFORMATION_MESSAGE);
                				    return;
                			 }
                			 else
                			 {
                				 String sql = "ALTER TABLE " + textCheiePrimara.getText() + " ADD " + numeCheiePrimara +" NUMERIC NOT NULL IDENTITY(1,1) PRIMARY KEY;";
                				 try {
                					 
									ResultSet rs3 = stmt3.executeQuery(sql);
									verificaExistentaCheilorPrimare(numeBaza);
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null,
	                                         "Cheia primara " + numeCheiePrimara + " a fost adaugata in tabela " + textCheiePrimara.getText(),
	                                         "Informatie",
	                                         JOptionPane.INFORMATION_MESSAGE);
									verificaExistentaCheilorPrimare(numeBaza);
								}
                			 }
                		 }

                	}
                });
                
                button1.addActionListener( new ActionListener() {
                	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		connectiondb(numeBaza);               
                		}});
            
              
            }
            
        } catch (Exception e)
        {
        	JOptionPane.showMessageDialog(null,
                    "A aparut o eroare...",
                    "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
        }
		
		
	}
	
	
	public static void verificaChei(String numeBaza1) {
		String numeBaza = numeBaza1;		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-TOLELQM\\SQLEXPRESS:1430;databaseName="+numeBaza+";user=sa;password=bogdan123";
		
		 Connection con = DriverManager.getConnection(url);     
         String cheiStricate = "SELECT DISTINCT kcu.column_name, kcu.ordinal_position, kcu.TABLE_NAME NumeTabel, kcu.CONSTRAINT_NAME, c.DATA_TYPE, C.IS_NULLABLE\r\n"
         		+ "FROM   information_schema.table_constraints tc\r\n"
         		+ "INNER JOIN information_schema.key_column_usage kcu\r\n"
         		+ "ON     tc.TABLE_NAME = kcu.TABLE_NAME AND    tc.CONSTRAINT_NAME = kcu.CONSTRAINT_NAME\r\n"
         		+ "INNER JOIN INFORMATION_SCHEMA.COLUMNS c\r\n"
         		+ "ON    c.TABLE_NAME=kcu.TABLE_NAME WHERE tc.constraint_type='PRIMARY KEY' AND tc.TABLE_CATALOG='"+numeBaza+"' AND c.DATA_TYPE!='numeric' AND c.IS_NULLABLE='no' AND kcu.TABLE_NAME!='sysdiagrams'";
        		 
         Statement stmt = con.createStatement();

         ResultSet rs = stmt.executeQuery(cheiStricate);
         
         List<String> tabelPk = new ArrayList<String>();
         List<String> NumeTabel = new ArrayList<String>();

         while(rs.next()) {
        	 tabelPk.add(rs.getString(1));  
        	 NumeTabel.add(rs.getString(3));
         }   
         if(tabelPk.size()==0) {
        	 JOptionPane.showMessageDialog(null,
                     "Nu exista chei primare care necesita reparatii..",
                     "Informatie",
                     JOptionPane.INFORMATION_MESSAGE);
         }
         else if(tabelPk.size()>1)
         {
        	 
        	//grafica pentru cheile primare >1
         	JFrame frame = new JFrame("SGBD Proiect");  
             JPanel panel = new JPanel(); 
             JButton button = new JButton("Alege");
             Label label1 = new Label("Chei primare stricate");  
             Label label2 = new Label("Care este cheia de normalizat?");  
             Object[][] data = new Object [tabelPk.size()+1][tabelPk.size()+1];
             JTextField textCheiePrimara = new JTextField(); 
             JButton button1 = new JButton("Inapoi");
             button1.setBounds(480,600,150,30);
             frame.add(button1);
             
             
             button.setBounds(480,560,150,30);
             label1.setBounds(120,100,250,30);
             label1.setFont(new Font("Verdana",18, 0)); 
             label2.setBounds(120,500,310,30);
             label2.setFont(new Font("Verdana",11, 0)); 
             textCheiePrimara.setBounds(450,500,310,30);
             textCheiePrimara.setBounds(450,500,310,30);
             data[0][1] = "Cheie primara";
      		data[0][0] = "Nume Tabel";
         	for(int i5=0;i5<tabelPk.size();i5++) {
         		data[i5+1][1] = tabelPk.get(i5);
         		data[i5+1][0] = NumeTabel.get(i5);
         	}
         	 String[] columns = new String[] {"Nr", "Nume_Cheie"};
             
             JTable table = new JTable(data, columns);
             
             table.setBounds(120,160,300,200);
             table.disable();
             JScrollPane sp=new JScrollPane(table);  
         	frame.add(sp);
         	frame.add(table);
         	frame.setSize(820, 720);  
             frame.setLayout(null); 
             frame.add(button);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
             frame.add(label1);
             frame.add(label2);
             frame.add(textCheiePrimara);
             frame.setLayout(null);
             frame.setVisible(true);
             
             button1.addActionListener( new ActionListener() {
              	
              	@Override
              	public void actionPerformed(ActionEvent e) {
              		connectiondb(numeBaza);
              	}});
             
             button.addActionListener( new ActionListener() {
               	boolean exista = false;
               	int indexTabel =0;
               	@Override
               	public void actionPerformed(ActionEvent e) {        		
               		for(int i2=0;i2<tabelPk.size();i2++) {
               			if(tabelPk.get(i2).toLowerCase().equals(textCheiePrimara.getText().toLowerCase() ) && textCheiePrimara.getText().length()>0){
               				indexTabel=i2;
               				exista=true;
               				
               			}
               		}
               		//cheie primara corecta
               		if(exista==true) {
               			
               			
               			cheieprimaranoua(textCheiePrimara.getText().toLowerCase(), numeBaza, NumeTabel.get(indexTabel));
               		}
               		
               		
               	}});
        	 
         }
         else if(tabelPk.size()==1){
        	 
        	//grafica pentru cheile primare =1 
          	JFrame frame = new JFrame("SGBD Proiect");  
              JPanel panel = new JPanel(); 
              JButton button = new JButton("Alege");
              Label label1 = new Label("Chei primare stricate: "+ tabelPk.get(0) + ", din tabela: " + NumeTabel.get(0));  
              Label label2 = new Label("Care este cheia de normalizat?");  
              Object[][] data = new Object [1][1];
              JTextField textCheiePrimara = new JTextField(); 
              JButton button1 = new JButton("Inapoi");
              button1.setBounds(480,600,150,30);
              frame.add(button1);
              
              
              button.setBounds(480,560,150,30);
              label1.setBounds(120,100,550,30);
              label1.setFont(new Font("Verdana",18, 0)); 
              label2.setBounds(120,500,310,30);
              label2.setFont(new Font("Verdana",11, 0)); 
              textCheiePrimara.setBounds(450,500,310,30);
              textCheiePrimara.setBounds(450,500,310,30);
		
              frame.setSize(820, 720);  
              frame.setLayout(null); 
              frame.add(button);
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
              frame.add(label1);
              frame.add(label2);
              frame.add(textCheiePrimara);
              frame.setLayout(null);
              frame.setVisible(true);
              
              button1.addActionListener( new ActionListener() {
                	
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		connectiondb(numeBaza);
                	}});
               
               button.addActionListener( new ActionListener() {
                 	boolean exista = false;
                 	int indexTabel=0;
                 	@Override
                 	public void actionPerformed(ActionEvent e) {        		
                 		for(int i2=0;i2<tabelPk.size();i2++) {
                 			if(tabelPk.get(i2).toLowerCase().equals(textCheiePrimara.getText().toLowerCase() ) && textCheiePrimara.getText().length()>0){
                 				exista=true;
                 				indexTabel = i2;
                 			}
                 		}
                 		//cheie primara corecta
                 		if(exista==true) {
                 			
                 			
                 			cheieprimaranoua(textCheiePrimara.getText().toLowerCase(), numeBaza, NumeTabel.get(indexTabel));
                 		}
                 		
                 		
                 	}});
         }
         
          
         
         
         
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
                    "Ceva nu a mers bine..",
                    "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
			    return;
		}
		
        
		
	}
	
	public static void cheieprimaranoua(String cheieprimara1, String numeBaza1, String tabel1) {
		String numeBaza = numeBaza1;
		String cheieprimara = cheieprimara1;
		String tabel = tabel1;
		String numeConstrangere=null;
        List<String> ParentTable = new ArrayList<String>();
        List<String> NameColParent = new ArrayList<String>();
        List<String> NameRef = new ArrayList<String>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-TOLELQM\\SQLEXPRESS:1430;databaseName="+numeBaza+";user=sa;password=bogdan123";
		 Connection con = DriverManager.getConnection(url);     
		 
		 
		 
		 
         String numeCheie = "SELECT DISTINCT kcu.CONSTRAINT_NAME\r\n"
         		+ "FROM   information_schema.table_constraints tc\r\n"
         		+ "INNER JOIN information_schema.key_column_usage kcu\r\n"
         		+ "ON     tc.TABLE_NAME = kcu.TABLE_NAME AND    tc.CONSTRAINT_NAME = kcu.CONSTRAINT_NAME\r\n"
         		+ "INNER JOIN INFORMATION_SCHEMA.COLUMNS c\r\n"
         		+ "ON    c.TABLE_NAME=kcu.TABLE_NAME\r\n"
         		+ "where  tc.constraint_type = 'PRIMARY KEY' and tc.TABLE_CATALOG='"+numeBaza+"' AND kcu.COLUMN_NAME='"+cheieprimara+"' AND kcu.TABLE_NAME= '"+tabel+"'";
         Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(numeCheie); 
        Statement stmt2 = con.createStatement();
        Statement stmt3 = con.createStatement();
        while(rs.next())
        {
        	numeConstrangere = rs.getString(1);
        }
        
        String legaturi = "use " + numeBaza +"\r\n"
		 		+ "SELECT\r\n"
		 		+ "    fk.name 'FK Name',\r\n"
		 		+ "    tp.name 'Parent table',\r\n"
		 		+ "    cp.name, cp.column_id,\r\n"
		 		+ "    tr.name 'Refrenced table',\r\n"
		 		+ "    cr.name, cr.column_id\r\n"
		 		+ "FROM \r\n"
		 		+ "    sys.foreign_keys fk\r\n"
		 		+ "INNER JOIN \r\n"
		 		+ "    sys.tables tp ON fk.parent_object_id = tp.object_id\r\n"
		 		+ "INNER JOIN \r\n"
		 		+ "    sys.tables tr ON fk.referenced_object_id = tr.object_id\r\n"
		 		+ "INNER JOIN \r\n"
		 		+ "    sys.foreign_key_columns fkc ON fkc.constraint_object_id = fk.object_id\r\n"
		 		+ "INNER JOIN \r\n"
		 		+ "    sys.columns cp ON fkc.parent_column_id = cp.column_id AND fkc.parent_object_id = cp.object_id\r\n"
		 		+ "INNER JOIN \r\n"
		 		+ "    sys.columns cr ON fkc.referenced_column_id = cr.column_id AND fkc.referenced_object_id = cr.object_id\r\n"
		 		+ "	where cr.name = '"+cheieprimara+"'"+" and tr.name='"+tabel+"'\r\n"
		 		+ "ORDER BY\r\n"
		 		+ "    tp.name, cp.column_id";
	 
         //System.out.print(legaturi);
	      
	       ResultSet rs2 = stmt2.executeQuery(legaturi); 
	       int i=0;
	       while(rs2.next()) {
	    	   ParentTable.add(rs2.getString(2));
	    	   NameColParent.add(rs2.getString(3));
	    	   NameRef.add(rs2.getString(1));
	    	   System.out.println("ParentTable "+ParentTable.get(i)+" NumeColParent: " + NameColParent.get(i)+" din tabelul: "+tabel +" cheia: " + cheieprimara);
	    	   i++;
	    	   System.out.println(ParentTable.size());
	       }
	       if(ParentTable.size()==0) {
	    	   JOptionPane.showMessageDialog(null,
	                    "NU am gasit legaturi cu cheia primara aleasa..",
	                    "Informatie",
	                    JOptionPane.INFORMATION_MESSAGE);
	    	   JOptionPane.showMessageDialog(null, "Se incearca normalizarea cheii primare..", "Informatie",
                       JOptionPane.INFORMATION_MESSAGE);
         String dropPK= "USE " +numeBaza +" ALTER TABLE " + tabel + " DROP CONSTRAINT " + numeConstrangere;
         Statement stmt6 = con.createStatement();
	         stmt6.execute(dropPK);
	         
	         String dropCol = "USE " +numeBaza +" ALTER TABLE " + tabel + " DROP COLUMN " + cheieprimara;
	         Statement stmt4 = con.createStatement();
	         System.out.println(dropCol);
	         stmt4.execute(dropCol);
         
         String createPK = "USE " +numeBaza + " ALTER TABLE " + tabel + " ADD " + cheieprimara +" NUMERIC NOT NULL IDENTITY(1,1) PRIMARY KEY;";
	         Statement stmt5 = con.createStatement();
	         stmt5.execute(createPK);
	         System.out.println(createPK);
	         JOptionPane.showMessageDialog(null, "Am normalizat cheia primara din tabela "+tabel, "Informatie",
	                 JOptionPane.INFORMATION_MESSAGE);
	       }
	       else if(ParentTable.size()>0)
	       {
	    	   int i1=0;
	    	   JOptionPane.showMessageDialog(null,
	                    "Am gasit legaturi cu cheia primara aleasa..",
	                    "Informatie",
	                    JOptionPane.INFORMATION_MESSAGE);
	    	   JOptionPane.showMessageDialog(null, "Se incearca normalizarea cheii primare..", "Informatie",
                       JOptionPane.INFORMATION_MESSAGE);
	    	   for(int i2=0;i2<NameRef.size();i2++) {
	    		   String dropPKRef= "USE " +numeBaza +" ALTER TABLE " + ParentTable.get(i2) + " DROP CONSTRAINT " + NameRef.get(i2);
		          Statement stmt6 = con.createStatement();
		  	      stmt6.execute(dropPKRef);
		  	       String dropCol = "USE " +numeBaza +" ALTER TABLE " + ParentTable.get(i2) + " DROP COLUMN " + NameColParent.get(i2);
		  	      Statement stmt7 = con.createStatement();
		  	       stmt7.execute(dropCol);
		  	       String createCol = "USE "+numeBaza+ " ALTER TABLE " + ParentTable.get(i2) +" ADD " + NameColParent.get(i2) + " numeric";
			  	  Statement stmt8 = con.createStatement();
			  	     stmt8.execute(createCol);
	    	   }
	    	   
	    	   String dropPK= "USE " +numeBaza +" ALTER TABLE " + tabel + " DROP CONSTRAINT " + numeConstrangere;
	             Statement stmt6 = con.createStatement();
	  	         stmt6.execute(dropPK);
	    	   
	  	       String dropCol = "USE " +numeBaza +" ALTER TABLE " + tabel + " DROP COLUMN " + cheieprimara;
		         Statement stmt4 = con.createStatement();
		         stmt4.execute(dropCol);
	  	         
	  	       String createPK = "USE " +numeBaza + " ALTER TABLE " + tabel + " ADD " + cheieprimara +" NUMERIC NOT NULL IDENTITY(1,1) PRIMARY KEY;";
		         Statement stmt5 = con.createStatement();
		        stmt5.execute(createPK);
		         for(int i2=0;i2<NameRef.size();i2++) {
		       String createPKRef = "USE " +numeBaza + " ALTER TABLE " + ParentTable.get(i2) +" ADD CONSTRAINT FK_"+i2+"_" + ParentTable.get(i2).toUpperCase()+"_"+tabel.toUpperCase()+" FOREIGN KEY( " + NameColParent.get(i2) +" ) REFERENCES " + tabel + "( "+cheieprimara +" )";
		       Statement stmt7 = con.createStatement();
		         stmt7.execute(createPKRef);
		         
		         JOptionPane.showMessageDialog(null,
		                    "Cheia primara a fost normalizata si referintele create!",
		                    "Informatie",
		                    JOptionPane.INFORMATION_MESSAGE);
		         
		         }
	       }
        

        
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
                    "Ceva nu a mers bine..",
                    "Informatie",
                    JOptionPane.INFORMATION_MESSAGE);
			    return;
		}
		
		
	}
	
	
	

}

