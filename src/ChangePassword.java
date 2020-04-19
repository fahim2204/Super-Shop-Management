
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dataconnection.DBConnect;

public class ChangePassword {
JFrame fpas;
private String uname;
private JLabel luser,loldpass,lnewpass,lconfirmpass;
private JTextField user,oldpass,newpass,confirmpass;
private Font font2 = new Font("Siyam Rupali", Font.BOLD, 18);	
private JButton change;	
static ChangePassword obj=null;

private ChangePassword(String n) {
	
		this.uname = n; 
		
	fpas = new JFrame("Change Password");
	fpas.setVisible(true);
	fpas.setResizable(false);
	fpas.setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon\\title.jpg"));
	fpas.setBounds(700, 400, 350, 410);	
	
	JLabel picn = new JLabel();
	picn.setIcon(new ImageIcon("image/icon\\user.png"));
	picn.setBounds(130, 15, 90, 90);
	fpas.add(picn);
	
	luser = new JLabel("User Name :");
	luser.setBounds(10, 120, 150, 35);
	luser.setFont(font2);
	fpas.add(luser);
	user = new JTextField(uname);
	user.setBounds(170, 120, 150, 35);
	user.setFont(font2);
	fpas.add(user);
	
	loldpass = new JLabel("Old Password :");
	loldpass.setBounds(10, 180, 150, 35);
	loldpass.setFont(font2);
	fpas.add(loldpass);
	oldpass = new JTextField();
	oldpass.setBounds(170, 180, 150, 35);
	oldpass.setFont(font2);
	fpas.add(oldpass);
	
	lnewpass = new JLabel("New Password :");
	lnewpass.setBounds(10, 220, 150, 35);
	lnewpass.setFont(font2);
	fpas.add(lnewpass);
	newpass = new JTextField();
	newpass.setBounds(170, 220, 150, 35);
	newpass.setFont(font2);
	fpas.add(newpass);
	
	lconfirmpass = new JLabel("Confirm Password :");
	lconfirmpass.setBounds(10, 260, 170, 35);
	lconfirmpass.setFont(new Font("Siyam Rupali", Font.BOLD, 17));
	fpas.add(lconfirmpass);
	confirmpass = new JTextField();
	confirmpass.setBounds(170, 260, 150, 35);
	confirmpass.setFont(font2);
	fpas.add(confirmpass);
	 confirmpass.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
              change.doClick();
            }
         }
      });
	
	change = new JButton(new ImageIcon("image/icon\\change.png"));
	change.setBounds(115, 320, 120, 40);
	change.setContentAreaFilled(false);
	change.setBorderPainted(true);
	fpas.add(change);
	change.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent f) {
			
			int b = new DBConnect().login(user.getText(),oldpass.getText());
			if(b==1 || b==2) {
				if(newpass.getText().equals(confirmpass.getText())) {
					String sql = "UPDATE login SET password='"+newpass.getText()+"' WHERE username='"+user.getText()+"'";
					DBConnect di = new DBConnect();
					di.insert(sql);
					JOptionPane.showMessageDialog(null, "Password Change is Succesfull!!!");
					fpas.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Type Same Password on Confirm Password");
				}
			}
			else if(b==-1){
				JOptionPane.showMessageDialog(null, "Password is Wrong !!!");
			}
		}
	});
	
	
	JLabel labelLogin = new JLabel();
	labelLogin.setIcon(new ImageIcon("image\\passback.jpg"));
	labelLogin.setBounds(0, 0, 350, 400);
	fpas.add(labelLogin);
	
	
	fpas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	fpas.getContentPane().setLayout(null);
	
	}
public static ChangePassword getObj(String n) {
	if(obj==null)
		obj = new ChangePassword(n);
	return obj;
	}
}
