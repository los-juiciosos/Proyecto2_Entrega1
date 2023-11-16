package Interfaz.Principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public interface MetodosAuxiliares {
	
	Color cutePurple = new Color(207, 159, 255);
	Color cuteYellow = new Color(255,253,141);
	Color redTheme = new Color (255, 71, 76);
	Color pastelYellow = new Color(250, 237, 203);
	Color pastelGreen = new Color(201, 228, 222);
	Color pastelBlue = new Color(198, 222, 241);
	Color pastelPurple = new Color(219, 205, 240);
	Color pastelPink= new Color(242, 198, 222);
	Color pastelOrange = new Color(247, 217, 196);
	Color lightOrange = new Color(255, 165, 0);
	
	//METODOS PARA USAR LA MISMA ESTETICA EN TODOS LOS PANELES
	
	default public void ponerTextitoGris(JTextField textField, String mensaje) {
		
		textField.setText(mensaje);
		textField.setForeground(Color.GRAY);
		
		textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the placeholder text and change the text color to black
                if (textField.getText().equals(mensaje)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // If the text field is empty, set the placeholder text and color
                if (textField.getText().isEmpty()) {
                	textField.setText(mensaje);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
	}
	
	default public void titleText(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.WHITE);
	}
	
	default public void subTitleText(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(Color.WHITE);
	}
	
	default public void formatLeftArrowButton(JButton button) {
		
		Dimension preferredSize = new Dimension(40, 25);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize);
        
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
		button.setBackground(redTheme);
		button.setFont(new Font("Arial", Font.BOLD, 30));
		button.setForeground(Color.WHITE);		
	}
	
	default public void formatLeftArrowButton(JButton button, Color color) {
		
		Dimension preferredSize = new Dimension(40, 25);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize);
        
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
		button.setBackground(color);
		button.setFont(new Font("Arial", Font.BOLD, 30));
		button.setForeground(Color.WHITE);		
	}
	
	default public void formatButton(JButton button) {
		//add(Box.createVerticalGlue()); 
		
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize); //for some reason you need to add both for it to work haha
		
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
		button.setBackground(redTheme);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.WHITE);
	}
	
	default public void formatButton(JButton button, Color color) {
		//add(Box.createVerticalGlue()); 
		
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize); //for some reason you need to add both for it to work haha
		
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
		button.setBackground(color);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.WHITE);
	}
	
	default public void formatButton(JButton button, int extraWidth) {
		//add(Box.createVerticalGlue()); 
		
        Dimension preferredSize = new Dimension(200 + extraWidth, button.getPreferredSize().height);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize); //for some reason you need to add both for it to work haha
		
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
		button.setBackground(redTheme);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.WHITE);
	}
	
	default public void formatJTable(JTable table) {
		
		Font tableFont = new Font("Arial", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.setForeground(Color.WHITE);
        
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.PINK); 
        header.setForeground(Color.WHITE); 
        Font headerFont = new Font("Arial", Font.BOLD, 22); 
        header.setFont(headerFont);
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(30);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		        setHorizontalAlignment(SwingConstants.CENTER);
		        
		        if (row%8 == 0) {
		            c.setBackground(pastelYellow);
		        } else if (row%8 == 1) {
		            c.setBackground(pastelGreen);
		        } else if (row%8 == 2) {
		            c.setBackground(pastelBlue);
		        } else if (row%8 == 3) {
		            c.setBackground(pastelPurple);
		        } else if (row%8 == 4) {
		            c.setBackground(pastelPink);
		        } else if (row%8 == 5) {
		            c.setBackground(pastelOrange);
		        } else if (row%8 == 6) {
		            c.setBackground(pastelYellow);
		        } else if (row%8 == 7) {
		            c.setBackground(pastelGreen);
		        }

		        return c;
		    }
		});
	}
	
	default public void formatBigButton(JButton button) {
		//add(Box.createVerticalGlue()); 
		
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height+30);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize); //for some reason you need to add both for it to work haha
		
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
//        Color globalTheme = new Color(227, 36, 43);
//		button.setBackground(globalTheme);
		button.setFont(new Font("Arial", Font.BOLD, 22));
		button.setForeground(Color.WHITE);
		
		button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                int w = c.getWidth();
                int h = c.getHeight();

                GradientPaint gradient = new GradientPaint(0, 0, pastelPurple, w, h, cutePurple);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, w, h);

                super.paint(g2d, c);
                g2d.dispose();
            }
        });
	}
	
	default public void formatGradientButton(JButton button, Color color1, Color color2) {
		//add(Box.createVerticalGlue()); 
		
		Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize); //for some reason you need to add both for it to work haha
		
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.WHITE);
		
		button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                int w = c.getWidth();
                int h = c.getHeight();

                GradientPaint gradient = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, w, h);

                super.paint(g2d, c);
                g2d.dispose();
            }
        });
	}

	
	
	default public void setTextFont(Graphics g) {
		Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.GRAY);
	}
	
	default public JPanel newPanelTransparente() {
		JPanel transparentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // Allow the panel to be transparent
                super.paintComponent(g);
                setOpaque(false);
            }
        };
        
        transparentPanel.setBackground(new Color(0, 0, 0, 0));
        
        return transparentPanel;
	}
	
	default public JPanel newGradientPanel(Color color1, Color color2) {
		JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            	Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();

                // Define the start and end points for the gradient
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, height);

                // Create a gradient paint
                GradientPaint gradientPaint = new GradientPaint(start, color1, end, color2);

                // Set the paint for the graphics context
                g2d.setPaint(gradientPaint);

                // Fill the background with the gradient paint
                g2d.fillRect(0, 0, width, height);

            }
        };
        
        return gradientPanel;
	}
	
	
}
