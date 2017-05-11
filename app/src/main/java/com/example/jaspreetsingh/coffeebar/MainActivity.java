package com.example.jaspreetsingh.coffeebar;

import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        display(quantity);
    }

    /**
     * This method is called to decrease the quantity when the order button is clicked.
     */

    public void decrement (View view)   {

        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is to calculate the price and quantity.
     * @param view
     */
    public void submitOrder(View view) {
        int price;
        //displayPrice(quantity*price);
        price = calculatePrice();
        String priceMessage = "Total $ " + price;
        priceMessage = priceMessage + "\nThank You";
        displayText(priceMessage);

        displayPrice(price);
    }

    /**
     * Calculate the price of order
     *
     * @param quantity is the number of coffee cups
     */

    private int calculatePrice ()   {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayText(String message)    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}