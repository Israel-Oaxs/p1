package oaxs.ico.com.p1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import ipcalculator.IpCalculator;

public class Actividad2 extends AppCompatActivity {
    private Button b;
    private  TextView t;
    private EditText e;
    private ImageButton ib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        b=(Button)findViewById(R.id.button);
        t=(TextView)findViewById(R.id.textView2);
        e=(EditText)findViewById(R.id.editText);
        ib=(ImageButton)findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               String c= e.getText().toString();
               IpCalculator ip=new IpCalculator(c);
                if(ip.ERROR==true){
                    Toast.makeText(getApplicationContext(), "Direccion no valida intente de nuevo", Toast.LENGTH_SHORT).show();
                }else{
                    t.setText(ip.toString());
                    e.setText("");
                    //e.setSelection(5);
                }
            }
        });
       ib.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),Help.class);
               startActivity(i);
           }
       });
    }
}
