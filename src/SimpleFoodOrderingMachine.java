import javax.swing.*;
import java.awt.Image;  //Imageの処理
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleFoodOrderingMachine {
    private JPanel root;
    private JLabel topLabel;
    private JButton MOSburgerButton;
    private JButton TeriyakiburgerButton;
    private JButton FishburgerButton;
    private JButton FrenchFriesButton;
    private JButton chickenButton;
    private JButton cupSaradaButton;
    private JButton CheckOutButton;
    private JTextPane orderedItemsList;
    private JLabel Totalordered;

    private int totalmoney=0;
    private int burgercount=0;
    private int sidecount=0;
    int MosBurgerPrice = 440;
    int TeriyakiBurgerPrice = 430;
    int FishBurgerPrice = 390;
    int FrenchFriesPrice = 0;    //サイズ設定を行うまでいったん0に
    int ChickenPrice = 350;
    int CupSaradaPrice = 320;

    static void order(String  food){
        JOptionPane.showMessageDialog(null,"Order for "+food+" received.");
    }
    private ImageIcon resizeIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    private void totalmoney(int price){
        totalmoney+= price;
        Totalordered.setText(totalmoney+" yen");
    }

    private void applySetDiscount() {
        if (burgercount > 0 && sidecount > 0) {
            totalmoney -= 50; // セット割引の適用
            burgercount--;
            sidecount--;
            String currentText=orderedItemsList.getText();
            orderedItemsList.setText(currentText + "set discount(-50 yen)" + "\n");
            Totalordered.setText(totalmoney + " yen");
        }
    }

    public SimpleFoodOrderingMachine() {
        MOSburgerButton.setIcon(resizeIcon("/resources/burger.jpg", 150, 200));
        TeriyakiburgerButton.setIcon(resizeIcon("/resources/Teriyaki.jpg", 150, 200));
        FishburgerButton.setIcon(resizeIcon("/resources/Fish.jpg", 150, 200));
        FrenchFriesButton.setIcon(resizeIcon("/resources/FrenchFries.jpg", 150, 200));
        chickenButton.setIcon(resizeIcon("/resources/chicken.jpg", 150, 200));
        cupSaradaButton.setIcon(resizeIcon("/resources/Cup Sarada.jpg", 150, 200));
        MOSburgerButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Would you like to order MOS burger?",
                        "Order Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation==0){
                    String currentText=orderedItemsList.getText();
                    if(currentText.equals("no order")){
                        orderedItemsList.setText("MOS Burger   "+MosBurgerPrice+" yen\n");
                    }
                    else {
                        orderedItemsList.setText(currentText + "MOS Burger    " +MosBurgerPrice+ " yen\n");
                    }
                    totalmoney(MosBurgerPrice);
                    burgercount++;
                    applySetDiscount();
                    order("MOS Burger");
                }
            }

        });
        TeriyakiburgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Would you like to order Teriyaki Burger?",
                        "Order Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation==0){
                    String currentText=orderedItemsList.getText();
                    if(currentText.equals("no order")){
                        orderedItemsList.setText("Teriyaki burger   "+TeriyakiBurgerPrice+" yen\n");
                    }
                    else {
                        orderedItemsList.setText(currentText + "Teriyaki burger   " +TeriyakiBurgerPrice+ " yen\n");
                    }
                    totalmoney(TeriyakiBurgerPrice);
                    burgercount++;
                    applySetDiscount();
                    order("Teriyaki burger");
                }
            }
        });
        FishburgerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Would you like to order Fish burger?",
                        "Order Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation==0){
                    String currentText=orderedItemsList.getText();
                    if(currentText.equals("no order")){
                        orderedItemsList.setText("Fish burger   "+FishBurgerPrice+" yen\n");
                    }
                    else {
                        orderedItemsList.setText(currentText + "Fish burger   " +FishBurgerPrice+ " yen\n");
                    }
                    totalmoney(FishBurgerPrice);
                    burgercount++;
                    applySetDiscount();
                    order("Fish burger");
                }
            }
        });
        FrenchFriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] sizes = {"S", "M", "L"};
                String size = (String) JOptionPane.showInputDialog(
                        null,
                        "Select size for French Fries:",
                        "Size Selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        sizes,
                        sizes[1]); // "M"

                if (size == null) {// キャンセルされたら処理を中断
                    return;
                }

                // サイズごとの価格を設定
                if (size.equals("S")) {
                    FrenchFriesPrice = 240;
                } else if (size.equals("M")) {
                    FrenchFriesPrice = 300;
                } else if (size.equals("L")) {
                    FrenchFriesPrice = 360;
                }

                int confirmation = JOptionPane.showConfirmDialog(
                        null,
                        "Would you like to order French Fries (" + size + ")?",
                        "Order Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmation==0){
                    String currentText=orderedItemsList.getText();
                    if(currentText.equals("no order")){
                        orderedItemsList.setText("French Fries("+size+")   "+FrenchFriesPrice+" yen\n");
                    }
                    else {
                        orderedItemsList.setText(currentText + "French Fries(" +size+ ")   "   +FrenchFriesPrice+" yen\n");
                    }
                    totalmoney(FrenchFriesPrice);
                    sidecount++;
                    applySetDiscount();
                    order("French Fries");
                }
            }
        });
        chickenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Would you like to order Chicken?",
                        "Order Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation==0){
                    String currentText=orderedItemsList.getText();
                    if(currentText.equals("no order")){
                        orderedItemsList.setText("Chicken   "+ChickenPrice+" yen\n");
                    }
                    else {
                        orderedItemsList.setText(currentText + "Chicken   " +ChickenPrice+ " yen\n");
                    }
                    totalmoney(ChickenPrice);
                    sidecount++;
                    applySetDiscount();
                    order("Chicken");
                }
            }
        });
        cupSaradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Would you like to order Cup Sarada?",
                        "Order Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation==0){
                    String currentText=orderedItemsList.getText();
                    if(currentText.equals("no order")){
                        orderedItemsList.setText("Cup Sarada   "+CupSaradaPrice+" yen\n");
                    }
                    else {
                        orderedItemsList.setText(currentText + "Cup Sarada   " +CupSaradaPrice+ " yen\n");
                    }
                    totalmoney(CupSaradaPrice);
                    sidecount++;
                    applySetDiscount();
                    order("Cup Sarada");
                }
            }
        });
        CheckOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null,
                        "Would you like to checkout?",
                        "order completed?",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation==0){
                    JOptionPane.showMessageDialog(null,"Thank you! The Total price is "+totalmoney+" yen");
                    orderedItemsList.setText("no order");
                    burgercount=0;
                    sidecount=0;
                    totalmoney(-totalmoney);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SimpleFoodOrderingMachine");
        frame.setContentPane(new SimpleFoodOrderingMachine().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
