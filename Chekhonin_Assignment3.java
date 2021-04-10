
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import static javax.swing.GroupLayout.Alignment.*;

public class Chekhonin_Assignment3 extends JFrame implements ActionListener {

  private double result = 0;
  private int numberOfDigits = 1;
  private String operator = "=";
  private boolean calculating = true;
  private Font myFont = new java.awt.Font("Tempus Sans ITC", 1, 24);
  private Color backgroundColor = Color.white;
  private Color fontColor = Color.red;
  private Color keyPadColor = Color.black;

  // main window control elements
  private JTextField display = new JTextField("0");
  private JButton one = new JButton("1");
  private JButton two = new JButton("2");
  private JButton thr = new JButton("3");
  private JButton fou = new JButton("4");
  private JButton fiv = new JButton("5");
  private JButton six = new JButton("6");
  private JButton sev = new JButton("7");
  private JButton eig = new JButton("8");
  private JButton nin = new JButton("9");
  private JButton zer = new JButton("0");
  private JButton add = new JButton("+");
  private JButton sub = new JButton("-");
  private JButton mul = new JButton("*");
  private JButton div = new JButton("/");
  private JButton equ = new JButton("=");
  private JButton clr = new JButton("C");
  private JButton dot = new JButton(".");
  private JButton settings = new JButton("S");
  private JButton[] buttArray = { one, two, thr, fou, fiv, six, sev, eig, nin, zer, add, sub, mul, div, equ, clr, dot };

  // pop-up window control elements
  private JLabel backGroundSign = new JLabel("Background");
  private JLabel foreGroundSign = new JLabel("Foreground");
  private JLabel keypadColorSign = new JLabel("Keypad color");
  private JLabel decimalSign = new JLabel("Decimal");
  private JLabel fontSign = new JLabel("Font");
  private JLabel[] labelArray = { backGroundSign, foreGroundSign, keypadColorSign, decimalSign, fontSign };

  private JButton backGroundButton = new JButton("Background");
  private JButton foreGroundButton = new JButton("Foreground");
  private JButton keypadColorButton = new JButton("Keypad color");
  private JButton decimalButton = new JButton("Decimal");
  private JButton fontButton = new JButton("Font");
  private JButton okButton = new JButton("OK");
  private JButton[] buttArray2 = { backGroundButton, foreGroundButton, keypadColorButton, decimalButton, fontButton };

