package com.example.sel.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sel.R;
import com.example.sel.model.Commentaire;
import com.example.sel.model.Transaction;
import com.example.sel.ui.adapter.CommentaireAdapter;
import com.example.sel.ui.viewModel.TransactionDetailViewModel;
import com.example.sel.ui.viewModelFactory.TransactionDetailViewModelFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDetailActivity extends ParentActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;
    private TextView userNameDonneur;
    private TextView userNameReceveur;
    private TextView date;
    private TextView description;
    private Button validateBtn;
    private RecyclerView commentView;
    private EditText commentArea;
    private Button sendPhotoBtn;
    private Button sendComBtn;
    private TransactionDetailViewModel transactionDetailViewModel;
    private List<Commentaire> commentaires;
    private CommentaireAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentaires = new ArrayList<>();
        setContentView(R.layout.activity_transaction_detail);
        setFooterBar();
        userNameDonneur = findViewById(R.id.donneur_pseudo);
        userNameReceveur = findViewById(R.id.receveur_pseudo);
        date = findViewById(R.id.infoDateCreationDetTransTextView);
        description = findViewById(R.id.infoDescDetTransTextView);
        validateBtn = findViewById(R.id.validateDetTransButton);
        commentView = findViewById(R.id.commDetTransListView);
        commentArea = findViewById(R.id.addCommDetTransEditText);
        sendPhotoBtn = findViewById(R.id.takePicCommDetTransButton);
        sendComBtn = findViewById(R.id.sendCommDetTransButton);
        sendPhotoBtn.setOnClickListener(view -> {
            dispatchTakePictureIntent();
        });
        sendComBtn.setOnClickListener(view -> {
            View viewFocus = this.getCurrentFocus();
            if (viewFocus != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewFocus.getWindowToken(), 0);
            }
            Commentaire com = new Commentaire();
            com.setCommentaire(commentArea.getText().toString());
            com.setIdMembre(5);
            transactionDetailViewModel.addCommentaire(com);
            commentArea.setText("");
        });
        Intent intent = getIntent();
        int proposition_id = Integer.parseInt(intent.getStringExtra("com.example.sel.TRANSACTION_ID"));
        transactionDetailViewModel = new ViewModelProvider(this, new TransactionDetailViewModelFactory(proposition_id)).get(TransactionDetailViewModel.class);
        transactionDetailViewModel.getProposition().observe(this, this::setTransactionInformation);
        transactionDetailViewModel.loadTransaction();

        adapter = new CommentaireAdapter(commentaires);
        commentView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        commentView.setLayoutManager(layout);
        transactionDetailViewModel.getAllCommentaire().observe(this, this::updateCommentaireRV);
        transactionDetailViewModel.loadAllCommentaire();


    }


    @Override
    protected void onStart() {
        super.onStart();
        transactionDetailViewModel.loadAllCommentaire();
    }

    @Override
    protected void onResume() {
        super.onResume();
        transactionDetailViewModel.loadAllCommentaire();
    }

    private void setTransactionInformation(Transaction transaction) {
        if (transaction.getBeneficiare() != null) {
            userNameDonneur.setText(transaction.getBeneficiare().getPseudo());
        }
        if (transaction.getDonneur() != null) {
            userNameReceveur.setText(transaction.getDonneur().getPseudo());
        }
        if (transaction.getEtat().equals("C")) {
            if (transaction.getIdBeneficaire() == 5) {
                validateBtn.setVisibility(View.VISIBLE);
            } else {
                validateBtn.setVisibility(View.INVISIBLE);
            }
        } else {
            validateBtn.setText("Validée");
            validateBtn.setEnabled(false);
        }

        date.setText(new SimpleDateFormat("yyyy-mm-dd").format(transaction.getCreationDate()));
        description.setText(transaction.getDescription());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File imgFile = new File(currentPhotoPath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            if (myBitmap != null) {
                byte[] fileContent = new byte[0];
                try {
                    byte[] byteArray = Files.readAllBytes(imgFile.toPath());
                    String a = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    Commentaire com = new Commentaire();
                    com.setPhoto(a);
                    com.setIdMembre(5);
                    transactionDetailViewModel.addCommentaire(com);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void updateCommentaireRV(List<Commentaire> commentaires) {


        this.commentaires.clear();
        this.commentaires.addAll(commentaires);
        adapter.notifyDataSetChanged();
    }
}