import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.*;

import java.text.*;
import java.awt.print.*;

import java.lang.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * home_page.javaem
 *
 * Created on Dec 9, 2016, 3:48:38 PM
 */
/**
 *
 * @author RAGHAV GUPTA
 */
class Printer implements Printable {
    final Component comp;

    public Printer(Component comp){
        this.comp = comp;
    }

    @Override
    public int print(Graphics g, PageFormat format, int page_index) 
            throws PrinterException {
        if (page_index > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = comp.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = format.getImageableHeight();
        double pWidth = format.getImageableWidth();

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;


        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);

        return Printable.PAGE_EXISTS;
    }
}

public class home_page extends javax.swing.JFrame {

    /** Creates new form home_page */
    float tax=0;
    DefaultTableModel model;
    public home_page() {
        /*try{
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/atm","root","");
		Statement stmt=con.createStatement();
			}
        catch(Exception ee){}

      */
        
        
        
        initComponents();
        
        //  *****************PRINT **************
       /* public int print(Graphics g,PageFormat pf,int page)
                throws PrinterException{
            if(page>0){
                return NO_SUCH_PAGE;
            }
            Graphics2d g2d=(Graphics2d)g;
            g2d.translate(pf.getImageableX(),pf.getImageableY());
            home_page.printAll(g);
          return PAGE_EXISTS.
          }
        
        */
        
        //INVOICE NUMBER
      
        tfinvoice.setText("1");
        tftax.setText("0");
        // while( band na ho )
        //{
            
         //   tfinvoice=tfinvoice++;
       // }
        
        
        
        
        
        
        
        
        
        
        
        
        
  //************DATE SETTING  ****************
        
        DateFormat dateformat=new SimpleDateFormat("dd-MM-yyyy HH:ss");
        Date date=new Date();
        tfdate.setText(dateformat.format(date));
    
        // **************TABLE MODEL  *********************
        getSum();
        model=(DefaultTableModel)tblitem.getModel();
        
        }
    
