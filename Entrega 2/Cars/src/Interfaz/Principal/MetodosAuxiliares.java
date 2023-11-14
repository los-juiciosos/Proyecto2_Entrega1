package Interfaz.Principal;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public interface MetodosAuxiliares {
	
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
	
	default public void formatButton(JButton button) {
		//add(Box.createVerticalGlue()); 
		
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setPreferredSize(preferredSize);
        button.setMaximumSize(preferredSize); //for some reason you need to add both for it to work haha
		
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border whiteLineBorder = BorderFactory.createLineBorder(Color.WHITE, 2, true);
        button.setBorder(whiteLineBorder);
        Color globalTheme = new Color(227, 36, 43);
		button.setBackground(globalTheme);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.WHITE);
	}
	
	default public void setTextFont(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 14);
        g.setFont(font);
        g.setColor(Color.black);
	}
	
	
}
