package br.com.calculo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView precoDaGasolinaT;
    private SeekBar precoDaGasolinaSeek;

    private TextView precoDoEtanolT;
    private SeekBar precoDoEtanolSeek;

    private TextView melhorOpcaoT;
    private ImageView melhorOpcaoImag;

    private double precoGaso ;
    private double precoEtan ;
    private double razo;

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        precoDaGasolinaT = (TextView) findViewById(R.id.gasosa);
        precoDaGasolinaSeek = (SeekBar) findViewById(R.id.Gasolina);
        precoDoEtanolT = (TextView) findViewById(R.id.etata);
        precoDoEtanolSeek = (SeekBar) findViewById(R.id.Etanol);        ;
        melhorOpcaoT = (TextView) findViewById(R.id.emelhor);
        melhorOpcaoImag =
                (ImageView) findViewById(R.id.imaag);

        precoGaso = precoEtan = 1;
        calcular();

        precoDaGasolinaSeek.setOnSeekBarChangeListener(observer);
        precoDoEtanolSeek.setOnSeekBarChangeListener(observer);

    }

    private void calcular (){
        razo = precoEtan / precoGaso;
        precoDaGasolinaT.setText(currencyFormat.format(precoGaso));
        precoDoEtanolT.setText(currencyFormat.format(precoEtan));

        if (razo >= 0.7){
            melhorOpcaoImag.setImageResource(R.drawable.gasolina);
            melhorOpcaoT.setText((getString(R.string.txt_gaso)));
        }
        else{
            melhorOpcaoImag.setImageResource(R.drawable.etanol);
            melhorOpcaoT.setText((getString(R.string.txt_etano)));
        }
    }

    private SeekBar.OnSeekBarChangeListener observer =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (seekBar.getId() == R.id.Gasolina){
                        precoGaso = progress / 100.;
                    }
                    else{
                        precoEtan = progress / 100.;
                    }
                    calcular();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
