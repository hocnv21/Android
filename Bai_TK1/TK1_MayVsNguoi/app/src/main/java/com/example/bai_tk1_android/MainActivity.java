package com.example.bai_tk1_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int manghinhbai[] = {
            R.drawable.c10, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Kết Quả");

        myDialog.setPositiveButton("Chơi tiếp", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Lượt chơi mới", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        myDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoát chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        //Máy
        final ImageView imgCPU1 = findViewById(R.id.imageViewCpu1);
        final ImageView imgCPU2 = findViewById(R.id.imageViewCpu2);
        final ImageView imgCPU3 = findViewById(R.id.imageViewCpu3);
        //Người
        final ImageView imgYou1 = findViewById(R.id.imageViewPlayer1);
        final ImageView imgYou2 = findViewById(R.id.imageViewPlayer2);
        final ImageView imgYou3 = findViewById(R.id.imageViewPlayer3);
        //Kết quả
        final TextView kq_CPU = findViewById(R.id.tv_kqCpu);
        final TextView kq_You = findViewById(R.id.tv_kqPlayer);

        final TextView resulOneRCpu = findViewById(R.id.tv_ResulCpu);
        final TextView resulOneRPlayer = findViewById(R.id.tv_ResulPlayer);


        //Điểm số
         final TextView CPU = findViewById(R.id.Cpu);
        final TextView Player = findViewById(R.id.Player);

        Button play = findViewById(R.id.btn_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  điểm thắng cộng dồn
                int CPU_Win = Integer.parseInt(CPU.getText().toString().trim());
                int Player_Win = Integer.parseInt(Player.getText().toString().trim());

                if ((CPU_Win + Player_Win) != 10) {

                    int[] valueCPU = lay3songaunhien(0, 51);
                    imgCPU1.setImageResource(manghinhbai[valueCPU[0]]);
                    imgCPU2.setImageResource(manghinhbai[valueCPU[1]]);
                    imgCPU3.setImageResource(manghinhbai[valueCPU[2]]);
                    kq_CPU.setText("Máy:"+tinhketqua(valueCPU));

                    int[] valuePlayer = lay3songaunhien(0, 51);
                    imgYou1.setImageResource(manghinhbai[valuePlayer[0]]);
                    imgYou2.setImageResource(manghinhbai[valuePlayer[1]]);
                    imgYou3.setImageResource(manghinhbai[valuePlayer[2]]);
                    kq_You.setText("Bạn:"+tinhketqua(valuePlayer));



                    int soNutCPU = tinhSoNut(valueCPU);
                    int soNutPlayer = tinhSoNut(valuePlayer);

                    if (soNutCPU > soNutPlayer) {
                        CPU_Win += 1;
                        CPU.setText(String.valueOf(CPU_Win));
                        resulOneRCpu.setText("Máy: Thắng");
                        resulOneRPlayer.setText("Bạn: Thua");


                    } else if (soNutCPU < soNutPlayer) {
                        Player_Win += 1;
                        Player.setText(String.valueOf(Player_Win));
                        resulOneRCpu.setText("Máy: Thua");
                        resulOneRPlayer.setText("Bạn: Thắng");

                    }
                } else {
                    if (CPU_Win > Player_Win){
                        String mes= "*** Máy Thắng ***\n"+"Máy: "+CPU_Win +" điểm"+"\n"+"Bạn: "+Player_Win +" điểm";
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                    else  if (Player_Win > CPU_Win){
                        String mes= "*** Bạn Thắng ***\n"+"Bạn: "+Player_Win +" điểm"+"\n"+"Máy: "+CPU_Win +" điểm";
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                    else{
                        String mes= "*** Hòa ***\n"+"Bạn: "+Player_Win+" điểm"+"\n"+"Máy: "+CPU_Win+" điểm";
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                }
            }
        });
    }


        // Lấy 3 lá ngẫu nhiên ko trùng
        private int[] lay3songaunhien(int min, int max) {
            int[] baso = new int[3];
            int i = 0;
            baso[i++] = random(min, max);
            do {
                int k = random(min, max);
                if (!ktratrung(k, baso))
                    baso[i++] = k;
            } while (i < 3);
            return baso;
        }
        // Lấy 6 lá ngẫu nhiên ko trùng
        private int[] lay6songaunhien(int min, int max) {
            int[] baso = new int[6];
            int i = 0;
            baso[i++] = random(min, max);
            do {
                int k = random(min, max);
                if (!ktratrung(k, baso))
                    baso[i++] = k;
            } while (i < 6);
            return baso;
        }

        //chọn ngẫu nhiên
        private int random(int min, int max) {
            return min + (int) (Math.random() * (max - min + 1));
        }

        //Kiểm tra giá trị trùng
        private boolean ktratrung(int k, int[] a) {
            for (int i = 0; i < a.length; i++)
                if (a[i] == k)
                    return true;
            return false;
        }

        //Tính số  Tây
        private int tinhsoTay(int[] a) {
            int k = 0;
            for (int i = 0; i < a.length; i++)
                if (a[i] % 13 >= 10 && a[i] % 13 < 13)
                    k++;
            return k;
        }

        //Tính kết quả của 3 lá bài
        private String tinhketqua(int[] baso) {
            String KQ = "";
            if (tinhsoTay(baso) == 3)
                KQ = " 3 tây";
            else {
                int tong = 0;
                for (int i = 0; i < baso.length; i++)
                    if (baso[i] % 13 < 10)
                        tong += baso[i] % 13 + 1;
                if (tong % 10 == 0)
                    KQ = "Bù, trong đó có " + tinhsoTay(baso) + " tây";
                else
                    KQ =  + tong % 10 + " nút, trong đó có " + tinhsoTay(baso) + " tây";
            }
            return KQ;
        }

        private int tinhSoNut(int[] baso) {
            if (tinhsoTay(baso) == 3) {
                return 10;
            } else {
                int tong = 0;
                for (int i = 0; i < baso.length; i++)
                    if (baso[i] % 13 < 10)
                        tong += baso[i] % 13 + 1;
                if (tong % 10 == 0)
                    return 0;
                else
                    return tong % 10;
            }
        }
}