    //******************TOTAL SUM OF ALL GOODS FROM THE TABLE   *******************
    public float getSum()
    {
    int rowsCount=tblitem.getRowCount();
    float sum=0;
    for(int i=0;i<rowsCount;i++)
        sum=sum+Float.parseFloat(tblitem.getValueAt(i, 3).toString());
      tftotalamt.setText(Float.toString(sum));
    return sum;
    
    }
    
    
    
    
    //***********************CONVERT NUMBER TO WORD*************************
    
       
         private static String input;
private static String[] units= {"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine" }; 
private static String[] teen= {" Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen" }; 
private static String[] tens= { " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety" };
 private static String[] maxs= {"", "", " Hundred", " Thousand", " Lakh", " Crore" }; 
String converthome_pages(int n)
 	{
		 input=bToString(n); 
		String converted=" ";
		int pos=1;
		 boolean hun=false;
                 System.out.println("hello1");
		 while(input.length()> 0)
		 {
			 if(pos==1) // TENS AND UNIT POSITION
			 {
				 if(input.length()>= 2) // TWO DIGIT BBERS 
				{	
					String temp=input.substring(input.length()-2,input.length());
					 input=input.substring(0,input.length()-2);
					 converted=converted+digits(temp);
				 }
                                         
				else if(input.length()==1) // 1 DIGIT BBER
				 {
				 converted+=digits(input);
				 input="";
				 }
			 pos++; 
                         System.out.println("hello2");
			}
                                 
			 else if(pos==2) // HUNDRED POSITION 
			{
				String temp=input.substring(input.length()-1,input.length()); 
				input=input.substring(0,input.length()-1); 
				if(converted.length()> 0&&digits(temp)!="")
				{
					 converted=(digits(temp)+maxs[pos]+" and")+converted; hun=true;
				 }
				 else 
				{
					 if (digits(temp)=="");
					 else
					 converted=(digits(temp)+maxs[pos])+converted;hun=true; 
				}
			 pos++;
                         System.out.println("hello3");
			 }
			 else if(pos > 2) // REMAINING BBERS PAIRED BY TWO 
			{
				 if(input.length()>= 2) // EXTRACT 2 DIGITS 
				{
					String temp=input.substring(input.length()-2,input.length());
					 input=input.substring(0,input.length()-2); 
					if(!hun&&converted.length()> 0) 
					converted=digits(temp)+maxs[pos]+" and"+converted; 
					else { if(digits(temp)=="") ; 
					else converted=digits(temp)+maxs[pos]+converted; 
				}
			 }
				 else if(input.length()==1) // EXTRACT 1 DIGIT 
				{
					 if(!hun&&converted.length()> 0)
					 converted=digits(input)+maxs[pos]+" and"+converted; 
					else
					 {
						 if(digits(input)=="") ;
						 else converted=digits(input)+maxs[pos]+converted;
                                                 input="";
					 } 
				}
			 pos++;
                         System.out.println("hello4");
			 }
	 	}
	 
                 return converted;
                 
         
	 } 
	 String digits(String temp) // TO RETURN SELECTED BBERS IN WORDS
	 {
		 String converted="";
		 for(int i=temp.length()-1;i >= 0;i--) 
		{
			 int ch=temp.charAt(i)-48; 
			if(i==0&&ch>1 && temp.length()> 1)
			 converted=tens[ch-2]+converted; // IF TENS DIGIT STARTS WITH 2 OR MORE IT FALLS UNDER TENS 
			else if(i==0&&ch==1&&temp.length()==2) // IF TENS DIGIT STARTS WITH 1 IT FALLS UNDER TEENS
			 {
				 int sum=0;
				 for(int j=0;j < 2;j++) 
				sum=(sum*10)+(temp.charAt(j)-48); 
        return teen[sum-10];
        
			}
			 else
			{
				 if(ch > 0)
				 converted=units[ch]+converted;
			 } // IF SINGLE DIGIT PROVIDED 
		}
		 return converted;
	 }
	  String bToString(int x) // CONVERT THE BBER TO STRING 
	{
		 String b="";
	 	while(x!=0)
		 {
			 b=((char)((x%10)+48))+b; x/=10; 
		}
	 return b;
	 } 
	

    
    
    
    
    
    
    
    
    
    
    
        
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heading = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        lblcontact = new javax.swing.JLabel();
        lbladdress = new javax.swing.JLabel();
        lblname = new javax.swing.JLabel();
        tfname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfqty = new javax.swing.JTextField();
        tfprice = new javax.swing.JTextField();
        tfamt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        tfcontact = new javax.swing.JTextField();
        tfaddress = new javax.swing.JTextField();
        tfdesc = new javax.swing.JComboBox();
        lblname1 = new javax.swing.JLabel();
        tfname1 = new javax.swing.JTextField();
        lblname2 = new javax.swing.JLabel();
        tfname2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblitem = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btnrm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tftotalamt = new javax.swing.JTextField();
        btntotalamt = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tftax = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tffinalamt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tfwords = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfdate = new javax.swing.JTextField();
        tfinvoice = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnprint = new javax.swing.JButton();
        btnlogout = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        heading.setBackground(new java.awt.Color(-987112,true));
        heading.setFont(new java.awt.Font("Serif", 1, 14));
        heading.setText("MAA BHAGWATI TRADERS");

        home.setBackground(new java.awt.Color(-1,true));
        home.setToolTipText("");
        home.setName(""); // NOI18N

        lblcontact.setText("CONTACT");

        lbladdress.setText("ADDERESS");

        lblname.setText("CUSTOMER NAME");

