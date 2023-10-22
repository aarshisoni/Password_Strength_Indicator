import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthIndicator extends JFrame {

    private JPasswordField passwordField;
    private JLabel strengthLabel;
    private JLabel infoLabel;

    public PasswordStrengthIndicator() {
        setTitle("Password Strength Indicator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        passwordField = new JPasswordField(20);
        strengthLabel = new JLabel("Password Strength: ");
        infoLabel = new JLabel("");

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStrengthIndicator();
            }
        });

        add(new JLabel("Enter Password: "));
        add(passwordField);
        add(strengthLabel);
        add(infoLabel);

        setVisible(true);
    }

    private void updateStrengthIndicator() {
        String password = new String(passwordField.getPassword());
        String strength = calculatePasswordStrength(password);
        String info = getPasswordInfo(password);

        strengthLabel.setText("Password Strength: " + strength);
        infoLabel.setText(info);

        if (strength.equals("Strong")) {
            strengthLabel.setForeground(Color.GREEN);
        } else if (strength.equals("Medium")) {
            strengthLabel.setForeground(Color.ORANGE);
        } else {
            strengthLabel.setForeground(Color.RED);
        }
    }

    private String calculatePasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < length; i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialCharacter = true;
            }
        }

        if (length >= 8 && hasUppercase && hasLowercase && hasDigit && hasSpecialCharacter) {
            return "Strong";
        } else if (length >= 6 && hasUppercase && hasLowercase && (hasDigit || hasSpecialCharacter)) {
            return "Medium";
        } else {
            return "Weak";
        }
    }

    private String getPasswordInfo(String password) {
        StringBuilder info = new StringBuilder("Password should contain: ");
        if (!password.isEmpty()) {
            if (!password.matches(".*[A-Z].*")) {
                info.append("Uppercase letters, ");
            }
            if (!password.matches(".*[a-z].*")) {
                info.append("Lowercase letters, ");
            }
            if (!password.matches(".*\\d.*")) {
                info.append("Digits, ");
            }
            if (!password.matches(".*[^A-Za-z0-9].*")) {
                info.append("Special characters, ");
            }

            if (info.toString().endsWith(", ")) {
                info.setLength(info.length() - 2); // Remove the trailing ", "
            } else {
                info.setLength(info.length() - 1); // Remove the trailing " "
            }
        }

        return info.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordStrengthIndicator();
            }
        });
    }
}