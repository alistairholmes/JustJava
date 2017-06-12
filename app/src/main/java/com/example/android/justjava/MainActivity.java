package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.duration;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;


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

        String orderMessage = createOrderSummary(customerName,price,hasWhippedCream,hasChocolate);

        //Creates intent to send order info to email app
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Coffee Order for " + customerName);
        intent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
        String orderSummary = getString(R.string.order_summary_name) + name;
        orderSummary += "\n" + getString(R.string.order_summary_whipped_cream) + hasWhippedCream;
        orderSummary += "\n" + getString(R.string.order_summary_chocolate) + hasChocolate;
        orderSummary += "\n" + getString(R.string.order_summary_quantity) + quantity;
        orderSummary += "\n" + getString(R.string.order_summary_price) + price;
        orderSummary += "\n" + getString(R.string.thank_you);
        return orderSummary;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            //Creates toast to prevent user from ordering more than 100 coffees
            Toast toast = Toast.makeText(getApplicationContext(), "You cannot have more than 100 coffees", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //Creates toast to prevent negative number when quantity is 1 and the minus button is pressed
            Toast toast = Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
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
    
}