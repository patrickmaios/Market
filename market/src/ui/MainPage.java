package ui;

import domain.Order;
import domain.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JDialog{
    private JPanel MainPage;
    private JButton btnGun;
    private JButton btnGem;
    private JButton btnAnimal;
    private JButton btnVirus;
    private JButton btnId;
    private JPanel orderPanel;
    private JButton btnOrder;

    public MainPage(JFrame parent) {
        super(parent);
        setTitle("Shop");
        setContentPane(MainPage);
        setMinimumSize(new Dimension(450, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        Order order=new Order();
        Product gun=new Product("GRRRRATATAAA", "AR-15", 500, 1, 12);
        Product gem=new Product("Shiny tainy", "Swarovsky", 500, 2, 500);
        Product animal=new Product("Rawr XD", "Brazilian Tiger", 3000, 3, 5);
        Product virus=new Product("All your base are belong to us", "Zip-Bomb", 15, 4, 50000);
        Product id=new Product("Be everyone but yourself!!!", "Fake-Id", 3000, 5, 50);


        btnGun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.add(gun, 1);
                JOptionPane.showMessageDialog(MainPage.this, gun.getDesc(), gun.getName()+" in cart!!", JOptionPane.OK_OPTION);
            }
        });
        btnGem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.add(gem, 1);
                JOptionPane.showMessageDialog(MainPage.this, gem.getDesc(), gem.getName()+" in cart!!", JOptionPane.OK_OPTION);
            }
        });
        btnAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.add(animal, 1);
                JOptionPane.showMessageDialog(MainPage.this, animal.getDesc(), animal.getName()+" in cart!!", JOptionPane.OK_OPTION);
            }
        });
        btnVirus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.add(virus, 1);
                JOptionPane.showMessageDialog(MainPage.this, virus.getDesc(),virus.getName()+" in cart!!", JOptionPane.OK_OPTION);
            }
        });
        btnId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.add(id, 1);
                JOptionPane.showMessageDialog(MainPage.this, id.getDesc(), id.getName()+" in cart!!", JOptionPane.OK_OPTION);
            }
        });

        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.showlist();
            }
        });
        setVisible(true);
    }


    public static void main(String[] args) {
        MainPage mainPage = new MainPage(null);
    }
}

