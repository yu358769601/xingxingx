package credit.sdk.spdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.moblie.zmxy.antgroup.creditsdk.app.CreditApp;


public class DemoActivity extends Activity implements DemoView {

    private final static String TAG = "ZHIMA_DemoActivity";
    private Button authButton;
    private DemoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);
        //init presenter
        presenter = new DemoPresenterImpl(this, this);
        setupViews();
        setupLinks();
    }

    //init views
    void setupViews(){
        authButton = (Button) findViewById(R.id.auth_test);

    }

    //set click listener
    void setupLinks(){
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "DemoActivity.setupLinks.authButton.setOnClickListener");
                presenter.doCreditRequest();
            }
        });
    }

    @Override
    public void toastMessage(final String msg) {
        //check context
        if(isFinishing() || TextUtils.isEmpty(msg)){
            return;
        }
        //toast message
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DemoActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "DemoActivity.onActivityResult");
        //onActivityResult callback
        CreditApp.onActivityResult(requestCode, resultCode, data);
    }

}