  public Chekhonin_Assignment3() {
    super();
    setTitle("Assignment_3_Calculator");
    setSize(420, 520);

    for (int i = 0; i < buttArray.length; i++) {
      buttArray[i].setFont(myFont);
      buttArray[i].setForeground(fontColor);
      buttArray[i].setBackground(keyPadColor);
      buttArray[i].addActionListener(this);
    }
    settings.setFont(myFont);
    settings.setBackground(keyPadColor);
    settings.setForeground(fontColor);
    settings.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        newFrame();
      }
    });

    // display.setPreferredSize();
    display.setEditable(false);
    display.setBackground(keyPadColor);
    display.setForeground(fontColor);
    display.setFont(myFont);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    getContentPane().setBackground(backgroundColor);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    layout.setVerticalGroup(layout.createSequentialGroup().addComponent(display)

        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING).addComponent(one).addComponent(two).addComponent(thr)
                .addComponent(clr))
            .addGroup(layout.createParallelGroup(LEADING).addComponent(fou).addComponent(fiv).addComponent(six)
                .addComponent(div).addComponent(mul))
            .addGroup(layout.createParallelGroup(LEADING).addComponent(sev).addComponent(eig).addComponent(nin)
                .addComponent(sub).addComponent(add))
            .addGroup(layout.createParallelGroup(LEADING).addComponent(settings).addComponent(dot).addComponent(zer)
                .addComponent(equ)))

    );

    layout.linkSize(SwingConstants.HORIZONTAL, one, settings, dot, mul, div, add, sub);

    layout.setHorizontalGroup(layout.createParallelGroup().addComponent(display)

        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING).addComponent(one).addComponent(fou).addComponent(sev)
                    .addComponent(settings))
                .addGroup(layout.createParallelGroup(LEADING).addComponent(two).addComponent(fiv).addComponent(eig)
                    .addComponent(dot))
                .addGroup(layout.createParallelGroup(LEADING).addComponent(thr).addComponent(six).addComponent(nin)
                    .addComponent(zer)))
            .addGroup(layout.createParallelGroup()
                .addComponent(clr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup().addComponent(div).addComponent(mul))
                .addGroup(layout.createSequentialGroup().addComponent(sub).addComponent(add))
                .addComponent(equ, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

    pack();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    Chekhonin_Assignment3 calculator = new Chekhonin_Assignment3();
    calculator.setVisible(true);
  }

  public void actionPerformed(ActionEvent evt) {
    String cmd = evt.getActionCommand();
    // System.out.printf(cmd);
    if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
      if (calculating)
        display.setText(cmd);
      else
        display.setText(display.getText() + cmd);
      calculating = false;
    } else if (cmd.equals("C")) {
      result = 0;
      display.setText("0");
    } else {
      if (calculating) {
        if (cmd.equals("-")) {
          display.setText(cmd);
          calculating = false;
        } else
          operator = cmd;
      } else {
        double x = Double.parseDouble(display.getText());
        calculate(x);
        operator = cmd;
        calculating = true;
      }
    }
  }

  private void calculate(double n) {
    if (operator.equals("+"))
      result += n;
    else if (operator.equals("-"))
      result -= n;
    else if (operator.equals("*"))
      result *= n;
    else if (operator.equals("/"))
      result /= n;
    else if (operator.equals("="))
      result = n;
    display.setText("" + result);
  }

  private void newFrame() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
    frame.setTitle("Settings");
    frame.setSize(420, 520);
    JPanel settingsPanel = new JPanel();
    for (JButton b : buttArray2) {
      settingsPanel.add(b);
    }
    for (JLabel b : labelArray) {
      settingsPanel.add(b);
    }

    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        keyPadColor = Color.black;
        frame.dispose();
      }
    });

    GroupLayout layout2 = new GroupLayout(frame.getContentPane());
    frame.getContentPane().setLayout(layout2);
    layout2.setAutoCreateGaps(true);
    layout2.setAutoCreateContainerGaps(true);

    layout2.setHorizontalGroup(layout2.createSequentialGroup()
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout2.createParallelGroup().addComponent(backGroundSign).addComponent(foreGroundSign)
            .addComponent(keypadColorSign).addComponent(decimalSign).addComponent(fontSign))
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout2.createParallelGroup()
            .addGroup(layout2.createSequentialGroup()
                .addGroup(layout2.createParallelGroup().addComponent(backGroundButton).addComponent(foreGroundButton)
                    .addComponent(keypadColorButton).addComponent(decimalButton)))
            .addGroup(layout2.createParallelGroup()
                .addComponent(fontButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout2.createSequentialGroup().addComponent(okButton))))

    );

    layout2.linkSize(SwingConstants.HORIZONTAL, backGroundButton, foreGroundButton, keypadColorButton, decimalButton);

    layout2.setVerticalGroup(layout2.createSequentialGroup()
        .addGroup(layout2.createParallelGroup(BASELINE).addComponent(backGroundSign)

            .addComponent(backGroundButton))
        .addGroup(layout2.createParallelGroup(BASELINE).addComponent(foreGroundSign).addComponent(foreGroundButton))
        .addGroup(layout2.createParallelGroup(BASELINE).addComponent(keypadColorSign).addComponent(keypadColorButton))
        .addGroup(layout2.createParallelGroup(BASELINE).addComponent(decimalSign).addComponent(decimalButton))
        .addGroup(layout2.createParallelGroup(BASELINE).addComponent(fontSign).addComponent(fontButton))
        .addComponent(okButton));

    frame.add(settingsPanel);
    frame.setVisible(true);
  }
}
