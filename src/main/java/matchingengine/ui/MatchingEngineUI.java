package matchingengine.ui;

import matchingengine.engine.OrderBook;
import matchingengine.model.LimitOrder;
import matchingengine.model.MarketOrder;
import matchingengine.model.Order;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchingEngineUI extends JFrame {

    private OrderBook orderBook;

    // Cards for different functions
    private JPanel mainPanel;
    private JPanel limitOrderPanel;
    private JPanel marketOrderPanel;
    private JPanel cancelOrderPanel;
    private JPanel updateOrderPanel;
    private JPanel historyPanel;

    // Fields for Limit Order
    private JRadioButton limitBuyRadioButton;
    private JRadioButton limitSellRadioButton;
    private JTextField limitPriceField;
    private JTextField limitQuantityField;
    private JButton submitLimitOrderButton;

    // Fields for Market Order
    private JRadioButton marketBuyRadioButton;
    private JRadioButton marketSellRadioButton;
    private JTextField marketQuantityField;
    private JButton submitMarketOrderButton;

    // Fields for Cancel Order
    private JTextField cancelOrderIdField;
    private JButton cancelOrderButton;

    // Fields for Update Order
    private JTextField updateOrderIdField;
    private JTextField updatePriceField;
    private JTextField updateQuantityField;
    private JButton updateOrderButton;

    // History Text Area
    private JTextArea historyArea;

    public MatchingEngineUI() {
        orderBook = new OrderBook();
        initComponents();
    }

    private void initComponents() {
        setTitle("Matching Engine UI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel with CardLayout
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(new Color(0x313338));

        // Create panels for different functions
        createMainMenu();
        createLimitOrderPanel();
        createMarketOrderPanel();
        createCancelOrderPanel();
        createUpdateOrderPanel();
        createHistoryPanel();

        add(mainPanel, BorderLayout.CENTER);
    }

    private void createMainMenu() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setBackground(new Color(0x313338));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        Dimension buttonSize = new Dimension(150, 40);

        JButton limitOrderButton = createMenuButton("Limit Order", buttonSize);
        limitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("LimitOrderPanel");
            }
        });

        JButton marketOrderButton = createMenuButton("Market Order", buttonSize);
        marketOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MarketOrderPanel");
            }
        });

        JButton cancelOrderButton = createMenuButton("Cancel Order", buttonSize);
        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("CancelOrderPanel");
            }
        });

        JButton updateOrderButton = createMenuButton("Update Order", buttonSize);
        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("UpdateOrderPanel");
            }
        });

        JButton viewHistoryButton = createMenuButton("View History", buttonSize);
        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("HistoryPanel");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        menuPanel.add(limitOrderButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        menuPanel.add(marketOrderButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        menuPanel.add(cancelOrderButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        menuPanel.add(updateOrderButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        menuPanel.add(viewHistoryButton, gbc);

        mainPanel.add(menuPanel, "MainMenu");
    }

    private JButton createMenuButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setBackground(new Color(0x5A5A5A));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);  // This is needed to make the button round
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),  // Rounded border with 5px radius
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return button;
    }

    private void createLimitOrderPanel() {
        limitOrderPanel = new JPanel(new GridBagLayout());
        limitOrderPanel.setBackground(new Color(0x313338));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel limitOrderLabel = new JLabel("Limit Order - Side:");
        limitOrderLabel.setForeground(Color.WHITE);
        limitOrderPanel.add(limitOrderLabel, gbc);

        limitBuyRadioButton = new JRadioButton("Buy");
        limitSellRadioButton = new JRadioButton("Sell");
        ButtonGroup limitButtonGroup = new ButtonGroup();
        limitButtonGroup.add(limitBuyRadioButton);
        limitButtonGroup.add(limitSellRadioButton);

        limitBuyRadioButton.setSelected(true); // Set default selection
        limitBuyRadioButton.setBackground(new Color(0x313338));
        limitBuyRadioButton.setForeground(Color.WHITE);
        limitSellRadioButton.setBackground(new Color(0x313338));
        limitSellRadioButton.setForeground(Color.WHITE);

        gbc.gridy = 1;
        limitOrderPanel.add(limitBuyRadioButton, gbc);
        limitOrderPanel.add(limitSellRadioButton, gbc);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        limitOrderPanel.add(priceLabel, gbc);
        limitPriceField = new JTextField(10);
        limitPriceField.setBackground(Color.DARK_GRAY);
        limitPriceField.setForeground(Color.WHITE);
        limitOrderPanel.add(limitPriceField, gbc);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        limitOrderPanel.add(quantityLabel, gbc);
        limitQuantityField = new JTextField(10);
        limitQuantityField.setBackground(Color.DARK_GRAY);
        limitQuantityField.setForeground(Color.WHITE);
        limitOrderPanel.add(limitQuantityField, gbc);

        submitLimitOrderButton = createActionButton("Submit Limit Order");
        submitLimitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitLimitOrder();
            }
        });
        gbc.gridy = 4;
        limitOrderPanel.add(submitLimitOrderButton, gbc);

        JButton backButton = createActionButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainMenu");
            }
        });
        gbc.gridy = 5;
        limitOrderPanel.add(backButton, gbc);

        mainPanel.add(limitOrderPanel, "LimitOrderPanel");
    }

    private void createMarketOrderPanel() {
        marketOrderPanel = new JPanel(new GridBagLayout());
        marketOrderPanel.setBackground(new Color(0x313338));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel marketOrderLabel = new JLabel("Market Order - Side:");
        marketOrderLabel.setForeground(Color.WHITE);
        marketOrderPanel.add(marketOrderLabel, gbc);

        marketBuyRadioButton = new JRadioButton("Buy");
        marketSellRadioButton = new JRadioButton("Sell");
        ButtonGroup marketButtonGroup = new ButtonGroup();
        marketButtonGroup.add(marketBuyRadioButton);
        marketButtonGroup.add(marketSellRadioButton);

        marketBuyRadioButton.setSelected(true); // Set default selection
        marketBuyRadioButton.setBackground(new Color(0x313338));
        marketBuyRadioButton.setForeground(Color.WHITE);
        marketSellRadioButton.setBackground(new Color(0x313338));
        marketSellRadioButton.setForeground(Color.WHITE);

        gbc.gridy = 1;
        marketOrderPanel.add(marketBuyRadioButton, gbc);
        marketOrderPanel.add(marketSellRadioButton, gbc);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        marketOrderPanel.add(quantityLabel, gbc);
        marketQuantityField = new JTextField(10);
        marketQuantityField.setBackground(Color.DARK_GRAY);
        marketQuantityField.setForeground(Color.WHITE);
        marketOrderPanel.add(marketQuantityField, gbc);

        submitMarketOrderButton = createActionButton("Submit Market Order");
        submitMarketOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitMarketOrder();
            }
        });
        gbc.gridy = 3;
        marketOrderPanel.add(submitMarketOrderButton, gbc);

        JButton backButton = createActionButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainMenu");
            }
        });
        gbc.gridy = 4;
        marketOrderPanel.add(backButton, gbc);

        mainPanel.add(marketOrderPanel, "MarketOrderPanel");
    }

    private void createCancelOrderPanel() {
        cancelOrderPanel = new JPanel(new GridBagLayout());
        cancelOrderPanel.setBackground(new Color(0x313338));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel cancelOrderLabel = new JLabel("Cancel Order - Order ID:");
        cancelOrderLabel.setForeground(Color.WHITE);
        cancelOrderPanel.add(cancelOrderLabel, gbc);
        cancelOrderIdField = new JTextField(10);
        cancelOrderIdField.setBackground(Color.DARK_GRAY);
        cancelOrderIdField.setForeground(Color.WHITE);
        gbc.gridy = 1;
        cancelOrderPanel.add(cancelOrderIdField, gbc);

        cancelOrderButton = createActionButton("Cancel Order");
        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelOrder();
            }
        });
        gbc.gridy = 2;
        cancelOrderPanel.add(cancelOrderButton, gbc);

        JButton backButton = createActionButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainMenu");
            }
        });
        gbc.gridy = 3;
        cancelOrderPanel.add(backButton, gbc);

        mainPanel.add(cancelOrderPanel, "CancelOrderPanel");
    }

    private void createUpdateOrderPanel() {
        updateOrderPanel = new JPanel(new GridBagLayout());
        updateOrderPanel.setBackground(new Color(0x313338));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel updateOrderLabel = new JLabel("Update Order - Order ID:");
        updateOrderLabel.setForeground(Color.WHITE);
        updateOrderPanel.add(updateOrderLabel, gbc);
        updateOrderIdField = new JTextField(10);
        updateOrderIdField.setBackground(Color.DARK_GRAY);
        updateOrderIdField.setForeground(Color.WHITE);
        gbc.gridy = 1;
        updateOrderPanel.add(updateOrderIdField, gbc);

        JLabel newPriceLabel = new JLabel("New Price:");
        newPriceLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        updateOrderPanel.add(newPriceLabel, gbc);
        updatePriceField = new JTextField(10);
        updatePriceField.setBackground(Color.DARK_GRAY);
        updatePriceField.setForeground(Color.WHITE);
        updateOrderPanel.add(updatePriceField, gbc);

        JLabel newQuantityLabel = new JLabel("New Quantity:");
        newQuantityLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        updateOrderPanel.add(newQuantityLabel, gbc);
        updateQuantityField = new JTextField(10);
        updateQuantityField.setBackground(Color.DARK_GRAY);
        updateQuantityField.setForeground(Color.WHITE);
        updateOrderPanel.add(updateQuantityField, gbc);

        updateOrderButton = createActionButton("Update Order");
        updateOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrder();
            }
        });
        gbc.gridy = 4;
        updateOrderPanel.add(updateOrderButton, gbc);

        JButton backButton = createActionButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainMenu");
            }
        });
        gbc.gridy = 5;
        updateOrderPanel.add(backButton, gbc);

        mainPanel.add(updateOrderPanel, "UpdateOrderPanel");
    }

    private void createHistoryPanel() {
        historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(new Color(0x313338));

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setBackground(new Color(0x313338));
        historyArea.setForeground(Color.WHITE);
        historyArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = createActionButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("MainMenu");
            }
        });
        historyPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(historyPanel, "HistoryPanel");
    }

    private JButton createActionButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0x5A5A5A));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);  // This is needed to make the button round
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),  // Rounded border with 5px radius
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return button;
    }

    private void submitLimitOrder() {
        try {
            String side = limitBuyRadioButton.isSelected() ? "buy" : "sell";
            double price = Double.parseDouble(limitPriceField.getText());
            int quantity = Integer.parseInt(limitQuantityField.getText());
            Order limitOrder = new LimitOrder(side, price, quantity);
            if (side.equalsIgnoreCase("buy")) {
                orderBook.buy(limitOrder);
            } else if (side.equalsIgnoreCase("sell")) {
                orderBook.sell(limitOrder);
            }
            String message = "Limit order submitted: " + side + " " + quantity + " @ " + price + " (ID: " + limitOrder.getId() + ")";
            JOptionPane.showMessageDialog(this, message, "Order Submitted", JOptionPane.INFORMATION_MESSAGE);
            historyArea.append(message + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price or quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitMarketOrder() {
        try {
            String side = marketBuyRadioButton.isSelected() ? "buy" : "sell";
            int quantity = Integer.parseInt(marketQuantityField.getText());
            Order marketOrder = new MarketOrder(side, quantity);
            if (side.equalsIgnoreCase("buy")) {
                orderBook.buy(marketOrder);
            } else if (side.equalsIgnoreCase("sell")) {
                orderBook.sell(marketOrder);
            }
            String message = "Market order submitted: " + side + " " + quantity + " (ID: " + marketOrder.getId() + ")";
            JOptionPane.showMessageDialog(this, message, "Order Submitted", JOptionPane.INFORMATION_MESSAGE);
            historyArea.append(message + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelOrder() {
        try {
            long orderId = Long.parseLong(cancelOrderIdField.getText());
            if (orderBook.cancelOrder(orderId)) {
                String message = "Order " + orderId + " canceled.";
                JOptionPane.showMessageDialog(this, message, "Order Canceled", JOptionPane.INFORMATION_MESSAGE);
                historyArea.append(message + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Order " + orderId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid orderId format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateOrder() {
        try {
            long orderId = Long.parseLong(updateOrderIdField.getText());
            double newPrice = Double.parseDouble(updatePriceField.getText());
            int newQuantity = Integer.parseInt(updateQuantityField.getText());
            if (orderBook.updateOrder(orderId, newPrice, newQuantity)) {
                String message = "Order " + orderId + " updated.";
                JOptionPane.showMessageDialog(this, message, "Order Updated", JOptionPane.INFORMATION_MESSAGE);
                historyArea.append(message + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Order " + orderId + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid orderId, newPrice, or newQuantity format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MatchingEngineUI().setVisible(true);
            }
        });
    }
}