package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Find out the user's name
        EditText nameEditTextView = (EditText) findViewById(R.id.name_edittext_view);
        Editable customerName = nameEditTextView.getText();

        //Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        //Calculate total price of a cup of coffee
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        //Show order summary
        displayMessage(createOrderSummary(customerName, price, hasWhippedCream, hasChocolate));
    }

    /**
     * Calculates the price of the order.
     * @param whippedCream tells us if the user wants cream.
     * @param chocolate tells us if the user wants chocolate.
     *@return the total price of order.
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        //Price of 1 cup of coffee
        int coffeePrice = 5;

        //Add $1 if whipped cream is added
        if (whippedCream) {
            coffeePrice += + 1;
        }

        //Add $2 if chocolate is added
        if (chocolate) {
            coffeePrice += + 2;
        }

        //Calculate total price by multiplying the quantity
        return coffeePrice * quantity;
    }

    /* Creates a summary of the order.
     *@param takes price from calculatePrice method.
     *@return string summary of order.
     */
    private String createOrderSummary(Editable name,int price, boolean hasWhippedCream, boolean hasChocolate) {
        String orderSummary = "Name: " + name;
        orderSummary += "\nAdded Whipped Cream? " + hasWhippedCream;
        orderSummary += "\nAdded Chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: " + price;
        orderSummary += "\nThank You!";
        return orderSummary;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    
}