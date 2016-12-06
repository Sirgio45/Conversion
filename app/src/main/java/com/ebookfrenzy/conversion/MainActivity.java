package com.ebookfrenzy.conversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner unitTypeSpinner;

    private EditText amountTextView;

    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, literTextView, milligramTextView, kilogramTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Fills the spinner with the unit options
        addItemsToUnitTypeSpinner();
        // Add listener to the Spinner
        addListenerToUnitTypeSpinner();
        // Get a reference to the edit text view to retrieve the amount of the unit type
        amountTextView = (EditText) findViewById(R.id.amount_text_view);
        initializeTextViews();


    }


    public void initializeTextViews() {

        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);

        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);

        cupTextView = (TextView) findViewById(R.id.cup_text_view);

        ounceTextView = (TextView) findViewById(R.id.oz_text_view);

        pintTextView = (TextView) findViewById(R.id.pint_text_view);

        quartTextView = (TextView) findViewById(R.id.quart_text_view);

        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);

        poundTextView = (TextView) findViewById(R.id.pound_text_view);

        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);

        literTextView = (TextView) findViewById(R.id.liter_text_view);

        milligramTextView = (TextView) findViewById(R.id.mg_text_view);

        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);
    }


    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();
                // Verify if I'm converting from teaspoon so that I use the right
                // cnversion algorithm
                checkIfConvertingFromTsp(itemSelectedInSpinner);

            }

            public void onNothingSelected(AdapterView<?> arg0)

            {
                // TODO maybe add something here later
            }
        });
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")) {
            updateUnitTypesUsingTsp(Quanity.Unit.tsp);
        } else {
            if (currentUnit.equals("tablespoon")) {
                updateUnitTypesUsingOther(Quanity.Unit.tbs);
            } else if (currentUnit.equals("cup")) {
                updateUnitTypesUsingOther(Quanity.Unit.cup);
            } else if (currentUnit.equals("ounce")) {
                updateUnitTypesUsingOther(Quanity.Unit.oz);
            } else if (currentUnit.equals("pint")) {
                updateUnitTypesUsingOther(Quanity.Unit.pint);
            } else if (currentUnit.equals("quart")) {
                updateUnitTypesUsingOther(Quanity.Unit.quart);
            } else if (currentUnit.equals("gallon")) {
                updateUnitTypesUsingOther(Quanity.Unit.gallon);
            } else if (currentUnit.equals("pound")) {
                updateUnitTypesUsingOther(Quanity.Unit.pound);
            } else if (currentUnit.equals("milliliter")) {
                updateUnitTypesUsingOther(Quanity.Unit.ml);
            } else if (currentUnit.equals("liter")) {
                updateUnitTypesUsingOther(Quanity.Unit.liter);
            } else if (currentUnit.equals("milligram")) {
                updateUnitTypesUsingOther(Quanity.Unit.mg);
            } else {
                updateUnitTypesUsingOther(Quanity.Unit.kg);
            }
        }


    }
    public void updateUnitTypesUsingTsp(Quanity.Unit currentUnit){
        // Convert the value in the EditText box to a double
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        // Combine value to unit
        String teaspoonValueAndUnit = doubleToConvert + " tsp";
        // Change the value for the teaspoon TextView
        teaspoonTextView.setText(teaspoonValueAndUnit);
        // Update all the Unit Text Fields
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quanity.Unit.kg, kilogramTextView);
    }
    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quanity.Unit unitConvertingTo, TextView theTextView){
        Quanity unitQuantity = new Quanity(doubleToConvert, Quanity.Unit.tsp);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);
    }

    public void updateUnitTypesUsingOther(Quanity.Unit currentUnit){
        // Convert the value in the EditText box to a double
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        // Create a Quantity using the teaspoon unit
        Quanity currentQuantitySelected = new Quanity(doubleToConvert, currentUnit);
        // Create the String for the teaspoon TextView
        String valueInTeaspoons = currentQuantitySelected.to(Quanity.Unit.tsp).toString();
        // Set the text for the teaspoon TextView
        teaspoonTextView.setText(valueInTeaspoons);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quanity.Unit.kg, kilogramTextView);
        // Set the currently selected unit to the number in the EditText
        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){
            // Create the TextView text by taking the value in EditText and adding
            // on the currently selected unit in the spinner
            String currentUnitTextViewText = doubleToConvert + " " +
            currentQuantitySelected.unit.name();
            // Create the TextView name to change by getting the currently
            // selected quantities unit name and tacking on _text_view
            String currentTextViewName = currentQuantitySelected.unit.name() + "_text_view";
            // Get the resource id needed for the textView to use in findViewById
            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            // Create an instance of the TextView we want to change
            TextView currentTextView = (TextView) findViewById(currentId);
            // Put the right data in the TextView
            currentTextView.setText(currentUnitTextViewText);
        }
    }
    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quanity.Unit currentUnit, Quanity.Unit preferredUnit, TextView targetTextView){
        Quanity currentQuantitySelected = new Quanity(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)
        String tempTextViewText = currentQuantitySelected.to(Quanity.Unit.tsp).
        to(preferredUnit).toString();
        targetTextView.setText(tempTextViewText);

    }

}




