package com.example.mescoursesjeina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.widget.ListAdapter;


import java.util.List;

public class makeShopList extends AppCompatActivity {

    private EditText productNameEditText, quantityEditText, departmentEditText;
    private Button addButton;
    private ListView productListView;
    private ProductDatabaseHelper productDatabaseHelper;
    private ProductListAdapter productListAdapter;
    private RecyclerView productRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_shop_list);

        productNameEditText = findViewById(R.id.product_name_edit_text);
        quantityEditText = findViewById(R.id.quantity_edit_text);
        departmentEditText = findViewById(R.id.department_edit_text);
        addButton = findViewById(R.id.add_button);

        productDatabaseHelper = new ProductDatabaseHelper(this);
        productListAdapter = new ProductListAdapter(productDatabaseHelper.getAllProducts());

        productRecyclerView = findViewById(R.id.product_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.setAdapter(productListAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = productNameEditText.getText().toString();
                String quantityStr = quantityEditText.getText().toString();
                String department = departmentEditText.getText().toString();

                if (productName.isEmpty() || quantityStr.isEmpty() || department.isEmpty()) {
                    Toast.makeText(makeShopList.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int quantity = Integer.parseInt(quantityStr);

                    Product product = new Product(productName, quantity, department);
                    long id = productDatabaseHelper.addProduct(product);

                    if (id != -1) {
                        List<Product> productList = productDatabaseHelper.getAllProducts();
                        productListAdapter.updateData(productList);


                        productNameEditText.setText("");
                        quantityEditText.setText("");
                        departmentEditText.setText("");

                        Toast.makeText(makeShopList.this, "Produit ajouté", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(makeShopList.this, "Erreur lors de l'ajout du produit", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(makeShopList.this, "La quantité doit être un nombre entier", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        productDatabaseHelper.close();
    }
}
