package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.flattemp.Model.Recipt;

public class AccountOptopns extends AppCompatActivity {
    CardView paymentenence,monthlyreciept,viewstatementself,viewstatementsociety,projectedexpences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_optopns);
        paymentenence=findViewById(R.id.paymentenece) ;
        monthlyreciept=findViewById(R.id.monthlyreciept);
       viewstatementself=findViewById(R.id.viewstatementself);
        viewstatementsociety=findViewById(R.id.viewstatementsociety);
        projectedexpences=findViewById(R.id.projectexpences);
        paymentenence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountOptopns.this,AccountPayment.class));
            }
        });
        monthlyreciept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountOptopns.this, ViewRecipt.class));
            }
        });
        viewstatementself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountOptopns.this,ViewStatementSelf.class));
            }
        });
        viewstatementsociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountOptopns.this,ViewStatementSociety.class));
            }
        });
        projectedexpences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountOptopns.this,ProjectExpences.class));
            }
        });

    }
}
