package com.sft.annam.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sft.annam.Connection.HttpRequestHelperForLogin;
import com.sft.annam.Connection.HttpRequestHelperForOnloadSignup;
import com.sft.annam.Model.Login_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
public class MainPage_Login_Signup_Fragment  extends Fragment {
    LinearLayout loginpanel;
    int timer;
    protected int splashTime = 3000;

    EditText edtlogin_email_phone,edt_login_password;
    Button login_button,signup_button;
    TextView forgot_text;
    Login_Model login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.login_signup_fragment,container,false);
        loginpanel = (LinearLayout)rootView.findViewById(R.id.pnl_login_panel);
        final Thread th=new Thread(){

            @Override
            public void run(){
                try{
                    for (timer = -400; timer < 41; timer++) {
                        int waited = 0;
                        while(waited < splashTime) {
                            Thread.sleep(1);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)loginpanel.getLayoutParams();
                                        params.bottomMargin = timer;
                                        loginpanel.setLayoutParams(params);
                                    } catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            waited += 900;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                th.start();
            }
        }, 3000);


        initViews(rootView);
        initListeners();
        getActivity().setTitle("Login");
        return rootView;
    }

    private void initListeners() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateloginfielsEntered()){

                    if(Utilities.getInstance(getActivity()).isNetAvailable()) {

                        String login = edtlogin_email_phone.getText().toString();
                        String password = edt_login_password.getText().toString();
                        String deviceid=Settings.Secure.getString(getActivity().getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        Log.e( "Device iD: ","id"+deviceid );
                        loginuser(login, password,deviceid);
                    }

                    else {
                        Toast.makeText(getActivity(), "" + getResources().getString(R.string.internet),
                                Toast.LENGTH_SHORT).show();
                    }

                }
                else{

                }

            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utilities.getInstance(getActivity()).isNetAvailable()){

                     String device_id = Settings.Secure.getString(getContext().getContentResolver(),
                            Settings.Secure.ANDROID_ID);
if(!device_id.isEmpty()) {


    HttpRequestHelperForOnloadSignup signup_upload = new HttpRequestHelperForOnloadSignup();
    signup_upload.uploadsignup(getActivity(), device_id);
}
                }
                else{
                    Toast.makeText(getActivity(),"network is not available",Toast.LENGTH_SHORT).show();
                }

           /* Fragment  fragment = new Signup_Fragment();
                Utilities.getInstance(getActivity()).changeChildFragment(
                        fragment, "Signup_Fragment", getActivity());*/

            }
        });

        forgot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment  fragment = new ForgotPassword();
                Utilities.getInstance(getActivity()).changeChildFragment(
                        fragment, "ForgotPassword_Fragment", getActivity());
            }
        });

    }

    private void loginuser(String username, String password,String device_id) {


        Assigndata();
        HttpRequestHelperForLogin loginn=new HttpRequestHelperForLogin();
        loginn.login(getActivity(),login,device_id);

    }

    public void Assigndata() {
        String username = edtlogin_email_phone.getText().toString().trim();
        String password = edt_login_password.getText().toString().trim();
      login = new Login_Model(username, password);
        /*login_model.setUsername(((EditText)findViewById(R.id.editTextUserName)).getText().toString().trim());
        login_model.setPassword(((EditText)findViewById(R.id.editTextPassword)).getText().toString().trim());*/
    }

    private void initViews(View rootView) {
        edtlogin_email_phone=(EditText)rootView.findViewById(R.id.edt_email_phone);
        edt_login_password=(EditText)rootView.findViewById(R.id.edt_passsword);
        forgot_text=(TextView)rootView.findViewById(R.id.forgot_txt_forgot);
        login_button=(Button) rootView.findViewById(R.id.btn_login);
        signup_button=(Button) rootView.findViewById(R.id.btn_signup);
    }

    private boolean validateloginfielsEntered() {

        String strEmailPhone = edtlogin_email_phone.getText().toString();
        String strTextPassword = edt_login_password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(strEmailPhone)) {
            edtlogin_email_phone.setError(getString(R.string.error_field_required));
            focusView = edtlogin_email_phone;
            cancel = true;
        }
        if (TextUtils.isEmpty(strTextPassword)) {
            edt_login_password.setError(getString(R.string.error_field_required));
            focusView = edt_login_password;
            cancel = true;
        }


        if (cancel) {

            focusView.requestFocus();
        } else {

            return cancel;
        }
        return cancel;
    }
}
