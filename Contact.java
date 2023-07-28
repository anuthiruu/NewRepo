import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


class Contact {
   private String name;
   private String phone;


   public Contact(String name, String phone) {
       this.name = name;
       this.phone = phone;
   }


   public String getName() {
       return name;
   }


   public String getPhone() {
       return phone;
   }
}


class ContactManagementSystem {
   private ArrayList<Contact> contacts;


   public ContactManagementSystem() {
       contacts = new ArrayList<>();
   }


   public void addContact(Contact contact) {
       contacts.add(contact);
   }


   public ArrayList<Contact> searchContacts(String name) {
       ArrayList<Contact> searchResults = new ArrayList<>();
       for (Contact contact : contacts) {
           if (contact.getName().equalsIgnoreCase(name)) {
               searchResults.add(contact);
           }
       }
       return searchResults;
   }


   public ArrayList<Contact> getAllContacts() {
       return contacts;
   }
}


class MainFrame extends Frame implements ActionListener {
   private TextField nameField;
   private TextField phoneField;
   private Button addButton;
   private Button viewButton;
   private TextField searchField;
   private Button searchButton;
   private TextArea displayArea;


   private ContactManagementSystem cms;


   public MainFrame() {
       super("Contact Management System");
       cms = new ContactManagementSystem();


       setLayout(new GridLayout(6, 2));


       Label nameLabel = new Label("Name:");
       nameField = new TextField(20);
       add(nameLabel);
       add(nameField);


       Label phoneLabel = new Label("Phone:");
       phoneField = new TextField(20);
       add(phoneLabel);
       add(phoneField);


       addButton = new Button("Add");
       addButton.addActionListener(this);
       add(addButton);


       viewButton = new Button("View Contacts");
       viewButton.addActionListener(this);
       add(viewButton);


       Label searchLabel = new Label("Search:");
       searchField = new TextField(20);
       add(searchLabel);
       add(searchField);


       searchButton = new Button("Search");
       searchButton.addActionListener(this);
       add(searchButton);


       displayArea = new TextArea();
       add(displayArea);


       setSize(400, 400);
       setVisible(true);
   }


   @Override
   public void actionPerformed(ActionEvent e) {
       if (e.getSource() == addButton) {
           String name = nameField.getText();
           String phone = phoneField.getText();
           Contact contact = new Contact(name, phone);
           cms.addContact(contact);
           displayArea.setText("Contact added successfully!");
       } else if (e.getSource() == viewButton) {
           displayContacts();
       } else if (e.getSource() == searchButton) {
           String searchName = searchField.getText();
           ArrayList<Contact> searchResults = cms.searchContacts(searchName);
           if (searchResults.isEmpty()) {
               displayArea.setText("No contacts found.");
           } else {
               StringBuilder sb = new StringBuilder();
               sb.append("Search Results:\n");
               for (Contact contact : searchResults) {
                   sb.append("Name: ").append(contact.getName()).append("\tPhone: ").append(contact.getPhone()).append("\n");
               }
               displayArea.setText(sb.toString());
           }
       }
   }


   private void displayContacts() {
       ArrayList<Contact> contacts = cms.getAllContacts();
       if (contacts.isEmpty()) {
           displayArea.setText("No contacts available.");
       } else {
           StringBuilder sb = new StringBuilder();
           sb.append("Contacts:\n");
           for (Contact contact : contacts) {
               sb.append("Name: ").append(contact.getName()).append("\tPhone: ").append(contact.getPhone()).append("\n");
           }
           displayArea.setText(sb.toString());
       }
   }
}


public class Main {
   public static void main(String[] args) {
       MainFrame mainFrame = new MainFrame();
   }
}
