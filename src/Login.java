import dataconnection.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Login {

	private JFrame logn;
	private DBConnect data;
	JTextField name;
	private JPasswordField pass;
	private JButton loginbtn, clcbtn;
	private Font font;
	public static Login l1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try {
					l1 = new Login();
					//l1.logn.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	public Login(){
			//System.out.println("in login");
			initialize();
			data = new DBConnect();
		}
	
	public void initialize() {
		logn = new JFrame("Welcome To Our Super Shop");
		logn.setResizable(false);
		logn.setVisible(true);
		logn.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
		//logn.setBounds(400, 200, 650, 500);
		 logn.setSize(590, 500);
		 logn.setLocationRelativeTo(null);

		
		font = new Font("arial", Font.BOLD,20);
		
		
		name = new JTextField();
		name.setBounds(190, 170, 240, 35);
		name.setFont(font);
		name.setBackground(new Color(204,247,255));
		name.setForeground(Color.black);
		//name.setBorder(new LineBorder(Color.white,3,true));
		name.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
		logn.add(name);
		
		pass = new JPasswordField();
		pass.setBounds(190, 240, 240, 35);
		pass.setBackground(new Color(204,221,255));
		pass.setFont(font);
		pass.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.white));
		logn.add(pass);
		
		JLabel usericn = new JLabel();
		usericn.setIcon(new ImageIcon("image/icon\\username.png"));
		usericn.setBounds(140, 170, 35, 35);
		logn.add(usericn);
		
		JLabel passicn = new JLabel();
		passicn.setIcon(new ImageIcon("image/icon\\password.png"));
		passicn.setBounds(140, 240, 35, 35);
		logn.add(passicn);
		
		loginbtn = new JButton(new ImageIcon("image/icon\\loginbtn.png"));
		loginbtn.setBounds(310, 350, 110, 40);
		loginbtn.setContentAreaFilled(false);
		loginbtn.setBorderPainted(true);
		logn.add(loginbtn);
		loginbtn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent f) {
				int b = data.login(name.getText(),pass.getText());
				if(b==1){
					logn.setVisible(false);
					new Admin(l1);
					//new Product();
					//new Purchase();
				}
				else if(b==2) {
					logn.setVisible(false);
					new Home(l1);
				}
				else {
					JOptionPane.showMessageDialog(null,"UserName or PassWord is incorrect");
				}
			}
			
		});
		
		
		clcbtn = new JButton(new ImageIcon("image/icon\\clear.png"));
		clcbtn.setBounds(180, 350, 110, 40);
		//clcbtn.setOpaque(true);
		clcbtn.setContentAreaFilled(false);
		clcbtn.setBorderPainted(true);
		logn.add(clcbtn);
		clcbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent f) {
				name.setText("");
				pass.setText("");
			}
			
		});
		
		
		JLabel labelLogin = new JLabel();
		labelLogin.setIcon(new ImageIcon("image\\loginBackg.jpg"));
		labelLogin.setBounds(0, 0, 590, 500);
		logn.add(labelLogin);
		
		
		 name.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
	              loginbtn.doClick();
	            }
	         }
	      });
		 pass.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
		            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		              loginbtn.doClick();
		            }
		         }
		      });
		
		
		logn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logn.getContentPane().setLayout(null);
		
		
	}


	}


