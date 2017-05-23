package com.example.jaspreetsingh.coffeebar;

import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import static android.icu.lang.UCharacter.JoiningGroup.E;

public class MainActivity extends AppCompatActivity {
    int quantity =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called to increase the quantity when the order button is clicked.
     */
    public void increment(View view)   {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called to decrease the quantity when the order button is clicked.
     */

    public void decrement (View view)   {

        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is to calculate the price and quantity.
     * @param view
     */
    public void submitOrder(View view) {

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        Boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        Boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice();
        Log.v("MainActivity", "This price is " + price);
        displayText(createOrderSummary(price, hasWhippedCream, hasChocolate));

    }

    /**
     * Calculate the price of order
     *
     * @return total price  of coffee cups
     */

    private int calculatePrice ()   {
        int price = quantity * 5;
        return price;
    }

    /**
     *
     * This method create the summary of the order
     * @param price is the total price of coffee order
     * @param addWhippedCream is to check whether we have added whipped cream or not
     * @return the summary of order as String
     */
    private String createOrderSummary (int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: Jaspreet Singh Baidwan";
        priceMessage += "\nAdded Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdded Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal $ " + price;
        priceMessage += "\nThank You";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayText(String message)    {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}