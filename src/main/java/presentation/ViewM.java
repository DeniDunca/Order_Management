package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewM extends JFrame {

    private JTextField t_idClient = new JTextField(20);
    private JTextField t_firstName = new JTextField(20);
    private JTextField t_lastName = new JTextField(20);
    private JTextField t_email = new JTextField(20);

    private JTextField t_idProduct = new JTextField(20);
    private JTextField t_name = new JTextField(20);
    private JTextField t_price = new JTextField(20);
    private JTextField t_quantityp = new JTextField(20);

    private JTextField t_idOrder = new JTextField(20);
    private JTextField t_idp = new JTextField(20);
    private JTextField t_idc = new JTextField(20);
    private JTextField t_quantity = new JTextField(20);

    private JButton b_viewc = new JButton("VIEW");
    private JButton b_insertc = new JButton("INSERT");
    private JButton b_updatec = new JButton("UPDATE");
    private JButton b_deletec = new JButton("DELETE");
    private JButton b_viewp = new JButton("VIEW");
    private JButton b_insertp = new JButton("INSERT");
    private JButton b_updatep = new JButton("UPDATE");
    private JButton b_deletep = new JButton("DELETE");
    private JButton b_viewo = new JButton("VIEW");
    private JButton b_inserto = new JButton("INSERT");
    private JButton b_updateo = new JButton("UPDATE");
    private JButton b_deleteo = new JButton("DELETE");

    private JButton b_products = new JButton("PRODUCTS");
    private JButton b_clients = new JButton("CLIENTS");
    private JButton b_orders = new JButton("ORDERS");
    private JButton b_exit = new JButton("EXIT");
    private JButton b_back = new JButton("BACK");

    private JTable tb_client = new JTable();
    private JTable tb_product = new JTable();
    private JTable tb_order = new JTable();

    public void addClientListener(ActionListener client)
    {
        b_clients.addActionListener(client);
    }
    public void addProductListener(ActionListener product)
    {
        b_products.addActionListener(product);
    }
    public void addOrderListener(ActionListener order)
    {
        b_orders.addActionListener(order);
    }
    public void addExitListener(ActionListener exit)
    {
        b_exit.addActionListener(exit);
    }
    public void addBackListener(ActionListener back)
    {
        b_back.addActionListener(back);
    }
    public void addInsertCListener(ActionListener insertc)
    {
        b_insertc.addActionListener(insertc);
    }
    public void addDeleteCListener(ActionListener deletec) {b_deletec.addActionListener(deletec);}
    public void addUpdateCListener(ActionListener updatec)
    {
        b_updatec.addActionListener(updatec);
    }
    public void addInsertPListener(ActionListener insertp)
    {
        b_insertp.addActionListener(insertp);
    }
    public void addDeletePListener(ActionListener deletep) {b_deletep.addActionListener(deletep);}
    public void addUpdatePListener(ActionListener updatep)
    {
        b_updatep.addActionListener(updatep);
    }
    public void addInsertOListener(ActionListener inserto)
    {
        b_inserto.addActionListener(inserto);
    }
    public void addDeleteOListener(ActionListener deleteo) {b_deleteo.addActionListener(deleteo);}
    public void addUpdateOListener(ActionListener updateo)
    {
        b_updateo.addActionListener(updateo);
    }
    public void addViewCListener(ActionListener viewc){b_viewc.addActionListener(viewc);}
    public void addViewPListener(ActionListener viewp){b_viewp.addActionListener(viewp);}
    public void addViewOListener(ActionListener viewo){b_viewo.addActionListener(viewo);}

    public ViewM()
    {
        framePrincipal();
    }

    public void framePrincipal()
    {
        JPanel content0 = new JPanel();
        content0.setLayout(new FlowLayout());
        content0.setBackground(Color.decode("#5d86ec"));
        content0.add(new JLabel("Choose the table you want to modify"));

        JPanel content1 = new JPanel();
        content1.setLayout(new FlowLayout());
        content1.setBackground(Color.decode("#5378d4"));
        content1.add(b_clients);
        content1.add(b_products);
        content1.add(b_orders);

        JPanel content2 = new JPanel();
        content2.setLayout(new FlowLayout());
        content2.setBackground(Color.decode("#4a6bbc"));
        content2.add(b_exit);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(content0);
        content.add(content1);
        content.add(content2);

        this.setContentPane(content);
        this.pack();
        this.setTitle("Choose table");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
    }

    public void frameClients()
    {
        JPanel content0 = new JPanel();
        content0.setLayout(new FlowLayout());
        content0.setBackground(Color.decode("#becef7"));
        content0.add(new JLabel("CLIENTS"));

        JPanel content1 = new JPanel();
        content1.setLayout(new FlowLayout());
        content1.setBackground(Color.decode("#aec2f5"));
        tb_client = ClientBLL.getAll();
        content1.add(tb_client);

        JPanel content2 = new JPanel();
        content2.setLayout(new FlowLayout());
        content2.setBackground(Color.decode("#9db6f3"));
        content2.add(b_insertc);
        content2.add(b_deletec);
        content2.add(b_updatec);

        JPanel content3 = new JPanel();
        content3.setLayout(new FlowLayout());
        content3.setBackground(Color.decode("#8daaf1"));
        content3.add(new JLabel("ID Client"));
        content3.add(t_idClient);

        JPanel content4 = new JPanel();
        content4.setLayout(new FlowLayout());
        content4.setBackground(Color.decode("#7d9eef"));
        content4.add(new JLabel("Firstname"));
        content4.add(t_firstName);

        JPanel content5 = new JPanel();
        content5.setLayout(new FlowLayout());
        content5.setBackground(Color.decode("#6d92ed"));
        content5.add(new JLabel("Lastname"));
        content5.add(t_lastName);

        JPanel content6 = new JPanel();
        content6.setLayout(new FlowLayout());
        content6.setBackground(Color.decode("#5d86ec"));
        content6.add(new JLabel("Email        "));
        content6.add(t_email);

        JPanel content7 = new JPanel();
        content7.setLayout(new FlowLayout());
        content7.setBackground(Color.decode("#5378d4"));
        content7.add(b_viewc);
        content7.add(b_back);
        content7.add(b_exit);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(content0);
        content.add(content1);
        content.add(content2);
        content.add(content3);
        content.add(content4);
        content.add(content5);
        content.add(content6);
        content.add(content7);

        this.setContentPane(content);
        this.pack();
        this.setTitle("clients");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);

    }

    public void frameProducts()
    {
        JPanel content0 = new JPanel();
        content0.setLayout(new FlowLayout());
        content0.setBackground(Color.decode("#becef7"));
        content0.add(new JLabel("PRODUCTS"));

        JPanel content1 = new JPanel();
        content1.setLayout(new FlowLayout());
        content1.setBackground(Color.decode("#aec2f5"));
        tb_product = ProductBLL.getAll();
        content1.add(tb_product);

        JPanel content2 = new JPanel();
        content2.setLayout(new FlowLayout());
        content2.setBackground(Color.decode("#9db6f3"));
        content2.add(b_insertp);
        content2.add(b_deletep);
        content2.add(b_updatep);

        JPanel content3 = new JPanel();
        content3.setLayout(new FlowLayout());
        content3.setBackground(Color.decode("#8daaf1"));
        content3.add(new JLabel("ID Product"));
        content3.add(t_idProduct);

        JPanel content4 = new JPanel();
        content4.setLayout(new FlowLayout());
        content4.setBackground(Color.decode("#7d9eef"));
        content4.add(new JLabel("Name        "));
        content4.add(t_name);

        JPanel content5 = new JPanel();
        content5.setLayout(new FlowLayout());
        content5.setBackground(Color.decode("#6d92ed"));
        content5.add(new JLabel("Price         "));
        content5.add(t_price);

        JPanel content6 = new JPanel();
        content6.setLayout(new FlowLayout());
        content6.setBackground(Color.decode("#6d92ed"));
        content6.add(new JLabel("Quantity    "));
        content6.add(t_quantityp);


        JPanel content7 = new JPanel();
        content7.setLayout(new FlowLayout());
        content7.setBackground(Color.decode("#5d86ec"));
        content7.add(b_viewp);
        content7.add(b_back);
        content7.add(b_exit);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(content0);
        content.add(content1);
        content.add(content2);
        content.add(content3);
        content.add(content4);
        content.add(content5);
        content.add(content6);
        content.add(content7);

        this.setContentPane(content);
        this.pack();
        this.setTitle("products");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
    }

    public void frameOrder()
    {
        JPanel content0 = new JPanel();
        content0.setLayout(new FlowLayout());
        content0.setBackground(Color.decode("#becef7"));
        content0.add(new JLabel("ORDER"));

        JPanel content1 = new JPanel();
        content1.setLayout(new FlowLayout());
        content1.setBackground(Color.decode("#aec2f5"));
        tb_order = OrderBLL.getAll();
        content1.add(tb_order);

        JPanel content2 = new JPanel();
        content2.setLayout(new FlowLayout());
        content2.setBackground(Color.decode("#9db6f3"));
        content2.add(b_inserto);
        content2.add(b_deleteo);
        content2.add(b_updateo);

        JPanel content3 = new JPanel();
        content3.setLayout(new FlowLayout());
        content3.setBackground(Color.decode("#8daaf1"));
        content3.add(new JLabel("ID Order   "));
        content3.add(t_idOrder);

        JPanel content4 = new JPanel();
        content4.setLayout(new FlowLayout());
        content4.setBackground(Color.decode("#7d9eef"));
        content4.add(new JLabel("ID Client   "));
        content4.add(t_idc);

        JPanel content5 = new JPanel();
        content5.setLayout(new FlowLayout());
        content5.setBackground(Color.decode("#6d92ed"));
        content5.add(new JLabel("ID Product"));
        content5.add(t_idp);

        JPanel content6 = new JPanel();
        content6.setLayout(new FlowLayout());
        content6.setBackground(Color.decode("#5d86ec"));
        content6.add(new JLabel("Quantity    "));
        content6.add(t_quantity);

        JPanel content7 = new JPanel();
        content7.setLayout(new FlowLayout());
        content7.setBackground(Color.decode("#5378d4"));
        content7.add(b_viewo);
        content7.add(b_back);
        content7.add(b_exit);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(content0);
        content.add(content1);
        content.add(content2);
        content.add(content3);
        content.add(content4);
        content.add(content5);
        content.add(content6);
        content.add(content7);

        this.setContentPane(content);
        this.pack();
        this.setTitle("order");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
    }

    public void refreshClient()
    {
        this.setVisible(false);
        this.dispose();
        this.frameClients();
        this.setVisible(true);
    }

    public void refreshProduct()
    {
        this.setVisible(false);
        this.dispose();
        this.frameProducts();
        this.setVisible(true);
    }

    public void refreshOrder()
    {
        this.setVisible(false);
        this.dispose();
        this.frameOrder();
        this.setVisible(true);
    }

    public int getIdClient(){

        return Integer.parseInt(t_idClient.getText());
    }

    public String getFirstnameClient(){
        return t_firstName.getText();
    }

    public String getLastnameClient(){
        return t_lastName.getText();
    }

    public String getEmailClient(){
        return t_email.getText();
    }

    public JTable getTb_client() {
        return tb_client;
    }

    public void setTb_client(JTable tb_client) {
        this.tb_client = tb_client;
    }

    public JTable getTb_product() {
        return tb_product;
    }

    public void setTb_product(JTable tb_product) {
        this.tb_product = tb_product;
    }

    public JTable getTb_order() {
        return tb_order;
    }

    public void setTb_order(JTable tb_order) {
        this.tb_order = tb_order;
    }

    public int getIdOrder(){
        return Integer.parseInt(t_idOrder.getText());
    }

    public int getIdClientOrder(){

        if(t_idc.getText().equals(""))
        {
            return 0;
        }
        return Integer.parseInt(t_idc.getText());
    }

    public int getIdProductOrder(){

        if(t_idp.getText().equals(""))
        {
            return 0;
        }
        return Integer.parseInt(t_idp.getText());
    }

    public int getQuantity(){

        if(t_quantity.getText().equals(""))
        {
            return 0;
        }
        return Integer.parseInt(t_quantity.getText());
    }

    public int getIdProduct(){
        return Integer.parseInt(t_idProduct.getText());
    }

    public String getName(){
        return t_name.getText();
    }

    public double getPrice(){

        if(t_price.getText().equals(""))
        {
            return 0.0;
        }
        return Double.parseDouble(t_price.getText());
    }

    public int getQuantityp() {
        if(t_quantityp.getText().equals(""))
        {
            return 0;
        }
        return Integer.parseInt(t_quantityp.getText()) ;
    }

    public void showError(String value)
    {
        JOptionPane.showMessageDialog(this, value);
    }
}