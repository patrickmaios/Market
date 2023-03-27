package ui;

import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JPanel registerLogin;
    private JPasswordField tfPass;
    private JTextField tfUser;
    private JButton btCancel;
    private JButton btLogin;
    private JCheckBox jcPass;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(registerLogin);
        setMinimumSize(new Dimension(450, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);




        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                jcPass.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange()==ItemEvent.SELECTED){

                        }
                    }
                });
                String mail=tfUser.getText();
                String pass=String.valueOf(tfPass.getPassword());

                user=getAuthentification(mail, pass);

                if(user != null){
                    dispose();
                    MainPage mainPage=new MainPage(null);
                }else{
                    JOptionPane.showMessageDialog(LoginForm.this, "Email or Password incorrect", "Better luck next time", JOptionPane.ERROR_MESSAGE);
                }

            }
        });


        setVisible(true);
    }
    public User user;

    private User getAuthentification(String mail, String pass){
        User user1=null;

        final String DB_URL="jdbc:postgresql://localhost:5432/blackmarket";
        final String USERNAME="postgres";
        final String PASSWORD="Pendragonul#1";

        try{
            Connection conn= DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt=conn.createStatement();
            String sql="SELECT * FROM client WHERE username=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, pass);

            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                user=new User();
                user.username=resultSet.getString("username");
                user.password=resultSet.getString("password");
                user.country=resultSet.getString("country");
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }


    public static void main(String[] args) {
        LoginForm loginForm= new LoginForm(null);
        User user= loginForm.user;
        if(user!=null){
            System.out.println("User has been logged in:"+  user.username);
        }else{
            System.out.println("Failed :( ");
        }
    }
}