        tfname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnameActionPerformed(evt);
            }
        });

        jLabel7.setText("DESCRIPTION");

        jLabel11.setText("QUANTITY");

        jLabel12.setText("AMOUNT");

        jLabel13.setText("UNIT PRICE");

        tfqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfqtyActionPerformed(evt);
            }
        });

        tfprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfpriceActionPerformed(evt);
            }
        });

        tfamt.setEditable(false);
        tfamt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfamtActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 24));
        jLabel14.setText("ITEMS PURCHASED");

        tfcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfcontactActionPerformed(evt);
            }
        });

        tfaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfaddressActionPerformed(evt);
            }
        });

        tfdesc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "KHAJOOR CHOKAR (34 K)", "KHAL SALONI (60 K)", "KHAL SWASTIK(60 K)", "KHAL SWASTIK(70 K)", "BALAJI BILAUNA KHAL", "CHILKHA", "CHOORI(50 K)", "CHANA", "KHANDA(45 K)", " " }));

        lblname1.setText("GSTIN NUMBER");

        tfname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfname1ActionPerformed(evt);
            }
        });

        lblname2.setText("PAN/UNIQUE NUMBER");

        tfname2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfname2ActionPerformed(evt);
            }
        });

        tblitem.setAutoCreateRowSorter(true);
        tblitem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPTION", "HSN CODE", "QUANTITY", "UNIT PRICE", "AMOUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblitem.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblitem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblitem.setName(""); // NOI18N
        jScrollPane1.setViewportView(tblitem);

        btnadd.setMnemonic('E');
        btnadd.setText("ADD NEW ITEM");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        btnadd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btnaddPropertyChange(evt);
            }
        });

        btnrm.setMnemonic('E');
        btnrm.setText("REMOVE SELECTED ROW");
        btnrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrmActionPerformed(evt);
            }
        });
        btnrm.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btnrmPropertyChange(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(-4144960,true));

        tftotalamt.setEditable(false);
        tftotalamt.setAutoscrolls(false);
        tftotalamt.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btntotalamt.setText("TOTAL AMOUNT");
        btntotalamt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntotalamtActionPerformed(evt);
            }
        });

        jLabel15.setText("+");

        jLabel16.setText("TAX");

        tftax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftaxActionPerformed(evt);
            }
        });

        jLabel17.setText("PAYABLE AMOUNT");

        tffinalamt.setEditable(false);

        jLabel19.setText("AMOUNT IN WORDS");

        tfwords.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btntotalamt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(tftotalamt, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tftax, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(tffinalamt, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(tfwords, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(101, 101, 101))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(463, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(280, 280, 280))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntotalamt)
                    .addComponent(tftotalamt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tffinalamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfwords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbladdress, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfname, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblname1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblname2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfname2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addComponent(tfname1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
            .addGroup(homeLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                        .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(101, 101, 101)))
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfamt, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfprice, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tfqty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                .addContainerGap(571, Short.MAX_VALUE))
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnrm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(237, 237, 237))
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblname)
                    .addComponent(tfname)
                    .addComponent(tfname1)
                    .addComponent(lblname1)
                    .addComponent(lblcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfcontact))
                .addGap(8, 8, 8)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbladdress)
                    .addComponent(lblname2)
                    .addComponent(tfname2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homeLayout.createSequentialGroup()
                                .addComponent(tfqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(homeLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnadd)
                        .addGap(18, 18, 18)
                        .addComponent(btnrm)
                        .addGap(73, 73, 73)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Monospaced", 0, 12));
        jLabel4.setText("KILA PARIKSHIT GARH");

        jLabel5.setText("KILA ROAD,OPPOSITE PNB BANK");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel6.setText("INVOICE");

        jLabel8.setText("INVOIVE DATE");

        tfdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfdateActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabel9.setText("BILL TO :");

        jLabel10.setText("INVOICE NUMBER");

        btnprint.setText("PRINT");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });

        btnlogout.setText("LOGOUT");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        jLabel18.setText("GSTIN-09AIPPG8276M1ZQ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(777, 777, 777)
                                .addComponent(btnprint))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfdate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(96, 96, 96)
                                        .addComponent(btnlogout)))
                                .addGap(151, 151, 151)))
                        .addGap(230, 230, 230))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(heading, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(111, 111, 111))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1132, 1132, 1132))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(heading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfinvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfdate)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnlogout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(btnprint)
                .addGap(669, 669, 669))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfdateActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_tfdateActionPerformed

    private void tfnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnameActionPerformed
        // TODO add your handling code here:
        
        //**************************************************************************************
        























    
    
    
    
    
    


	























        
        

        
        //*****************************************************************************************
    }//GEN-LAST:event_tfnameActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        float amt = 0;
        String hsn="";
        int qty = Integer.parseInt(tfqty.getText());
        float price = Float.parseFloat(tfprice.getText());
        int index=tfdesc.getSelectedIndex();
        if(index==0)       //khajoor
        {
            hsn="23023000";
            amt=qty*price;
        }
        else
            if(index==1) //saloni 60k
            {
                amt=qty*(price*60/100);
            hsn="2306";
                    tax=tax+(float) (amt*0.05);
            }
                    else
            if(index==2)//swastik 60k
            {
                        hsn="2306";
                         amt=qty*(price*60/100);
                         tax=tax+(float) (amt*0.05);
                         
            }             
        else
            if(index==3)  //swastik 70k
            {
                hsn="2306";
                amt=qty*(price*70/100);
            tax=tax+(float) (amt*0.05);
            }
        else
            if(index==4)    //balaji bilona khal
            {
                hsn="2306";
                amt=qty*(price*50/100);
                   tax=tax+(float) (amt*0.05);
            }
        else
            if(index==5){//chilkha
                hsn="2309";
                amt=qty*(price*20/100);    //chilkha yet to confirm kg
                tax=0;
            }
            else    
                if(index==6){
                    //choori 50k
                amt=qty*(price*50/100);
                tax=0;
                }
                else
            if(index==7){
                //chana choori 40k
                amt=qty*(price*40/100);     //chana price yet to be confirm
            tax=0;
            }
            else
            if(index==8){
                //khanda
                amt=qty*(price*45/100);
                   tax=0;
            }
        tfamt.setText(String.valueOf(amt));
    
        model.addRow(new Object[]{tfdesc.getSelectedItem(),hsn,tfqty.getText(),tfprice.getText(),tfamt.getText()});
         
        tfqty.setText("");
        tfprice.setText("");
       tfamt.setText("");
       
    }//GEN-LAST:event_btnaddActionPerformed

    private void tfqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfqtyActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfqtyActionPerformed

    private void tfpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfpriceActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfpriceActionPerformed

    private void tfamtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfamtActionPerformed
        // TODO add your handling code here:
        float a=Float.parseFloat(tfamt.getText());
       
    }//GEN-LAST:event_tfamtActionPerformed

    private void btnaddPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btnaddPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_btnaddPropertyChange

    private void btntotalamtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntotalamtActionPerformed
        // TODO add your handling code here:
      getSum();
      double f=0;
      float a=Float.parseFloat(tftax.getText());
      float b=Float.parseFloat(tftotalamt.getText());
      
      tftax.setText(Float.toString(tax));
      
       f=tax+b;
       tffinalamt.setText(Double.toString(f));
      int m=(int) f; 
       
       // CODE FOR CONVERTING NUMBER TO WORD
       
            home_page obj=new home_page();
            String word=obj.converthome_pages(m);
            tfwords.setText(word);
            //System.out.println("input in Words : "+obj.converthome_pages(m));


    }//GEN-LAST:event_btntotalamtActionPerformed

    private void tfcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfcontactActionPerformed

    private void tfaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfaddressActionPerformed

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed

        /*
        JFrame frame1 = new JFrame();
PrinterJob pjob = PrinterJob.getPrinterJob();
PageFormat preformat = pjob.defaultPage();
preformat.setOrientation(PageFormat.LANDSCAPE);
PageFormat postformat = pjob.pageDialog(preformat);
//If user does not hit cancel then print.
if (preformat != postformat) {
    //Set print component
    pjob.setPrintable(new Printer(frame1), postformat);
    
        if (pjob.printDialog()) {
                    try {
                        pjob.print();
                    } catch (PrinterException ex) {
                        Logger.getLogger(home_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
        
}
        
    */    
             
        MessageFormat header=new MessageFormat("INVOICE");
           MessageFormat footer=new MessageFormat("INVOICE");
           MessageFormat body=new MessageFormat(tfname.getText());
           try
           {
               tblitem.print(JTable.PrintMode.NORMAL, header, footer);
               
           }
           catch(java.awt.print.PrinterException e)
           {
               System.err.format("Cannot print %s%n", e.getMessage());
           }
         
       
    }//GEN-LAST:event_btnprintActionPerformed

    private void btnrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrmActionPerformed
        // TODO add your handling code here:
        int selectedRowIndex=tblitem.getSelectedRow();
        model.removeRow(selectedRowIndex);
    }//GEN-LAST:event_btnrmActionPerformed

    private void btnrmPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btnrmPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_btnrmPropertyChange

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        // TODO add your handling code here:
        this.dispose();
        start s=new start();
        s.setVisible(true);
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void tftaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftaxActionPerformed
        
    }//GEN-LAST:event_tftaxActionPerformed

    private void tfname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfname1ActionPerformed

    private void tfname2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfname2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfname2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
               
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new home_page().setVisible(true);
            }
        });
    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnprint;
    private javax.swing.JButton btnrm;
    private javax.swing.JButton btntotalamt;
    private javax.swing.JLabel heading;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbladdress;
    private javax.swing.JLabel lblcontact;
    private javax.swing.JLabel lblname;
    private javax.swing.JLabel lblname1;
    private javax.swing.JLabel lblname2;
    private javax.swing.JTable tblitem;
    private javax.swing.JTextField tfaddress;
    private javax.swing.JTextField tfamt;
    private javax.swing.JTextField tfcontact;
    private javax.swing.JTextField tfdate;
    private javax.swing.JComboBox tfdesc;
    private javax.swing.JTextField tffinalamt;
    private javax.swing.JTextField tfinvoice;
    private javax.swing.JTextField tfname;
    private javax.swing.JTextField tfname1;
    private javax.swing.JTextField tfname2;
    private javax.swing.JTextField tfprice;
    private javax.swing.JTextField tfqty;
    private javax.swing.JTextField tftax;
    private javax.swing.JTextField tftotalamt;
    private javax.swing.JTextField tfwords;
    // End of variables declaration//GEN-END:variables
}
