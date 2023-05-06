package com.example.laparolequichange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class About_Activity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(About_Activity.this,Setting_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent,0);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("A propos");

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);



        // Display icon in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get a reference to the container view
        LinearLayout container = findViewById(R.id.container);

        // Create a text view and add it to the container
        TextView textView = findViewById(R.id.tvAboutDetail);
        textView.setText("La Parole Qui Change est une application Android de Calvary Chapel qui procure une aide à " +
                "ses utilisateurs dans l’étude de la parole de Dieu. Chaque chapitre de la Bible est traité, commenté " +
                "et appuyé d’exemples dans un fichier PDF. L’utilisateur aura aussi la version audio de cette étude à " +
                "sa disposition.\n" +
                "\n" +
                "Cette application tient son origine de l’émission du même nom diffusé sur la Radio Lumière. " +
                "Elle a été créée premièrement dans le but de rendre accessible aux auditeurs le contenu de l’émission. " +
                "Mais il s’avère que cette étude biblique peut aider beaucoup de gens. Donc La Parole Qui Change est plutôt " +
                "un complément apporte à la lecture de la bible, une mise en contexte de ce que l’utilisateur a lu dans la bible.\n" +
                "\n" +
                "Il existe bien d’autres applications fournissant des commentaires bien élaborés sur la Parole de Dieu. " +
                "Contrairement à ces dernières, La Parole Qui Change accompagne son contenu d’une audio plaçant les paroles " +
                "enseignées par la bible dans un contexte haïtien, facilitant ainsi une meilleure compréhension du sujet en question. " +
                "En guise de résumer, La Parole Qui Change est une application tout comme l’émission, qui étudie la Bible livre par livre, " +
                "chapitre par chapitre, verset par verset.\n" +
                "\n" +
                "Audio et PDF réalisés par Seige R. Poteau\n" +
                "Version: 1.0\n" +
                "La Parole Qui Change\n" +
                "© 2023 UEspoir. tous droits reservés.");
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(16);
//        container.addView(textView);

    }
}