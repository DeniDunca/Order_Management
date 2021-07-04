package presentation;

import bill.Bill;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa care contine totalitatea Listenerelor de pe butoane si care face legatura
 * intre Clasele model din proiect si Clasa View
 */
public class ControllerM {

    private ViewM m_view;

    public ControllerM(ViewM view) {
        m_view = view;
        view.addExitListener(new ExitListener());
        view.addBackListener(new BackListener());
        view.addClientListener(new ClientListener());
        view.addProductListener(new ProductListener());
        view.addOrderListener(new OrderListener());
        view.addInsertCListener(new InsertCListener());
        view.addDeleteCListener(new DeleteCListener());
        view.addUpdateCListener(new UpdateCListener());
        view.addInsertPListener(new InsertPListener());
        view.addDeletePListener(new DeletePListener());
        view.addUpdatePListener(new UpdatePListener());
        view.addInsertOListener(new InsertOListener());
        view.addDeleteOListener(new DeleteOListener());
        view.addUpdateOListener(new UpdateOListener());
        view.addViewCListener(new ViewCListener());
        view.addViewPListener(new ViewPListener());
        view.addViewOListener(new ViewOListener());

    }

    /**
     * Listener pentru butonul de EXIT care inchide aplicatia
     */
    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Listener pentru butonul de BACK care ne duce inapoi la primul frame unde alegem tabelul
     * in care dorim sa lucram
     */
    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.framePrincipal();
        }
    }

    /**
     * Listener pentru butonul de CLIENT care deschide o fereastra unde putem lucra pe tabelul client din baza de date
     */
    private class ClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.frameClients();
        }
    }

    /**
     * Listener pentru butonul de PRODUCT care deschide o fereastra unde putem lucra pe tabelul product din baza de date
     */
    private class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.frameProducts();
        }
    }

    /**
     * Listener pentru butonul de ORDER care deschide o fereastra unde putem lucra pe tabelul order din baza de date
     */
    private class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m_view.frameOrder();
        }
    }

    /**
     * Listener pentru butonul de INSERT din Frame-ul pentru CLIENT care ia datele din textfielduri si pe adauga in tabelul din baza de date
     */
    private class InsertCListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientBLL cl = new ClientBLL();
            Client c = new Client(m_view.getIdClient(), m_view.getFirstnameClient(),m_view.getLastnameClient(), m_view.getEmailClient());
            try {
                cl.insert(c);
                m_view.setTb_client(ClientBLL.getAll());

            } catch (Exception exception) {
                m_view.showError(exception.getMessage());
            }
        }
    }

    /**
     * Listener pentru butonul de DELETE din Frame-ul pentru CLIENT care ia id-ul introdus in textfieldul cu id si
     * sterge randul cu toate informatiile referitoare la clientul cu id-ul dat.
     */
    private class DeleteCListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ClientBLL cl = new ClientBLL();
                int id = m_view.getIdClient();
                cl.delete(id);

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de UPDATE din Frame-ul pentru CLIENT care ia datele din textfielduri in functie de id si verifica fiecare textfield
     * si vede care a fost modificat astfel facand update la tabel
     */
    private class UpdateCListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String firstname = m_view.getFirstnameClient();
                String lastname = m_view.getLastnameClient();
                String email = m_view.getEmailClient();
                ClientBLL client = new ClientBLL();

                if(m_view.getFirstnameClient().equals("")) {
                    firstname = client.getClient(m_view.getIdClient()).getFirstName();
                }
                if(m_view.getLastnameClient().equals("")) {
                    lastname = client.getClient(m_view.getIdClient()).getLastName();
                }
                if(m_view.getEmailClient().equals("")) {
                    email = client.getClient(m_view.getIdClient()).getEmail();
                }
                Client c1 = new Client(m_view.getIdClient(),firstname,lastname, email);
                client.update(c1);
                m_view.setTb_client(client.getAll());

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de INSERT din Frame-ul pentru PRODUCT care ia datele din textfielduri si pe adauga in tabelul din baza de date
     */
    private class InsertPListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductBLL pr = new ProductBLL();
            Product c = new Product(m_view.getIdProduct(), m_view.getName(),m_view.getPrice(), m_view.getQuantityp());
            try {
                pr.insert(c);
                m_view.setTb_product(ProductBLL.getAll());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de DELETE din Frame-ul pentru PRODUCT care ia id-ul introdus in textfieldul cu id si
     * sterge randul cu toate informatiile referitoare la produsul cu id-ul dat.
     */
    private class DeletePListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ProductBLL pr = new ProductBLL();
                int id = m_view.getIdProduct();
                pr.delete(id);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de UPDATE din Frame-ul pentru PRODUCT care ia datele din textfielduri in functie de id si verifica fiecare textfield
     * si vede care a fost modificat astfel facand update la tabel
     */
    private class UpdatePListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = m_view.getName();
                double price = m_view.getPrice();
                int quantity = m_view.getQuantityp();
                ProductBLL product = new ProductBLL();

                if(m_view.getName().equals("")) {
                    name = product.getProduct(m_view.getIdProduct()).getName();
                }
                if(m_view.getPrice() == 0.0) {
                    price = product.getProduct(m_view.getIdProduct()).getPrice();
                }
                if(m_view.getQuantityp() == 0) {
                    quantity = product.getProduct(m_view.getIdProduct()).getQuantity();
                }
                System.out.println(name + " " + price + " " + quantity);
                Product p1 = new Product(m_view.getIdProduct(), name, price, quantity);
                product.update(p1);
                m_view.setTb_product(product.getAll());
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de INSERT din Frame-ul pentru ORDER care ia datele din textfielduri si pe adauga in tabelul din baza de date
     * Verifica si daca exista stock in produse pentru orderul facut
     */
    private class InsertOListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderBLL or = new OrderBLL();
            ClientBLL cl = new ClientBLL();
            ProductBLL pr = new ProductBLL();
            Orders order = new Orders(m_view.getIdOrder(), m_view.getIdClientOrder(),m_view.getIdProductOrder(), m_view.getQuantity());
            Client client = cl.getClient(m_view.getIdClientOrder());
            Product product = pr.getProduct(m_view.getIdProductOrder());
            try {

                if(order.getQuantity() > product.getQuantity())
                {
                    JOptionPane.showMessageDialog(m_view, "Not enough items in stock! :( ", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    or.insert(order);
                    m_view.setTb_order(OrderBLL.getAll());
                    Bill bill = new Bill();
                    bill.generateBill(order,client,product);
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de DELETE din Frame-ul pentru ORDER care ia id-ul introdus in textfieldul cu id si
     * sterge randul cu toate informatiile referitoare la orderul cu id-ul dat.
     */
    private class DeleteOListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                OrderBLL or = new OrderBLL();
                int id = m_view.getIdOrder();
                or.delete(id);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener pentru butonul de UPDATE din Frame-ul pentru ORDER care ia datele din textfielduri in functie de id si verifica fiecare textfield
     * si vede care a fost modificat astfel facand update la tabel
     * Verifica si daca modificarea facuta are suficient stock
     */
    private class UpdateOListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int idClient = m_view.getIdClientOrder();
                int idProduct = m_view.getIdProductOrder();
                int quantity = m_view.getQuantity();
                ProductBLL pr = new ProductBLL();
                Product product = pr.getProduct(m_view.getIdOrder());
                OrderBLL order = new OrderBLL();

                if(m_view.getIdClientOrder() == 0) {
                    idClient = order.getOrder(m_view.getIdOrder()).getIdClient();
                }
                if(m_view.getIdProductOrder() == 0) {
                    idProduct = order.getOrder(m_view.getIdOrder()).getIdProduct();
                }
                if(m_view.getQuantity() == 0) {
                    quantity = order.getOrder(m_view.getIdOrder()).getQuantity();
                }

                Orders o1 = new Orders(m_view.getIdOrder(),idClient, idProduct, quantity);

                if(o1.getQuantity() > product.getQuantity())
                {
                    JOptionPane.showMessageDialog(m_view, "Not enough items in stock! :( ", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    order.update(o1);
                    m_view.setTb_order(order.getAll());
                }

            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener care da un "refresh" la frame-ul Client
     */
    private class ViewCListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                m_view.setTb_client(ClientBLL.getAll());
                m_view.refreshClient();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener care da un "refresh" la frame-ul Product
     */
    private class ViewPListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                m_view.setTb_product(ProductBLL.getAll());
                m_view.refreshProduct();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Listener care da un "refresh" la frame-ul Order
     */
    private class ViewOListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                m_view.setTb_order(OrderBLL.getAll());
                m_view.refreshOrder();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}