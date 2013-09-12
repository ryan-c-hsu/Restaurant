package restaurant.gui;

import restaurant.CustomerAgent;
import restaurant.HostAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Subpanel of restaurantPanel.
 * This holds the scroll panes for the customers and, later, for waiters
 */
public class ListPanel extends JPanel implements ActionListener {

    public JScrollPane pane =
            new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel view = new JPanel();
    private List<JCheckBox> list = new ArrayList<JCheckBox>();
    private JButton addPersonB = new JButton("Add");
    private JPanel addNewInfo = new JPanel();
    private JPanel addNewName = new JPanel();
    private JTextField name = new JTextField(100);
    JCheckBox optionH = new JCheckBox("Hungry? ");
    private JCheckBox currentBox = new JCheckBox();
    
    private CustomerAgent currentPerson;
    
    private RestaurantPanel restPanel;
    private String type;

    /**
     * Constructor for ListPanel.  Sets up all the gui
     *
     * @param rp   reference to the restaurant panel
     * @param type indicates if this is for customers or waiters
     */
    public ListPanel(RestaurantPanel rp, String type) {
        restPanel = rp;
        this.type = type;
        currentPerson = null;
        
        setLayout(new BoxLayout((Container) this, BoxLayout.Y_AXIS));
        add(new JLabel("<html><pre> <u>" + type + "</u><br></pre></html>"));
        
        addNewInfo.setLayout(new BoxLayout(addNewInfo, BoxLayout.X_AXIS));
        addNewInfo.setMaximumSize(new Dimension(200,100));
        
        JLabel ask = new JLabel("Name: ");
        addNewName.setLayout(new BoxLayout(addNewName, BoxLayout.X_AXIS));
        addNewName.setMaximumSize(new Dimension (300, 100));
        addNewName.add(ask);
        addNewName.add(name);
        
        addNewInfo.add(addNewName);
        addPersonB.addActionListener(this);
        addNewInfo.add(addPersonB);
        add(addNewInfo);
        
        view.setLayout(new BoxLayout((Container) view, BoxLayout.Y_AXIS));
        pane.setViewportView(view);
        add(pane);
        
    }

    /**
     * Method from the ActionListener interface.
     * Handles the event of the add button being pressed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPersonB) {
        	// Chapter 2.19 describes showInputDialog()
            addPerson(name.getText());
            name.setText("");
        }
        /*if (e.getSource() == ){
        	if (currentPerson instanceof CustomerAgent) {
                CustomerAgent c = (CustomerAgent) currentPerson;
                c.getGui().setHungry();
                optionH.setEnabled(false);
            	}
        }*/
        else {
        	// Isn't the second for loop more beautiful?
            /*for (int i = 0; i < list.size(); i++) {
                JButton temp = list.get(i);*/
        	for (JCheckBox temp:list){
                if (e.getSource() == temp) {
                		currentPerson = restPanel.matchPerson( temp.getText());
                		currentBox = temp;
                        currentPerson.getGui().setHungry();
                        temp.setEnabled(false);
                		System.out.println("merr");
                    }
            }
        }
    }
    
    public void Reenable(String customerName){
    	for (JCheckBox temp:list){
            if (customerName.equals(temp.getText()))
            		currentBox = temp;
            		System.out.println("hi");
            		currentBox.setEnabled(true);
                    currentBox.setSelected(false);
    	}
    }

    /**
     * If the add button is pressed, this function creates
     * a spot for it in the scroll pane, and tells the restaurant panel
     * to add a new person.
     *
     * @param name name of new person
     */
    public void addPerson(String name) {
        if (name != null || name != " ") {
            JPanel PersonPanel = new JPanel();
            PersonPanel.setLayout(new FlowLayout());
            PersonPanel.setMaximumSize(new Dimension(300, 30));
            PersonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            
            JCheckBox PersonHungry = new JCheckBox(name);
            PersonHungry.addActionListener(this);
            PersonPanel.add(PersonHungry);
            
            restPanel.addPerson(type, name);
            restPanel.showInfo(type, name);
            list.add(PersonHungry);
            view.add(PersonPanel);
            
            /*
            button.setBackground(Color.white);

            Dimension paneSize = pane.getSize();
            Dimension buttonSize = new Dimension(paneSize.width - 20,
                    (int) (paneSize.height / 7));
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.addActionListener(this);
            list.add(button);
            view.add(button);
            restPanel.addPerson(type, name);//puts customer on list
            restPanel.showInfo(type, name);//puts hungry button on panel*/
            validate();
            
        }
    }
}
