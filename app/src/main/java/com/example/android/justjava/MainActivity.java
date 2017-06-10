package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream));
    }

    /**
     * Calculates the price of the order.
     *@return the total price of order.
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /* Creates a summary of the order.
     *@param takes price from calculatePrice method.
     *@return string summary of order.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream) {
        String orderSummary = "Name: Alistair Holmes";
        orderSummary += "\nAdded Whipped Cream? " + hasWhippedCream;
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