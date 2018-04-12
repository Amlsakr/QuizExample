package com.example.aml.quizexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button_next ;
    private  int mCurrentState ;
    private final int STATE_HIDDEN = 0 ;
    private final int STATE_SHOWN =1 ;
    private Cursor mData ;
    private TextView  text_view_word , text_view_definition ;
private int mDefCol , mWordCol ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_next = (Button) findViewById(R.id.button_next);
        text_view_word = (TextView) findViewById(R.id.text_view_word);
        text_view_definition = (TextView) findViewById(R.id.text_view_definition);

        new wordFetchTask().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mData.close();
    }

    public void onButtonClick(View view) {

        switch (mCurrentState)  {
            case STATE_SHOWN :
                nextWord();
                break;
            case STATE_HIDDEN :
                showDefinition();
                break;

        }

    }

    public void nextWord () {

        if(mData != null){
            if (!mData.moveToNext()){
                mData.moveToFirst();
            }
            text_view_definition.setVisibility(View.INVISIBLE);
            button_next.setText(getString(R.string.show_definition));
            text_view_word.setText(mData.getString(mWordCol));
            text_view_definition.setText(mData.getString(mDefCol));
            mCurrentState = STATE_HIDDEN ;
        }



    }

    public  void  showDefinition () {

        if (mData != null) {
            text_view_definition.setVisibility(View.VISIBLE);
            button_next.setText(getString(R.string.next_word));
            mCurrentState = STATE_SHOWN;

        }
    }
    public class wordFetchTask extends AsyncTask < Void , Void , Cursor > {

        @Override
        protected Cursor doInBackground(Void... params) {

            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(DroidTermsExampleContract.CONTENT_URI , null, null , null , null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
               mData = cursor;
//            int wordColumn = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);
//            int defCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
////            while (mData.moveToNext()){
//                String word = mData.getString(wordColumn);
//                String mDefCol = mData.getString(defCol);
//                Log.i(MainActivity.class.getSimpleName() , word + "__"+def);
//            }

            // Get the column index, in the Cursor, of each piece of data
            mDefCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
            mWordCol = mData.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);

            // Set the initial state
            nextWord();
        }
    }
}
