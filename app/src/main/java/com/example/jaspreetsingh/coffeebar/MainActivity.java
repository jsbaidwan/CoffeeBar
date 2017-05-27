package com.example.jaspreetsingh.coffeebar;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.JoiningGroup.E;

public class MainActivity extends AppCompatActivity {
    int quantity =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called to increase the quantity when the order button is clicked.
     */
    public void increment(View view)   {
        if(quantity ==100)  {
            Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called to decrease the quantity when the order button is clicked.
     */

    public void decrement (View view)   {
        if(quantity==0) {
            Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is to calculate the price and quantity.
     * @param view
     */
    public void submitOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        //Figure out if you want to add whipped cream
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        Boolean hasWhippedCream = whippedCream.isChecked();
        //Figure out if you want to add chocolate
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        Boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

//       // Log.v("MainActivity", "This price is " + price);

        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);


        composeEmail("jsbaidwan@gmail.com", name, priceMessage);
       // displayText(priceMessage);
    }
    public void composeEmail(String addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order summary of " + subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculate the price of order
     *
     * @return total price  of coffee cups
     */

    private int calculatePrice (boolean addWhippedCream, boolean addChocolate)   {
        //Base  price of coffee is $5
        int coffeePrice = 5;
        //adding whipped cream adds $1 to the price of coffee
        if (addWhippedCream)    {
            coffeePrice += 1;
        }
        //adding chocolate adds $2 to the price of coffee
        if (addChocolate)  {
            coffeePrice += 2;
        }
        //total price of the coffee is multiplied with the quantity
        int totalPrice = coffeePrice * quantity;
        return totalPrice;
    }

    /**
     *
     * This method create the summary of the order
     * @param price is the total price of coffee order
     * @param addWhippedCream is to check whether we have added whipped cream or not
     * @return the summary of order as String
     */
    private String createOrderSummary (String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name;
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