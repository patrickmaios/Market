package ui;

import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class RegisterForm extends JDialog {
    private JTextField tfusername;
    private JTextField tfpassword;
    private JTextField tfconfirmpassword;
    private JTextField tfcountry;
    private JButton btregister;
    private JButton btcancel;
    private JPanel register;
    private JButton btLogin;

    public RegisterForm(JFrame parent){
        super(parent);
        setTitle("Create new account");
        setContentPane(register);
        setMinimumSize(new Dimension(450, 471));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btcancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm= new LoginForm(null);
            }
        });


        setVisible(true);

    }

    private void registerUser() {
        String username=tfusername.getText();
        String password=tfpassword.getText();
        String confirmpass=tfconfirmpassword.getText();
        String country=tfcountry.getText();

        if(username.isEmpty() || password.isEmpty() || confirmpass.isEmpty()|| country.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please input all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!password.equals(confirmpass)){
            JOptionPane.showMessageDialog(this, "Passwords do not match", "BAAAAAAAAA", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user= addUserDatabase(username, password, country);
        if(user != null){
            dispose();
            LoginForm loginForm=new LoginForm(null);
        }else{
            JOptionPane.showMessageDialog(RegisterForm.this, "Failed to register user", "Better luck next time", JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;

    private User addUserDatabase(String name, String pass, String country){
        User user=null;
        Connection conn=null;
        final String DB_URL="jdbc:postgresql://localhost:5432/blackmarket";
        final String USERNAME="postgres";
        final String PASSWORD="Pendragonul#1";

        try{
            conn= DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stat= conn.createStatement();
            String sql= "INSERT INTO client (username, password, country)" + "VALUES(?, ?, ?)";
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, country);


            int addedRows = preparedStatement.executeUpdate();
            if (addedRows>0){
                user= new User();
                user.username=name;
                user.password=pass;
                user.country=country;
            }
            stat.close();
            conn.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        RegisterForm register= new RegisterForm(null);
        User user= register.user;
        if(user!=null){
            System.out.println("Succes :)" + user.username);
        }else{
            System.out.println("Weeee :(");
        }
    }
}
